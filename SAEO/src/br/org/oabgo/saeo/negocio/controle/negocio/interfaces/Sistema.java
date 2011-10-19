package br.org.oabgo.saeo.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.saeo.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

public interface Sistema {

	public void alterar(SistemaTO sistema, UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;
	
	public void excluir(SistemaTO sistema, UsuarioTO usuarioLogado) throws RegraNegocioException,BDException;
	
	public void incluir(SistemaTO sistema, UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public SistemaTO consultar(SistemaTO sistema) throws BDException;
	
	public List<TransferObject> listar(SistemaTO sistema, String orderBy)throws BDException;
	
	public SistemaTO buscarObjeto(SistemaTO sistema)throws BDException;
	
	public void retirarObjetoSessao(SistemaTO sistema) throws BDException;
	
	public void inicializarObjeto(SistemaTO sistema) throws BDException;
	
	public SistemaTO consultarSistemaNome(String nome) throws BDException;
	
	public SistemaTO consultarSistemaContexto(String contexto) throws BDException;

}
