package br.org.oabgo.saeo.negocio.controle.persistencia;

import java.util.List;

import org.springframework.stereotype.Service;

import br.org.oabgo.saeo.negocio.controle.SAEOPersistencia;
import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.ApplicationException;
import core.excecoes.BDException;

@Service
public class ServicoGenericoPO extends SAEOPersistencia {

	@Override
	protected String getNomeOperacaoAlteracao() {
		return null;
	}

	@Override
	protected String getNomeOperacaoExclusao() {
		return null;
	}

	@Override
	protected String getNomeOperacaoInclusao() {
		return null;
	}
	
	public <T extends TransferObject> T load(TransferObject transferObject, Class<T> classe) {
		return this.getDaoHibernate().load(classe, transferObject);
	}
	
	public void executarSQL(String sql) throws BDException, ApplicationException{
		this.getDaoHibernate().executarSQL(sql);
	}
	
	public <T extends TransferObject> T get(TransferObject transferObject, Class<T> classe) {
		return this.getDaoHibernate().get(classe, transferObject);
	}
	
	public <T extends TransferObject> T buscarObjeto(TransferObject transferObject, Class<T> classe) throws BDException {
		return this.getDaoHibernate().buscarObjeto(transferObject, classe);
	}
	
	public Integer count(TransferObject transferObject) throws BDException {
		return super.count(transferObject);
	}

	public <T extends TransferObject> List<T> listar(TransferObject transferObject, Class<T> classe, String orderBy) throws BDException {
		return this.getDaoHibernate().listar(transferObject, classe, orderBy);
	}

	public List<TransferObject> listar(TransferObject transferObject, String orderBy) throws BDException {
		return this.getDaoHibernate().listar(transferObject, orderBy);
	}
	
	public void excluir(TransferObject transferObject, UsuarioTO usuarioLogado) throws BDException {
		super.excluir(transferObject, usuarioLogado);
	}
	
	public void update(TransferObject transferObject, UsuarioTO usuarioLogado) throws BDException {
		super.alterar(transferObject, usuarioLogado);
	}
	
	public void save(TransferObject transferObject, UsuarioTO usuarioLogado) throws BDException {
		super.incluir(transferObject, usuarioLogado);
	}
	
}
