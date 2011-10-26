package br.com.scode.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.scode.PedidoDeEnvio;
import br.com.scode.PedidoDeEnvio.ItemPedido;

public class Importador {
	
	DatabaseUtil db = DatabaseUtil.getDb();
	
	private static Log log = LogFactory.getLog(Importador.class);

	private static final String ARQUIVO_IMPORTACAO = "requisicao/importacao_requisicoes.sql";
	private static final String ARQUIVO_IMPORTACAO_ITEM = "requisicao/importacao_itens_requisicao.sql";
	
	private static final String ARQUIVO_PEDIDO_DE_ENVIO_ATUALIZA_ULT_ID_MOVIMENTO = "requisicao/pedidoDeEnvio_atualiza_idAutoIncremento_tab_tbCdIntegracaoScodeTotvs.sql";

	public String lerArquivoComSQL(String arquivo) throws Throwable {
		File file = new File(arquivo);
		if (!file.exists()) {
			throw new FileNotFoundException("Não foi encontrado o arquivo " + arquivo);
		}
		String sql = FileUtils.readFileToString(file);
		if (sql == null || sql.trim().equals("")) {
			throw new Throwable("Arquivo " + arquivo + " esta sem conteúdo");
		}

		log.trace("Conteúdo encontrado em " + arquivo + " [" + sql.trim() + "]");

		return sql.trim();
	}

	public void importarRequisicoesDoRm() throws Throwable {
		
		try {

			PedidoDeEnvio pedido = new PedidoDeEnvio();

			// obtem o SQL que busca do banco do RM as requisições aprovadas
			// prontas para o envio para o scode.
			// Raliza a consulta no banco RM, se existir requisições
			// estão dentro de "requisicoes"
			List<Map<String, Serializable>> requisicoes = db.lista(lerArquivoComSQL(ARQUIVO_IMPORTACAO), false);

			log.trace("Foram encontrados " + requisicoes.size() + " requisições que serão enviadas para o SCODE");

			// objeto para organizar os parametros da requisição
			Requisicao req = new Requisicao();

			// obtem o SQL que busca no banco do RM os itens de uma requisição
			String sqlItem = lerArquivoComSQL(ARQUIVO_IMPORTACAO_ITEM);

			// percorre todas as requisicoes encontradas para cada requisicao
			// encontra seus itens
			for (Map<String, Serializable> requisicao : requisicoes) {

				// objeto nos moldes da requisição
				pedido = new PedidoDeEnvio();
				pedido.setPedidoRmId(requisicao.get("pedido_rm_id").toString());
				pedido.setUsuarioId(ParametrosUtil.get(ParametrosUtil.SCODE_USUARIO_ID));
				pedido.setComentario(requisicao.get("comentario") == null ? "" : requisicao.get("comentario").toString());
				req.addPedidoDeEnvio(pedido);
				
				Integer idmov = (Integer) requisicao.get("pedido_rm_id");
				log.trace("Lendo o movimento número " + idmov);

				String sql = MessageFormat.format(sqlItem, idmov.toString());
				log.trace("SQL para retornar os itens do movimento " + idmov + ": " + sql);

				// buscando os itens da requisição da vez
				List<Map<String, Serializable>> itens = db.lista(sql, false);
				log.trace("Foram encontrados " + itens.size() + " itens do movimento " + idmov);

				if (itens.size() > 0) {

					// percorre todos os itens encontrados, monta o ItemPedido e 
					// adiciona-os dentro do pedido
					for (Map<String, Serializable> item : itens) {

						ItemPedido itemPedido = new ItemPedido();
						itemPedido.setItemRmId(item.get("insumo_rm_id").toString());
						itemPedido.setDescricao(item.get("descricao").toString());
						itemPedido.setUnidade(item.get("unidade").toString());
						itemPedido.setQuantidade(item.get("quantidade").toString());
						itemPedido.setMarca(item.get("marca") == null ? "" : item.get("marca").toString());
						itemPedido.setComentario(item.get("comentario") == null ? "" : item.get("comentario").toString());
						pedido.addItemAoPedido(itemPedido);

						log.trace("Pedido: " + requisicao.get("pedido_rm_id") + "-" + requisicao.get("comentario"));
						log.trace("Insumo: " + item.get("insumo_rm_id") + "-" + item.get("descricao") + "-" + item.get("unidade") + "-" + item.get("quantidade") + "-" + item.get("marca") + "-" + item.get("comentario"));

					}
					new ComunicadorComScode().enviarConteudo(req.getParametros());
				}
			}
			// Verifica se existe itens de movimento.
			// Caso sim, armazena o último IDMOV na tab. GTI..tbCdIntegracaoScodeTotvs
			if (requisicoes.size() > 0) {
				atualizaIdAutoIncrementoTabTbCdIntegracaoScodeTotvs(pedido);
			}
			
		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: importarRequisicoesDoRm().\n" + e);
		}
	}
	
	/**
	 * Atualiza o campo "intIdAutoIncremento" da tabela "GTI..tbCdIntegracaoScodeTotvs, para o último Id do Movimento enviado para o SCODE.
	 *
	 * @param pedidoDeEnvio
	 * @throws Throwable - 
	 * @return void
	 */
	private void atualizaIdAutoIncrementoTabTbCdIntegracaoScodeTotvs(PedidoDeEnvio pedidoDeEnvio) throws Throwable {
		
		String sql = lerArquivoComSQL(ARQUIVO_PEDIDO_DE_ENVIO_ATUALIZA_ULT_ID_MOVIMENTO);
		
		PreparedStatement stmt = db.criarComando(sql);
		
		try {
			stmt.setInt(1, Integer.parseInt(pedidoDeEnvio.getPedidoRmId()));
			
			stmt.execute();
			stmt.close();
			
			db.commit();
			
		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: atualizaIdAutoIncrementoTabTbCdIntegracaoScodeTotvs().\n" + e);
		} 
	}
}