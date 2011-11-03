package br.com.flavios.pbpf.negocio.controle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlPanelGrid;

import org.springframework.dao.DataIntegrityViolationException;

import br.com.flavios.pbpf.negocio.controle.entidade.SistemaTO;
import br.com.flavios.pbpf.negocio.controle.entidade.UsuarioTO;
import br.com.flavios.pbpf.negocio.controle.negocio.interfaces.Sistema;
import br.com.flavios.pbpf.negocio.enumerador.PBPFEnumSecurity;
import br.com.flavios.pbpf.negocio.enumerador.PBPFEnumTipoMensagem;
import core.controlador.Controlador;
import core.dao.TransferObject;
import core.excecoes.AcessoException;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import core.jsf.FacesUtil;
import core.mensagem.Mensagem;
import core.mensagem.MensagemLista;
import core.resource.ResourceServiceUtil;
import core.utilitario.Util;

public class PBPFControle extends Controlador implements PBPFConstantes {
	
	private static final long serialVersionUID = 1L;
	
	private PBPFBusinessFactory pbpfBusinessFactory;

	//protected 	HtmlToolBar 				menuBarra;
	//protected   HtmlSimpleTogglePanel    	simpleTogglePanel;
	protected   boolean                  	estadoSimpleTogglePanel;
	
	/**
	 * Retorna a instancia de negocio das classes de negocio.
	 * @return PBPFBusinessFactory
	 */
	protected PBPFBusinessFactory getPBPFBusinessFactory(){
		if(pbpfBusinessFactory == null){
			pbpfBusinessFactory = PBPFBusinessFactory.getInstance();
		}
		return pbpfBusinessFactory;
	}
	
	/**
	 * Lista os obejtos ordenados por uma propriedade.
	 * @param bean - {@link TransferObject}
	 * @param orderBy - {@link String} - parametro de ordenação
	 * @throws BDException 
	 * @return TransferObject
	 */
	protected List<TransferObject> listarObjeto(TransferObject bean, String orderBy) throws BDException{
		return getPBPFBusinessFactory().createServicoGenerico().listar(bean, orderBy);
	}
	
	/**
	 * Retorna o usuário logado.
	 * @return UsuarioTO
	 */
	public UsuarioTO getUsuarioLogado(){
		return (UsuarioTO)getParamSession(PBPFEnumSecurity.USUARIO_LOGADO); 
	}

	/**
	 * Retorna o sistema logado.
	 * @return SistemaTO
	 */
	public SistemaTO getSistemaLogado(){
		return (SistemaTO)getParamSession(PBPFEnumSecurity.SISTEMA_CONTROLADO); 
	}
	
	/**
	 * Insere um parámetro no request.
	 * @param alias - AdmNetEnumSecurity
	 * @param parameter - Object
	 */
	protected void setParamRequest(PBPFEnumSecurity alias, Object parameter) {
		FacesUtil.setParamRequest(this.getContext(), alias.name(), parameter);
	}
	
	/**
	 * Dispara mensagem via modal panel no request.
	 *
	 * @param key - {@link String} - Chave de texto existente no arquivo de internacionalização.
	 * @param tipo - {@link Integer} - Constante que indica o tipo de mensagem - [MSG_ERRO, MSG_SUCESSO, MSG_ATENCAO].
	 * @return void
	 */
	protected void setMessage(String key, PBPFEnumTipoMensagem tipo) {
		setParamRequest(PBPFEnumSecurity.PBPF_MODAL_MENSAGEM, 	Boolean.TRUE);
		setParamRequest(PBPFEnumSecurity.PBPF_TIPO_MENSAGEM, 		tipo);
		FacesUtil.addMessageCtx(this.getContext(), 		new FacesMessage(this.getMessage(key)));
	}

	/**
	 * Dispara mensagem via modal panel no request.
	 *
	 * @param key - {@link String} - Chave de texto existente no arquivo de internacionalização.
	 * @param parametros - {@link String[]} - Lista de parametros que serviço inseridos na mensagem.
	 * @param tipo - {@link Integer} - Constante que indica o tipo de mensagem - [MSG_ERRO, MSG_SUCESSO, MSG_ATENCAO].
	 * @return void
	 */
	protected void setMessage(String key, String[] parametros, PBPFEnumTipoMensagem tipo) {
		setParamRequest(PBPFEnumSecurity.PBPF_MODAL_MENSAGEM, 	Boolean.TRUE);
		setParamRequest(PBPFEnumSecurity.PBPF_TIPO_MENSAGEM, 		tipo);
		FacesUtil.addMessageCtx(this.getContext(), 		new FacesMessage(this.getMessage(key, parametros)));
	}
	
	/**
	 * Adiciona uma lista de mensagens no request.
	 * @param mensagens - List<Mensagem>
	 * @param tipo - int
	 */
	protected void setMessage(List<Mensagem> listaMensagem, PBPFEnumTipoMensagem tipo) {
		setParamRequest(PBPFEnumSecurity.PBPF_MODAL_MENSAGEM, 	Boolean.TRUE);
		setParamRequest(PBPFEnumSecurity.PBPF_TIPO_MENSAGEM, 		tipo);
		for(Mensagem msg : listaMensagem){
			FacesUtil.addMessageCtx(this.getContext(),new FacesMessage(ResourceServiceUtil.getMessageResourceString(msg.getMensagem(), null)));
		}
	}
	
