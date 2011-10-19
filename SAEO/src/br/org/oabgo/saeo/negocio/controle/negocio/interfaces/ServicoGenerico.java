package br.org.oabgo.saeo.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.ApplicationException;
import core.excecoes.BDException;

public interface ServicoGenerico {
	
	public void retirarObjetoSessao(TransferObject transferObject) throws ApplicationException;
	
	public void flush() throws BDException;
	
	public Integer count(TransferObject transferObject) throws BDException;
	
	public void excluir(TransferObject transferObject, UsuarioTO usuarioLogado) throws BDException;
	
	public void update(TransferObject transferObject, UsuarioTO usuarioLogado) throws BDException;
	
	public void save(TransferObject transferObject, UsuarioTO usuarioLogado) throws BDException;
	
	public <T extends TransferObject> T load(TransferObject transferObject, Class<T> classe);
	
	public <T extends TransferObject> T get(TransferObject transferObject, Class<T> classe);
	
	public void executarSQL(String sql) throws BDException, ApplicationException;
	
	public <T extends TransferObject> T buscarObjeto(TransferObject transferObject, Class<T> classe) throws BDException;
	
	public <T extends TransferObject> List<T> listar(TransferObject transferObject, Class<T> classe, String orderBy) throws BDException;

}
