package br.org.oabgo.sati.negocio.controle.persistencia;

import java.util.List;

import org.springframework.stereotype.Service;

import br.org.oabgo.sati.negocio.controle.SATIPersistencia;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> PerfilAcessoPO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.persistencia <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
@Service("perfilAcessoPO")
public class PerfilAcessoPO extends SATIPersistencia {
	
	/**
	 * Persiste as informações contidas no objeto passado como parâmetro.
	 * 
	 * @param perfilAcesso - PerfilAcessoTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 */
	public void alterar(PerfilAcessoTO perfilAcesso, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(perfilAcesso, usuarioLogado);
	}
	
	/**
	 * Exclui o objeto passado como referencia.
	 * 
	 * @param perfilAcesso - PerfilAcessoTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 */
	public void excluir(PerfilAcessoTO perfilAcesso, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(perfilAcesso, usuarioLogado);
	}

	/**
	 * Inclui as informações contidas no objeto.
	 * 
	 * @param perfilAcesso - PerfilAcessoTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 */
	public void incluir(PerfilAcessoTO perfilAcesso, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(perfilAcesso, usuarioLogado);
	}
	
	/**
	 * Consulta um objeto.
	 * 
	 * @param perfilAcesso - PerfilAcessoTO
	 * @return PerfilAcessoTO
	 * @throws BDException
	 */
	public PerfilAcessoTO consultar(PerfilAcessoTO perfilAcesso) throws BDException{
		return (PerfilAcessoTO)super.consultar(perfilAcesso);
	}
	
	/**
	 * Retorna a lista de objetos listados com base nas propriedades do
	 * TransferObject passado como parâmetro.
	 * 
	 * @param transferObject - TransferObject 
	 * @param orderBy - String, parâmetro de ordenação
	 * @return List
	 * @throws RegraNegocioException
	 */
	public List<TransferObject> listar(PerfilAcessoTO perfil, String orderBy) throws BDException{
		return this.getDaoHibernate().listar(perfil, orderBy);
	}

	/**
	 * Busca o primeiro objeto encontrado com as configurações do objeto parametro.
	 * 
	 * @param perfilAcesso - PerfilAcessoTO
	 * @return PerfilAcessoTO
	 * @throws BDException
	 */
	public PerfilAcessoTO buscarObjeto(PerfilAcessoTO perfilAcesso) throws BDException{
		return (PerfilAcessoTO) this.getDaoHibernate().buscarObjeto(perfilAcesso);
	}
	
	/**
	 * Retorna o nome da operação de alteração.
	 * @return String
	 */
	protected String getNomeOperacaoAlteracao() {
		return "Alterar Perfil Acesso";
	}

	/**
	 * Retorna o nome da operação de exclusão.
	 * @return String
	 */
	protected String getNomeOperacaoExclusao() {
		return "Excluir Perfil Acesso";
	}
	
	/**
	 * Retorna o nome da operação de inclusão.
	 * @return String
	 */
	protected String getNomeOperacaoInclusao() {
		return "Incluir Perfil Acesso";
	}
	
}
