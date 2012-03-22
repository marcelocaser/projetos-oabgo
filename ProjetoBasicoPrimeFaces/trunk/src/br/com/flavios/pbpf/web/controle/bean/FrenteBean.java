package br.com.flavios.pbpf.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import core.dao.TransferObject;

import br.com.flavios.pbpf.negocio.controle.entidade.EstoqueProdutoTO;
import br.com.flavios.pbpf.negocio.controle.entidade.OrcamentoTO;
import br.com.flavios.pbpf.negocio.controle.entidade.UsuarioTO;
import br.com.flavios.pbpf.negocio.controle.negocio.interfaces.EstoqueProduto;
import br.com.flavios.pbpf.negocio.controle.negocio.interfaces.Usuario;
import br.com.flavios.pbpf.negocio.enumerador.PBPFEnumTipoMensagem;
import br.com.flavios.pbpf.web.controle.PBPFManagedBean;

/**
 * <b>Classe:</b> FrenteBean.java <br>
 * <b>Descrição:</b>		   <br>
 *
 * <b>Projeto:</b> ProjetoBasicoPrimeFaces <br>
 * <b>Pacote:</b> br.com.flavios.pbpf.web.controle.bean <br>
 * <b>Empresa:</b> Flávio´s Calçados e Esportes <br>
 * 
 *    Copyright (c) 2011 Flávio´s - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@ManagedBean
@SessionScoped
public class FrenteBean extends PBPFManagedBean {

	private static final long serialVersionUID = -5347559914851537587L;
	
	private UsuarioTO usuario;
	
	private OrcamentoTO orcamento;
	
	private EstoqueProdutoTO estoqueProduto;
	
	private List<TransferObject> listaEstoqueProdutoDataTable;

	private Boolean validacaoCartao;
	
	private Boolean validacaoVendedor;
	
	private Integer tamanhoDialogHeight;

	public String novo() {
		this.setUsuario(null);
		this.setOrcamento(null);
		this.setEstoqueProduto(null);
		this.setListaEstoqueProdutoDataTable(null);
		this.setValidacaoCartao(false);
		this.setValidacaoVendedor(false);
		this.setTamanhoDialogHeight(null);
		return URL_FRENTE_NOVO;
	}
	
	public String salvar() {
		return null;
	}
	
	public void validarCartao() {
		this.setValidacaoCartao(true);
	}
	
	public String validarVendedor() {
		Usuario negocioUsuario = getPBPFBusinessFactory().createUsuario();
		try {
			negocioUsuario.retirarObjetoSessao(this.getUsuario());
			this.setUsuario(negocioUsuario.buscarUsuarioVendedor(this.getUsuario(), getFilialLogada()));
			if (this.getUsuario().getQdfNomFunc() != null) {
				this.setValidacaoVendedor(true);
				this.setTamanhoDialogHeight(480);			
			}
			
			return null;
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String consultarEstoqueProduto() {
		EstoqueProduto negocioEstoqueProduto = getPBPFBusinessFactory().createEstoqueProduto();
		try {
			EstoqueProdutoTO estoqueProduto = negocioEstoqueProduto.consultar(this.getEstoqueProduto(), getFilialLogada());
			if (estoqueProduto != null) {
				negocioEstoqueProduto.incluirDataTable(estoqueProduto, this.getListaEstoqueProdutoDataTable());				
			} else {
				setMessage("Produto não encontrado!", PBPFEnumTipoMensagem.ERRO);
			}
		} catch (Exception e) {
			return tratarExcecao(e);
		}
		this.setEstoqueProduto(null);
		return "";
	}

	public UsuarioTO getUsuario() {
		if (usuario == null) {
			usuario = new UsuarioTO();
		}
		return usuario;
	}

	public void setUsuario(UsuarioTO usuario) {
		this.usuario = usuario;
	}
	
	public OrcamentoTO getOrcamento() {
		if (orcamento == null) {
			orcamento = new OrcamentoTO();
		}
		return orcamento;
	}

	public void setOrcamento(OrcamentoTO orcamento) {
		this.orcamento = orcamento;
	}
	
	public EstoqueProdutoTO getEstoqueProduto() {
		if (estoqueProduto == null) {
			estoqueProduto = new EstoqueProdutoTO();
		}
		return estoqueProduto;
	}

	public void setEstoqueProduto(EstoqueProdutoTO estoqueProduto) {
		this.estoqueProduto = estoqueProduto;
	}

	public List<TransferObject> getListaEstoqueProdutoDataTable() {
		if (listaEstoqueProdutoDataTable == null) {
			listaEstoqueProdutoDataTable = new ArrayList<TransferObject>();
		}
		return listaEstoqueProdutoDataTable;
	}

	public void setListaEstoqueProdutoDataTable(
			List<TransferObject> listaEstoqueProdutoDataTable) {
		this.listaEstoqueProdutoDataTable = listaEstoqueProdutoDataTable;
	}

	public Boolean getValidacaoCartao() {
		return validacaoCartao;
	}

	public void setValidacaoCartao(Boolean validacaoCartao) {
		this.validacaoCartao = validacaoCartao;
	}

	public Boolean getValidacaoVendedor() {
		return validacaoVendedor;
	}

	public void setValidacaoVendedor(Boolean validacaoVendedor) {
		this.validacaoVendedor = validacaoVendedor;
	}

	public Integer getTamanhoDialogHeight() {
		if (tamanhoDialogHeight == null) {
			tamanhoDialogHeight = 66;
		}
		return tamanhoDialogHeight;
	}

	public void setTamanhoDialogHeight(Integer tamanhoDialogHeight) {
		this.tamanhoDialogHeight = tamanhoDialogHeight;
	}
}
