package br.com.flavios.pbpf.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.flavios.pbpf.negocio.controle.entidade.UsuarioTO;
import br.com.flavios.pbpf.negocio.controle.negocio.interfaces.ServicoGenerico;
import br.com.flavios.pbpf.negocio.controle.persistencia.ServicoGenericoPO;
import core.dao.TransferObject;
import core.excecoes.ApplicationException;
import core.excecoes.BDException;

@Service
public class ServicoGenericoBO implements ServicoGenerico {
	
	@Autowired
	private ServicoGenericoPO persistencia;

	public Integer count(TransferObject transferObject) throws BDException {
		return this.persistencia.count(transferObject);
	}

	public void flush() throws BDException {
		this.persistencia.sincronizar();
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void excluir(TransferObject transferObject, UsuarioTO usuarioLogado) throws BDException {
		this.persistencia.excluir(transferObject, usuarioLogado);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void update(TransferObject transferObject, UsuarioTO usuarioLogado) throws BDException {
		this.persistencia.update(transferObject, usuarioLogado);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void save(TransferObject transferObject, UsuarioTO usuarioLogado) throws BDException {
		this.persistencia.save(transferObject, usuarioLogado);
	}

	public void retirarObjetoSessao(TransferObject transferObject) throws ApplicationException{
		this.persistencia.retirarObjetoSessao(transferObject);
	}
	
	public <T extends TransferObject> T load(TransferObject transferObject, Class<T> classe) {
		return this.persistencia.load(transferObject, classe);
	}
	
	public <T extends TransferObject> T get(TransferObject transferObject, Class<T> classe) {
		return this.persistencia.get(transferObject, classe);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void executarSQL(String sql) throws BDException, ApplicationException{
		this.persistencia.executarSQL(sql);
	}
	
	public <T extends TransferObject> T buscarObjeto(TransferObject transferObject, Class<T> classe) throws BDException {
		return this.persistencia.buscarObjeto(transferObject, classe);
	}
	
	public <T extends TransferObject> List<T> listar(TransferObject transferObject, Class<T> classe, String orderBy) throws BDException {
		return this.persistencia.listar(transferObject, classe, orderBy);
	}

	public List<TransferObject> listar(TransferObject transferObject, String orderBy) throws BDException {
		return this.persistencia.listar(transferObject, orderBy);
	}
	
}
