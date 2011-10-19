package br.org.oabgo.sati.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.sati.negocio.controle.entidade.PaginaTO;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Pagina;
import br.org.oabgo.sati.negocio.controle.persistencia.PaginaPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> PaginaBO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.negocio <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
@Service
public class PaginaBO implements Pagina {

	private static final long serialVersionUID = -6137413362653990464L;
	
	@Autowired
	private PaginaPO	persistencia;

	/**
	 * Persiste as alterações no objeto passado como parâmetro.
	 * 
	 * @param pagina - PaginaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void alterar(PaginaTO pagina, UsuarioTO usuarioLogado)	throws RegraNegocioException, BDException {
		this.persistencia.alterar(pagina, usuarioLogado);
	}
	
	/**
	 * Exclui um objeto PaginaTO
	 * 
	 * @param pagina - PaginaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void excluir(PaginaTO pagina, UsuarioTO usuarioLogado)throws RegraNegocioException, BDException {
		this.persistencia.excluir(pagina, usuarioLogado);
	}
	
	/**
	 * Persiste as informações no objeto passado como parâmetro.
	 * 
	 * @param pagina - PaginaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void incluir(PaginaTO pagina, UsuarioTO usuarioLogado)throws RegraNegocioException, BDException {
		this.persistencia.incluir(pagina, usuarioLogado);
	}

	/**
	 * Consulta um objeto PaginaTO com base no ID.
	 * 
	 * @param pagina - PaginaTO
	 * @param usuarioLogado - UsuarioTO
	 * @return PaginaTO
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public PaginaTO consultar(PaginaTO pagina)throws BDException {
		return this.persistencia.consultar(pagina);
	}
	
	/**
	 * Verifica se o perfil de acesso passado como parâmetro tem permissão para visualizar a página requisitada.
	 *
	 * @param pagina - {@link PaginaTO}
	 * @param perfilAcesso - {@link PerfilAcessoTO}
	 * @throws BDException 
	 * @return boolean
	 */
	public boolean possuiAcessoPagina(PaginaTO pagina, PerfilAcessoTO perfilAcesso) throws BDException{
		return persistencia.possuiAcessoPagina(pagina, perfilAcesso);
	}
	
	/**
	 * Retorna uma lista de objetos PaginaTO.
	 * 
	 * @param pagina - PaginaTO
	 * @param usuarioLogado - UsuarioTO
	 * @return List<TransferObject>
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public List<TransferObject> listar(PaginaTO pagina, String orderBy)throws BDException {
		return this.persistencia.listar(pagina, orderBy);
	}

	/**
	 * Retorna o primeiro objeto que satisfaça os parâmetros de busca.
	 * 
	 * @param pagina - PaginaTO
	 * @return PaginaTO
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public PaginaTO buscarObjeto(PaginaTO pagina)throws BDException {
		return this.persistencia.buscarObjeto(pagina);
	}
	
	/**
	 * Remove o objeto atual da Session do Hibernate.
	 * @param pagina - PaginaTO
	 * @throws BDException
	 */
	public void retirarObjetoSessao(PaginaTO pagina) throws BDException{
		this.persistencia.retirarObjetoSessao(pagina);
	}

	/**
	 * Inicializa as propriedades de um obejto.
	 * @param pagina - PaginaTO
	 * @throws BDException
	 */
	public void inicializarObjeto(PaginaTO pagina) throws BDException{
		this.persistencia.inicializarObjeto(pagina);
	}

	/**
	 * Retorna o pagina com base no NOME - Constante definida no projeto.
	 * @param nome - {@link String} 
	 * @return PaginaTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public PaginaTO consultarPaginaNome(String nome) throws BDException {
		return this.persistencia.consultarPaginaNome(nome);
	}

}
