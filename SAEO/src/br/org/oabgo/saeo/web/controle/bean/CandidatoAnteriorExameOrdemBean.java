package br.org.oabgo.saeo.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.org.oabgo.saeo.negocio.controle.entidade.AreaAtuacaoTO;
import br.org.oabgo.saeo.negocio.controle.entidade.CandidatoAnteriorExameOrdemTO;
import br.org.oabgo.saeo.negocio.controle.entidade.CertameTO;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.AreaAtuacao;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.CandidatoAnteriorExameOrdem;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.Certame;
import br.org.oabgo.saeo.negocio.enumerador.SAEOEnumTipoMensagem;
import br.org.oabgo.saeo.web.controle.SAEOManagedBean;
import core.dao.TransferObject;
import core.enumeration.OrderType;
import core.utilitario.Util;

public class CandidatoAnteriorExameOrdemBean extends SAEOManagedBean {

	private static final long serialVersionUID = 2867350005645640074L;
	
	private CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem;
	
	private Integer itensPorPagina = 15;
	
	private List<SelectItem> listaAreaAtuacao;
	
	private List<SelectItem> listaCertame;
	
	private List<TransferObject> listaCandidatoAnteriorExameOrdemDataTable;
	
	private List<TransferObject> listaCandidatoAnteriorExameOrdemDezUltimos;
	
	private String idAreaAtuacao;
	
	private String idCertame;
	
	private String nomeFiltro;
	
	private String totalDeRegistros;
	
