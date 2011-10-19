package br.org.oabgo.sati.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.TipoMenuSistemaTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Sistema;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.TipoMenuSistema;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumTipoMensagem;
import br.org.oabgo.sati.web.controle.SATIManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

public class TipoMenuSistemaBean extends SATIManagedBean {

	private static final long serialVersionUID = -2359924319166327894L;

	private TipoMenuSistemaTO tipoMenuSistema;

	private Integer itensPorPagina = 15;

	private List<TransferObject> listaTipoMenuSistemaDataTable;
	
	private List<SelectItem> listaSistema;
	
	private String idSistema;

	private String descricaoFiltro;

	private String nomeFiltro;
	
	public String listar() {
		TipoMenuSistema negocioTipoMenuSistema = getSATIBusinessFactory().createTipoMenuSistema();
		try {
			TipoMenuSistemaTO tipoMenuSistema = this.pegarValorFiltro();
			this.setListaTipoMenuSistemaDataTable(negocioTipoMenuSistema.listar(tipoMenuSistema, "vchNome"));
			this.setListaTipoMenuSistemaDataTable(negocioTipoMenuSistema.listar(tipoMenuSistema, "vchDescricao"));
			return "listarTipoMenuSistema";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public TipoMenuSistemaTO pegarValorFiltro() {
		TipoMenuSistemaTO tipoMenuSistema = new TipoMenuSistemaTO();
		tipoMenuSistema.setVchDescricao(Util.setString(this.getDescricaoFiltro()));
		tipoMenuSistema.setVchNome(Util.setString(this.getNomeFiltro()));
		return tipoMenuSistema;
	}
	
	public String incluir() {
		TipoMenuSistema negocioTipoMenuSistema = getSATIBusinessFactory().createTipoMenuSistema();
		try {
			
			this.getTipoMenuSistema().setSistema(Util.setValueTO(this.getIdSistema(), SistemaTO.class));
			
			negocioTipoMenuSistema.incluir(this.getTipoMenuSistema(), this.getUsuarioLogado());
			setMessage("msgSucessoIncluir", SATIEnumTipoMensagem.SUCESSO);
			
			this.setIdSistema(new String());
			
			return this.novo();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String excluir() {
		TipoMenuSistema negocioTipoMenuSistema = getSATIBusinessFactory().createTipoMenuSistema();
		try {
			negocioTipoMenuSistema.excluir(this.getTipoMenuSistema(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SATIEnumTipoMensagem.SUCESSO);
			
			return this.listar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String salvar() {
		TipoMenuSistema negocioTipoMenuSistema = getSATIBusinessFactory().createTipoMenuSistema();
		try {
			
			this.getTipoMenuSistema().setSistema(Util.setValueTO(this.getIdSistema(), SistemaTO.class));
			
			negocioTipoMenuSistema.alterar(this.getTipoMenuSistema(), this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", SATIEnumTipoMensagem.SUCESSO);

			return this.consultar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String editar() {
		TipoMenuSistema negocioTipoMenuSistema = getSATIBusinessFactory().createTipoMenuSistema();
		try {
			this.setTipoMenuSistema(negocioTipoMenuSistema.consultar(new TipoMenuSistemaTO(this.getTipoMenuSistema().getId())));
		
			this.setIdSistema(String.valueOf(this.getTipoMenuSistema().getSistema().getId()));

			return "editarTipoMenuSistema";
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String novo() {
		this.setTipoMenuSistema(new TipoMenuSistemaTO());

		return "novoTipoMenuSistema";
	}
	
	public String consultar() {
		TipoMenuSistema negocioTipoMenuSistema = getSATIBusinessFactory().createTipoMenuSistema();
		try {
			negocioTipoMenuSistema.retirarObjetoSessao(this.getTipoMenuSistema());
			this.setTipoMenuSistema(negocioTipoMenuSistema.consultar(new TipoMenuSistemaTO(this.getTipoMenuSistema().getId())));

			return "consultarTipoMenuSistema";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public TipoMenuSistemaTO getTipoMenuSistema() {
		if (tipoMenuSistema == null) {
			tipoMenuSistema = new TipoMenuSistemaTO();
		}
		return tipoMenuSistema;
	}

	public void setTipoMenuSistema(TipoMenuSistemaTO tipoMenuSistema) {
		this.tipoMenuSistema = tipoMenuSistema;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public List<TransferObject> getListaTipoMenuSistemaDataTable() {
		if (listaTipoMenuSistemaDataTable == null) {
			listaTipoMenuSistemaDataTable = new ArrayList<TransferObject>();
		}
		return listaTipoMenuSistemaDataTable;
	}

	public void setListaTipoMenuSistemaDataTable(
			List<TransferObject> listaTipoMenuSistemaDataTable) {
		this.listaTipoMenuSistemaDataTable = listaTipoMenuSistemaDataTable;
	}
	
	public List<SelectItem> getListaSistema() {
		if (listaSistema == null) {
			Sistema negocioSistema = getSATIBusinessFactory().createSistema();
			List<TransferObject> lista = negocioSistema.listar(new SistemaTO(),"vchNome");
			listaSistema = Util.createListSelectItens(lista, "id", "vchNome");
		}
		return listaSistema;
	}

	public void setListaSistema(List<SelectItem> listaSistema) {
		this.listaSistema = listaSistema;
	}
	
	public String getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}

	public String getNomeFiltro() {
		return nomeFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}
	
}
