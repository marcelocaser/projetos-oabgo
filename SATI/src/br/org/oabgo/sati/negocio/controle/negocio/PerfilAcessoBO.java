package br.org.oabgo.sati.negocio.controle.negocio;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.sati.negocio.controle.entidade.AcaoMenuTO;
import br.org.oabgo.sati.negocio.controle.entidade.MenuSistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.PaginaTO;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.AcaoMenu;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.MenuSistema;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Pagina;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.PerfilAcesso;
import br.org.oabgo.sati.negocio.controle.persistencia.PerfilAcessoPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import core.mensagem.MensagemLista;

/**
 * <b>Classe:</b> PerfilAcessoBO.java <br>
 * <b>Descrição:</b> <br>
 * 
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.negocio <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 * Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
@Service
public class PerfilAcessoBO implements PerfilAcesso {

	@Autowired
	private PerfilAcessoPO	persistencia;
	
	@Autowired
	private MenuSistema		negocioMenu;
	
	@Autowired
	private Pagina			negocioPagina;
	
	@Autowired
	private AcaoMenu		negocioAcao;
	
	/**
	 * Executa a regra de negocio antes de inserir um novo objeto.
	 * 
	 * @param colaborador
	 *            - ColaboradorTO
	 * @throws RegraNegocioException
	 * 
	 *             <b># - Não pode existir dois objetos ColaboradorTO com a
	 *             mesma abreviatura. </b> <b># - Não pode existir dois objetos
	 *             ColaboradorTO com o mesmo código. </b>
	 */
	protected void beforeInsert(TransferObject bean)
			throws RegraNegocioException {
		PerfilAcessoTO perfil = (PerfilAcessoTO) bean;
		MensagemLista msgs = new MensagemLista();

		// verifica se já existe um colaborador com a mesma abreviatura
		// cadastrado
		PerfilAcessoTO perfilTmp = new PerfilAcessoTO();
		perfilTmp.setVchDescricao(perfil.getVchDescricao());

		if (this.persistencia.beforeInsert(perfilTmp)) {
			msgs.addMensagem("admPerfilAcessoDescricaoRegra");
		}

		// se existir mensagens dispara a exceção de regra de negocio
		if (msgs.getNumeroRegistros() > 0) {
			throw new RegraNegocioException(msgs);
		}
	}

	/**
	 * Executa a validação da regra de negocio antes de atualizar o objeto.
	 * 
	 * @param departamento
	 *            - ColaboradorTO
	 * @throws RegraNegocioException
	 * 
	 *             <b># - Não pode existir dois objetos ColaboradorTO com a
	 *             mesma abreviatura. </b> <b># - Não pode existir dois objetos
	 *             ColaboradorTO com o mesmo código. </b>
	 */
	protected void beforeUpdate(TransferObject bean)
			throws RegraNegocioException {
		PerfilAcessoTO perfil = (PerfilAcessoTO) bean;
		MensagemLista msgs = new MensagemLista();

		// verifica se já existe um colaborador com a mesma abreviatura
		// cadastrado
		PerfilAcessoTO perfilTmp = new PerfilAcessoTO();
		perfilTmp.setVchDescricao(perfil.getVchDescricao());

		if (this.persistencia.beforeUpdate(perfil, perfilTmp)) {
			msgs.addMensagem("admPerfilAcessoDescricaoRegra");
		}

		// se existir mensagens dispara a exceção de regra de negocio
		if (msgs.getNumeroRegistros() > 0) {
			throw new RegraNegocioException(msgs);
		}
	}

	/**
	 * Persiste as alterações contidas no objeto.
	 * 
	 * @param perfilAcesso
	 *            - PerfilAcessoTO
	 * @param usuarioLogado
	 *            - UsuarioTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(PerfilAcessoTO perfilAcesso, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeUpdate(perfilAcesso);
		this.persistencia.alterar(perfilAcesso, usuarioLogado);
	}
	
	/**
	 * Exclui o objeto.
	 * 
	 * @param perfilAcesso
	 *            - PerfilAcessoPO
	 * @param usuarioLogado
	 *            - UsuarioTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(PerfilAcessoTO perfilAcesso, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.persistencia.excluir(perfilAcesso, usuarioLogado);
	}
	
	/**
	 * Persiste as informações contidas no objeto.
	 * 
	 * @param perfilAcesso
	 *            - PerfilAcessoPO
	 * @param usuarioLogado
	 *            - UsuarioTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 * 
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(PerfilAcessoTO perfilAcesso, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeInsert(perfilAcesso);
		this.persistencia.incluir(perfilAcesso, usuarioLogado);
	}

	/**
	 * Consulta o objeto.
	 * 
	 * @param perfilAcesso
	 *            - PerfilAcessoPO
	 * @return PerfilAcessoTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public PerfilAcessoTO consultar(PerfilAcessoTO perfilAcesso)
			throws BDException {
		return this.persistencia.consultar(perfilAcesso);
	}
	
	/**
	 * associa os perfis de acesso a ação.
	 */
	public void associarPerfilAcao(List<String> listaPerfil, AcaoMenuTO acaoMenu, UsuarioTO usuarioLogado) throws Exception {
		AcaoMenuTO acaoTO = this.negocioAcao.consultar(new AcaoMenuTO(acaoMenu.getId()));
		acaoTO.setListaPerfilAcesso(this.pegarListaPerfil(listaPerfil));
		this.negocioAcao.alterar(acaoTO, usuarioLogado);
	}
	
	/**
	 * associa os perfis de acesso ao MenuTO.
	 */
	public void associarPerfilMenu(List<String> listaPerfil, MenuSistemaTO menuSistema,UsuarioTO usuarioLogado) throws Exception {
		menuSistema = this.negocioMenu.consultar(new MenuSistemaTO(menuSistema.getId()));
		menuSistema.setListaPerfilAcesso(this.pegarListaPerfil(listaPerfil));
		this.negocioMenu.alterar(menuSistema, usuarioLogado);
	}
	
	/**
	 * associa os perfis de acesso a PaginaTO.
	 */
	public void associarPerfilPagina(List<String> listaPerfil, PaginaTO pagina,UsuarioTO usuarioLogado) throws Exception {
		pagina = this.negocioPagina.consultar(new PaginaTO(pagina.getId()));
		pagina.setListaPerfilAcesso(this.pegarListaPerfil(listaPerfil));
		this.negocioPagina.alterar(pagina, usuarioLogado);
	}
	
	/**
	 * 
	 * Recebe uma List<String> com os Id's dos perfis e retorna uma
	 * Set<PerfilAcessoTO> com os PerfilAcessoTO sincronizados.
	 * 
	 * @return Set<PerfilAcessoTO>
	 */
	public Set<PerfilAcessoTO> pegarListaPerfil(List<String> listaPerfil){
		Set<PerfilAcessoTO> newList = new HashSet<PerfilAcessoTO>();
		for(String idPerfil : listaPerfil){
			PerfilAcessoTO perfil = this.consultar(new PerfilAcessoTO(new Long(idPerfil)));
			newList.add(perfil);
		}
		return newList;
	}
	
	/**
	 * Lista os Perfils de acesso cadastrados com base nas propriedades do
	 * objeto.
	 * 
	 * @param to
	 *            - TransferObject
	 * @param orderBy
	 *            - String, parâmetro de ordenação
	 * @return List
	 */
	public List<TransferObject> listar(PerfilAcessoTO perfil, String orderBy)
			throws BDException {
		return this.persistencia.listar(perfil, orderBy);
	}

	/**
	 * Remove um objeto da sessão do Hibernate.
	 */
	public void retirarObjetoSessao(PerfilAcessoTO perfilAcesso) {
		this.persistencia.retirarObjetoSessao(perfilAcesso);
	}

	/**
	 * Realiza um Flush no Hibernate descarregado todos os comandos pendentes no
	 * banco.
	 */
	public void sincronizar() throws Throwable {
		this.persistencia.sincronizar();
	}

}
