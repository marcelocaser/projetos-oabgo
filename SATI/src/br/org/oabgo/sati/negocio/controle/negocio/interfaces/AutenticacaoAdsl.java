package br.org.oabgo.sati.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.sati.negocio.controle.entidade.AutenticacaoAdslTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> AutenticacaoAdsl.java <br>
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
public interface AutenticacaoAdsl {
	
	public void alterar(AutenticacaoAdslTO autenticacaoAdsl,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void excluir(AutenticacaoAdslTO autenticacaoAdsl,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void incluir(AutenticacaoAdslTO autenticacaoAdsl,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public AutenticacaoAdslTO consultar(AutenticacaoAdslTO autenticacaoAdsl)
			throws BDException;

	public List<TransferObject> listar(AutenticacaoAdslTO autenticacaoAdsl)
			throws BDException;

	public List<TransferObject> listar(AutenticacaoAdslTO autenticacaoAdsl,
			String orderBy) throws BDException;
	
	public void retirarObjetoSessao(AutenticacaoAdslTO autenticacaoAdsl);

}
