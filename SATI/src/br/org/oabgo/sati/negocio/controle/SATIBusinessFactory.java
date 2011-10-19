package br.org.oabgo.sati.negocio.controle;

import br.org.oabgo.sati.negocio.controle.negocio.AcaoMenuBO;
import br.org.oabgo.sati.negocio.controle.negocio.AcessoSistemaBO;
import br.org.oabgo.sati.negocio.controle.negocio.AtendimentoAutenticacaoAdslBO;
import br.org.oabgo.sati.negocio.controle.negocio.AutenticacaoAdslBO;
import br.org.oabgo.sati.negocio.controle.negocio.EmpresaBO;
import br.org.oabgo.sati.negocio.controle.negocio.ItemCiaTelefonicaBO;
import br.org.oabgo.sati.negocio.controle.negocio.MenuSistemaBO;
import br.org.oabgo.sati.negocio.controle.negocio.PaginaBO;
import br.org.oabgo.sati.negocio.controle.negocio.PerfilAcessoBO;
import br.org.oabgo.sati.negocio.controle.negocio.ServicoGenericoBO;
import br.org.oabgo.sati.negocio.controle.negocio.SistemaBO;
import br.org.oabgo.sati.negocio.controle.negocio.StatusAutenticacaoAdslBO;
import br.org.oabgo.sati.negocio.controle.negocio.TipoMenuSistemaBO;
import br.org.oabgo.sati.negocio.controle.negocio.TipoUnidadeAdministrativaBO;
import br.org.oabgo.sati.negocio.controle.negocio.UnidadeAdministrativaBO;
import br.org.oabgo.sati.negocio.controle.negocio.UsuarioBO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.AcaoMenu;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.AcessoSistema;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.AtendimentoAutenticacaoAdsl;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.AutenticacaoAdsl;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Empresa;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.ItemCiaTelefonica;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.MenuSistema;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Pagina;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.PerfilAcesso;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.ServicoGenerico;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Sistema;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.StatusAutenticacaoAdsl;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.TipoMenuSistema;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.TipoUnidadeAdministrativa;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.UnidadeAdministrativa;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Usuario;
import core.businessFactory.BusinessFactory;

public class SATIBusinessFactory extends BusinessFactory {

	private static SATIBusinessFactory satiBusinessFactory;

	
	private static final String ACAO_MENU_BO = "acaoMenuBO";
	private static final String ACESSO_SISTEMA_BO = "acessoSistemaBO";
	private static final String ATENDIMENTO_AUTENTICACAO_ADSL_BO = "atendimentoAutenticacaoAdslBO";
	private static final String AUTENTICACAO_ADSL_BO = "autenticacaoAdslBO";
	private static final String EMPRESA_BO = "empresaBO";
	private static final String ITENS_CONTAS_TELEFONICAS_BO = "itemCiaTelefonicaBO";
	private static final String MENU_SISTEMA_BO = "menuSistemaBO";
	private static final String PAGINA_BO = "paginaBO";
	private static final String SERVICO_GENERICO_BO = "servicoGenericoBO";
	private static final String PERFIL_ACESSO_BO = "perfilAcessoBO";
	private static final String SISTEMA_BO = "sistemaBO";
	private static final String STATUS_AUTENTICACAO_ADSL = "statusAutenticacaoAdslBO";
	private static final String TIPO_UNIDADE_ADMINISTRATIVA_BO = "tipoUnidadeAdministrativaBO";
	private static final String TIPO_MENU_SISTEMA_BO = "tipoMenuSistemaBO";
	private static final String UNIDADE_ADMINISTRATIVA_BO = "unidadeAdministrativaBO";
	private static final String USUARIO_BO = "usuarioBO";
	
	
	private static AcaoMenu acaoMenu;
	private static AcessoSistema acessoSistema;
	private static AtendimentoAutenticacaoAdsl atendimentoAutenticacaoAdsl;
	private static AutenticacaoAdsl autenticacaoAdsl;
	private static Empresa empresa;
	private static ItemCiaTelefonica itemCiaTelefonica;
	private static MenuSistema menuSistema;
	private static Pagina pagina;
	private static ServicoGenerico servicoGenerico;
	private static PerfilAcesso perfilAcesso;
	private static Sistema sistema;
	private static StatusAutenticacaoAdsl statusAutenticacaoAdsl;
	private static TipoMenuSistema tipoMenuSistema;
	private static TipoUnidadeAdministrativa tipoUnidadeAdministrativa;
	private static UnidadeAdministrativa unidadeAdministrativa;
	private static Usuario usuario;

	public SATIBusinessFactory() {
	}

	/**
	 * Cria o SINGLETON do objeto de negocio.
	 * 
	 * @return {@link SaeoBusinessFactory}
	 */
	public static SATIBusinessFactory getInstance() {
		if (satiBusinessFactory == null) {
			satiBusinessFactory = new SATIBusinessFactory();
		}
		return satiBusinessFactory;
	}

	

	/**
	 * Retorna a implementacao de negocio de AcaoMenu
	 * @return AcaoMenu
	 */
	public AcaoMenu createAcaoMenu() {
		if (acaoMenu == null) {
			acaoMenu = (AcaoMenu) createBusinessObject(AcaoMenuBO.class,
					ACAO_MENU_BO);
		}
		return acaoMenu;
	}
	
