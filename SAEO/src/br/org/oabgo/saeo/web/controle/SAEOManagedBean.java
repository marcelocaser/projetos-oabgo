package br.org.oabgo.saeo.web.controle;

import org.richfaces.component.html.HtmlToolBar;

import br.org.oabgo.saeo.negocio.controle.SAEOControle;

@SuppressWarnings(value = "serial")
public class SAEOManagedBean extends SAEOControle {
	
	//Constantes de navegação
	protected static final String			JSF_DEFAULT			= "paginaPrincipal";
	protected static final String			JSF_ALTERAR_SENHA	= "alterarSenha";
	protected static final String			JSF_CTR_ACESSO		= "msgCtrAcesso";
	
	/**
	 * Insere um valor ao atributo menuBarra
	 * @param menuBarra - HtmlToolBar
	 */
	public void setMenuBarra(HtmlToolBar menuBarra) {
		this.menuBarra = menuBarra;
	}
	
}
