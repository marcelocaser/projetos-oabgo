package br.org.oabgo.sati.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.sati.negocio.controle.entidade.StatusAutenticacaoAdslTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> StatusAutenticacaoAdsl.java <br>
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
public interface StatusAutenticacaoAdsl {
	
	public void alterar(StatusAutenticacaoAdslTO statusAutenticacaoAdsl,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void excluir(StatusAutenticacaoAdslTO statusAutenticacaoAdsl,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void incluir(StatusAutenticacaoAdslTO statusAutenticacaoAdsl,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public StatusAutenticacaoAdslTO consultar(StatusAutenticacaoAdslTO statusAutenticacaoAdsl)
			throws BDException;

	public List<TransferObject> listar(StatusAutenticacaoAdslTO statusAutenticacaoAdsl)
			throws BDException;

	public List<TransferObject> listar(StatusAutenticacaoAdslTO statusAutenticacaoAdsl,
			String orderBy) throws BDException;
	
	public void retirarObjetoSessao(StatusAutenticacaoAdslTO statusAutenticacaoAdsl);

}
