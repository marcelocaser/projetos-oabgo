package br.org.oabgo.siged.web.controle.bean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.org.oabgo.siged.negocio.controle.SIGEDConstantes;
import br.org.oabgo.siged.negocio.controle.entidade.DocumentoEletronicoArquivoTO;
import br.org.oabgo.siged.negocio.controle.entidade.DocumentoEletronicoTO;
import br.org.oabgo.siged.negocio.controle.entidade.RelatorTO;
import br.org.oabgo.siged.negocio.controle.entidade.TipoDocumentoTO;
import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.DocumentoEletronico;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.DocumentoEletronicoArquivo;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.Relator;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.TipoDocumento;
import br.org.oabgo.siged.negocio.enumerador.SIGEDEnumTipoMensagem;
import br.org.oabgo.siged.web.controle.SIGEDManagedBean;
import core.dao.TransferObject;
import core.utilitario.Data;
import core.utilitario.Numero;
import core.utilitario.Util;

public class DocumentoEletronicoBean extends SIGEDManagedBean {

	private static final long serialVersionUID = -3381018860572563841L;

	private DocumentoEletronicoTO documentoEletronico;

	private DocumentoEletronicoArquivoTO documentoEletronicoArquivo;
	
	private TipoDocumentoTO tipoDocumento;

	private Integer itensPorPagina = 15;

	private Integer totalArquivosEnviados = 0;
	
	private Integer numeroSequencialProcesso = 0;
	
	private Boolean validacaoProcessoJaExiste;
	
	private Boolean validacaoNovoArquivo;

	private List<TransferObject> listaDocumentoEletronicoDataTable;
	
	private List<TransferObject> listaDocumentoEletronicoArquivoDataTable;

	private List<SelectItem> listaTipoDocumento;

	private UploadItem item;

	private StringBuffer validacaoArquivoAnexo;
	
	private StringBuffer validacaoRelatores;

	private String idTipoDocumento;

	private String idDocumentoEletronico;
	
	private String paginaSelecionada;
	
	private String auxDescricaoTipoDocumento;

	private String numeroFiltro;
	
	private Date dataAutuacaoFiltro;
	
	private Date dataDecisaoFiltro;

	private String relatorFiltro;
	
	private String conteudoFiltro;
	
	private String anoDecisaoDeFiltro;
	
	private String anoDecisaoAteFiltro;

