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
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.web.controle <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
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
	 * Valida se o usuário logado possui AUTORIZAÇÃO para
	 * acessar uma determinada página.
	 * 
	 * @param session - HttpSession
	 * @param pagina  - TipoDoParâmetro
	 */
	public SATIAutorizacao(HttpSession session, String pagina) {
		this.session 		= session;
		this.pagina 		= pagina;
		this.sistema 		= (SistemaTO)session.getAttribute(SATIEnumSecurity.SISTEMA_CONTROLADO.name());
		this.perfilAcesso 	= (PerfilAcessoTO)session.getAttribute(SATIEnumSecurity.PERFIL_ACESSO_LOGADO.name());
		
		executarRotinaAutorizadcaoFACES();
	}

	
	/**
	 * Método que verifica se o usuário logado esta autorizado a acessar o
	 * recurso requisitado. Esta rotina so é executada se e somente se as
	 * rotinas que validão o quesito AUTENTICAÇÃO estiverem sido executados e
	 * validados corretamente, para que o conceito de autorização seja aplicado
	 * é necessario a existencia de um usuário AUTENTICADO.
	 *
	 */
	private void executarRotinaAutorizadcaoFACES(){
		//so executa a regra de validação se existirem SISTEMA E USUÁRIO
		if(sistema != null && perfilAcesso != null){
			// 3° -- VERIFICA SE A PÁGINA REQUISITADA ESTA CADASTRADA NO ACS 
			Pagina negocioPagina = SATIBusinessFactory.getInstance().createPagina();
			PaginaTO pagina = new PaginaTO();
			pagina.setVchPagina(this.pagina);
			pagina.setSistema(new SistemaTO(this.sistema.getId()));
			//busca a página vinculada ao sistema
			pagina = negocioPagina.buscarObjeto(pagina);
			// a pagina de login
			if(pagina == null){
				this.session.setAttribute(SATIEnumSecurity.MENSAGEM_ACESSO_RECURSO.name(), "Página ["+this.pagina+"] não reconhecida pelo controle de Acesso, entre em contato com o Administrador do Sistema");
				this.autorizado = false;
			}else if(!negocioPagina.possuiAcessoPagina(pagina, perfilAcesso)){
				//insere a mensagem de restrição de acesso no request
				this.session.setAttribute(SATIEnumSecurity.MENSAGEM_ACESSO_RECURSO.name(), "Acesso à página ["+this.pagina+"] não autorizado, entre em contato com o Administrador do Sistema.");
				this.autorizado =  false;
			}else{
				this.autorizado =  true;				
			}
		}
	}
	
	
	/**
	 * Método que verifica se o usuário logado esta autorizado a acessar o
	 * recurso requisitado. Esta rotina so é executada se e somente se as
	 * rotinas que validão o quesito AUTENTICAÇÃO estiverem sido executados e
	 * validados corretamente, para que o conceito de autorização seja aplicado
	 * é necessario a existencia de um usuário AUTENTICADO.
	 *
	 * @return boolean
	 */
	public boolean isAutorizado(){
		return autorizado;
	}

	/**
	 * Retorna a URL de destino de Redirect caso o usuário não tenha autorização para acessar a página.
	 * @return String
	 */
	public String getUrlDestino(){
		return this.sistema.getVchUrlPaginaPrincipal();
	}
}
