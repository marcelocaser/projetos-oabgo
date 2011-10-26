package br.com.scode.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.scode.ClienteFornecedor;
import br.com.scode.Cotacao;
import br.com.scode.PedidoDeRetorno;
import br.com.scode.PedidoDeRetorno.Fornecedor;
import br.com.scode.PedidoDeRetorno.Fornecedor.ItemPedidoRetorno;

/**
 * <b>Classe:</b> Integracao.java <br>
 * <b>Descrição:</b> <br>
 * 
 * <b>Projeto:</b> scode-rm-oab <br>
 * <b>Pacote:</b> br.com.scode.util <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 * Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author marcelo
 * @version Revision: $ Date: $
 */
public class Integracao {

	ClienteFornecedor clienteFornecedor = new ClienteFornecedor();
	
	Cotacao cotacao = new Cotacao();

	DatabaseUtil db = DatabaseUtil.getDb();

	private static Log log = LogFactory.getLog(Integracao.class);

	private static final String ARQUIVO_CLI_FORN_BUSCA_CNPJ_CPF_TAB_FCFO = "clienteFornecedor/clienteFornecedor_busca_cnpj_cpf_tab_fcfo.sql";
	private static final String ARQUIVO_CLI_FORN_BUSCA_ID_TAB_GAUTOINC = "clienteFornecedor/clienteFornecedor_busca_id_tab_gautoinc.sql";
	private static final String ARQUIVO_CLI_FORN_BUSCA_ID_TAB_FPARAM = "clienteFornecedor/clienteFornecedor_busca_id_tab_fparam.sql";
	private static final String ARQUIVO_CLI_FORN_BUSCA_MUNICIPIO_TAB_GMUNICIPIO = "clienteFornecedor/clienteFornecedor_busca_municipio_tab_gmunicipio.sql";
	// private static final String ARQUIVO_CLI_FORN_BUSCA_ESTADO_TAB_GETD = "clienteFornecedor/clienteFornecedor_busca_estado_tab_getd.sql";
	private static final String ARQUIVO_COTACAO_BUSCA_PRODUTO_TAB_TPRD = "cotacao/cotacao_busca_produto_tab_tprd.sql";
	private static final String ARQUIVO_COTACAO_BUSCA_NUM_SEQUENCIAL_TAB_TITMMOV = "cotacao/cotacao_busca_nseqitmmov_tab_titmmov.sql";
	private static final String ARQUIVO_COTACAO_BUSCA_COD_COTACAO_TAB_TCCOTACAOITMMOV = "cotacao/cotacao_busca_cod_cotacao_tab_tccotacaoitmov.sql";
	private static final String ARQUIVO_COTACAO_BUSCA_ULT_PEDIDO_TAB_TBCDINTEGRACAOSCODETOTVS = "cotacao/cotacao_busca_ult_pedido_tab_tbCdHistoricoEnvioDePedidoScodeTotvs.sql";

	private static final String ARQUIVO_CLI_FORN_ATUALIZA_IDCFO_TAB_GAUTOINC = "clienteFornecedor/clienteFornecedor_atualiza_idcfo_tab_gautoinc.sql";
	private static final String ARQUIVO_CLI_FORN_ATUALIZA_IDHISTORICO_TAB_GAUTOINC = "clienteFornecedor/clienteFornecedor_atualiza_idhistorico_tab_gautoinc.sql";
	private static final String ARQUIVO_CLI_FORN_ATUALIZA_CODCFO_TAB_FPARAM = "clienteFornecedor/clienteFornecedor_atualiza_codcfo_tab_fparam.sql";
	private static final String ARQUIVO_CLI_FORN_ATUALIZA_DADOS_TAB_FCFO = "clienteFornecedor/clienteFornecedor_atualiza_dados_tab_fcfo.sql";
	private static final String ARQUIVO_COTACAO_ATUALIZA_TAB_GAUTOINC = "cotacao/cotacao_atualiza_tab_gautoinc.sql";
	private static final String ARQUIVO_COTACAO_ATUALIZA_STATUS_STSCOMPRAS_TAB_TMOV = "cotacao/cotacao_atualiza_status_stscompras_tab_tmov.sql";

