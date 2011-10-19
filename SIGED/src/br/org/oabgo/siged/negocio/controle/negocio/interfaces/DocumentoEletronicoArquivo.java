package br.org.oabgo.siged.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.siged.negocio.controle.entidade.DocumentoEletronicoArquivoTO;
import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

public interface DocumentoEletronicoArquivo {

	public void alterar(DocumentoEletronicoArquivoTO documentoEletronicoArquivo,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void excluir(DocumentoEletronicoArquivoTO documentoEletronicoArquivo,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void incluir(DocumentoEletronicoArquivoTO documentoEletronicoArquivo,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public DocumentoEletronicoArquivoTO consultar(
			DocumentoEletronicoArquivoTO documentoEletronicoArquivo) throws BDException;

	public DocumentoEletronicoArquivoTO consultarPorNomeDoArquivo(
			DocumentoEletronicoArquivoTO documentoEletronicoArquivo) throws BDException;

	public List<TransferObject> listar(DocumentoEletronicoArquivoTO documentoEletronicoArquivo) throws BDException;

	public List<TransferObject> listar(DocumentoEletronicoArquivoTO documentoEletronicoArquivo, String orderBy)
			throws BDException;
	
	public List<TransferObject> listar(DocumentoEletronicoArquivoTO documentoEletronicoArquivo, UsuarioTO usuario) 
			throws BDException;
	
	public void retirarObjetoSessao(DocumentoEletronicoArquivoTO documentoEletronicoArquivo);

}
