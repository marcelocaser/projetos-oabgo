package br.org.oabgo.saeo.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import br.org.oabgo.saeo.negocio.controle.entidade.AreaAtuacaoTO;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.AreaAtuacao;
import br.org.oabgo.saeo.negocio.enumerador.SAEOEnumTipoMensagem;
import br.org.oabgo.saeo.web.controle.SAEOManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

public class AreaAtuacaoBean extends SAEOManagedBean {

	private static final long serialVersionUID = 1952949239762078702L;
	
	private AreaAtuacaoTO areaAtuacao;
	
	private Integer itensPorPagina = 15;
	
	private List<TransferObject> listaAreaAtuacaoDataTable;
	
	private String descricaoFiltro;
	
	public String listar() {
		AreaAtuacao negocioAreaAtuacao = getSAEOBusinessFactory().createAreaAtuacao();
		try {
			AreaAtuacaoTO areaAtuacao = this.pegarValorFiltro();
			this.setListaAreaAtuacaoDataTable(negocioAreaAtuacao.listar(areaAtuacao, "vchDescricao"));
			return "listarAreaAtuacao";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public AreaAtuacaoTO pegarValorFiltro() {
		AreaAtuacaoTO areaAtuacao = new AreaAtuacaoTO();
		areaAtuacao.setVchDescricao(Util.setString(this.getDescricaoFiltro()));
		return areaAtuacao;
	}
	
	public String incluir() {
		AreaAtuacao negocioAreaAtuacao = getSAEOBusinessFactory().createAreaAtuacao();
		try {
			negocioAreaAtuacao.incluir(this.getAreaAtuacao(), this.getUsuarioLogado());
			setMessage("msgSucessoIncluir", SAEOEnumTipoMensagem.SUCESSO);
			
			return this.novo();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String excluir() {
		AreaAtuacao negocioAreaAtuacao = getSAEOBusinessFactory().createAreaAtuacao();
		try {
			negocioAreaAtuacao.excluir(this.getAreaAtuacao(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SAEOEnumTipoMensagem.SUCESSO);
			
			return this.listar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String salvar() {
		AreaAtuacao negocioAreaAtuacao = getSAEOBusinessFactory().createAreaAtuacao();
		try {
			negocioAreaAtuacao.alterar(this.getAreaAtuacao(), this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", SAEOEnumTipoMensagem.SUCESSO);

			return this.consultar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String editar() {
		AreaAtuacao negocioAreaAtuacao = getSAEOBusinessFactory().createAreaAtuacao();
		try {
			this.setAreaAtuacao(negocioAreaAtuacao.consultar(new AreaAtuacaoTO(this.getAreaAtuacao().getId())));
			
			return "editarAreaAtuacao";
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String novo() {
		this.setAreaAtuacao(new AreaAtuacaoTO());

		return "novaAreaAtuacao";
	}
	
	public String consultar() {
		AreaAtuacao negocioAreaAtuacao = getSAEOBusinessFactory().createAreaAtuacao();
		try {
			negocioAreaAtuacao.retirarObjetoSessao(this.getAreaAtuacao());
			this.setAreaAtuacao(negocioAreaAtuacao.consultar(new AreaAtuacaoTO(this.getAreaAtuacao().getId())));

			return "consultarAreaAtuacao";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public AreaAtuacaoTO getAreaAtuacao() {
		if (areaAtuacao == null) {
			areaAtuacao = new AreaAtuacaoTO();
		}
		return areaAtuacao;
	}

	public void setAreaAtuacao(AreaAtuacaoTO areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public List<TransferObject> getListaAreaAtuacaoDataTable() {
		if (listaAreaAtuacaoDataTable == null) {
			listaAreaAtuacaoDataTable = new ArrayList<TransferObject>();
		}
		return listaAreaAtuacaoDataTable;
	}

	public void setListaAreaAtuacaoDataTable(
			List<TransferObject> listaAreaAtuacaoDataTable) {
		this.listaAreaAtuacaoDataTable = listaAreaAtuacaoDataTable;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}
	
}
