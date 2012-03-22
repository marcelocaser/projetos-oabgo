package br.com.flavios.pbpf.negocio.controle;

import br.com.flavios.pbpf.negocio.controle.negocio.EstoqueProdutoBO;
import br.com.flavios.pbpf.negocio.controle.negocio.ServicoGenericoBO;
import br.com.flavios.pbpf.negocio.controle.negocio.SistemaBO;
import br.com.flavios.pbpf.negocio.controle.negocio.UsuarioBO;
import br.com.flavios.pbpf.negocio.controle.negocio.interfaces.EstoqueProduto;
import br.com.flavios.pbpf.negocio.controle.negocio.interfaces.ServicoGenerico;
import br.com.flavios.pbpf.negocio.controle.negocio.interfaces.Sistema;
import br.com.flavios.pbpf.negocio.controle.negocio.interfaces.Usuario;
import core.businessFactory.BusinessFactory;

public class PBPFBusinessFactory extends BusinessFactory {

	private static PBPFBusinessFactory pbpfBusinessFactory;

	
	private static final String ESTOQUE_PRODUTO_BO = "estoqueProdutoBO";
	private static final String SERVICO_GENERICO_BO = "servicoGenericoBO";
	private static final String SISTEMA_BO = "sistemaBO";
	private static final String USUARIO_BO = "usuarioBO";
	
	private static EstoqueProduto estoqueProduto;
	private static ServicoGenerico servicoGenerico;
	private static Sistema sistema;
	private static Usuario usuario;

	public PBPFBusinessFactory() {
	}

	/**
	 * Cria o SINGLETON do objeto de negocio.
	 * 
	 * @return {@link SaeoBusinessFactory}
	 */
	public static PBPFBusinessFactory getInstance() {
		if (pbpfBusinessFactory == null) {
			pbpfBusinessFactory = new PBPFBusinessFactory();
		}
		return pbpfBusinessFactory;
	}
	
	/**
	 * Retorna a implementação de negocio de ServicoGenerico
	 * @return ServicoGenerico
	 */
	public EstoqueProduto createEstoqueProduto() {
		if (estoqueProduto == null) {
			estoqueProduto = (EstoqueProduto) createBusinessObject(EstoqueProdutoBO.class, ESTOQUE_PRODUTO_BO);
		}
		return estoqueProduto;
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
