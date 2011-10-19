package br.org.oabgo.sati.web.controle;

import javax.servlet.http.HttpSession;

import br.org.oabgo.sati.negocio.controle.SATIBusinessFactory;
import br.org.oabgo.sati.negocio.controle.entidade.PaginaTO;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Pagina;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumSecurity;

/**
 * <b>Classe:</b> SATIAutorizacao.java <br>
 * <b>Descri��o:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.web.controle <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Se��o de Goi�s <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
public class SATIAutorizacao {
	
	private HttpSession			session;
	private String 				pagina;
	private PerfilAcessoTO		perfilAcesso;
	private SistemaTO			sistema;
	private boolean				autorizado;
	
	/**
	 * Valida se o usu�rio logado possui AUTORIZA��O para
	 * acessar uma determinada p�gina.
	 * 
	 * @param session - HttpSession
	 * @param pagina  - TipoDoPar�metro
	 */
	public SATIAutorizacao(HttpSession session, String pagina) {
		this.session 		= session;
		this.pagina 		= pagina;
		this.sistema 		= (SistemaTO)session.getAttribute(SATIEnumSecurity.SISTEMA_CONTROLADO.name());
		this.perfilAcesso 	= (PerfilAcessoTO)session.getAttribute(SATIEnumSecurity.PERFIL_ACESSO_LOGADO.name());
		
		executarRotinaAutorizadcaoFACES();
	}

	
	/**
	 * M�todo que verifica se o usu�rio logado esta autorizado a acessar o
	 * recurso requisitado. Esta rotina so � executada se e somente se as
	 * rotinas que valid�o o quesito AUTENTICA��O estiverem sido executados e
	 * validados corretamente, para que o conceito de autoriza��o seja aplicado
	 * � necessario a existencia de um usu�rio AUTENTICADO.
	 *
	 */
	private void executarRotinaAutorizadcaoFACES(){
		//so executa a regra de valida��o se existirem SISTEMA E USU�RIO
		if(sistema != null && perfilAcesso != null){
			// 3� -- VERIFICA SE A P�GINA REQUISITADA ESTA CADASTRADA NO ACS 
			Pagina negocioPagina = SATIBusinessFactory.getInstance().createPagina();
			PaginaTO pagina = new PaginaTO();
			pagina.setVchPagina(this.pagina);
			pagina.setSistema(new SistemaTO(this.sistema.getId()));
			//busca a p�gina vinculada ao sistema
			pagina = negocioPagina.buscarObjeto(pagina);
			// a pagina de login
			if(pagina == null){
				this.session.setAttribute(SATIEnumSecurity.MENSAGEM_ACESSO_RECURSO.name(), "P�gina ["+this.pagina+"] n�o reconhecida pelo controle de Acesso, entre em contato com o Administrador do Sistema");
				this.autorizado = false;
			}else if(!negocioPagina.possuiAcessoPagina(pagina, perfilAcesso)){
				//insere a mensagem de restri��o de acesso no request
				this.session.setAttribute(SATIEnumSecurity.MENSAGEM_ACESSO_RECURSO.name(), "Acesso � p�gina ["+this.pagina+"] n�o autorizado, entre em contato com o Administrador do Sistema.");
				this.autorizado =  false;
			}else{
				this.autorizado =  true;				
			}
		}
	}
	
	
	/**
	 * M�todo que verifica se o usu�rio logado esta autorizado a acessar o
	 * recurso requisitado. Esta rotina so � executada se e somente se as
	 * rotinas que valid�o o quesito AUTENTICA��O estiverem sido executados e
	 * validados corretamente, para que o conceito de autoriza��o seja aplicado
	 * � necessario a existencia de um usu�rio AUTENTICADO.
	 *
	 * @return boolean
	 */
	public boolean isAutorizado(){
		return autorizado;
	}

	/**
	 * Retorna a URL de destino de Redirect caso o usu�rio n�o tenha autoriza��o para acessar a p�gina.
	 * @return String
	 */
	public String getUrlDestino(){
		return this.sistema.getVchUrlPaginaPrincipal();
	}
}
