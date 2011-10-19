package br.org.oabgo.sati.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.ServicoGenerico;
import br.org.oabgo.sati.negocio.controle.persistencia.ServicoGenericoPO;
import core.dao.TransferObject;
import core.excecoes.ApplicationException;
import core.excecoes.BDException;

/**
 * <b>Classe:</b> ServicoGenericoBO.java <br>
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