	public String listar() {
		DocumentoEletronico negocioDocumentoEletronico = getSIGEDBusinessFactory().createDocumentoEletronico();
		try {
			DocumentoEletronicoTO documentoEletronico = this.pegarValorFiltro();
			this.setListaDocumentoEletronicoDataTable(negocioDocumentoEletronico.listar(documentoEletronico, "datDecisao"));
			return "listarDocumentoEletronico";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public DocumentoEletronicoTO pegarValorFiltro() {
		DocumentoEletronicoTO documentoEletronico = new DocumentoEletronicoTO();
		documentoEletronico.setVchNumeroProcesso(Util.setString(this.getNumeroFiltro()));
		documentoEletronico.setDatProcesso(this.getDataAutuacaoFiltro());
		documentoEletronico.setDatDecisao(this.getDataDecisaoFiltro());
		return documentoEletronico;
	}

	public String incluir() {
		DocumentoEletronico negocioDocumentoEletronico = getSIGEDBusinessFactory().createDocumentoEletronico();
		DocumentoEletronicoArquivo negocioDocumentoEletronicoArquivo = getSIGEDBusinessFactory().createDocumentoEletronicoArquivo();
		Relator negocioRelator = getSIGEDBusinessFactory().createRelator();
		RelatorBean relatorBean = (RelatorBean) getManagedBean("relatorBean");
		try {
			
			if (this.validaListaRelatorSelecionado() != null) {
				return this.validaListaRelatorSelecionado();
			}
			
			//Adiciona os Relatores na entidade DocumentoEletronicoTO
			//e persistindo também na table tbAxDocumentosEletronicosRelatores
			if (!relatorBean.getListaRelatorSelecionado().isEmpty()) {
				for (Iterator<String> iterator = relatorBean.getListaRelatorSelecionado().iterator(); iterator.hasNext();) {
					Long idRelator = Long.parseLong(iterator.next());
					relatorBean.setRelator(negocioRelator.consultar(new RelatorTO(idRelator)));
					relatorBean.getRelatores().add(relatorBean.getRelator());
				}
				this.getDocumentoEletronico().setListaRelator(new  HashSet<RelatorTO>(relatorBean.getRelatores()));
			}
			
			negocioDocumentoEletronico.incluir(this.getDocumentoEletronico(), this.getUsuarioLogado());
			
			if (!this.getListaDocumentoEletronicoArquivoDataTable().isEmpty()) {
				for (Iterator<TransferObject> iterator = this.getListaDocumentoEletronicoArquivoDataTable().iterator(); iterator.hasNext();) {
					DocumentoEletronicoArquivoTO documentoEletronicoArquivo = (DocumentoEletronicoArquivoTO) iterator.next();
					documentoEletronicoArquivo.setDocumentoEletronico(this.getDocumentoEletronico());
					this.setDocumentoEletronicoArquivo(documentoEletronicoArquivo);
					negocioDocumentoEletronicoArquivo.incluir(this.getDocumentoEletronicoArquivo(), this.getUsuarioLogado());
				}
			}
			
			setMessage("msgSucessoIncluir", SIGEDEnumTipoMensagem.SUCESSO);

			this.getListaDocumentoEletronicoArquivoDataTable().clear();
			//relatorBean.getListaRelatorSelecionado().clear();
			this.setTotalArquivosEnviados(0);
			this.setValidacaoArquivoAnexo(null);
			this.setValidacaoRelatores(null);
			this.setIdTipoDocumento(null);
			this.setValidacaoProcessoJaExiste(Boolean.TRUE);

			return this.novo();

		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public String excluir() {
		DocumentoEletronico negocioDocumentoEletronico = getSIGEDBusinessFactory().createDocumentoEletronico();
		try {
			negocioDocumentoEletronico.excluir(this.getDocumentoEletronico(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SIGEDEnumTipoMensagem.SUCESSO);

			return this.listar();

		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public String salvar() {
		DocumentoEletronico negocioDocumentoEletronico = getSIGEDBusinessFactory().createDocumentoEletronico();
		DocumentoEletronicoArquivo negocioDocumentoEletronicoArquivo = getSIGEDBusinessFactory().createDocumentoEletronicoArquivo();
		Relator negocioRelator = getSIGEDBusinessFactory().createRelator();
		RelatorBean relatorBean = (RelatorBean) getManagedBean("relatorBean");
		try {
			
			if (this.validaListaRelatorSelecionado() != null) {
				return this.validaListaRelatorSelecionado();
			}
			
			//Adiciona os relatores na entidade DocumentoEletronicoTO
			//e persistindo também na table tbAxDocumentosEletronicosRelatores
			if (!relatorBean.getListaRelatorSelecionado().isEmpty()) {
				for (Iterator<String> iterator = relatorBean.getListaRelatorSelecionado().iterator(); iterator.hasNext();) {
					Long idRelator = Long.parseLong(iterator.next());
					relatorBean.setRelator(negocioRelator.consultar(new RelatorTO(idRelator)));
					relatorBean.getRelatores().add(relatorBean.getRelator());
				}
				this.getDocumentoEletronico().setListaRelator(new  HashSet<RelatorTO>(relatorBean.getRelatores()));
			}
			
			negocioDocumentoEletronico.alterar(this.getDocumentoEletronico(), this.getUsuarioLogado());
			
			if (!this.getListaDocumentoEletronicoArquivoDataTable().isEmpty()) {
				for (Iterator<TransferObject> iterator = this.getListaDocumentoEletronicoArquivoDataTable().iterator(); iterator.hasNext();) {
					DocumentoEletronicoArquivoTO documentoEletronicoArquivo = (DocumentoEletronicoArquivoTO) iterator.next();
					if (documentoEletronicoArquivo.getId() == null) {
						documentoEletronicoArquivo.setDocumentoEletronico(new DocumentoEletronicoTO(this.getDocumentoEletronico().getId()));
						this.setDocumentoEletronicoArquivo(documentoEletronicoArquivo);
						negocioDocumentoEletronicoArquivo.incluir(this.getDocumentoEletronicoArquivo(), getUsuarioLogado());
					} else {
						this.setDocumentoEletronicoArquivo(documentoEletronicoArquivo);
						negocioDocumentoEletronicoArquivo.alterar(this.getDocumentoEletronicoArquivo(), getUsuarioLogado());
					}
				}
			}
			
			setMessage("msgSucessoAlterar", SIGEDEnumTipoMensagem.SUCESSO);

			return this.consultar();

		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public String editar() {
		DocumentoEletronico negocioDocumentoEletronico = getSIGEDBusinessFactory().createDocumentoEletronico();
		try {
			this.setDocumentoEletronico(negocioDocumentoEletronico.consultar(new DocumentoEletronicoTO(this.getDocumentoEletronico().getId())));

			return "editarDocumentoEletronico";

		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public String novo() {
		this.setDocumentoEletronico(new DocumentoEletronicoTO());
		this.setDocumentoEletronicoArquivo(new DocumentoEletronicoArquivoTO());

		return "novoDocumentoEletronico";
	}

	public String consultar() {
		DocumentoEletronico negocioDocumentoEletronico = getSIGEDBusinessFactory().createDocumentoEletronico();
		//DocumentoEletronicoArquivo negocioDocumentoEletronicoArquivo = getSIGEDBusinessFactory().createDocumentoEletronicoArquivo();
		RelatorBean relatorBean = (RelatorBean) getManagedBean("relatorBean");
		try {
			negocioDocumentoEletronico.retirarObjetoSessao(this.getDocumentoEletronico());
			this.setDocumentoEletronico(negocioDocumentoEletronico.consultar(new DocumentoEletronicoTO(this.getDocumentoEletronico().getId())));
			
			if (!this.getDocumentoEletronico().getListaRelator().isEmpty()) {
				relatorBean.setRelatores(new ArrayList<RelatorTO>(this.getDocumentoEletronico().getListaRelator()));
				List<String> relatores = new ArrayList<String>();
				for (Iterator<RelatorTO> iterator = relatorBean.getRelatores().iterator(); iterator.hasNext();) {
					RelatorTO relator = (RelatorTO) iterator.next();
					relatores.add(relator.getId().toString());
					relatorBean.setListaRelatorSelecionado(relatores);
				}
			}
			
			if (!this.getDocumentoEletronico().getListaDocumentoEletronicoArquivo().isEmpty()) {
				this.setListaDocumentoEletronicoArquivoDataTable(negocioDocumentoEletronico.listar
						(this.getDocumentoEletronico(), (UsuarioTO) this.getParamSession(SIGEDConstantes.USUARIO_LOGADO)));
			}
			
			return "consultarDocumentoEletronico";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public void abrirAnexo() {

		try {
			File file = new File(this.getDocumentoEletronicoArquivo().getVchCaminho());
			setResponseWriter(Util.bytesFromFile(file), this.getDocumentoEletronicoArquivo().getVchMimeType());

		} catch (IOException e) {
			tratarExcecao(e);
		}
	}

	public String adicionarArquivos(UploadEvent event) {
		DocumentoEletronico negocioDocumentoEletronico = getSIGEDBusinessFactory().createDocumentoEletronico();
		TipoDocumento negocioTipoDocumento = getSIGEDBusinessFactory().createTipoDocumento();
		try {

			this.setValidacaoRelatores(null);
			this.setValidacaoArquivoAnexo(null);
			
			//Valida a obrigatoriedade do campo "Tipo do Documento".
			if (this.getIdTipoDocumento() == null) {
				this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(getMessage("sigedMsgCampo")));
				this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(" "));
				this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(getMessage("sigedDocumentoEletronicoTipoDocumento")));
				this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(" "));
				this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(getMessage("sigedMsgCampoDeveSerInformado")));
				this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append("<br>"));
			} else {
				//Valida a obrigatoriedade do campo "Informa a página desejada".
				if (this.getPaginaSelecionada() == null) {
					this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(getMessage("sigedMsgCampo")));
					this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(" "));
					this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(getMessage("sigedDocumentoEletronicoArquivoInfomeAPagina")));
					this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(" "));
					this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(getMessage("sigedMsgCampoDeveSerInformado")));
					this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append("<br>"));
				} else {
					//Prepara os dados obtidos do arquivo para ser gravado em disco.
					this.setItem(event.getUploadItem());
					ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
					byteArrayOutputStream.write(this.getItem().getData());
					
					//Seta objeto TipoDocumentoTO
					this.setTipoDocumento(negocioTipoDocumento.consultar(Util.setValueTO(this.getIdTipoDocumento(), TipoDocumentoTO.class)));
					
					//Seta o objeto DocumentoEletronicoArquivoTO e adiciona na lista de arquivos envidados.
					this.setDocumentoEletronicoArquivo(new DocumentoEletronicoArquivoTO());
					//Define número sequencial do arquivo de acordo com o tipo do processo.
					//A variável auxDescricaoTipoDocumento armazena o último tipo do processo enviado.
					if (!this.getTipoDocumento().getVchDescricao().equals(this.getAuxDescricaoTipoDocumento()) && this.getAuxDescricaoTipoDocumento() != null) {
						this.setNumeroSequencialProcesso(0);
					}
					this.getDocumentoEletronicoArquivo().setVchNome(this.getDocumentoEletronico().getVchNumeroProcesso() + this.getNumeroSequencialProcesso() + this.getPaginaSelecionada() + this.getTipoDocumento().getVchDescricao());
					this.getDocumentoEletronicoArquivo().setVchNomeAnterior(this.getItem().getFileName());
					this.getDocumentoEletronicoArquivo().setIntTamanho(this.getItem().getFileSize());
					
					//Verifica se o arquivo em questão já não foi anexado.
					//Em caso de sim, envia mensagem de validação.
					//Caso não, busca o arquivo no banco de dados.
					if (this.consultaAnexo()) {
						this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(getMessage("sigedDocumentoEletronicoOpsArquivo")));
						this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(" "));
						this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(this.getDocumentoEletronicoArquivo().getVchNomeAnterior()));
						this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(" "));
						this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(getMessage("sigedDocumentoEletronicoArquivoJaAnexado")));
						this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append("<br>"));
					} else {
						//Verifica se o arquivo em questão está no banco de dados.
						//Em caso de sim, envia mensagem de validação.
						//Caso não, grava o arquivo em disco.
						if (!this.consultaArquivo()) {
							File file = new File(PATH_ARQUIVO_ELETRONICO + this.getDocumentoEletronicoArquivo().getVchNome() + ".pdf");
							byteArrayOutputStream.writeTo(new FileOutputStream(file));
							
							//Verifica a disponibilidade de espaço em disco e informa ao Usuário.
							if (file.getTotalSpace() < TAMANHO_DISPONIVEL_MENOR_100_MB) {
								this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(getMessage("sigedDocumentoEletronicoEspacoInsuficiente")));
								this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append("<br>"));
								this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(getMessage("sigedDocumentoEletronicoEspacoDisponivel")));
								this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(" "));
								this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(Numero.converteToMegabyte(file.getFreeSpace())));
								this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(" "));
								this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(getMessage("sigedDocumentoEletronicoEspacoEmMegabyte")));
								this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append("."));
								this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append("<br>"));
							}
							
							this.getDocumentoEletronicoArquivo().setVchMimeType(this.getItem().getContentType());
							this.getDocumentoEletronicoArquivo().setVchCaminho(PATH_ARQUIVO_ELETRONICO + this.getDocumentoEletronicoArquivo().getVchNome() + ".pdf");
							this.getDocumentoEletronicoArquivo().setIntPagina(Integer.parseInt(this.getPaginaSelecionada()));
							this.getDocumentoEletronicoArquivo().setBitArquivoExcluido(Boolean.FALSE);
							
							//Define o tipo de acesso ao arquivo de acordo com o Tipo do Documento.
							//1 - Relatório
							if (this.getIdTipoDocumento().equals(TIPO_DOCUMENTO_RELATORIO)) {
								this.getDocumentoEletronicoArquivo().setBitAcessoAnonimo(Boolean.FALSE);						
							} else {
								this.getDocumentoEletronicoArquivo().setBitAcessoAnonimo(Boolean.TRUE);
							}
							//this.getDocumentoArquivoAnexo().setImgDados(Hibernate.createBlob(this.getItem().getData()));
							this.getDocumentoEletronicoArquivo().setTipoDocumento(this.getTipoDocumento());
							this.getListaDocumentoEletronicoArquivoDataTable().add(this.getDocumentoEletronicoArquivo());
							this.setTotalArquivosEnviados(this.getListaDocumentoEletronicoArquivoDataTable().size());
							this.setIdTipoDocumento(null);
							this.setPaginaSelecionada(null);
							this.setValidacaoArquivoAnexo(null);
							
						} else {
							this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(getMessage("sigedDocumentoEletronicoOpsArquivo")));
							this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(" "));
							this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(this.getDocumentoEletronicoArquivo().getVchNome()));
							this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(" "));
							this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(getMessage("sigedDocumentoEletronicoArquivoJaEnviado")));
							this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(". "));
							this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(getMessage("sigedDocumentoEletronicoArquivoPertenceAoProcesso")));
							
							//Busca pelo id o Documento pertence ao arquivo em questão.
							DocumentoEletronicoTO documentoEletronicoTO = new DocumentoEletronicoTO();
							documentoEletronicoTO = negocioDocumentoEletronico.consultar(new DocumentoEletronicoTO(this.getDocumentoEletronicoArquivo().getDocumentoEletronico().getId()));
							this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(" "));
							this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(Util.formataStringMascara("####/#####", documentoEletronicoTO.getVchNumeroProcesso()) + "."));
							this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append("<br>"));
							this.setValidacaoNovoArquivo(Boolean.TRUE);
						}
					}
				}
			}

			//Retorna mensagens de validação quando houver.
			if (this.getValidacaoArquivoAnexo().length() > 0) {
				return this.getValidacaoArquivoAnexo().toString();
			}
			
			return null;

		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public String excluirAnexo() {
		DocumentoEletronicoArquivo negocioDocumentoEletronicoArquivo = getSIGEDBusinessFactory().createDocumentoEletronicoArquivo();
		
		this.setValidacaoRelatores(null);
		this.setValidacaoArquivoAnexo(null);
		
		for (int i = 0; i < this.getListaDocumentoEletronicoArquivoDataTable().size(); i++) {
			//Compara o nome do arquivo encontrado na lista, com o arquivo selecionado pelo Usuário.
			if (((DocumentoEletronicoArquivoTO) this.getListaDocumentoEletronicoArquivoDataTable().get(i)).getVchNome().equals(this.getDocumentoEletronicoArquivo().getVchNome())) {
				
				if (this.getDocumentoEletronicoArquivo().getId() != null) {
					((DocumentoEletronicoArquivoTO) this.getListaDocumentoEletronicoArquivoDataTable().get(i)).setBitArquivoExcluido(Boolean.TRUE);
					try {
						negocioDocumentoEletronicoArquivo.alterar(this.getDocumentoEletronicoArquivo(), getUsuarioLogado());
					} catch (Exception e) {
						return tratarExcecao(e);
					}
				}
				
				if (!this.consultaArquivo()) {
					File file = new File(PATH_ARQUIVO_ELETRONICO + this.getDocumentoEletronicoArquivo().getVchNome() + ".pdf");
					file.delete();
				}
				
				this.getListaDocumentoEletronicoArquivoDataTable().remove(i);
				this.setTotalArquivosEnviados(this.getListaDocumentoEletronicoArquivoDataTable().size());
			}
		}
		return null;
	}

	public boolean consultaAnexo() {
		for (Iterator<TransferObject> iterator = this.getListaDocumentoEletronicoArquivoDataTable().iterator(); iterator.hasNext();) {
			DocumentoEletronicoArquivoTO documentoEletronicoArquivo = (DocumentoEletronicoArquivoTO) iterator.next();
			if (this.getDocumentoEletronicoArquivo().getVchNome().equals(documentoEletronicoArquivo.getVchNome()) || this.getDocumentoEletronicoArquivo().getIntTamanho().equals(documentoEletronicoArquivo.getIntTamanho()) || this.getDocumentoEletronicoArquivo().getVchNomeAnterior().equals(documentoEletronicoArquivo.getVchNomeAnterior())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean consultaArquivo() {
		DocumentoEletronicoArquivo negocioDocumentoEletronicoArquivo = getSIGEDBusinessFactory().createDocumentoEletronicoArquivo();
		try {
			negocioDocumentoEletronicoArquivo.retirarObjetoSessao(this.getDocumentoEletronicoArquivo());
			DocumentoEletronicoArquivoTO documentoEletronicoArquivo = new DocumentoEletronicoArquivoTO();
			documentoEletronicoArquivo = negocioDocumentoEletronicoArquivo.consultarPorNomeDoArquivo(this.getDocumentoEletronicoArquivo());
			
			if (documentoEletronicoArquivo != null) {
				this.setDocumentoEletronicoArquivo(documentoEletronicoArquivo);
				return true;			
			}
			
		} catch (Exception e) {
			tratarExcecao(e);
		}
		return false;
	}
	
	public String consultaDocumento() {
		DocumentoEletronico negocioDocumentoEletronico = getSIGEDBusinessFactory().createDocumentoEletronico();
		try {
			DocumentoEletronicoTO documentoEletronico = this.pegarValorFiltro();
			this.setListaDocumentoEletronicoDataTable(negocioDocumentoEletronico.listar(documentoEletronico, "vchNumeroProcesso"));
			return "consultaDeDocumento";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String consultaNumeroProcesso() {
		DocumentoEletronico negocioDocumentoEletronico = getSIGEDBusinessFactory().createDocumentoEletronico();
		try {
			if (negocioDocumentoEletronico.listar(this.getDocumentoEletronico(), "vchNumeroProcesso").isEmpty()) {
				this.setValidacaoProcessoJaExiste(Boolean.FALSE);				
			} else {
				setMessage("sigedDocumentoEletronicoJaCadastrado", SIGEDEnumTipoMensagem.ERRO);
			}
		} catch (Exception e) {
			return tratarExcecao(e);
		}
		return null;
	}
	
	public void adicionaNovoArquivo() {
		this.setNumeroSequencialProcesso(this.getNumeroSequencialProcesso() + 1);
		this.setAuxDescricaoTipoDocumento(this.getTipoDocumento().getVchDescricao());
		this.setValidacaoArquivoAnexo(null);
		this.setValidacaoArquivoAnexo(this.getValidacaoArquivoAnexo().append(getMessage("sigedDocumentoEletronicoArquivoAnexarNovoArquivo")));
		this.setValidacaoNovoArquivo(Boolean.FALSE);
	}
	
	public void alterouListaRelatorSelecionado(ValueChangeEvent event) {
		this.setValidacaoRelatores(null);
		this.setValidacaoArquivoAnexo(null);
	}
	
	public String validaListaRelatorSelecionado() {
		RelatorBean relatorBean = (RelatorBean) getManagedBean("relatorBean");
		
		this.setValidacaoRelatores(null);
		this.setValidacaoArquivoAnexo(null);
		this.setValidacaoNovoArquivo(Boolean.FALSE);
		
		if (relatorBean.getListaRelatorSelecionado().isEmpty()) {
			
			/* Valida a obrigatoriedade do campo "Selecione um ou mais Relator(es) da lista ao lado". */
			this.setValidacaoRelatores(this.getValidacaoRelatores().append(getMessage("sigedMsgCampo")));
			this.setValidacaoRelatores(this.getValidacaoRelatores().append(" "));
			this.setValidacaoRelatores(this.getValidacaoRelatores().append(getMessage("sigedRelatorNenhumRelatorSelecionado")));
			this.setValidacaoRelatores(this.getValidacaoRelatores().append(" "));
			this.setValidacaoRelatores(this.getValidacaoRelatores().append(getMessage("sigedMsgCampoDeveSerInformado")));
			this.setValidacaoRelatores(this.getValidacaoRelatores().append("<br>"));
		}
		
		//Retorna mensagens de validação quando houver.
		if (this.getValidacaoRelatores().length() > 0) {
			return this.getValidacaoRelatores().toString();
		}
		return null;
	}
	
	public String consultaDeDocumento() {
		DocumentoEletronico negocioDocumentoEletronico = getSIGEDBusinessFactory().createDocumentoEletronico();
		DocumentoEletronicoArquivo negocioDocumentoEletronicoArquivo = getSIGEDBusinessFactory().createDocumentoEletronicoArquivo();
		Relator negocioRelator = getSIGEDBusinessFactory().createRelator();
		RelatorBean relatorBean = (RelatorBean) getManagedBean("relatorBean");
		this.setDocumentoEletronico(null);
		this.setListaDocumentoEletronicoDataTable(null);
		this.setListaDocumentoEletronicoArquivoDataTable(null);
		relatorBean.setRelatores(null);
		relatorBean.setListaRelatorDataTable(null);
		try {
			this.tratarFiltroDePesquisa();
			
			if (this.getDocumentoEletronico().getVchNumeroProcesso() != null) {
				DocumentoEletronicoTO documentoEletronico = this.getDocumentoEletronico();
				this.setDocumentoEletronico(negocioDocumentoEletronico.consultarPorNumeroProcesso(documentoEletronico));
				
				if (this.getDocumentoEletronico().getId() != null) {
					this.setListaDocumentoEletronicoDataTable(negocioDocumentoEletronico.listar(documentoEletronico, "vchNumeroProcesso"));
				}
				
				if (this.getDocumentoEletronico().getListaRelator() != null) {
					relatorBean.setListaRelatorDataTable(new ArrayList<TransferObject>(this.getDocumentoEletronico().getListaRelator()));
				}
				
				if (this.getDocumentoEletronico().getListaDocumentoEletronicoArquivo() != null) {
					this.setListaDocumentoEletronicoArquivoDataTable(negocioDocumentoEletronico.listar
							(this.getDocumentoEletronico(), (UsuarioTO) this.getParamSession(SIGEDConstantes.USUARIO_LOGADO)));
					if (this.getListaDocumentoEletronicoArquivoDataTable().isEmpty()) {
						this.setListaDocumentoEletronicoDataTable(null);
						relatorBean.setListaRelatorDataTable(null);
						setMessage("sigedDocumentoEletronicoProcessoNaoLocalizado", SIGEDEnumTipoMensagem.ERRO);
					}
				}
			}
			
			if (relatorBean.getRelator().getVchNome() != null) {
				relatorBean.setListaRelatorDataTable(negocioRelator.listar(relatorBean.getRelator(), "vchNome"));
				if (relatorBean.getListaRelatorDataTable().isEmpty()) {
					setMessage("sigedDocumentoEletronicoRelatorNaoLocalizado", SIGEDEnumTipoMensagem.ERRO);
				}
			}
			
			if (this.getDocumentoEletronicoArquivo().getVchConteudo() != null) {
				this.setListaDocumentoEletronicoArquivoDataTable(negocioDocumentoEletronicoArquivo.listar
						(this.getDocumentoEletronicoArquivo(), (UsuarioTO) this.getParamSession(SIGEDConstantes.USUARIO_LOGADO)));
				
				if (this.getListaDocumentoEletronicoArquivoDataTable().isEmpty()) {
					setMessage("sigedDocumentoEletronicoTextoPesquisaNaoLocalizado", SIGEDEnumTipoMensagem.ERRO);
				}
			}
			
			if (this.getAnoDecisaoDeFiltro() != null || this.getAnoDecisaoAteFiltro() != null) {
				if (this.getAnoDecisaoDeFiltro().isEmpty() && !this.getAnoDecisaoAteFiltro().isEmpty() || !this.getAnoDecisaoDeFiltro().isEmpty() && this.getAnoDecisaoAteFiltro().isEmpty()) {
					setMessage("sigedDocumentoEletronicoIntervaloDeAno", SIGEDEnumTipoMensagem.ERRO);
				}
				
				if (!this.getAnoDecisaoDeFiltro().isEmpty() && !this.getAnoDecisaoAteFiltro().isEmpty()) {
					if (Integer.parseInt(this.getAnoDecisaoDeFiltro()) > Integer.parseInt(this.getAnoDecisaoAteFiltro())) {
						setMessage("sigedDocumentoEletronicoAnoInicialMenorAnoFinal", SIGEDEnumTipoMensagem.ERRO);
					} else {
						if (Data.retornarDiferencaEntreDatasEmAnos("0101" + this.getAnoDecisaoDeFiltro(), "3112" + this.getAnoDecisaoAteFiltro()) > 1) {
							setMessage("sigedDocumentoEletronicoIntervaloNaoPodeUltrapassar1Ano", SIGEDEnumTipoMensagem.ERRO);
						} else {
							this.setListaDocumentoEletronicoArquivoDataTable(negocioDocumentoEletronico.listar(
									Data.toDate("01/01/" + this.getAnoDecisaoDeFiltro(), Data.DATA_PADRAO),
									Data.toDate("31/12/" + this.getAnoDecisaoAteFiltro(), Data.DATA_PADRAO),
									(UsuarioTO) this.getParamSession(SIGEDConstantes.USUARIO_LOGADO)));
							
							if (this.getListaDocumentoEletronicoArquivoDataTable().isEmpty()) {
								setMessage("sigedDocumentoEletronicoTextoPesquisaNaoLocalizado", SIGEDEnumTipoMensagem.ERRO);
							}
						}
					}
				}
			}
			
			if (this.getNumeroFiltro() != null && this.getRelatorFiltro() != null 
					&& this.getConteudoFiltro() != null 
					&& this.getAnoDecisaoDeFiltro() != null 
					&& this.getAnoDecisaoAteFiltro() != null) {
				if (this.getDocumentoEletronico().getVchNumeroProcesso() == null 
						&& relatorBean.getRelator().getVchNome() == null
						&& this.getDocumentoEletronicoArquivo().getVchConteudo() == null 
						&& this.getAnoDecisaoDeFiltro().isEmpty() 
						&& this.getAnoDecisaoAteFiltro().isEmpty()) {
					setMessage("sigedDocumentoEletronicoTextoPesquisaNaoLocalizado", SIGEDEnumTipoMensagem.ERRO);					
				}
			}
			
			return "consultaDeDocumento";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public void tratarFiltroDePesquisa() {
		
		//Pesquisa pelo número do processo.
		if (this.getNumeroFiltro() != null && !this.getNumeroFiltro().isEmpty()) {
			DocumentoEletronicoTO documentoEletronico = new DocumentoEletronicoTO();
			documentoEletronico.setVchNumeroProcesso(Util.setString(this.getNumeroFiltro()));
			this.setDocumentoEletronico(documentoEletronico);
		}
		
		//Pesquisa pelo nome do relator.
		if (this.getRelatorFiltro() != null && !this.getRelatorFiltro().isEmpty()) {
			RelatorBean relatorBean = (RelatorBean) getManagedBean("relatorBean");
			RelatorTO relator = new RelatorTO();
			relator.setVchNome(Util.setString(Util.setString(this.getRelatorFiltro())));
			relatorBean.setRelator(relator);
		}
		
		//Pesquisa conteudo do arquivo.
		if (this.getConteudoFiltro() != null && !this.getConteudoFiltro().isEmpty()) {
			DocumentoEletronicoArquivoTO documentoEletronicoArquivo = new DocumentoEletronicoArquivoTO();
			documentoEletronicoArquivo.setVchConteudo(Util.setString(this.getConteudoFiltro()));
			this.setDocumentoEletronicoArquivo(documentoEletronicoArquivo);
		}
		
	}
	
	public DocumentoEletronicoTO getDocumentoEletronico() {
		if (documentoEletronico == null) {
			documentoEletronico = new DocumentoEletronicoTO();
		}
		return documentoEletronico;
	}

	public void setDocumentoEletronico(DocumentoEletronicoTO documentoEletronico) {
		this.documentoEletronico = documentoEletronico;
	}

	public DocumentoEletronicoArquivoTO getDocumentoEletronicoArquivo() {
		if (documentoEletronicoArquivo == null) {
			documentoEletronicoArquivo = new DocumentoEletronicoArquivoTO();
		}
		return documentoEletronicoArquivo;
	}

	public void setDocumentoEletronicoArquivo(
			DocumentoEletronicoArquivoTO documentoEletronicoArquivo) {
		this.documentoEletronicoArquivo = documentoEletronicoArquivo;
	}
	
	public TipoDocumentoTO getTipoDocumento() {
		if (tipoDocumento == null) {
			tipoDocumento = new TipoDocumentoTO();
		}
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoTO tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public List<TransferObject> getListaDocumentoEletronicoDataTable() {
		if (listaDocumentoEletronicoDataTable == null) {
			listaDocumentoEletronicoDataTable = new ArrayList<TransferObject>();
		}
		return listaDocumentoEletronicoDataTable;
	}

	public void setListaDocumentoEletronicoDataTable(
			List<TransferObject> listaDocumentoEletronicoDataTable) {
		this.listaDocumentoEletronicoDataTable = listaDocumentoEletronicoDataTable;
	}
	
	public List<TransferObject> getListaDocumentoEletronicoArquivoDataTable() {
		if (listaDocumentoEletronicoArquivoDataTable == null) {
			listaDocumentoEletronicoArquivoDataTable = new ArrayList<TransferObject>();
		}
		return listaDocumentoEletronicoArquivoDataTable;
	}

	public void setListaDocumentoEletronicoArquivoDataTable(
			List<TransferObject> listaDocumentoEletronicoArquivoDataTable) {
		this.listaDocumentoEletronicoArquivoDataTable = listaDocumentoEletronicoArquivoDataTable;
	}

	public List<SelectItem> getListaTipoDocumento() {
		if (listaTipoDocumento == null) {
			TipoDocumento negocioTipoDocumento = getSIGEDBusinessFactory().createTipoDocumento();
			List<TransferObject> lista = negocioTipoDocumento.listarAtivos(new TipoDocumentoTO());
			listaTipoDocumento = Util.createListSelectItens(lista, "id", "vchDescricao");
		}
		return listaTipoDocumento;
	}

	public void setListaTipoDocumento(List<SelectItem> listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}

	public UploadItem getItem() {
		return item;
	}

	public void setItem(UploadItem item) {
		this.item = item;
	}

	public StringBuffer getValidacaoArquivoAnexo() {
		if (validacaoArquivoAnexo == null) {
			validacaoArquivoAnexo = new StringBuffer();
		}
		return validacaoArquivoAnexo;
	}

	public void setValidacaoArquivoAnexo(StringBuffer validacaoArquivoAnexo) {
		this.validacaoArquivoAnexo = validacaoArquivoAnexo;
	}
	
	public StringBuffer getValidacaoRelatores() {
		if (validacaoRelatores == null) {
			validacaoRelatores = new StringBuffer();
		}
		return validacaoRelatores;
	}

	public void setValidacaoRelatores(StringBuffer validacaoRelatores) {
		this.validacaoRelatores = validacaoRelatores;
	}

	public Integer getTotalArquivosEnviados() {
		if (this.getListaDocumentoEletronicoArquivoDataTable() != null && this.getListaDocumentoEletronicoArquivoDataTable().size() > 0) {
			totalArquivosEnviados = this.getListaDocumentoEletronicoArquivoDataTable().size();			
		}
		return totalArquivosEnviados;
	}

	public void setTotalArquivosEnviados(Integer totalArquivosEnviados) {
		this.totalArquivosEnviados = totalArquivosEnviados;
	}
	
	public Integer getNumeroSequencialProcesso() {
		return numeroSequencialProcesso;
	}
	
	public void setNumeroSequencialProcesso(Integer numeroSequencialProcesso) {
		this.numeroSequencialProcesso = numeroSequencialProcesso;
	}

	public Boolean getValidacaoProcessoJaExiste() {
		if (validacaoProcessoJaExiste == null) {
			validacaoProcessoJaExiste = new Boolean(Boolean.TRUE);
		}
		return validacaoProcessoJaExiste;
	}

	public void setValidacaoProcessoJaExiste(Boolean validacaoProcessoJaExiste) {
		this.validacaoProcessoJaExiste = validacaoProcessoJaExiste;
	}

	public Boolean getValidacaoNovoArquivo() {
		if (validacaoNovoArquivo == null) {
			validacaoNovoArquivo = new Boolean(Boolean.FALSE);
		}
		return validacaoNovoArquivo;
	}
	
	public void setValidacaoNovoArquivo(Boolean validacaoNovoArquivo) {
		this.validacaoNovoArquivo = validacaoNovoArquivo;
	}

	public String getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(String idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	
	public String getPaginaSelecionada() {
		return paginaSelecionada;
	}

	public void setPaginaSelecionada(String paginaSelecionada) {
		this.paginaSelecionada = paginaSelecionada;
	}
	
	public String getAuxDescricaoTipoDocumento() {
		return auxDescricaoTipoDocumento;
	}

	public void setAuxDescricaoTipoDocumento(String auxDescricaoTipoDocumento) {
		this.auxDescricaoTipoDocumento = auxDescricaoTipoDocumento;
	}

	public String getIdDocumentoEletronico() {
		return idDocumentoEletronico;
	}

	public void setIdDocumentoEletronico(String idDocumentoEletronico) {
		this.idDocumentoEletronico = idDocumentoEletronico;
	}

	public String getNumeroFiltro() {
		return numeroFiltro;
	}

	public void setNumeroFiltro(String numeroFiltro) {
		this.numeroFiltro = numeroFiltro;
	}
	
	public Date getDataAutuacaoFiltro() {
		return dataAutuacaoFiltro;
	}

	public void setDataAutuacaoFiltro(Date dataAutuacaoFiltro) {
		this.dataAutuacaoFiltro = dataAutuacaoFiltro;
	}
	
	public Date getDataDecisaoFiltro() {
		return dataDecisaoFiltro;
	}

	public void setDataDecisaoFiltro(Date dataDecisaoFiltro) {
		this.dataDecisaoFiltro = dataDecisaoFiltro;
	}

	public String getRelatorFiltro() {
		return relatorFiltro;
	}

	public void setRelatorFiltro(String relatorFiltro) {
		this.relatorFiltro = relatorFiltro;
	}
	
	public String getConteudoFiltro() {
		return conteudoFiltro;
	}

	public void setConteudoFiltro(String conteudoFiltro) {
		this.conteudoFiltro = conteudoFiltro;
	}

	public String getAnoDecisaoDeFiltro() {
		return anoDecisaoDeFiltro;
	}

	public void setAnoDecisaoDeFiltro(String anoDecisaoDeFiltro) {
		this.anoDecisaoDeFiltro = anoDecisaoDeFiltro;
	}
	
	public String getAnoDecisaoAteFiltro() {
		return anoDecisaoAteFiltro;
	}

	public void setAnoDecisaoAteFiltro(String anoDecisaoAteFiltro) {
		this.anoDecisaoAteFiltro = anoDecisaoAteFiltro;
	}
}
