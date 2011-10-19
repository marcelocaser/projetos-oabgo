package br.org.oabgo.sati.negocio.controle.persistencia;

import java.util.List;

import org.springframework.stereotype.Service;

import br.org.oabgo.sati.negocio.controle.SATIPersistencia;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.ApplicationException;
import core.excecoes.BDException;

/**
 * <b>Classe:</b> ServicoGenericoPO.java <br>
 * <b>Descri��o:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.persistencia <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Se��o de Goi�s <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
@Service("servicoGenericoPO")
public class ServicoGenericoPO extends SATIPersistencia {

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
	
	/**
	 * Executa opera��o de load em fun��o do id setado no objeto de busca.
	 * 
	 * @param classe - Class<T>
	 * @param transferObject - TransferObject
	 * 
	 * @return T - <T extends TransferObject>
	 */
	public <T extends TransferObject> T load(TransferObject transferObject, Class<T> classe) {
		return this.getDaoHibernate().load(classe, transferObject);
	}
	
	/**
	 * Executa um comando SQL nativo na Banco de Dados.
	 * @param sql - String
	 * @throws ApplicationException
	 */
	public void executarSQL(String sql) throws BDException, ApplicationException{
		this.getDaoHibernate().executarSQL(sql);
	}
	
	/**
	 * Executa opera��o de get em fun��o do id setado no objeto de busca.
	 * Retorna null, caso n�o existe registro com o id especificado.
	 * 
	 * @param classe - Class<T>
	 * @param transferObject - TransferObject
	 * 
	 * @return T - <T extends TransferObject>
	 */
	public <T extends TransferObject> T get(TransferObject transferObject, Class<T> classe) {
		return this.getDaoHibernate().get(classe, transferObject);
	}
	
	/**
	 * Realiza um select baseado nas propriedades setadas do objeto passado como par�metro. E retorna um resultado apenas.
	 * Obs: Popule o objeto de maneira que ele seja �nico. Dessa forma � garantido o retorno do objeto certo.
	 * 
	 * @param tranferObject - TranferObject
	 * @param classe - Class<T>
	 * @return T - <T extends TransferObject>
	 * @throws BDException
	 */
	public <T extends TransferObject> T buscarObjeto(TransferObject transferObject, Class<T> classe) throws BDException {
		return this.getDaoHibernate().buscarObjeto(transferObject, classe);
	}
	
	/**
	 * Realiza a opera��o de contagem de registros em fun��o das propriedades setadas no objeto de busca.
	 * 
	 * @param transferObject - TransferObject
	 * @return n�mero de registros - Integer
	 * @throws BDException
	 */
	public Integer count(TransferObject transferObject) throws BDException {
		return super.count(transferObject);
	}
	
	/**
	 * Retorna uma lista tipada e ordenada de objetos em fun��o das propriedades setadas no objeto de busca.
	 * 
	 * @param <T extends TransferObject>
	 * @param transferObject - TransferObject
	 * @param classe - Class<T>
	 * @param orderBy - String
	 * @throws BDException
	 * @return List<T>
	 */
	public <T extends TransferObject> List<T> listar(TransferObject transferObject, Class<T> classe, String orderBy) throws BDException {
		return this.getDaoHibernate().listar(transferObject, classe, orderBy);
	}

	/**
	 * Retorna uma lista tipada e ordenada de objetos em fun��o das propriedades setadas no objeto de busca.
	 * 
	 * @param TransferObject
	 * @param transferObject - TransferObject
	 * @param orderBy - String
	 * @throws BDException
	 * @return List<TransferObject>
	 */
	public List<TransferObject> listar(TransferObject transferObject, String orderBy) throws BDException {
		return this.getDaoHibernate().listar(transferObject, orderBy);
	}
	
	/**
	 * Realiza o delete do objeto persistente. Use com modera��o.
	 * 
	 * @param transferObject - TransferObject
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 */
	public void excluir(TransferObject transferObject, UsuarioTO usuarioLogado) throws BDException {
		super.excluir(transferObject, usuarioLogado);
	}
	
	/**
	 * Realiza o update do objeto persistente. Use com modera��o, pois o before update n�o � chamado.
	 * Saiba que � um update seguro.
	 * 
	 * @param transferObject - TransferObject
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 * @return void
	 */
	public void update(TransferObject transferObject, UsuarioTO usuarioLogado) throws BDException {
		super.alterar(transferObject, usuarioLogado);
	}
	
	/**
	 * Realiza o insert do objeto persistente. Use com modera��o, pois o before insert n�o � chamado.
	 * Antes de chamar, tenha certeza que � um insert ordin�rio que dispensa valida��o.
	 * 
	 * @param transferObject - TransferObject
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 * @return void
	 */
	public void save(TransferObject transferObject, UsuarioTO usuarioLogado) throws BDException {
		super.incluir(transferObject, usuarioLogado);
	}
	
}
