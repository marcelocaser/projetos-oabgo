package br.org.oabgo.sati.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioUnidadeAdministrativaTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> UsuarioUnidadeAdministrativa.java <br>
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
public interface UsuarioUnidadeAdministrativa {
	
	public void alterar(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void excluir(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void incluir(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public UsuarioUnidadeAdministrativaTO consultar(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa)
			throws BDException;

	public List<TransferObject> listar(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa)
			throws BDException;

	public List<TransferObject> listar(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa,
			String orderBy) throws BDException;
	
	public void retirarObjetoSessao(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa);


}
