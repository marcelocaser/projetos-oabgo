package br.org.oabgo.sati.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.sati.negocio.controle.entidade.EmpresaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> Empresa.java <br>
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
public interface Empresa {
	
	public void alterar(EmpresaTO empresa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void excluir(EmpresaTO empresa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void incluir(EmpresaTO empresa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public EmpresaTO consultar(EmpresaTO empresa)
			throws BDException;

	public List<TransferObject> listar(EmpresaTO empresa)
			throws BDException;

	public List<TransferObject> listar(EmpresaTO empresa,
			String orderBy) throws BDException;
	
	public void retirarObjetoSessao(EmpresaTO empresa);

}
