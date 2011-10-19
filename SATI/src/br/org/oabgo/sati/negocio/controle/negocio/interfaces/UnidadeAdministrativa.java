package br.org.oabgo.sati.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.sati.negocio.controle.entidade.UnidadeAdministrativaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> UnidadeAdministrativa.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.negocio.interfaces <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
public interface UnidadeAdministrativa {

	public void alterar(UnidadeAdministrativaTO unidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void excluir(UnidadeAdministrativaTO unidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void incluir(UnidadeAdministrativaTO unidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public UnidadeAdministrativaTO consultar(UnidadeAdministrativaTO unidadeAdministrativa)
			throws BDException;

	public List<TransferObject> listar(UnidadeAdministrativaTO unidadeAdministrativa)
			throws BDException;

	public List<TransferObject> listar(UnidadeAdministrativaTO unidadeAdministrativa,
			String orderBy) throws BDException;
	
	public void retirarObjetoSessao(UnidadeAdministrativaTO unidadeAdministrativa);

}
