package br.org.oabgo.sati.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.org.oabgo.sati.negocio.controle.entidade.AcessoSistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.AcessoSistema;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Sistema;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Usuario;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumTipoMensagem;
import br.org.oabgo.sati.web.controle.SATIManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

public class AcessoSistemaBean extends SATIManagedBean {

	private static final long serialVersionUID = -2538947100656833564L;
	
	private AcessoSistemaTO acessoSistema;
	
	private Integer itensPorPagina = 15;
	
	private List<SelectItem> listaUsuario;
	
	private List<SelectItem> listaSistema;
	
	private List<TransferObject> listaAcessoSistemaDataTable;
	
	private String idUsuario;
	
	private String idSistema;
	
	private String ipFiltro;
	
	public String listar() {
		AcessoSistema negocioAcessoSistema = getSATIBusinessFactory().createAcessoSistema();
		try {
			AcessoSistemaTO acessoSistema = this.pegarValorFiltro();
			this.setListaAcessoSistemaDataTable(negocioAcessoSistema.listar(acessoSistema, "vchIp"));
			return "listarAcessoSistema";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public AcessoSistemaTO pegarValorFiltro() {
		AcessoSistemaTO acessoSistema = new AcessoSistemaTO();
		acessoSistema.setVchIp(Util.setString(this.getIpFiltro()));
		return acessoSistema;
	}
	
	public String incluir() {
		AcessoSistema negocioAcessoSistema = getSATIBusinessFactory().createAcessoSistema();
		try {
			
			if (this.getIdUsuario() != null) {
				this.getAcessoSistema().setUsuario(Util.setValueTO(this.getIdUsuario(), UsuarioTO.class));
			}
			
			if (this.getIdSistema() != null) {
				this.getAcessoSistema().setSistema(Util.setValueTO(this.getIdSistema(), SistemaTO.class));
			}
			
			negocioAcessoSistema.incluir(this.getAcessoSistema(), this.getUsuarioLogado());
			setMessage("msgSucessoIncluir", SATIEnumTipoMensagem.SUCESSO);
			
			this.setIdSistema(new String());
			this.setIdUsuario(new String());
			
			return this.novo();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String excluir() {
		AcessoSistema negocioAcessoSistema = getSATIBusinessFactory().createAcessoSistema();
		try {
			negocioAcessoSistema.excluir(this.getAcessoSistema(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SATIEnumTipoMensagem.SUCESSO);
			
			return this.listar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String salvar() {
		AcessoSistema negocioAcessoSistema = getSATIBusinessFactory().createAcessoSistema();
		try {
			
			if (this.getIdUsuario() != null) {
				this.getAcessoSistema().setUsuario(Util.setValueTO(this.getIdUsuario(), UsuarioTO.class));
			}
			
			if (this.getIdSistema() != null) {
				this.getAcessoSistema().setSistema(Util.setValueTO(this.getIdSistema(), SistemaTO.class));
			}
			negocioAcessoSistema.alterar(this.getAcessoSistema(), this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", SATIEnumTipoMensagem.SUCESSO);

			return this.consultar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String editar() {
		AcessoSistema negocioAcessoSistema = getSATIBusinessFactory().createAcessoSistema();
		try {
			this.setAcessoSistema(negocioAcessoSistema.consultar(new AcessoSistemaTO(this.getAcessoSistema().getId())));
			
			if (this.getAcessoSistema().getSistema() != null) {
				this.setIdSistema(String.valueOf(this.getAcessoSistema().getSistema().getId()));
			} else {
				this.setIdSistema(null);
			}
			
			if (this.getAcessoSistema().getUsuario() != null) {
				this.setIdUsuario(String.valueOf(this.getAcessoSistema().getUsuario().getId()));
			} else {
				this.setIdUsuario(null);
			}
			
			return "editarAcessoSistema";
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String novo() {
		this.setAcessoSistema( new AcessoSistemaTO());

		return "novoAcessoSistema";
	}
	
	public String consultar() {
		AcessoSistema negocioAcessoSistema = getSATIBusinessFactory().createAcessoSistema();
		try {
			negocioAcessoSistema.retirarObjetoSessao(this.getAcessoSistema());
			this.setAcessoSistema(negocioAcessoSistema.consultar(new AcessoSistemaTO(this.getAcessoSistema().getId())));

			return "consultarAcessoSistema";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public void setAcessoSistema(AcessoSistemaTO acessoSistema) {
		this.acessoSistema = acessoSistema;
	}

	public AcessoSistemaTO getAcessoSistema() {
		if (acessoSistema == null) {
			acessoSistema = new AcessoSistemaTO();
		}
		return acessoSistema;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setListaUsuario(List<SelectItem> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public List<SelectItem> getListaUsuario() {
		if (listaUsuario == null) {
			Usuario negocioUsuario = getSATIBusinessFactory().createUsuario();
			List<TransferObject> lista = negocioUsuario.listar(new UsuarioTO(),
					"vchNome");
			this.listaUsuario = Util.createListSelectItens(lista, "id",
					"vchNome");
		}
		return listaUsuario;
	}

	public void setListaSistema(List<SelectItem> listaSistema) {
		this.listaSistema = listaSistema;
	}

	public List<SelectItem> getListaSistema() {
		if (listaSistema == null) {
			Sistema negocioSistema = getSATIBusinessFactory().createSistema();
			List<TransferObject> lista = negocioSistema.listar(new SistemaTO(),
					"vchSigla");
			this.listaSistema = Util.createListSelectItens(lista, "id",
					"vchSigla");
		}
		return listaSistema;
	}

	public void setListaAcessoSistemaDataTable(List<TransferObject> listaAcessoSistemaDataTable) {
		this.listaAcessoSistemaDataTable = listaAcessoSistemaDataTable;
	}

	public List<TransferObject> getListaAcessoSistemaDataTable() {
		if (listaAcessoSistemaDataTable == null) {
			listaAcessoSistemaDataTable = new ArrayList<TransferObject>();
		}
		return listaAcessoSistemaDataTable;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	public String getIdSistema() {
		return idSistema;
	}

	public void setIpFiltro(String ipFiltro) {
		this.ipFiltro = ipFiltro;
	}

	public String getIpFiltro() {
		return ipFiltro;
	}

}
