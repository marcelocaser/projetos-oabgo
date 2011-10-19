package br.org.oabgo.sati.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.org.oabgo.sati.negocio.controle.entidade.EmpresaTO;
import br.org.oabgo.sati.negocio.controle.entidade.ItemCiaTelefonicaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UnidadeAdministrativaTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Empresa;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.ItemCiaTelefonica;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.UnidadeAdministrativa;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumTipoMensagem;
import br.org.oabgo.sati.web.controle.SATIManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

public class ItemCiaTelefonicaBean extends SATIManagedBean {

	private static final long serialVersionUID = 4645682661245078489L;
	
	private ItemCiaTelefonicaTO itemCiaTelefonica;
	
	private Integer itemsPorPagina = 15;
	
	private List<TransferObject> listaItemCiaTelefonicaDataTable;
	
	private List<SelectItem> listaEmpresa;
	
	private List<SelectItem> listaUnidadeAdministrativa;
	
	private String idEmpresa;
	
	private String idUnidadeAdministrativa;
	
	private String numeroContratoAgrupadorFiltro;
	
	private String numeroTelefoneFiltro;
	
	public String listar() {
		ItemCiaTelefonica negocioItemCiaTelefonica = getSATIBusinessFactory().createItemCiaTelefonica();
		try {
			ItemCiaTelefonicaTO itemCiaTelefonica = this.pegarValorFiltro();
			this.setListaItemCiaTelefonicaDataTable(negocioItemCiaTelefonica.listar(itemCiaTelefonica, "vchNumrContratoAgrupador"));
			this.setListaItemCiaTelefonicaDataTable(negocioItemCiaTelefonica.listar(itemCiaTelefonica, "vchNumrTelefone"));
			return "listarItemCiaTelefonica";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public ItemCiaTelefonicaTO pegarValorFiltro() {
		ItemCiaTelefonicaTO itemCiaTelefonica = new ItemCiaTelefonicaTO();
		itemCiaTelefonica.setVchNumrContratoAgrupador(Util.setString(this.getNumeroContratoAgrupadorFiltro()));
		itemCiaTelefonica.setVchNumrTelefone(Util.setString(this.getNumeroTelefoneFiltro()));
		return itemCiaTelefonica;
	}
	
	public String incluir() {
		ItemCiaTelefonica negocioItemCiaTelefonica = getSATIBusinessFactory().createItemCiaTelefonica();
		try {
			
			this.getItemCiaTelefonica().setEmpresa(Util.setValueTO(this.getIdEmpresa(), EmpresaTO.class));
			
			this.getItemCiaTelefonica().setUnidadeAdministrativa(Util.setValueTO(this.getIdUnidadeAdministrativa(), UnidadeAdministrativaTO.class));
			
			negocioItemCiaTelefonica.incluir(this.getItemCiaTelefonica(), this.getUsuarioLogado());
			setMessage("msgSucessoIncluir", SATIEnumTipoMensagem.SUCESSO);
			
			this.setIdEmpresa(new String());
			this.setIdUnidadeAdministrativa(new String());
			
			return this.novo();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String excluir() {
		ItemCiaTelefonica negocioItemCiaTelefonica = getSATIBusinessFactory().createItemCiaTelefonica();
		try {
			negocioItemCiaTelefonica.excluir(this.getItemCiaTelefonica(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SATIEnumTipoMensagem.SUCESSO);
			
			return this.listar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String salvar() {
		ItemCiaTelefonica negocioItemCiaTelefonica = getSATIBusinessFactory().createItemCiaTelefonica();
		try {
			
			this.getItemCiaTelefonica().setEmpresa(Util.setValueTO(this.getIdEmpresa(), EmpresaTO.class));
			
			this.getItemCiaTelefonica().setUnidadeAdministrativa(Util.setValueTO(this.getIdUnidadeAdministrativa(), UnidadeAdministrativaTO.class));
			
			negocioItemCiaTelefonica.alterar(this.getItemCiaTelefonica(), this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", SATIEnumTipoMensagem.SUCESSO);

			return this.consultar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String editar() {
		ItemCiaTelefonica negocioItemCiaTelefonica = getSATIBusinessFactory().createItemCiaTelefonica();
		try {
			this.setItemCiaTelefonica(negocioItemCiaTelefonica.consultar(new ItemCiaTelefonicaTO(this.getItemCiaTelefonica().getId())));
			
			this.setIdEmpresa(String.valueOf(this.getItemCiaTelefonica().getEmpresa().getId()));
			
			this.setIdUnidadeAdministrativa(String.valueOf(this.getItemCiaTelefonica().getUnidadeAdministrativa().getId()));

			return "editarItemCiaTelefonica";
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String novo() {
		this.setItemCiaTelefonica(new ItemCiaTelefonicaTO());

		return "novoItemCiaTelefonica";
	}
	
	public String consultar() {
		ItemCiaTelefonica negocioItemCiaTelefonica = getSATIBusinessFactory().createItemCiaTelefonica();
		try {
			negocioItemCiaTelefonica.retirarObjetoSessao(this.getItemCiaTelefonica());
			this.setItemCiaTelefonica(negocioItemCiaTelefonica.consultar(new ItemCiaTelefonicaTO(this.getItemCiaTelefonica().getId())));

			return "consultarItemCiaTelefonica";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public ItemCiaTelefonicaTO getItemCiaTelefonica() {
		if (itemCiaTelefonica == null) {
			itemCiaTelefonica = new ItemCiaTelefonicaTO();
		}
		return itemCiaTelefonica;
	}

	public void setItemCiaTelefonica(ItemCiaTelefonicaTO itemCiaTelefonica) {
		this.itemCiaTelefonica = itemCiaTelefonica;
	}

	public Integer getItensPorPagina() {
		return itemsPorPagina;
	}

	public void setItensPorPagina(Integer itemsPorPagina) {
		this.itemsPorPagina = itemsPorPagina;
	}

	public List<TransferObject> getListaItemCiaTelefonicaDataTable() {
		if (listaItemCiaTelefonicaDataTable == null) {
			listaItemCiaTelefonicaDataTable = new ArrayList<TransferObject>();
		}
		return listaItemCiaTelefonicaDataTable;
	}

	public void setListaItemCiaTelefonicaDataTable(
			List<TransferObject> listaItemCiaTelefonicaDataTable) {
		this.listaItemCiaTelefonicaDataTable = listaItemCiaTelefonicaDataTable;
	}

	public List<SelectItem> getListaEmpresa() {
		if (listaEmpresa == null) {
			Empresa negocioEmpresa = getSATIBusinessFactory().createEmpresa();
			List<TransferObject> lista = negocioEmpresa.listar(new EmpresaTO(),"vchNomeFantasia");
			listaEmpresa = Util.createListSelectItens(lista, "id", "vchNomeFantasia");
		}
		return listaEmpresa;
	}

	public void setListaEmpresa(List<SelectItem> listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}

	public List<SelectItem> getListaUnidadeAdministrativa() {
		if (listaUnidadeAdministrativa == null) {
			UnidadeAdministrativa negocioUnidadeAdministrativa = getSATIBusinessFactory().createUnidadeAdministrativa();
			List<TransferObject> lista = negocioUnidadeAdministrativa.listar(new UnidadeAdministrativaTO(),"vchNome");
			listaUnidadeAdministrativa = Util.createListSelectItens(lista, "id", "vchNome");
		}
		return listaUnidadeAdministrativa;
	}

	public void setListaUnidadeAdministrativa(
			List<SelectItem> listaUnidadeAdministrativa) {
		this.listaUnidadeAdministrativa = listaUnidadeAdministrativa;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdUnidadeAdministrativa() {
		return idUnidadeAdministrativa;
	}

	public void setIdUnidadeAdministrativa(String idUnidadeAdministrativa) {
		this.idUnidadeAdministrativa = idUnidadeAdministrativa;
	}

	public String getNumeroContratoAgrupadorFiltro() {
		return numeroContratoAgrupadorFiltro;
	}

	public void setNumeroContratoAgrupadorFiltro(
			String numeroContratoAgrupadorFiltro) {
		this.numeroContratoAgrupadorFiltro = numeroContratoAgrupadorFiltro;
	}

	public String getNumeroTelefoneFiltro() {
		return numeroTelefoneFiltro;
	}

	public void setNumeroTelefoneFiltro(String numeroTelefoneFiltro) {
		this.numeroTelefoneFiltro = numeroTelefoneFiltro;
	}
	
}