	/**
	 * Adiciona uma lista de mensagens no request.
	 * @param mensagens - MensagemLista
	 * @param tipo - int
	 */
	protected void setMessage(MensagemLista listaMensagem, PBPFEnumTipoMensagem tipo) {
		setParamRequest(PBPFEnumSecurity.PBPF_MODAL_MENSAGEM, 	Boolean.TRUE);
		setParamRequest(PBPFEnumSecurity.PBPF_TIPO_MENSAGEM, 		tipo);
		for(Mensagem msg : listaMensagem.getMensagens()){
			FacesUtil.addMessageCtx(this.getContext(),new FacesMessage(ResourceServiceUtil.getMessageResourceString(msg.getMensagem(), null)));
		}
	}
	
	/**
	 * Retorna o contexto do sistema.
	 * @return String
	 */
	protected String getContextPath(){
		return FacesUtil.getRequest(getContext()).getContextPath();
	}
	
	/**
	 * Insere parametros do controle de acesso na sessão.
	 *
	 * @param key - AdmNetEnumSecurity
	 * @param parameter - Object
	 * @return void
	 */
	protected void setParamSession(PBPFEnumSecurity key, Object parameter){
		super.setParamSession(key.name(), parameter);
	}

	/**
	 * Remove parametros do controle de acesso na sessão.
	 *
	 * @param key - AdmNetEnumSecurity
	 * @return void
	 */
	protected void removeParamSession(PBPFEnumSecurity key){
		super.removeParamSession(key.name());
	}
	
	/**
	 * Remove a autenticação do usuário no controle de acesso.
	 * @param sistema - {@link String} - Atributo que representa o sistema que requisitou o logoff 
	 * no controle de acesso.
	 * 
	 * @return void
	 */
	protected void removerAcesso() throws Exception{
		//Destroi a sessão do Usuario
		sessionDestroy();
		Sistema negocioSistema = PBPFBusinessFactory.getInstance().createSistema();
		SistemaTO sistema = negocioSistema.consultarSistemaContexto(getRequest().getContextPath());
		//Redireciona para a página inicial do sistema.
		FacesUtil.redirect(getContext(), sistema.getVchUrlSistema());
	}
	
	/**
	 * Retorna parametros do controle de acesso armazenados na sessão.
	 *
	 * @param key - AdmNetEnumSecurity
	 * @return Object
	 */
	protected Object getParamSession(PBPFEnumSecurity key){
		return super.getParamSession(key.name());
	}
	
	/**
	 * Tratamento de exceções para filtrar as mensagens emitidas ao usuário.
	 * @param e - {@link Exception}
	 */
	protected String tratarExcecao(Exception e){
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		//Busca a origem de exceções não declaradas
		if(e instanceof UndeclaredThrowableException){
			e = Util.getException(e);
		}else if(e instanceof InvocationTargetException){
			e = Util.getException(e);
		}
		
		//exceções customizadas
		if (e instanceof RegraNegocioException) {
			mensagens = ((RegraNegocioException)e).getMensagens();
		}else if(e instanceof AcessoException){
			mensagens = ((AcessoException)e).getMensagens();
		}else if(e instanceof DataIntegrityViolationException){
			mensagens.add(new Mensagem(getMessage("admDataIntegrityViolationException")));	
		}else if(e instanceof NullPointerException){
			mensagens.add(new Mensagem(getMessage("admNullPointerException")));	
		}else{
			mensagens.add(new Mensagem(e.getMessage()));			
		}
		e.printStackTrace();
		this.setMessage(mensagens, PBPFEnumTipoMensagem.ERRO);
		return null;
	}
	
	/**
	 * Retorna o valor armazenado no atributo simpleTogglePanel.
	 *
	 * @return HtmlSimpleTogglePanel
	 */
	/*public HtmlSimpleTogglePanel getSimpleTogglePanel() {
		simpleTogglePanel = new HtmlSimpleTogglePanel();
		simpleTogglePanel.saveState(this.getContext());
		simpleTogglePanel.setOpened(estadoSimpleTogglePanel);
		
		return simpleTogglePanel;
	}*/

	/**
	 * Altera o estado do toggle panel.
	 */
	public void alterarEstadoToggle(){
		estadoSimpleTogglePanel = !estadoSimpleTogglePanel;
	}
	
	/**
	 * Insere um valor ao atributo simpleTogglePanel.
	 *
	 * @param simpleTogglePanel - HtmlSimpleTogglePanel
	 */
	/*public void setSimpleTogglePanel(HtmlSimpleTogglePanel simpleTogglePanel) {
		this.simpleTogglePanel = simpleTogglePanel;
	}*/

	/**
	 * Retorna o valor armazenado no atributo estadoSimpleTogglePanel.
	 * @return Boolean
	 */
	public Boolean getEstadoSimpleTogglePanel() {
		return estadoSimpleTogglePanel;
	}

	/**
	 * Insere um valor ao atributo estadoSimpleTogglePanel.
	 *
	 * @param estadoSimpleTogglePanel - Boolean
	 */
	public void setEstadoSimpleTogglePanel(Boolean estadoSimpleTogglePanel) {
		this.estadoSimpleTogglePanel = estadoSimpleTogglePanel;
	}
	
	/**
	 * Retorna o valor armazenado no atributo menuAcoes.
	 * @param grid - HtmlPanelGrid
	 */
	public void setListaAcoes(HtmlPanelGrid grid) {}
	
}


