package br.org.oabgo.saeo.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.saeo.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.Sistema;
import br.org.oabgo.saeo.negocio.controle.persistencia.SistemaPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

@Service
public class SistemaBO implements Sistema {

	private static final long serialVersionUID = -6137413362653990464L;
	
	@Autowired
	private SistemaPO	persistencia;

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void alterar(SistemaTO sistema, UsuarioTO usuarioLogado)	throws RegraNegocioException, BDException {
		this.persistencia.alterar(sistema, usuarioLogado);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void excluir(SistemaTO sistema, UsuarioTO usuarioLogado)throws RegraNegocioException, BDException {
		this.persistencia.excluir(sistema, usuarioLogado);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void incluir(SistemaTO sistema, UsuarioTO usuarioLogado)throws RegraNegocioException, BDException {
		this.persistencia.incluir(sistema, usuarioLogado);
	}

	public SistemaTO consultar(SistemaTO sistema)throws BDException {
		return this.persistencia.consultar(sistema);
	}
	
	public List<TransferObject> listar(SistemaTO sistema, String orderBy)throws BDException {
		return this.persistencia.listar(sistema, orderBy);
	}

	public SistemaTO buscarObjeto(SistemaTO sistema)throws BDException {
		return this.persistencia.buscarObjeto(sistema);
	}
	
	public void retirarObjetoSessao(SistemaTO sistema) throws BDException{
		this.persistencia.retirarObjetoSessao(sistema);
	}

	public void inicializarObjeto(SistemaTO sistema) throws BDException{
		this.persistencia.inicializarObjeto(sistema);
	}

	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public SistemaTO consultarSistemaNome(String nome) throws BDException {
		return this.persistencia.consultarSistemaNome(nome);
	}
	
	public SistemaTO consultarSistemaContexto(String contexto) throws BDException {
		return this.persistencia.consultarSistemaContexto(contexto);
	}

}