	/**
	 * Retorna a implementacao de negocio de AcessoSistema
	 * @return AcessoSistema
	 */
	public AcessoSistema createAcessoSistema() {
		if (acessoSistema == null) {
			acessoSistema = (AcessoSistema) createBusinessObject(AcessoSistemaBO.class,
					ACESSO_SISTEMA_BO);
		}
		return acessoSistema;
	}
	
	/**
	 * Retorna a implementacao de negocio de AtendimentoAutenticacaoAdsl
	 * @return AtendimentoAutenticacaoAdsl
	 */
	public AtendimentoAutenticacaoAdsl createAtendimentoAutenticacaoAdsl() {
		if (atendimentoAutenticacaoAdsl == null) {
			atendimentoAutenticacaoAdsl = (AtendimentoAutenticacaoAdsl) createBusinessObject(AtendimentoAutenticacaoAdslBO.class,
					ATENDIMENTO_AUTENTICACAO_ADSL_BO);
		}
		return atendimentoAutenticacaoAdsl;
	}
	
	/**
	 * Retorna a implementacao de negocio de AutenticacaoAdsl
	 * @return AutenticacaoAdsl
	 */
	public AutenticacaoAdsl createAutenticacaoAdsl() {
		if (autenticacaoAdsl == null) {
			autenticacaoAdsl = (AutenticacaoAdsl) createBusinessObject(AutenticacaoAdslBO.class,
					AUTENTICACAO_ADSL_BO);
		}
		return autenticacaoAdsl;
	}
	
	/**
	 * Retorna a implementacao de negocio de Empresa
	 * @return Empresa
	 */
	public Empresa createEmpresa() {
		if (empresa == null) {
			empresa = (Empresa) createBusinessObject(EmpresaBO.class,
					EMPRESA_BO);
		}
		return empresa;
	}
	
	/**
	 * Retorna a implementacao de negocio de ItemCiaTelefonica
	 * @return ItemCiaTelefonica
	 */
	public ItemCiaTelefonica createItemCiaTelefonica() {
		if (itemCiaTelefonica == null) {
			itemCiaTelefonica = (ItemCiaTelefonica) createBusinessObject(ItemCiaTelefonicaBO.class,
					ITENS_CONTAS_TELEFONICAS_BO);
		}
		return itemCiaTelefonica;
	}
	
	/**
	 * Retorna a implementacao de negocio de MenuSistema
	 * @return MenuSistema
	 */
	public MenuSistema createMenuSistema() {
		if (menuSistema == null) {
			menuSistema = (MenuSistema) createBusinessObject(
					MenuSistemaBO.class, MENU_SISTEMA_BO);
		}
		return menuSistema;
	}

	/**
	 * Retorna a implementacao de negocio de Pagina
	 * @return Pagina
	 */
	public Pagina createPagina() {
		if (pagina == null) {
			pagina = (Pagina) createBusinessObject(
					PaginaBO.class, PAGINA_BO);
		}
		return pagina;
	}

	/**
	 * Retorna a implementacao de negocio de PerfilAcesso
	 * @return PerfilAcesso
	 */
	public PerfilAcesso createPerfilAcesso() {
		if (perfilAcesso == null) {
			perfilAcesso = (PerfilAcesso) createBusinessObject(PerfilAcessoBO.class,
					PERFIL_ACESSO_BO);
		}
		return perfilAcesso;
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
	 * Retorna a implementacao de negocio de StatusAutenticacaoAdsl
	 * @return StatusAutenticacaoAdsl
	 */
	public StatusAutenticacaoAdsl createStatusAutenticacaoAdsl() {
		if (statusAutenticacaoAdsl == null) {
			statusAutenticacaoAdsl = (StatusAutenticacaoAdsl) createBusinessObject(StatusAutenticacaoAdslBO.class, STATUS_AUTENTICACAO_ADSL);
		}
		return statusAutenticacaoAdsl;
	}
	
	/**
	 * Retorna a implementacao de negocio de TipoMenuSistema
	 * @return TipoMenuSistema
	 */
	public TipoMenuSistema createTipoMenuSistema() {
		if (tipoMenuSistema == null) {
			tipoMenuSistema = (TipoMenuSistema) createBusinessObject(
					TipoMenuSistemaBO.class, TIPO_MENU_SISTEMA_BO);
		}
		return tipoMenuSistema;
	}
	
	/**
	 * Retorna a implementacao de negocio de TipoUnidadeAdministrativa
	 * @return TipoUnidadeAdministrativa
	 */
	public TipoUnidadeAdministrativa createTipoUnidadeAdministrativa() {
		if (tipoUnidadeAdministrativa == null) {
			tipoUnidadeAdministrativa = (TipoUnidadeAdministrativa) createBusinessObject(
					TipoUnidadeAdministrativaBO.class, TIPO_UNIDADE_ADMINISTRATIVA_BO);
		}
		return tipoUnidadeAdministrativa;
	}
	
	/**
	 * Retorna a implementacao de negocio de UnidadeAdministrativa
	 * @return AcessoSistema
	 */
	public UnidadeAdministrativa createUnidadeAdministrativa() {
		if (unidadeAdministrativa == null) {
			unidadeAdministrativa = (UnidadeAdministrativa) createBusinessObject(
					UnidadeAdministrativaBO.class, UNIDADE_ADMINISTRATIVA_BO);
		}
		return unidadeAdministrativa;
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
