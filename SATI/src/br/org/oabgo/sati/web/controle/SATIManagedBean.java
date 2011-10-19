package br.org.oabgo.sati.web.controle;

import org.richfaces.component.html.HtmlToolBar;

import br.org.oabgo.sati.negocio.controle.SATIConstantes;
import br.org.oabgo.sati.negocio.controle.SATIControle;
import core.excecoes.RegraNegocioException;

@SuppressWarnings(value = "serial")
public class SATIManagedBean extends SATIControle {
	
	//Constantes de navegação
	protected static final String			JSF_DEFAULT			= "paginaPrincipal";
	protected static final String			JSF_ALTERAR_SENHA	= "alterarSenha";
	protected static final String			JSF_CTR_ACESSO		= "msgCtrAcesso";
	
	/**
	 * Método chamado durante as requisições para construir o 
	 * menu de ações.
	 * @return void
	 */
	protected void setAcoes(){
		super.construirMenuAcoes();
	}

	/**
	 * Retorna o valor armazenado no atributo menuBarra.
	 * @return HtmlToolBar
	 * @throws RegraNegocioException 
	 */
	public HtmlToolBar getMenuBarra() throws RegraNegocioException {
		if(menuBarra == null){
			menuBarra = super.construirMenuBarra(SATIConstantes.SISTEMA);
		}
		return menuBarra;
	}

	/**
	 * Insere um valor ao atributo menuBarra.
	 * @param menuBarra - HtmlToolBar
	 */
	public void setMenuBarra(HtmlToolBar menuBarra) {
		this.menuBarra = menuBarra;
	}
	
}
