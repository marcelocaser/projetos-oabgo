package br.com.flavios.pbpf.web.controle.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import br.com.flavios.pbpf.web.controle.PBPFManagedBean;

@ManagedBean
@SessionScoped
public class MobileBean extends PBPFManagedBean {

	private static final long serialVersionUID = -1722385045455303258L;

	private String tema;

	private String codigoCliente;

	private String codigoGerado;

	public String salvar() {
		return "";
	}

	public void gerarCodigo() {
		Integer calcular = Integer.parseInt(this.getCodigoCliente()) + 5;
		this.setCodigoGerado(calcular.toString());
		this.setCodigoCliente(null);
		//Na Versao 3.0.RC1 nao sera mais necessario usar o oncomplete="PrimeFaces.navigate('nomedaview')"
		//podendo ser subistituido por: return "pm:nomedaview";
	}

	public String mostrarCodigoGerado() {
		return "";
	}

	public void temaEscolhido(ValueChangeEvent value) {
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoGerado() {
		return codigoGerado;
	}

	public void setCodigoGerado(String codigoGerado) {
		this.codigoGerado = codigoGerado;
	}
}
