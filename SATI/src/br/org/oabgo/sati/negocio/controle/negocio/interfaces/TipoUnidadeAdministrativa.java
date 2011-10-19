package br.org.oabgo.sati.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.sati.negocio.controle.entidade.TipoUnidadeAdministrativaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> TipoUnidadeAdministrativa.java <br>
 * <b>Descri��o:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.negocio.interfaces <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Se��o de Goi�s <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
public interface TipoUnidadeAdministrativa {
	
	public void alterar(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void excluir(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void incluir(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public TipoUnidadeAdministrativaTO consultar(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa)
			throws BDException;

	public List<TransferObject> listar(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa)
			throws BDException;

	public List<TransferObject> listar(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa,
			String orderBy) throws BDException;
	
	public void retirarObjetoSessao(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa);

}
