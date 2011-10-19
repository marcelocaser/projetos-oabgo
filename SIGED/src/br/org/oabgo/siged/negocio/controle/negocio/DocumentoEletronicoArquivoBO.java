package br.org.oabgo.siged.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.siged.negocio.controle.entidade.DocumentoEletronicoArquivoTO;
import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.DocumentoEletronicoArquivo;
import br.org.oabgo.siged.negocio.controle.persistencia.DocumentoEletronicoArquivoPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

@Service
public class DocumentoEletronicoArquivoBO implements DocumentoEletronicoArquivo {

	@Autowired
	private DocumentoEletronicoArquivoPO persistencia;

	protected void beforeInsert(TransferObject bean)
			throws RegraNegocioException {
	}

	protected void beforeUpdate(TransferObject bean)
			throws RegraNegocioException {
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(DocumentoEletronicoArquivoTO documentoEletronicoArquivo,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeUpdate(documentoEletronicoArquivo);
		this.persistencia.alterar(documentoEletronicoArquivo, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(DocumentoEletronicoArquivoTO documentoEletronicoArquivo,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.persistencia.excluir(documentoEletronicoArquivo, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(DocumentoEletronicoArquivoTO documentoEletronicoArquivo,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeInsert(documentoEletronicoArquivo);
		this.persistencia.incluir(documentoEletronicoArquivo, usuarioLogado);
	}

	public DocumentoEletronicoArquivoTO consultar(
			DocumentoEletronicoArquivoTO documentoEletronicoArquivo) throws BDException {
		return this.persistencia.consultar(documentoEletronicoArquivo);
	}

	public DocumentoEletronicoArquivoTO consultarPorNomeDoArquivo(
			DocumentoEletronicoArquivoTO documentoEletronicoArquivo) throws BDException {
		return this.persistencia.consultarPorNomeDoArquivo(documentoEletronicoArquivo);
	}

	public List<TransferObject> listar(
			DocumentoEletronicoArquivoTO documentoEletronicoArquivo) throws BDException {
		return this.persistencia.listar(documentoEletronicoArquivo);
	}

	public List<TransferObject> listar(
			DocumentoEletronicoArquivoTO documentoEletronicoArquivo, String orderBy)
			throws BDException {
		return this.persistencia.listar(documentoEletronicoArquivo, orderBy);
	}
	
	public List<TransferObject> listar(DocumentoEletronicoArquivoTO documentoEletronicoArquivo, UsuarioTO usuario) 
			throws BDException {
		return this.persistencia.listar(documentoEletronicoArquivo, usuario);
	}
	
	public void retirarObjetoSessao(
			DocumentoEletronicoArquivoTO documentoEletronicoArquivo) {
		this.persistencia.retirarObjetoSessao(documentoEletronicoArquivo);
	}

}
