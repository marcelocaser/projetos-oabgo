package br.org.oabgo.siged.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.siged.negocio.controle.entidade.TipoDocumentoTO;
import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> TipoDocumento.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SIGED <br>
 * <b>Pacote:</b> br.org.oabgo.siged.negocio.controle.negocio.interfaces <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
public interface TipoDocumento {
	
	public void alterar(TipoDocumentoTO tipoDocumento,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void excluir(TipoDocumentoTO tipoDocumento,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void incluir(TipoDocumentoTO tipoDocumento,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public TipoDocumentoTO consultar(TipoDocumentoTO tipoDocumento)
			throws BDException;

	public List<TransferObject> listar(TipoDocumentoTO tipoDocumento)
			throws BDException;

	public List<TransferObject> listar(TipoDocumentoTO tipoDocumento,
			String orderBy) throws BDException;
	
	public List<TransferObject> listarAtivos(TipoDocumentoTO tipoDocumento) throws BDException;
	
	public void retirarObjetoSessao(TipoDocumentoTO tipoDocumento);

}