	private static final String ARQUIVO_CLI_FORN_GRAVA_DADOS_TAB_FCFO = "clienteFornecedor/clienteFornecedor_grava_dados_tab_fcfo.sql";
	private static final String ARQUIVO_CLI_FORN_GRAVA_DADOS_TAB_FCFOCOMPL = "clienteFornecedor/clienteFornecedor_grava_dados_tab_fcfocompl.sql";
	private static final String ARQUIVO_CLI_FORN_GRAVA_DADOS_TAB_FCFOHISTORICO = "clienteFornecedor/clienteFornecedor_grava_dados_tab_fcfohistorico.sql";
	private static final String ARQUIVO_COTACAO_GRAVA_DADOS_TAB_TCCOTACAO = "cotacao/cotacao_grava_dados_tab_tccotacao.sql";
	private static final String ARQUIVO_COTACAO_GRAVA_DADOS_TAB_TCCOTACAOITMMOV = "cotacao/cotacao_grava_dados_tab_tccotacaoitmov.sql";
	private static final String ARQUIVO_COTACAO_GRAVA_DADOS_TAB_TCORCAMENTO = "cotacao/cotacao_grava_dados_tab_tcorcamento.sql";
	private static final String ARQUIVO_COTACAO_GRAVA_DADOS_TAB_TCITMORCAMENTO = "cotacao/cotacao_grava_dados_tab_tcitorcamento.sql";
	private static final String ARQUIVO_COTACAO_GRAVA_HISTORICO_TAB_TBCDHISTORICOENVIODEPEDIDOSCODETOTVS = "cotacao/cotacao_grava_historico_tab_tbCdHistoricoEnvioDePedidoScodeTotvs.sql";

	/**
	 * Exporta o "PedidoDeRetorno" do SCODE para ser gerado o "MAPA COMPARATIVO DE PREÇOS" no RM.
	 * 
	 * @param pedidoRetornoList
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	public void gravaDadosDaCotacao(List<PedidoDeRetorno> pedidoRetornoList) throws Throwable {

		// Verifica se o "Pedido de Retorno" já não foi enviado ao RM.
		// Caso não, envia somente os pedidos faltantes.
		pedidoRetornoList = buscaPedidoJaEnviado(pedidoRetornoList);

		if (pedidoRetornoList.size() > 0) {

			try {

				for (int i = 0; i < pedidoRetornoList.size(); i++) {

					buscaCODCOTACAO();
					gravaDadosTabTCCOTACAO(pedidoRetornoList.get(i));
					atualizaIDTabGAUTOINC("CODCOTACAO2011");
					
					for (int z = 0; z < pedidoRetornoList.get(i).getFornecedor().size(); z++) {
										
						// Verifica se CNPJ informado é valido.
						if (Util.CNPJ(pedidoRetornoList.get(i).getFornecedor().get(z).getCnpj())) {
							buscaClienteFornecedorPeloCNPJ(pedidoRetornoList.get(i).getFornecedor().get(z).getCnpj());
						} else {
							throw new Exception("CNPJ informado não é valido!"
									+ " \nCNPJ: " + pedidoRetornoList.get(i).getFornecedor().get(z).getCnpj()
									+ " \nNome Empresa: " + pedidoRetornoList.get(i).getFornecedor().get(z).getRazaoSocial() 
									+ " \nCotação nº: " + pedidoRetornoList.get(i).getOrdemServicoId()
									+ " \nIdentificador Cotação: " + pedidoRetornoList.get(i).getOrdemCompraId());
						}

						// Verifica se existe Fornecedor cadastrado.
						// Caso não, inseri o cadastro no RM.
						if (clienteFornecedor.getCodcfo() == null) {
								geraCODCFO();
								buscaCodMunicipo(pedidoRetornoList.get(i).getFornecedor().get(z).getCidade());
								atualizaIDTabGAUTOINC("IDCFO");
								gravaDadosTabFCFO(pedidoRetornoList.get(i).getFornecedor().get(z));
								atualizaIDTabGAUTOINC("IDHISTORICO");
								atualizaCODCFOTabFPARAM();
								gravaDadosTabFCFOCOMPL();
						} else {
							buscaCodMunicipo(pedidoRetornoList.get(i).getFornecedor().get(z).getCidade());
							atualizaIDTabGAUTOINC("IDHISTORICO");
							gravaDadosTabFCFOHISTORICO(pedidoRetornoList.get(i).getFornecedor().get(z));
							atualizaFornecedor(pedidoRetornoList.get(i).getFornecedor().get(z));
						}

						// Grava os dados do Orçamento, e dos Produtos para cada Fornecedor.
						gravadaDadosTabTCORCAMENTO(pedidoRetornoList.get(i), pedidoRetornoList.get(i).getFornecedor().get(z));

						for (int w = 0; w < pedidoRetornoList.get(i).getFornecedor().get(z).getItensDoPedido().size(); w++) {

							buscaNumeroSequencialTabTCCOTACAOITMMOV(pedidoRetornoList.get(i), pedidoRetornoList.get(i).getFornecedor().get(z).getItensDoPedido().get(w));

							if (!buscaDadosTCCOTACAOITMMOV(pedidoRetornoList.get(i), pedidoRetornoList.get(i).getFornecedor().get(z).getItensDoPedido().get(w))) {
								gravaDadosTabTCCOTACAOITMMOV(pedidoRetornoList.get(i));
							}

							gravaDadosTabTCITMORCAMENTO(pedidoRetornoList.get(i), pedidoRetornoList.get(i).getFornecedor().get(z).getItensDoPedido().get(w), pedidoRetornoList.get(i).getFornecedor().get(z));
						}
						
					}

					atualizaStatusStsComprasTabTMOV(pedidoRetornoList.get(i));
					gravaHistoricoDeEnvioDosPedidos(pedidoRetornoList.get(i));
					db.commit();
					cotacao = new Cotacao();
					clienteFornecedor = new ClienteFornecedor();
				}
			} catch (SQLException e) {
				db.rollback();
				log.trace("Não foi possível concluir a operação! Fornecedor/Cotação/Orçamento não cadastrado!");
				throw new SQLException(e);
			} catch (Exception e) {
				db.rollback();
				throw new Exception(e);
			}
		}
	}

	/**
	 * Faz a leitura os arquivos contendo as "query" de Insersão/Update/Busca.
	 * 
	 * @param arquivo
	 * @return
	 * @throws Throwable
	 *             -
	 * @return String
	 */
	private String lerArquivoComSQL(String arquivo) throws Throwable {
		File file = new File(arquivo);
		if (!file.exists()) {
			throw new FileNotFoundException("Não foi encontrado o arquivo " + arquivo);
		}
		String sql = FileUtils.readFileToString(file);
		if (sql == null || sql.trim().equals("")) {
			throw new Throwable("Arquivo " + arquivo + " esta sem conteúdo");
		}

		log.trace("Lendo conteúdo: " + arquivo + " [" + sql.trim() + "]");

		return sql.trim();
	}

