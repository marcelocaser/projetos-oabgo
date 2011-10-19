package br.org.oabgo.siged.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.org.oabgo.siged.negocio.controle.SIGEDConstantes;
import br.org.oabgo.siged.negocio.controle.entidade.RelatorTO;
import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.DocumentoEletronico;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.Relator;
import br.org.oabgo.siged.negocio.enumerador.SIGEDEnumTipoMensagem;
import br.org.oabgo.siged.web.controle.SIGEDManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

public class RelatorBean extends SIGEDManagedBean {

	private static final long serialVersionUID = 4344808359058318915L;
	
	private RelatorTO relator;
	
	private Integer itensPorPagina = 15;
	
	private Integer totalRelatoresCadastrados = 0;
	
	private List<TransferObject> listaRelatorDataTable;
	
	private ArrayList<RelatorTO> relatores;
	
	private List<SelectItem> listaRelator;
	
	private List<SelectItem> listaRelatorPickList;
	
	private List<String> listaRelatorSelecionado;
	
	private String relatorFiltro;
	
	public String listar() {
		Relator negocioRelator = getSIGEDBusinessFactory().createRelator();
		try {
			RelatorTO relator = this.pegarValorFiltro();
			this.setListaRelatorDataTable(negocioRelator.listar(relator, "vchNome"));
			return "listarRelator";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public RelatorTO pegarValorFiltro() {
		RelatorTO relator = new RelatorTO();
		relator.setVchNome(Util.setString(this.getRelatorFiltro()));
		return relator;
	}
	
	public String consultaDeDocumento() {
		Relator negocioRelator = getSIGEDBusinessFactory().createRelator();
		DocumentoEletronico negocioDocumentoEletronico = getSIGEDBusinessFactory().createDocumentoEletronico();
		DocumentoEletronicoBean documentoEletronicoBean = (DocumentoEletronicoBean) getManagedBean("documentoEletronicoBean");
		try {
			negocioRelator.retirarObjetoSessao(this.getRelator());
			this.setRelator(negocioRelator.consultar(this.getRelator()));
			
			if (!this.getRelator().getListaDocumentoEletronico().isEmpty()) {
				documentoEletronicoBean.setListaDocumentoEletronicoArquivoDataTable(negocioDocumentoEletronico.listar
						(new ArrayList<TransferObject>(this.getRelator().getListaDocumentoEletronico()), 
								(UsuarioTO) this.getParamSession(SIGEDConstantes.USUARIO_LOGADO)));
			}
			
			if (documentoEletronicoBean.getListaDocumentoEletronicoArquivoDataTable().isEmpty()) {
				setMessage("sigedDocumentoEletronicoTextoPesquisaNaoLocalizado", SIGEDEnumTipoMensagem.ERRO);
			}

		} catch (Exception e) {
			return tratarExcecao(e);
		}
		return null;
	}
	
	public RelatorTO getRelator() {
		if (relator == null) {
			relator = new RelatorTO();
		}
		return relator;
	}

	public void setRelator(RelatorTO relator) {
		this.relator = relator;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public Integer getTotalRelatoresCadastrados() {
		if (this.getListaRelatorSelecionado().size() > 0) {
			totalRelatoresCadastrados = this.getListaRelatorSelecionado().size();
		}
		
		if (this.getRelatores().size() > 0) {
			totalRelatoresCadastrados = this.getRelatores().size();
		}
		
		return totalRelatoresCadastrados;
	}

	public void setTotalRelatoresCadastrados(Integer totalRelatoresCadastrados) {
		this.totalRelatoresCadastrados = totalRelatoresCadastrados;
	}

	public List<TransferObject> getListaRelatorDataTable() {
		return listaRelatorDataTable;
	}

	public void setListaRelatorDataTable(List<TransferObject> listaRelatorDataTable) {
		this.listaRelatorDataTable = listaRelatorDataTable;
	}

	public ArrayList<RelatorTO> getRelatores() {
		if (relatores == null) {
			relatores = new ArrayList<RelatorTO>();
		}
		return relatores;
	}

	public void setRelatores(ArrayList<RelatorTO> relatores) {
		this.relatores = relatores;
	}

	public List<SelectItem> getListaRelator() {
		if (listaRelator == null) {
			Relator negocioRelator = getSIGEDBusinessFactory().createRelator();
			List<TransferObject> lista = negocioRelator.listar(new RelatorTO(), "vchNome");
			listaRelator = Util.createListSelectItens(lista, "id", "vchNome");
		}
		return listaRelator;
	}

	public void setListaRelator(List<SelectItem> listaRelator) {
		this.listaRelator = listaRelator;
	}
	
	public List<SelectItem> getListaRelatorPickList() {
		if (listaRelatorPickList == null) {
			Relator negocioRelator = getSIGEDBusinessFactory().createRelator();
			List<TransferObject> lista = negocioRelator.listar(new RelatorTO(), "vchNome");
			listaRelatorPickList = Util.createListStringSelecionada(lista, "id", "vchNome");
		}
		return listaRelatorPickList;
	}

	public void setListaRelatorPickList(List<SelectItem> listaRelatorPickList) {
		this.listaRelatorPickList = listaRelatorPickList;
	}

	public List<String> getListaRelatorSelecionado() {
		if (listaRelatorSelecionado == null) {
			listaRelatorSelecionado = new ArrayList<String>();
		}
		return listaRelatorSelecionado;
	}

	public void setListaRelatorSelecionado(List<String> listaRelatorSelecionado) {
		this.listaRelatorSelecionado = listaRelatorSelecionado;
	}

	public String getRelatorFiltro() {
		return relatorFiltro;
	}
	
	public void setRelatorFiltro(String relatorFiltro) {
		this.relatorFiltro = relatorFiltro;
	}

}
