package br.org.oabgo.sati.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.org.oabgo.sati.negocio.controle.entidade.AtendimentoAutenticacaoAdslTO;
import br.org.oabgo.sati.negocio.controle.entidade.AutenticacaoAdslTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.AtendimentoAutenticacaoAdsl;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.AutenticacaoAdsl;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumTipoMensagem;
import br.org.oabgo.sati.web.controle.SATIManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

public class AtendimentoAutenticacaoAdslBean extends SATIManagedBean {

	private static final long serialVersionUID = -529500680649584065L;
	
	private AtendimentoAutenticacaoAdslTO atendimentoAutenticacaoAdsl;
	
	private Integer itensPorPagina = 15;
	
	private List<TransferObject> listaAtendimentoAutenticacaoAdslDataTable;
	
	private List<SelectItem> listaAutenticacaoAdsl;
	
	private String idAutenticacaoADSL;
	
	private String protocoloFiltro;
	
	private String assuntoFiltro;
	
	public String listar() {
		AtendimentoAutenticacaoAdsl negocioAtendimentoAutenticacaoAdsl = getSATIBusinessFactory().createAtendimentoAutenticacaoAdsl();
		try {
			AtendimentoAutenticacaoAdslTO atendimentoAutenticacaoAdsl = this.pegarValorFiltro();
			this.setListaAtendimentoAutenticacaoAdslDataTable(negocioAtendimentoAutenticacaoAdsl.listar(atendimentoAutenticacaoAdsl, "intProtocolo"));
			this.setListaAtendimentoAutenticacaoAdslDataTable(negocioAtendimentoAutenticacaoAdsl.listar(atendimentoAutenticacaoAdsl, "vchAssunto"));
			return "listarAtendimentoAutenticacaoAdsl";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public AtendimentoAutenticacaoAdslTO pegarValorFiltro() {
		AtendimentoAutenticacaoAdslTO atendimentoAutenticacaoAdsl = new AtendimentoAutenticacaoAdslTO();
		if (this.getProtocoloFiltro() != null && !this.getProtocoloFiltro().isEmpty()) {
			atendimentoAutenticacaoAdsl.setIntProtocolo(Integer.parseInt(this.getProtocoloFiltro()));			
		}
		atendimentoAutenticacaoAdsl.setVchAssunto(Util.setString(this.getAssuntoFiltro()));
		return atendimentoAutenticacaoAdsl;
	}
	
	public String incluir() {
		AtendimentoAutenticacaoAdsl negocioAtendimentoAutenticacaoAdsl = getSATIBusinessFactory().createAtendimentoAutenticacaoAdsl();
		try {
			
			this.getAtendimentoAutenticacaoAdsl().setAutenticacaoAdsl(Util.setValueTO(this.getIdAutenticacaoADSL(), AutenticacaoAdslTO.class));
			
			negocioAtendimentoAutenticacaoAdsl.incluir(this.getAtendimentoAutenticacaoAdsl(), this.getUsuarioLogado());
			setMessage("msgSucessoIncluir", SATIEnumTipoMensagem.SUCESSO);
			
			this.setIdAutenticacaoADSL(new String());
			
			return this.novo();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String excluir() {
		AtendimentoAutenticacaoAdsl negocioAtendimentoAutenticacaoAdsl = getSATIBusinessFactory().createAtendimentoAutenticacaoAdsl();
		try {
			negocioAtendimentoAutenticacaoAdsl.excluir(this.getAtendimentoAutenticacaoAdsl(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SATIEnumTipoMensagem.SUCESSO);
			
			return this.listar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String salvar() {
		AtendimentoAutenticacaoAdsl negocioAtendimentoAutenticacaoAdsl = getSATIBusinessFactory().createAtendimentoAutenticacaoAdsl();
		try {
			
			this.getAtendimentoAutenticacaoAdsl().setAutenticacaoAdsl(Util.setValueTO(this.getIdAutenticacaoADSL(), AutenticacaoAdslTO.class));
			
			negocioAtendimentoAutenticacaoAdsl.alterar(this.getAtendimentoAutenticacaoAdsl(), this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", SATIEnumTipoMensagem.SUCESSO);
			
			this.setIdAutenticacaoADSL(new String());

			return this.consultar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String editar() {
		AtendimentoAutenticacaoAdsl negocioAtendimentoAutenticacaoAdsl = getSATIBusinessFactory().createAtendimentoAutenticacaoAdsl();
		try {
			this.setAtendimentoAutenticacaoAdsl(negocioAtendimentoAutenticacaoAdsl.consultar(new AtendimentoAutenticacaoAdslTO(this.getAtendimentoAutenticacaoAdsl().getId())));
			
			this.setIdAutenticacaoADSL(String.valueOf(this.getAtendimentoAutenticacaoAdsl().getAutenticacaoAdsl().getId()));

			return "editarAtendimentoAutenticacaoAdsl";
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String novo() {
		this.setAtendimentoAutenticacaoAdsl(new AtendimentoAutenticacaoAdslTO());

		return "novoAtendimentoAutenticacaoAdsl";
	}
	
	public String consultar() {
		AtendimentoAutenticacaoAdsl negocioAtendimentoAutenticacaoAdsl = getSATIBusinessFactory().createAtendimentoAutenticacaoAdsl();
		try {
			negocioAtendimentoAutenticacaoAdsl.retirarObjetoSessao(this.getAtendimentoAutenticacaoAdsl());
			this.setAtendimentoAutenticacaoAdsl(negocioAtendimentoAutenticacaoAdsl.consultar(new AtendimentoAutenticacaoAdslTO(this.getAtendimentoAutenticacaoAdsl().getId())));

			return "consultarAtendimentoAutenticacaoAdsl";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public AtendimentoAutenticacaoAdslTO getAtendimentoAutenticacaoAdsl() {
		if (atendimentoAutenticacaoAdsl == null) {
			atendimentoAutenticacaoAdsl = new AtendimentoAutenticacaoAdslTO();
		}
		return atendimentoAutenticacaoAdsl;
	}

	public void setAtendimentoAutenticacaoAdsl(
			AtendimentoAutenticacaoAdslTO atendimentoAutenticacaoAdsl) {
		this.atendimentoAutenticacaoAdsl = atendimentoAutenticacaoAdsl;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public List<TransferObject> getListaAtendimentoAutenticacaoAdslDataTable() {
		if (listaAtendimentoAutenticacaoAdslDataTable == null) {
			listaAtendimentoAutenticacaoAdslDataTable = new ArrayList<TransferObject>();
		}
		return listaAtendimentoAutenticacaoAdslDataTable;
	}

	public void setListaAtendimentoAutenticacaoAdslDataTable(
			List<TransferObject> listaAtendimentoAutenticacaoAdslDataTable) {
		this.listaAtendimentoAutenticacaoAdslDataTable = listaAtendimentoAutenticacaoAdslDataTable;
	}

	public List<SelectItem> getListaAutenticacaoAdsl() {
		if (listaAutenticacaoAdsl == null) {
			AutenticacaoAdsl negocioAutenticacaoAdsl = getSATIBusinessFactory().createAutenticacaoAdsl();
			List<TransferObject> lista = negocioAutenticacaoAdsl.listar(new AutenticacaoAdslTO(), "vchContaEmail");
			listaAutenticacaoAdsl = Util.createListSelectItens(lista, "id", "vchContaEmail");
		}
		return listaAutenticacaoAdsl;
	}

	public void setListaAutenticacaoAdsl(List<SelectItem> listaAutenticacaoAdsl) {
		this.listaAutenticacaoAdsl = listaAutenticacaoAdsl;
	}

	public String getIdAutenticacaoADSL() {
		return idAutenticacaoADSL;
	}

	public void setIdAutenticacaoADSL(String idAutenticacaoADSL) {
		this.idAutenticacaoADSL = idAutenticacaoADSL;
	}

	public String getProtocoloFiltro() {
		return protocoloFiltro;
	}

	public void setProtocoloFiltro(String protocoloFiltro) {
		this.protocoloFiltro = protocoloFiltro;
	}

	public String getAssuntoFiltro() {
		return assuntoFiltro;
	}

	public void setAssuntoFiltro(String assuntoFiltro) {
		this.assuntoFiltro = assuntoFiltro;
	}
	
}
