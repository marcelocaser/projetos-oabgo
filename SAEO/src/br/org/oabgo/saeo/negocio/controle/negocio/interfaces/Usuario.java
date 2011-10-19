package br.org.oabgo.saeo.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

public interface Usuario {

	public void alterar(UsuarioTO usuarioTO, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException;
	
	public void excluir(UsuarioTO usuarioTO, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException;
	
	public void incluir(UsuarioTO usuarioTO, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException;

	public UsuarioTO consultar(UsuarioTO usuarioTO) throws BDException;
	
	public List<TransferObject> listar(UsuarioTO usuario) throws BDException;
	
	public List<TransferObject> listar(UsuarioTO usuario, String orderBy) throws BDException;
	
	public void retirarObjetoSessao(UsuarioTO usuario);
	
	public UsuarioTO buscarUsuarioPorLogin(String userName) throws BDException;

}
