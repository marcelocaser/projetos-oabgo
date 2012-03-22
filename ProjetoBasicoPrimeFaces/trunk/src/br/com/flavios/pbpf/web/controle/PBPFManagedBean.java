package br.com.flavios.pbpf.web.controle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.flavios.pbpf.negocio.controle.PBPFControle;

@SuppressWarnings(value = "serial")
@ManagedBean(name="managedBean")
@SessionScoped
public class PBPFManagedBean extends PBPFControle {

	/**
	 * DEFINIÇÕES DO JSF 2.0 PARA REDIRECT DAS PÁGINAS
	 */
	protected static final String URL_USUARIO_LISTAR = "/xhtml/usuario/usuarioListar";
	protected static final String URL_USUARIO_CONSULTAR = "/xhtml/usuario/usuarioConsultar";
	
	protected static final String URL_FRENTE_NOVO = "/xhtml/orcamento/orcamentoNovo";
	
	protected static final String URL_MOBILE_VENDA_AUTORIZADA = "/xhtml/mobile/vendaAutorizada";
	
	protected static final String JSF_DEFAULT = "/xhtml/principal/paginaPrincipal";
	protected static final String JSF_DEFAULT_MOBILE = "/xhtml/mobile/paginaPrincipal";
	//protected static final String JSF_ALTERAR_SENHA = "alterarSenha";
	//protected static final String JSF_CTR_ACESSO = "msgCtrAcesso";

}
