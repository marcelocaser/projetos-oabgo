package br.org.oabgo.siged.web.controle.bean;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.richfaces.model.UploadItem;

import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.Usuario;
import br.org.oabgo.siged.negocio.enumerador.SIGEDEnumTipoMensagem;
import br.org.oabgo.siged.web.controle.SIGEDManagedBean;
import core.dao.TransferObject;
import core.richfaces.datahelper.SessionDataHelper;
import core.utilitario.Util;

public class UsuarioBean extends SIGEDManagedBean {

	private static final long serialVersionUID = 1336108950785391584L;

	private Integer itensPorPagina = 15;

	private List<SelectItem> listaUsuario;

	private UsuarioTO usuario;
	
	private List<TransferObject> listaUsuarioDataTable;

	private String userNameFiltro;

	private String nomeFiltro;
	
	private String senhaAtual;
	
	private String confirmaSenha;
	
	private UploadedFile fileFoto;
	
	public String listar() {
		Usuario negocioUsuario = getSIGEDBusinessFactory().createUsuario();
		try {
			UsuarioTO usuario = this.pegarValorFiltro();
			this.setListaUsuarioDataTable(negocioUsuario.listar(usuario));

			return "listarUsuario";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public UsuarioTO pegarValorFiltro() {
		UsuarioTO usuario = new UsuarioTO();
		usuario.setVchLogin(Util.setString(this.getUserNameFiltro()));
		usuario.setVchNome(Util.setString(this.getNomeFiltro()));
		return usuario;
	}

	public String incluir() {
		Usuario negocioUsuario = getSIGEDBusinessFactory().createUsuario();
		try {
			
			if (this.getFileFoto() != null) {
				this.getUsuario().setImgFoto(this.getFileFoto().getBytes());
			}
			
			negocioUsuario.incluir(this.getUsuario(), this.getUsuarioLogado(), this.getConfirmaSenha());
			
			setMessage("msgSucessoIncluir", SIGEDEnumTipoMensagem.SUCESSO);
			
			return this.listar();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public String excluir() {
		Usuario negocioUsuario = getSIGEDBusinessFactory().createUsuario();
		try {
			negocioUsuario.excluir(this.getUsuario(), this.getUsuarioLogado());
			setMessage("msgSucessoExcluir", SIGEDEnumTipoMensagem.SUCESSO);
			return this.listar();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public String salvar() {
		Usuario negocioUsuario = getSIGEDBusinessFactory().createUsuario();
		try {
			
			if (this.getFileFoto() != null) {
				this.getUsuario().setImgFoto(this.getFileFoto().getBytes());
			}

			negocioUsuario.alterar(this.getUsuario(), this.getUsuarioLogado());
			setMessage("msgSucessoAlterar",SIGEDEnumTipoMensagem.SUCESSO);
			
			return this.consultar();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public String editar() {
		Usuario negocioUsuario = getSIGEDBusinessFactory().createUsuario();
		try {
			this.setUsuario(negocioUsuario.consultar(new UsuarioTO(this.getUsuario().getId())));

			return "editarUsuario";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public String novo() {
		this.setUsuario(new UsuarioTO());
		
		return "novoUsuario";
	}

	public String consultar() {
		Usuario negocioUsuario = getSIGEDBusinessFactory().createUsuario();
		try {
			negocioUsuario.retirarObjetoSessao(this.getUsuario());
			this.setUsuario(negocioUsuario.consultar(new UsuarioTO(this.getUsuario().getId())));
			
			return "consultarUsuario";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public void abrirFoto(OutputStream out, Object data) {
		
		Object objectData = SessionDataHelper.getDataByKey(data);
		
		Usuario negocioUsuario = getSIGEDBusinessFactory().createUsuario();
		
		if (objectData instanceof UsuarioTO) {
			
			UsuarioTO usuario = (UsuarioTO) objectData;
			
			negocioUsuario.retirarObjetoSessao(usuario);
			
			try {
				out.write(usuario.getImgFoto() != null ? usuario.getImgFoto() : Util.bytesFromFile(new File(getRequest().getSession().getServletContext().getRealPath("/imagens/icons/iconeFoto.png"))));
			} catch (IOException e) {
				tratarExcecao(e);
			}
			
		}
		
		if (data == null || data instanceof UploadItem) {
			try {
				out.write(Util.bytesFromFile(new File(getRequest().getSession().getServletContext().getRealPath("/imagens/icons/iconeFoto.png"))));
			} catch (IOException e) {
				tratarExcecao(e);
			}
		}
		
	}
	
	public void excluirFoto() {
		this.getUsuario().setImgFoto(null);
	}
	
	public List<SelectItem> getListaUsuario() {
		if (listaUsuario == null) {
			Usuario negocio = getSIGEDBusinessFactory().createUsuario();
			List<TransferObject> lista = negocio.listar(new UsuarioTO(), "vchNome");
			this.listaUsuario = Util.createListSelectItens(lista, "id", "vchNome");
		}
		return listaUsuario;
	}

	public void setListaUsuario(List<SelectItem> listaUsuario) {
		this.listaUsuario = listaUsuario;
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

	public List<TransferObject> getListaUsuarioDataTable() {
		return listaUsuarioDataTable;
	}

	public void setListaUsuarioDataTable(List<TransferObject> listaUsuarioDataTable) {
		this.listaUsuarioDataTable = listaUsuarioDataTable;
	}

	public String getUserNameFiltro() {
		return userNameFiltro;
	}

	public void setUserNameFiltro(String userNameFiltro) {
		this.userNameFiltro = userNameFiltro;
	}

	public String getNomeFiltro() {
		return nomeFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}
	
	public String getSenhaAtual() {
		return senhaAtual;
	}
	
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}
	
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public void setFileFoto(UploadedFile fileFoto) {
		this.fileFoto = fileFoto;
	}

	public UploadedFile getFileFoto() {
		return fileFoto;
	}
}
