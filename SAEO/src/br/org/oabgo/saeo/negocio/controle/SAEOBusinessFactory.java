package br.org.oabgo.saeo.negocio.controle;

import br.org.oabgo.saeo.negocio.controle.negocio.AreaAtuacaoBO;
import br.org.oabgo.saeo.negocio.controle.negocio.CandidatoAnteriorExameOrdemBO;
import br.org.oabgo.saeo.negocio.controle.negocio.CertameBO;
import br.org.oabgo.saeo.negocio.controle.negocio.ServicoGenericoBO;
import br.org.oabgo.saeo.negocio.controle.negocio.SistemaBO;
import br.org.oabgo.saeo.negocio.controle.negocio.UsuarioBO;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.AreaAtuacao;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.CandidatoAnteriorExameOrdem;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.Certame;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.ServicoGenerico;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.Sistema;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.Usuario;
import core.businessFactory.BusinessFactory;

public class SAEOBusinessFactory extends BusinessFactory {

	private static SAEOBusinessFactory saeoBusinessFactory;

	
	private static final String AREA_ATUACAO_BO = "areaAtuacaoBO";
	private static final String CANDIDATO_ANTERIOR_EXAME_ORDEM_BO = "candidatoAnteriorExameOrdemBO";
	private static final String CERTAME_BO = "certameBO";
	private static final String SERVICO_GENERICO_BO = "servicoGenericoBO";
	private static final String SISTEMA_BO = "sistemaBO";
	private static final String USUARIO_BO = "usuarioBO";
	
	
	private static AreaAtuacao areaAtuacao;
	private static Certame certame;
	private static CandidatoAnteriorExameOrdem candidatoAnteriorExameOrdem;
	private static ServicoGenerico servicoGenerico;
	private static Sistema sistema;
	private static Usuario usuario;

	public SAEOBusinessFactory() {
	}

	/**
	 * Cria o SINGLETON do objeto de negocio.
	 * 
	 * @return {@link SaeoBusinessFactory}
	 */
	public static SAEOBusinessFactory getInstance() {
		if (saeoBusinessFactory == null) {
			saeoBusinessFactory = new SAEOBusinessFactory();
		}
		return saeoBusinessFactory;
	}
	
	/**
	 * Retorna a implementacao de negocio de AreaAtuacao
	 * @return AreaAtuacao
	 */
	public AreaAtuacao createAreaAtuacao() {
		if (areaAtuacao == null) {
			areaAtuacao = (AreaAtuacao) createBusinessObject(AreaAtuacaoBO.class,
					AREA_ATUACAO_BO);
		}
		return areaAtuacao;
	}
	
	/**
	 * Retorna a implementacao de negocio de Certame
	 * @return Certame
	 */
	public Certame createCertame() {
		if (certame == null) {
			certame = (Certame) createBusinessObject(CertameBO.class,
					CERTAME_BO);
		}
		return certame;
	}
	
	/**
	 * Retorna a implementacao de negocio de CandidatoAnteriorExameOrdem
	 * @return CandidatoAnteriorExameOrdem
	 */
	public CandidatoAnteriorExameOrdem createCandidatoAnteriorExameOrdem() {
		if (candidatoAnteriorExameOrdem == null) {
			candidatoAnteriorExameOrdem = (CandidatoAnteriorExameOrdem) createBusinessObject(CandidatoAnteriorExameOrdemBO.class,
					CANDIDATO_ANTERIOR_EXAME_ORDEM_BO);
		}
		return candidatoAnteriorExameOrdem;
	}
	
	/**
	 * Retorna a implementacao de negocio de ServicoGenerico
	 * @return ServicoGenerico
	 */
	public ServicoGenerico createServicoGenerico() {
		if (servicoGenerico == null) {
			servicoGenerico = (ServicoGenerico) createBusinessObject(ServicoGenericoBO.class, SERVICO_GENERICO_BO);
		}
		return servicoGenerico;
	}
	
	/**
	 * Retorna a implementacao de negocio de Sistema
	 * @return Sistema
	 */
	public Sistema createSistema() {
		if (sistema == null) {
			sistema = (Sistema) createBusinessObject(SistemaBO.class, SISTEMA_BO);
		}
		return sistema;
	}
	
	/**
	 * Retorna a implementacao de negocio de Usuario
	 * @return Usuario
	 */
	public Usuario createUsuario() {
		if (usuario == null) {
			usuario = (Usuario) createBusinessObject(UsuarioBO.class, USUARIO_BO);
		}
		return usuario;
	}

}
