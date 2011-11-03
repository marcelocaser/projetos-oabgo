package br.com.flavios.pbpf.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.flavios.pbpf.negocio.controle.entidade.SistemaTO;
import br.com.flavios.pbpf.negocio.controle.entidade.UsuarioTO;
import br.com.flavios.pbpf.negocio.controle.negocio.interfaces.Sistema;
import br.com.flavios.pbpf.negocio.controle.persistencia.SistemaPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

@Service
public class SistemaBO implements Sistema {

	@Autowired
	private SistemaPO	persistencia;

	/**
	 * Persiste as alterações no objeto passado como parâmetro.
	 * 
	 * @param sistema - SistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void alterar(SistemaTO sistema, UsuarioTO usuarioLogado)	throws RegraNegocioException, BDException {
		this.persistencia.alterar(sistema, usuarioLogado);
	}
	
	/**
	 * Exclui um objeto SistemaTO.
	 * 
	 * @param sistema - SistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void excluir(SistemaTO sistema, UsuarioTO usuarioLogado)throws RegraNegocioException, BDException {
		this.persistencia.excluir(sistema, usuarioLogado);
	}
	
	/**
	 * Persiste as informações no objeto passado como parâmetro.
	 * 
	 * @param sistema - SistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void incluir(SistemaTO sistema, UsuarioTO usuarioLogado)throws RegraNegocioException, BDException {
		this.persistencia.incluir(sistema, usuarioLogado);
	}

	/**
	 * Consulta um objeto SistemaTO com base no ID.
	 * 
	 * @param sistema - SistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @return SistemaTO
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public SistemaTO consultar(SistemaTO sistema)throws BDException {
		return this.persistencia.consultar(sistema);
	}
	
	/**
	 * Retorna uma lista de objetos SistemaTO.
	 * 
	 * @param sistema - SistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @return List<TransferObject>
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public List<TransferObject> listar(SistemaTO sistema, String orderBy)throws BDException {
		return this.persistencia.listar(sistema, orderBy);
	}

	/**
	 * Retorna o primeiro objeto que satisfaça os parâmetros de busca.
	 * 
	 * @param sistema - SistemaTO
	 * @return SistemaTO
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public SistemaTO buscarObjeto(SistemaTO sistema)throws BDException {
		return this.persistencia.buscarObjeto(sistema);
	}
	
	/**
	 * Remove o objeto atual da Session do Hibernate.
	 * @param sistema - SistemaTO
	 * @throws BDException
	 */
	public void retirarObjetoSessao(SistemaTO sistema) throws BDException{
		this.persistencia.retirarObjetoSessao(sistema);
	}

	/**
	 * Inicializa as propriedades de um obejto.
	 * @param sistema - SistemaTO
	 * @throws BDException
	 */
	public void inicializarObjeto(SistemaTO sistema) throws BDException{
		this.persistencia.inicializarObjeto(sistema);
	}

	/**
	 * Retorna o sistema com base no NOME - Constante definida no projeto.
	 * @param nome - {@link String} 
	 * @return SistemaTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public SistemaTO consultarSistemaNome(String nome) throws BDException {
		return this.persistencia.consultarSistemaNome(nome);
	}
	
	/**
	 * Retorna o sistema com base no Contexto.
	 * @param contexto - {@link String} 
	 * @return SistemaTO
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public SistemaTO consultarSistemaContexto(String contexto) throws BDException {
		return this.persistencia.consultarSistemaContexto(contexto);
	}

}
