package br.org.oabgo.siged.negocio.controle.negocio.interfaces;

import java.util.Date;
import java.util.List;

import br.org.oabgo.siged.negocio.controle.entidade.DocumentoEletronicoTO;
import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> DocumentoEletronico.java <br>
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
public interface DocumentoEletronico {
	
	public void alterar(DocumentoEletronicoTO documentoEletronico,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void excluir(DocumentoEletronicoTO documentoEletronico,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void incluir(DocumentoEletronicoTO documentoEletronico,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public DocumentoEletronicoTO consultar(DocumentoEletronicoTO documentoEletronico)
			throws BDException;
	
	public DocumentoEletronicoTO consultarPorNumeroProcesso(DocumentoEletronicoTO documentoEletronico)
		throws RegraNegocioException, BDException;

	public List<TransferObject> listar(DocumentoEletronicoTO documentoEletronico)
			throws BDException;

	public List<TransferObject> listar(DocumentoEletronicoTO documentoEletronico,
			String orderBy) throws RegraNegocioException ,BDException;
	
	public List<TransferObject> listar(DocumentoEletronicoTO documentoEletronico, UsuarioTO usuario) 
			throws BDException;
	
	public List<TransferObject> listar(List<TransferObject> documentoEletronicos, UsuarioTO usuario) 
			throws BDException;
	
	public List<TransferObject> listar(Date dataInicial, Date dataFinal, UsuarioTO usuario) 
			throws BDException;
	
	public void retirarObjetoSessao(DocumentoEletronicoTO documentoEletronico);

}