	/**
	 * Traz o último registro "VALAUTOINC" da tabela "GAUTOINC" de acordo com o campo "CODAUTOINC".
	 * 
	 * @param campo
	 * @return
	 * @throws Throwable
	 *             -
	 * @return int
	 */
	private int buscaUltimoIDTabGAUTOINC(String campo) throws Throwable {

		int valautoinc = 0;

		String sql = lerArquivoComSQL(ARQUIVO_CLI_FORN_BUSCA_ID_TAB_GAUTOINC);

		try {
			
			PreparedStatement stmt = db.criarComando(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				if (rs.getString("CODAUTOINC").equals(campo)) {
					valautoinc = rs.getInt("VALAUTOINC");
				}
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: buscaUltimoIDTabGAUTOINC().\n" + e);
		}

		return valautoinc;
	}

	/**
	 * Busca último registro "TEXTO" da tabela "FPARAM".
	 * 
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private void buscaUltimoCODCFOTabFPARAM() throws Throwable {

		String sql = lerArquivoComSQL(ARQUIVO_CLI_FORN_BUSCA_ID_TAB_FPARAM);

		try {
			
			PreparedStatement stmt = db.criarComando(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				clienteFornecedor.setCodcfo(rs.getString("TEXTO").trim());
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: buscaUltimoCODCFOTabFPARAM().\n" + e);
		}
	}

	/**
	 * Busca o "ID" do Município da tabela "GMUNICIPIO".
	 * 
	 * @param municipio
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private void buscaCodMunicipo(String municipio) throws Throwable {

		String sql = lerArquivoComSQL(ARQUIVO_CLI_FORN_BUSCA_MUNICIPIO_TAB_GMUNICIPIO);

		try {
			
			PreparedStatement stmt = db.criarComando(sql);
			
			stmt.setString(1, municipio);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				clienteFornecedor.setCodmunicipio(rs.getString("CODMUNICIPIO"));
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: buscaUltimoCODCFOTabFPARAM().\n" + e);
		}
	}

	/**
	 * Atualiza o campo "VALAUTOINC" da tabela "GAUTOINC".
	 * 
	 * @param campo
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private void atualizaIDTabGAUTOINC(String campo) throws Throwable {

		String sql = null;

		int valautoinc = buscaUltimoIDTabGAUTOINC(campo);

		if (campo.equals("IDCFO")) {
			sql = lerArquivoComSQL(ARQUIVO_CLI_FORN_ATUALIZA_IDCFO_TAB_GAUTOINC);
		}

		if (campo.equals("IDHISTORICO")) {
			sql = lerArquivoComSQL(ARQUIVO_CLI_FORN_ATUALIZA_IDHISTORICO_TAB_GAUTOINC);
		}

		if (campo.equals("CODCOTACAO2011")) {
			sql = lerArquivoComSQL(ARQUIVO_COTACAO_ATUALIZA_TAB_GAUTOINC);
		}

		try {
			
			PreparedStatement stmt = db.criarComando(sql);
			
			stmt.setInt(1, valautoinc + 1);
			stmt.setInt(2, valautoinc);

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: atualizaIDTabGAUTOINC().\n" + e);
		}

	}

	/**
	 * Atualiza o campo "TEXTO" da tabela "FPARAM".
	 * 
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private void atualizaCODCFOTabFPARAM() throws Throwable {

		String sql = lerArquivoComSQL(ARQUIVO_CLI_FORN_ATUALIZA_CODCFO_TAB_FPARAM);

		try {
			
			PreparedStatement stmt = db.criarComando(sql);
			
			stmt.setString(1, clienteFornecedor.getCodcfo());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: atualizaCODCFOTabFPARAM().\n" + e);
		}
	}
	
	private void atualizaFornecedor(Fornecedor forn) throws Throwable {
		
		String sql = lerArquivoComSQL(ARQUIVO_CLI_FORN_ATUALIZA_DADOS_TAB_FCFO);
		
		try {
			
			PreparedStatement stmt = db.criarComando(sql);
			
			stmt.setString(1, Util.stringVazia(forn.getRazaoSocial()));
			stmt.setString(2, Util.stringVazia(forn.getNomeFantasia()));
			stmt.setString(3, Util.formataStringMascara("##.###.###/####-##", forn.getCnpj()));
			stmt.setString(4, Util.stringVazia(forn.getInscricaoEstadual()));
			stmt.setString(5, Util.stringVazia(forn.getEndereco()));
			stmt.setString(6, Util.stringVazia(forn.getBairro()));
			stmt.setString(7, Util.stringVazia(forn.getCidade()));
			stmt.setString(8, Util.stringVazia(forn.getEstado()));
			stmt.setString(9, Util.stringVazia(forn.getCep()));
			stmt.setString(10, Util.stringVazia(forn.getTelefone()));
			stmt.setString(11, Util.stringVazia(forn.getFax()));
			stmt.setString(12, Util.stringVazia(forn.getEmail()));
			stmt.setString(13, Util.stringVazia(forn.getContato()));
			stmt.setString(14, clienteFornecedor.getCodmunicipio());
			stmt.setString(15, Util.stringVazia(forn.getInscricaoMunicipal()));
			stmt.setString(16, clienteFornecedor.getCodcfo());
			stmt.setInt(17, clienteFornecedor.getIdcfo());
			
			stmt.execute();
			stmt.close();
			//db.commit();
			
		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: gravaDadosTabFCFO().\n" + e);
		}
		
	}

	/**
	 * Grava dados do Fornecedor na tabela "FCFO".
	 * 
	 * @param fornecedor
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private void gravaDadosTabFCFO(Fornecedor fornecedor) throws Throwable {

		int valautoinc = buscaUltimoIDTabGAUTOINC("IDCFO");

		String sql = lerArquivoComSQL(ARQUIVO_CLI_FORN_GRAVA_DADOS_TAB_FCFO);

		try {
			
			PreparedStatement stmt = db.criarComando(sql);
			
			stmt.setInt(1, clienteFornecedor.getCodcoligada());
			stmt.setString(2, clienteFornecedor.getCodcfo());
			stmt.setString(3, Util.stringVazia(fornecedor.getRazaoSocial()));
			if (Util.stringVazia(fornecedor.getNomeFantasia()) == null) {
				stmt.setString(4, Util.stringVazia(fornecedor.getRazaoSocial()));
			} else {
				stmt.setString(4, Util.stringVazia(fornecedor.getNomeFantasia()));				
			}
			stmt.setString(5, Util.formataStringMascara("##.###.###/####-##", fornecedor.getCnpj()));
			stmt.setString(6, Util.stringVazia(fornecedor.getInscricaoEstadual()));
			stmt.setInt(7, clienteFornecedor.getPagrec());
			stmt.setString(8, Util.stringVazia(fornecedor.getEndereco()));
			stmt.setString(9, Util.stringVazia(fornecedor.getBairro()));
			stmt.setString(10, Util.stringVazia(fornecedor.getCidade()));
			stmt.setString(11, Util.stringVazia(fornecedor.getEstado()));
			stmt.setString(12, Util.stringVazia(fornecedor.getCep()));
			stmt.setString(13, Util.stringVazia(fornecedor.getTelefone()));
			stmt.setString(14, Util.stringVazia(fornecedor.getFax()));
			stmt.setString(15, Util.stringVazia(fornecedor.getEmail()));
			stmt.setString(16, Util.stringVazia(fornecedor.getContato()));
			stmt.setInt(17, clienteFornecedor.getAtivo());
			stmt.setString(18, Util.stringVazia(fornecedor.getDescricaoProdutoServicoFornecido()));
			stmt.setString(19, clienteFornecedor.getCodmunicipio());
			stmt.setString(20, Util.stringVazia(fornecedor.getInscricaoMunicipal()));
			stmt.setString(21, clienteFornecedor.getPessoafisoujur());
			stmt.setString(22, clienteFornecedor.getPais());
			stmt.setInt(23, valautoinc);
			stmt.setString(24, clienteFornecedor.getUsuario());
			stmt.setInt(25, clienteFornecedor.getTipoopcombustivel());
			stmt.setInt(26, clienteFornecedor.getCodpais());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: gravaDadosTabFCFO().\n" + e);
		}
	}

	/**
	 * Grava dados do Cliente/Fornecedor na tabela "FCFOHISTORICO".
	 * 
	 * @param fornecedor
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private void gravaDadosTabFCFOHISTORICO(Fornecedor fornecedor) throws Throwable {

		int valautoinc = buscaUltimoIDTabGAUTOINC("IDHISTORICO");

		String sql = lerArquivoComSQL(ARQUIVO_CLI_FORN_GRAVA_DADOS_TAB_FCFOHISTORICO);

		PreparedStatement stmt = db.criarComando(sql);

		try {
			stmt.setInt(1, clienteFornecedor.getCodcoligada());
			stmt.setInt(2, valautoinc);
			stmt.setString(3, clienteFornecedor.getCodcfo());
			stmt.setString(4, Util.stringVazia(fornecedor.getRazaoSocial()));
			stmt.setString(5, Util.stringVazia(fornecedor.getNomeFantasia()));
			stmt.setString(6, Util.formataStringMascara("##.###.###/####-##", fornecedor.getCnpj()));
			stmt.setString(7, Util.stringVazia(fornecedor.getInscricaoMunicipal()));
			stmt.setString(8, Util.stringVazia(fornecedor.getInscricaoEstadual()));
			stmt.setString(9, Util.stringVazia(fornecedor.getEndereco()));
			stmt.setString(10, Util.stringVazia(fornecedor.getCep()));
			stmt.setString(11, Util.stringVazia(fornecedor.getBairro()));
			stmt.setString(12, Util.stringVazia(fornecedor.getCidade()));
			stmt.setString(13, Util.stringVazia(fornecedor.getEstado()));
			stmt.setString(14, Util.stringVazia(fornecedor.getTelefone()));
			stmt.setString(15, Util.stringVazia(fornecedor.getFax()));
			stmt.setString(16, Util.stringVazia(fornecedor.getEmail()));
			stmt.setString(17, Util.stringVazia(fornecedor.getContato()));
			stmt.setString(18, Util.stringVazia(clienteFornecedor.getCodmunicipio()));
			stmt.setString(19, Util.stringVazia(clienteFornecedor.getPessoafisoujur()));
			stmt.setString(20, Util.stringVazia(clienteFornecedor.getPais()));
			stmt.setString(21, clienteFornecedor.getUsuario());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: gravaDadosTabFCFOHISTORICO().\n" + e);
		}
	}

	/**
	 * Grava dados do Cliente/Fornecedor na tabela "FCFOCOMPL".
	 * 
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private void gravaDadosTabFCFOCOMPL() throws Throwable {

		String sql = lerArquivoComSQL(ARQUIVO_CLI_FORN_GRAVA_DADOS_TAB_FCFOCOMPL);

		PreparedStatement stmt = db.criarComando(sql);

		try {
			stmt.setInt(1, clienteFornecedor.getCodcoligada());
			stmt.setString(2, clienteFornecedor.getCodcfo());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: gravaDadosTabFCFOCOMPL().\n" + e);
		}
	}

	/**
	 * Gera "CODCFO" do Fornecedor.
	 * 
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private void geraCODCFO() throws Throwable {

		buscaUltimoCODCFOTabFPARAM();

		int codigo = Integer.parseInt(clienteFornecedor.getCodcfo().substring(1, 7)) + 1;

		clienteFornecedor.setCodcfo("F" + String.format("%06d", codigo));
	}

	/**
	 * Busca "CODCOTACAO" da Cotação.
	 * 
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private void buscaCODCOTACAO() throws Throwable {
		cotacao.setCodcotacao(cotacao.getAno() + "." + String.format("%06d", buscaUltimoIDTabGAUTOINC("CODCOTACAO2011") + 1));
	}

	/**
	 * Grava dados da Cotação na tabela "TCCOTACAO".
	 * 
	 * @param pedidoDeRetorno
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private void gravaDadosTabTCCOTACAO(PedidoDeRetorno pedidoDeRetorno) throws Throwable {

		String sql = lerArquivoComSQL(ARQUIVO_COTACAO_GRAVA_DADOS_TAB_TCCOTACAO);

		PreparedStatement stmt = db.criarComando(sql);

		try {
			stmt.setString(1, cotacao.getCodcotacao());
			stmt.setInt(2, cotacao.getCodcoligada());
			//stmt.setString(3, Util.stringVazia(pedidoDeRetorno.getDataOrdemCompra()));
			stmt.setString(3, Util.stringVazia(pedidoDeRetorno.getDataLimiteRespCotacao()));
			stmt.setString(4, cotacao.getCodcomprador());
			stmt.setString(5, cotacao.getStscotacao());
			stmt.setString(6, cotacao.getTxtobservacao());
			stmt.setString(7, Util.stringVazia(pedidoDeRetorno.getFormaPagamento()));
			stmt.setString(8, Util.stringVazia(pedidoDeRetorno.getDataLimiteEntegraMercadoria()));
			stmt.setInt(9, cotacao.getCodfilial());
			stmt.setString(10, cotacao.getCodmoeda());
			stmt.setString(11, cotacao.getTipojulgamento());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: gravaDadosTabTCCOTACAO().\n" + e);
		}
	}

	/**
	 * Grava dados da Cotação na tabela "TCCOTACAOITMMOV".
	 * 
	 * @param pedidoDeRetorno
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private void gravaDadosTabTCCOTACAOITMMOV(PedidoDeRetorno pedidoDeRetorno) throws Throwable {

		String sql = lerArquivoComSQL(ARQUIVO_COTACAO_GRAVA_DADOS_TAB_TCCOTACAOITMMOV);

		PreparedStatement stmt = db.criarComando(sql);

		try {
			stmt.setInt(1, cotacao.getCodcoligada());
			stmt.setString(2, cotacao.getCodcotacao());
			stmt.setInt(3, Integer.parseInt(Util.stringVazia(pedidoDeRetorno.getPedidoRmId())));
			stmt.setInt(4, cotacao.getNseqitmmov());
			stmt.setInt(5, cotacao.getTipomovcompras());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: gravaDadosTabTCCOTACAOITMMOV().\n" + e);
		}
	}

	/**
	 * Atualiza o campo "STATUS" E "STSCOMPRAS" da tabela "TMOV".
	 * 
	 * @param pedidoDeRetorno
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private void atualizaStatusStsComprasTabTMOV(PedidoDeRetorno pedidoDeRetorno) throws Throwable {

		String sql = lerArquivoComSQL(ARQUIVO_COTACAO_ATUALIZA_STATUS_STSCOMPRAS_TAB_TMOV);

		PreparedStatement stmt = db.criarComando(sql);

		try {
			stmt.setString(1, Util.stringVazia(pedidoDeRetorno.getPedidoRmId()));

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: gravaDadosTabTCCOTACAOITMMOV().\n" + e);
		}
	}

	/**
	 * Grava dados do Orçamento na tabela "TCORCAMENTO".
	 * 
	 * @param pedidoDeRetorno
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private void gravadaDadosTabTCORCAMENTO(PedidoDeRetorno pedidoDeRetorno, Fornecedor fornecedor) throws Throwable {

		String sql = lerArquivoComSQL(ARQUIVO_COTACAO_GRAVA_DADOS_TAB_TCORCAMENTO);

		PreparedStatement stmt = db.criarComando(sql);

		try {
			stmt.setInt(1, cotacao.getCodcoligada());
			stmt.setString(2, cotacao.getCodcotacao());
			stmt.setInt(3, cotacao.getCodcolcfo());
			stmt.setString(4, clienteFornecedor.getCodcfo());
			if (Util.stringVazia(fornecedor.getDiasParaEntrega()) != null) {
				stmt.setInt(5, Integer.parseInt(Util.stringVazia(fornecedor.getDiasParaEntrega())));
			} else {
				stmt.setInt(5, 0);
			}
			stmt.setString(6, Util.stringVazia(fornecedor.getDataPrevisaoEntrega()));
			if (Util.stringVazia(fornecedor.getDiasParaEntrega()) != null) {
				stmt.setInt(7, Integer.parseInt(Util.stringVazia(fornecedor.getDiasParaEntrega())));
			} else {
				stmt.setInt(7, 0);
			}
			stmt.setString(8, Util.stringVazia(fornecedor.getFormaPagamento()));
			stmt.setString(9, Util.stringVazia(fornecedor.getFormaPagamento()));
			stmt.setString(10, cotacao.getCodmoeda());
			if (Util.stringVazia(fornecedor.getFrete()) != null) {
				stmt.setFloat(11, Float.parseFloat(Util.stringVazia(fornecedor.getFrete())));
			} else {
				stmt.setFloat(11, 0);
			}

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: gravadaDadosTabTCORCAMENTO().\n" + e);
		}
	}

	/**
	 * Grava dados dos Itens do Orçamento na tabela "TCITMORCAMENTO".
	 * 
	 * @param pedidoDeRetorno
	 * @param itemPedidoRetorno
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private void gravaDadosTabTCITMORCAMENTO(PedidoDeRetorno pedidoDeRetorno, ItemPedidoRetorno itemPedidoRetorno, Fornecedor fornecedor) throws Throwable {

		buscaTipoUnidadeMedida(itemPedidoRetorno);

		String sql = lerArquivoComSQL(ARQUIVO_COTACAO_GRAVA_DADOS_TAB_TCITMORCAMENTO);

		PreparedStatement stmt = db.criarComando(sql);

		try {
			stmt.setInt(1, cotacao.getCodcoligada());
			stmt.setString(2, cotacao.getCodcotacao());
			stmt.setInt(3, cotacao.getCodcolcfo());
			stmt.setString(4, clienteFornecedor.getCodcfo());
			stmt.setInt(5, Integer.parseInt(Util.stringVazia(itemPedidoRetorno.getProdutoSolicitadoRmId())));
			stmt.setInt(6, Integer.parseInt(Util.stringVazia(pedidoDeRetorno.getPedidoRmId())));
			stmt.setInt(7, cotacao.getNseqitmmov());
			if (Util.stringVazia(itemPedidoRetorno.getPrecoUnitario()) != null) {
				stmt.setDouble(8, Double.parseDouble(Util.stringVazia(itemPedidoRetorno.getPrecoUnitario())));
			} else {
				stmt.setDouble(8, 0.0000);
				//Caso o fornecedor nao tenha informado o preco, 
				//passa a considerar como status "nao cotou".
				cotacao.setStsitem("2");
			}
			if (Util.stringVazia(itemPedidoRetorno.getPrecoUnitario()) != null) {
				stmt.setDouble(9, Double.parseDouble(Util.stringVazia(itemPedidoRetorno.getPrecoUnitario())));
			} else {
				stmt.setDouble(9, 0.0000);
			}
			stmt.setString(10, cotacao.getCodund());
			stmt.setString(11, cotacao.getStsitem());
			stmt.setInt(12, cotacao.getCfovencedor());
			stmt.setString(13, Util.stringVazia(fornecedor.getFormaPagamento()));
			stmt.setString(14, Util.stringVazia(fornecedor.getDataPrevisaoEntrega()));
			stmt.setString(15, Util.stringVazia(fornecedor.getFormaPagamento()));
			stmt.setString(16, cotacao.getCodmoeda());
			if (Util.stringVazia(fornecedor.getDiasParaEntrega()) != null) {
				stmt.setInt(17, Integer.parseInt(Util.stringVazia(fornecedor.getDiasParaEntrega())));
			} else {
				stmt.setInt(17, 0);				
			}
			
			cotacao.setStsitem("0");
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: gravaDadosTabTCITMORCAMENTO().\n" + e);
		}
	}

	/**
	 * Busca Fornecedor pelo CNPJ.
	 * 
	 * @param cnpj
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private Fornecedor buscaClienteFornecedorPeloCNPJ(String cnpj) throws Throwable {

		clienteFornecedor = new ClienteFornecedor();

		Fornecedor fornecedor = new Fornecedor();

		String sql = lerArquivoComSQL(ARQUIVO_CLI_FORN_BUSCA_CNPJ_CPF_TAB_FCFO);

		PreparedStatement stmt = db.criarComando(sql);

		try {
			stmt.setString(1, cnpj);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				clienteFornecedor.setCodcfo(rs.getString("CODCFO"));
				fornecedor.setRazaoSocial(rs.getString("NOME"));
				fornecedor.setNomeFantasia(rs.getString("NOMEFANTASIA"));
				fornecedor.setCnpj(rs.getString("CGCCFO"));
				fornecedor.setInscricaoEstadual(rs.getString("INSCRESTADUAL"));
				fornecedor.setEndereco(rs.getString("RUA"));
				fornecedor.setBairro(rs.getString("BAIRRO"));
				fornecedor.setCidade(rs.getString("CIDADE"));
				fornecedor.setEstado(rs.getString("CODETD"));
				fornecedor.setCep(rs.getString("CEP"));
				fornecedor.setTelefone(rs.getString("TELEFONE"));
				fornecedor.setFax(rs.getString("FAX"));
				fornecedor.setEmail(rs.getString("EMAIL"));
				fornecedor.setContato(rs.getString("CONTATO"));
				fornecedor.setDescricaoProdutoServicoFornecido(rs.getString("CAMPOLIVRE"));
				clienteFornecedor.setCodmunicipio(rs.getString("CODMUNICIPIO"));
				fornecedor.setInscricaoMunicipal(rs.getString("INSCRMUNICIPAL"));
				clienteFornecedor.setIdcfo(rs.getInt("IDCFO"));
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: buscaClienteFornecedorPeloCNPJ().\n" + e);
		}

		return fornecedor;
	}

	/**
	 * Busca "CODUNDCONTROLE" da tabela "TPRD".
	 * 
	 * @param itemPedidoRetorno
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private void buscaTipoUnidadeMedida(ItemPedidoRetorno itemPedidoRetorno) throws Throwable {

		String sql = lerArquivoComSQL(ARQUIVO_COTACAO_BUSCA_PRODUTO_TAB_TPRD);

		PreparedStatement stmt = db.criarComando(sql);

		try {
			stmt.setString(1, Util.stringVazia(itemPedidoRetorno.getProdutoSolicitadoRmId()));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				cotacao.setCodund(rs.getString("CODUNDCONTROLE"));
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: buscaTipoUnidadeMedida()." + e);
		}
	}

	/**
	 * Busca o numero sequencial do item do movimento cadastrado. Tabela "TITMMOV".
	 * 
	 * @param pedidoDeRetorno
	 * @param itemPedidoRetorno
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private void buscaNumeroSequencialTabTCCOTACAOITMMOV(PedidoDeRetorno pedidoDeRetorno, ItemPedidoRetorno itemPedidoRetorno) throws Throwable {

		String sql = lerArquivoComSQL(ARQUIVO_COTACAO_BUSCA_NUM_SEQUENCIAL_TAB_TITMMOV);

		PreparedStatement stmt = db.criarComando(sql);

		try {
			stmt.setInt(1, Integer.parseInt(Util.stringVazia(pedidoDeRetorno.getPedidoRmId())));
			stmt.setInt(2, Integer.parseInt(Util.stringVazia(itemPedidoRetorno.getProdutoSolicitadoRmId())));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				cotacao.setNseqitmmov(rs.getInt("sequencia"));
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: geraNumeroSequencialTabTCCOTACAOITMMOV().\n" + e);
		}

	}

	/**
	 * Retorna "true" para o movimento já cadastrado na tabela "TCCOTACAOITMMOV".
	 * 
	 * @param pedidoDeRetorno
	 * @param itemPedidoRetorno
	 * @return
	 * @throws Throwable
	 *             -
	 * @return boolean
	 */
	private boolean buscaDadosTCCOTACAOITMMOV(PedidoDeRetorno pedidoDeRetorno, ItemPedidoRetorno itemPedidoRetorno) throws Throwable {

		Boolean encontrou = false;

		String sql = lerArquivoComSQL(ARQUIVO_COTACAO_BUSCA_COD_COTACAO_TAB_TCCOTACAOITMMOV);

		PreparedStatement stmt = db.criarComando(sql);

		try {
			stmt.setString(1, cotacao.getCodcotacao());
			stmt.setInt(2, Integer.parseInt(Util.stringVazia(pedidoDeRetorno.getPedidoRmId())));
			stmt.setInt(3, cotacao.getNseqitmmov());

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				encontrou = true;
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: buscaDadosTCCOTACAOITMMOV().\n" + e);
		}

		return encontrou;

	}

	/**
	 * Retorna os "PedidoDeRetorno" que serão enviados ao RM.
	 * 
	 * @param pedidoRetornoList
	 * @return
	 * @throws Throwable
	 *             -
	 * @return List<PedidoDeRetorno>
	 */
	private List<PedidoDeRetorno> buscaPedidoJaEnviado(List<PedidoDeRetorno> pedidoRetornoList) throws Throwable {

		List<PedidoDeRetorno> pedidoRetornoListAux = new ArrayList<PedidoDeRetorno>();

		String sql = lerArquivoComSQL(ARQUIVO_COTACAO_BUSCA_ULT_PEDIDO_TAB_TBCDINTEGRACAOSCODETOTVS);

		PreparedStatement stmt = db.criarComando(sql);

		for (int i = 0; i < pedidoRetornoList.size(); i++) {

			try {
				stmt.setInt(1, Integer.parseInt(Util.stringVazia(pedidoRetornoList.get(i).getPedidoRmId())));
				stmt.setInt(2, Integer.parseInt(Util.stringVazia(pedidoRetornoList.get(i).getOrdemServicoId())));
				stmt.setString(3, Util.stringVazia(pedidoRetornoList.get(i).getOrdemCompraId()));

				ResultSet rs = stmt.executeQuery();

				if (!rs.next()) {
					pedidoRetornoListAux.add(pedidoRetornoList.get(i));
				}

				rs.close();

			} catch (SQLException e) {
				throw new SQLException("Erro ao tentar executar o metodo: buscaPedidoJaEnviado().\n" + e);
			}
		}

		stmt.close();

		return pedidoRetornoListAux;
	}

	/**
	 * Grava histórico dos pedidos que foram enviados do SCODE para o RM.
	 * 
	 * @param pedidoDeRetorno
	 * @throws Throwable
	 *             -
	 * @return void
	 */
	private void gravaHistoricoDeEnvioDosPedidos(PedidoDeRetorno pedidoDeRetorno) throws Throwable {

		String sql = lerArquivoComSQL(ARQUIVO_COTACAO_GRAVA_HISTORICO_TAB_TBCDHISTORICOENVIODEPEDIDOSCODETOTVS);

		PreparedStatement stmt = db.criarComando(sql);

		try {
			stmt.setInt(1, Integer.parseInt(Util.stringVazia(pedidoDeRetorno.getPedidoRmId())));
			stmt.setInt(2, Integer.parseInt(Util.stringVazia(pedidoDeRetorno.getOrdemServicoId())));
			stmt.setString(3, Util.stringVazia(pedidoDeRetorno.getOrdemCompraId()));

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar executar o metodo: gravaHistoricoDeEnvioDosPedidos().\n" + e);
		}
	}

}