	public String listar() {
		CandidatoAnteriorExameOrdem negocioCandidatoAnteriorExameOrdem = getSAEOBusinessFactory().createCandidatoAnteriorExameOrdem();
		try {
			CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem = this.pegarValorFiltro();
			this.setListaCandidatoAnteriorExameOrdemDataTable(negocioCandidatoAnteriorExameOrdem.listar(candidatoAnteriorExameOrdem, "id", OrderType.DESC));
			this.setTotalDeRegistros(String.valueOf(this.getListaCandidatoAnteriorExameOrdemDataTable().size()));
			return "listarCandidatoAnteriorExameOrdem";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public void listarDezUltimos() {
		CandidatoAnteriorExameOrdem negocioCandidatoAnteriorExameOrdem = getSAEOBusinessFactory().createCandidatoAnteriorExameOrdem();
		this.setListaCandidatoAnteriorExameOrdemDezUltimos(negocioCandidatoAnteriorExameOrdem.listarDezUltimos());
		this.setListaCandidatoAnteriorExameOrdemDataTable(negocioCandidatoAnteriorExameOrdem.listar(this.getCandidatoAnteriorExameOrdem()));
		this.setTotalDeRegistros(String.valueOf(this.getListaCandidatoAnteriorExameOrdemDataTable().size()));
	}
	
	public CandidatoAnteriorExameOrdemTO pegarValorFiltro() {
		CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem = new CandidatoAnteriorExameOrdemTO();
		candidatoAnteriorExameOrdem.setVchNomeCandidato(Util.setString(this.getNomeFiltro()));
		return candidatoAnteriorExameOrdem;
	}
	
	public String incluir() {
		CandidatoAnteriorExameOrdem negocioCandidatoAnteriorExameOrdem = getSAEOBusinessFactory().createCandidatoAnteriorExameOrdem();
		try {
			if (this.getIdAreaAtuacao() != null) {
				this.getCandidatoAnteriorExameOrdem().setAreaAtuacao(Util.setValueTO(this.getIdAreaAtuacao(), AreaAtuacaoTO.class));
			}
			if (this.getIdCertame() != null) {
				this.getCandidatoAnteriorExameOrdem().setCertame(Util.setValueTO(this.getIdCertame(), CertameTO.class));
			}
			
			negocioCandidatoAnteriorExameOrdem.incluir(this.getCandidatoAnteriorExameOrdem(), this.getUsuarioLogado());
			setMessage("msgSucessoIncluir", SAEOEnumTipoMensagem.SUCESSO);
			
			this.setIdAreaAtuacao(new String());
			this.setIdCertame(new String());
			
			return this.novo();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String excluir() {
		CandidatoAnteriorExameOrdem negocioCandidatoAnteriorExameOrdem = getSAEOBusinessFactory().createCandidatoAnteriorExameOrdem();
		try {
			negocioCandidatoAnteriorExameOrdem.excluir(this.getCandidatoAnteriorExameOrdem(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SAEOEnumTipoMensagem.SUCESSO);
			
			return this.listar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String salvar() {
		CandidatoAnteriorExameOrdem negocioCandidatoAnteriorExameOrdem = getSAEOBusinessFactory().createCandidatoAnteriorExameOrdem();
		try {
			if (this.getIdAreaAtuacao() != null) {
				this.getCandidatoAnteriorExameOrdem().setAreaAtuacao(Util.setValueTO(this.getIdAreaAtuacao(), AreaAtuacaoTO.class));
			}
			if (this.getIdCertame() != null) {
				this.getCandidatoAnteriorExameOrdem().setCertame(Util.setValueTO(this.getIdCertame(), CertameTO.class));
			}
			
			negocioCandidatoAnteriorExameOrdem.alterar(this.getCandidatoAnteriorExameOrdem(), this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", SAEOEnumTipoMensagem.SUCESSO);

			return this.consultar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String editar() {
		CandidatoAnteriorExameOrdem negocioCandidatoAnteriorExameOrdem = getSAEOBusinessFactory().createCandidatoAnteriorExameOrdem();
		try {
			this.setCandidatoAnteriorExameOrdem(negocioCandidatoAnteriorExameOrdem.consultar(new CandidatoAnteriorExameOrdemTO(this.getCandidatoAnteriorExameOrdem().getId())));
			
			if (this.getCandidatoAnteriorExameOrdem().getAreaAtuacao() != null) {
				this.setIdAreaAtuacao(String.valueOf(this.getCandidatoAnteriorExameOrdem().getAreaAtuacao().getId()));
			} else {
				this.setIdAreaAtuacao(null);
			}
			
			if (this.getCandidatoAnteriorExameOrdem().getCertame() != null) {
				this.setIdCertame(String.valueOf(this.getCandidatoAnteriorExameOrdem().getCertame().getId()));
			} else {
				this.setIdCertame(null);
			}
			
			return "editarCandidatoAnteriorExameOrdem";
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String novo() {
		this.setCandidatoAnteriorExameOrdem(new CandidatoAnteriorExameOrdemTO());
		
		this.listarDezUltimos();

		return "novoCandidatoAnteriorExameOrdem";
	}
	
	public String consultar() {
		CandidatoAnteriorExameOrdem negocioCandidatoAnteriorExameOrdem = getSAEOBusinessFactory().createCandidatoAnteriorExameOrdem();
		try {
			negocioCandidatoAnteriorExameOrdem.retirarObjetoSessao(this.getCandidatoAnteriorExameOrdem());
			this.setCandidatoAnteriorExameOrdem(negocioCandidatoAnteriorExameOrdem.consultar(new CandidatoAnteriorExameOrdemTO(this.getCandidatoAnteriorExameOrdem().getId())));

			return "consultarCandidatoAnteriorExameOrdem";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public CandidatoAnteriorExameOrdemTO getCandidatoAnteriorExameOrdem() {
		if (candidatoAnteriorExameOrdem == null) {
			candidatoAnteriorExameOrdem = new CandidatoAnteriorExameOrdemTO();
		}
		return candidatoAnteriorExameOrdem;
	}

	public void setCandidatoAnteriorExameOrdem(
			CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem) {
		this.candidatoAnteriorExameOrdem = candidatoAnteriorExameOrdem;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public List<SelectItem> getListaAreaAtuacao() {
		if (listaAreaAtuacao == null) {
			AreaAtuacao negocioAreaAtuacao = getSAEOBusinessFactory().createAreaAtuacao();
			List<TransferObject> lista = negocioAreaAtuacao.listarPorStatus(true);
			listaAreaAtuacao = Util.createListSelectItens(lista, "id",
					"vchDescricao");
		}
		return listaAreaAtuacao;
	}

	public void setListaAreaAtuacao(List<SelectItem> listaAreaAtuacao) {
		this.listaAreaAtuacao = listaAreaAtuacao;
	}
	
	public List<SelectItem> getListaCertame() {
		if (listaCertame == null) {
			Certame negocioCertameo = getSAEOBusinessFactory().createCertame();
			List<TransferObject> lista = negocioCertameo.listar(new CertameTO(),
					"vchDescricao");
			listaCertame = Util.createListSelectItens(lista, "id",
					"vchDescricao");
		}
		return listaCertame;
	}

	public void setListaCertame(List<SelectItem> listaCertame) {
		this.listaCertame = listaCertame;
	}

	public List<TransferObject> getListaCandidatoAnteriorExameOrdemDataTable() {
		if (listaCandidatoAnteriorExameOrdemDataTable == null) {
			listaCandidatoAnteriorExameOrdemDataTable = new ArrayList<TransferObject>();
		}
		return listaCandidatoAnteriorExameOrdemDataTable;
	}

	public void setListaCandidatoAnteriorExameOrdemDataTable(
			List<TransferObject> listaCandidatoAnteriorExameOrdemDataTable) {
		this.listaCandidatoAnteriorExameOrdemDataTable = listaCandidatoAnteriorExameOrdemDataTable;
	}
	
	public List<TransferObject> getListaCandidatoAnteriorExameOrdemDezUltimos() {
		if (listaCandidatoAnteriorExameOrdemDezUltimos == null) {
			listaCandidatoAnteriorExameOrdemDezUltimos = new ArrayList<TransferObject>();
		}
		return listaCandidatoAnteriorExameOrdemDezUltimos;
	}

	public void setListaCandidatoAnteriorExameOrdemDezUltimos(
			List<TransferObject> listaCandidatoAnteriorExameOrdemDezUltimos) {
		this.listaCandidatoAnteriorExameOrdemDezUltimos = listaCandidatoAnteriorExameOrdemDezUltimos;
	}

	public String getIdAreaAtuacao() {
		return idAreaAtuacao;
	}

	public void setIdAreaAtuacao(String idAreaAtuacao) {
		this.idAreaAtuacao = idAreaAtuacao;
	}

	public String getIdCertame() {
		return idCertame;
	}

	public void setIdCertame(String idCertame) {
		this.idCertame = idCertame;
	}

	public String getNomeFiltro() {
		return nomeFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}
	
	public String getTotalDeRegistros() {
		return totalDeRegistros;
	}

	public void setTotalDeRegistros(String totalDeRegistros) {
		this.totalDeRegistros = totalDeRegistros;
	}

}
