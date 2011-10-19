package br.org.oabgo.siged.negocio.controle;

import br.org.oabgo.siged.negocio.controle.negocio.DocumentoEletronicoArquivoBO;
import br.org.oabgo.siged.negocio.controle.negocio.DocumentoEletronicoBO;
import br.org.oabgo.siged.negocio.controle.negocio.RelatorBO;
import br.org.oabgo.siged.negocio.controle.negocio.ServicoGenericoBO;
import br.org.oabgo.siged.negocio.controle.negocio.SistemaBO;
import br.org.oabgo.siged.negocio.controle.negocio.TipoDocumentoBO;
import br.org.oabgo.siged.negocio.controle.negocio.UsuarioBO;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.DocumentoEletronico;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.DocumentoEletronicoArquivo;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.Relator;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.ServicoGenerico;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.Sistema;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.TipoDocumento;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.Usuario;
import core.businessFactory.BusinessFactory;

public class SIGEDBusinessFactory extends BusinessFactory {

	private static SIGEDBusinessFactory sigedBusinessFactory;

	
	private static final String DOCUMENTO_ELETRONICO_ARQUIVO_BO = "documentoEletronicoArquivoBO";
	private static final String DOCUMENTO_ELETRONICO_BO = "documentoEletronicoBO";
	private static final String RELATOR_BO = "relatorBO";
	private static final String SERVICO_GENERICO_BO = "servicoGenericoBO";
	private static final String SISTEMA_BO = "sistemaBO";
	private static final String TIPO_DOCUMENTO_BO = "tipoDocumentoBO";
	private static final String USUARIO_BO = "usuarioBO";
	
	
	private static DocumentoEletronicoArquivo documentoEletronicoArquivo;
	private static DocumentoEletronico documentoEletronico;
	private static Relator relator;
	private static ServicoGenerico servicoGenerico;
	private static Sistema sistema;
	private static TipoDocumento tipoDocumento;
	private static Usuario usuario;

	public SIGEDBusinessFactory() {
	}

	/**
	 * Cria o SINGLETON do objeto de negocio.
	 * 
	 * @return {@link SaeoBusinessFactory}
	 */
	public static SIGEDBusinessFactory getInstance() {
		if (sigedBusinessFactory == null) {
			sigedBusinessFactory = new SIGEDBusinessFactory();
		}
		return sigedBusinessFactory;
	}
	
	/**
	 * Retorna a implementação de negocio de DocumentoEletronicoArquivo
	 * @return DocumentoEletronicoArquivo
	 */
	public DocumentoEletronicoArquivo createDocumentoEletronicoArquivo() {
		if (documentoEletronicoArquivo == null) {
			documentoEletronicoArquivo = (DocumentoEletronicoArquivo) createBusinessObject(DocumentoEletronicoArquivoBO.class, DOCUMENTO_ELETRONICO_ARQUIVO_BO);
		}
		return documentoEletronicoArquivo;
	}
	
	/**
	 * Retorna a implementação de negocio de DocumentoEletronico
	 * @return DocumentoEletronico
	 */
	public DocumentoEletronico createDocumentoEletronico() {
		if (documentoEletronico == null) {
			documentoEletronico = (DocumentoEletronico) createBusinessObject(DocumentoEletronicoBO.class, DOCUMENTO_ELETRONICO_BO);
		}
		return documentoEletronico;
	}
	
	/**
	 * Retorna a implementação de negocio de Relator
	 * @return Relator
	 */
	public Relator createRelator() {
		if (relator == null) {
			relator = (Relator) createBusinessObject(RelatorBO.class, RELATOR_BO);
		}
		return relator;
	}
	
	/**
	 * Retorna a implementação de negocio de ServicoGenerico
	 * @return ServicoGenerico
	 */
	public ServicoGenerico createServicoGenerico() {
		if (servicoGenerico == null) {
			servicoGenerico = (ServicoGenerico) createBusinessObject(ServicoGenericoBO.class, SERVICO_GENERICO_BO);
		}
		return servicoGenerico;
	}

	/**
	 * Retorna a implementação de negocio de Sistema
	 * @return Sistema
	 */
	public Sistema createSistema() {
		if (sistema == null) {
			sistema = (Sistema) createBusinessObject(SistemaBO.class, SISTEMA_BO);
		}
		return sistema;
	}
	
	/**
	 * Retorna a implementação de negocio de TipoDocumento
	 * @return TipoDocumento
	 */
	public TipoDocumento createTipoDocumento() {
		if (tipoDocumento == null) {
			tipoDocumento = (TipoDocumento) createBusinessObject(TipoDocumentoBO.class, TIPO_DOCUMENTO_BO);
		}
		return tipoDocumento;
	}
	
	/**
	 * Retorna a implementação de negocio de Usuario
	 * @return Usuario
	 */
	public Usuario createUsuario() {
		if (usuario == null) {
			usuario = (Usuario) createBusinessObject(UsuarioBO.class, USUARIO_BO);
		}
		return usuario;
	}

}
