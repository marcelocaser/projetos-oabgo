package br.org.oabgo.siged.negocio.controle.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.siged.negocio.controle.entidade.DocumentoEletronicoTO;
import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.DocumentoEletronico;
import br.org.oabgo.siged.negocio.controle.persistencia.DocumentoEletronicoPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import core.mensagem.MensagemLista;

/**
 * <b>Classe:</b> DocumentoEletronicoBO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SIGED <br>
 * <b>Pacote:</b> br.org.oabgo.siged.negocio.controle.negocio <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@Service
public class DocumentoEletronicoBO implements DocumentoEletronico {
	
	@Autowired
	private DocumentoEletronicoPO persistencia;
	
	protected void beforeInsert(TransferObject bean) throws RegraNegocioException {
		
		DocumentoEletronicoTO documentoEletronico = (DocumentoEletronicoTO) bean;
		
		DocumentoEletronicoTO documentoTmp = new DocumentoEletronicoTO();
		
		documentoEletronico.setVchNumeroProcesso(documentoEletronico.getVchNumeroProcesso().replaceAll("[/]", ""));
		
		documentoTmp.setVchNumeroProcesso(documentoEletronico.getVchNumeroProcesso());
		
		MensagemLista msgs = new MensagemLista();
		
		if(this.persistencia.beforeInsert(documentoTmp)){
			msgs.addMensagem("sigedDocumentoEletronicoJaCadastrado");
		}
		
		if(msgs.getNumeroRegistros() > 0) {
			throw new RegraNegocioException(msgs);
		}
		
	}

	protected void beforeUpdate(TransferObject bean) throws RegraNegocioException {
	}
	
	protected void beforeListar(TransferObject bean) throws RegraNegocioException {
		
		DocumentoEletronicoTO documentoEletronico = (DocumentoEletronicoTO) bean;
		
		documentoEletronico.setVchNumeroProcesso(documentoEletronico.getVchNumeroProcesso() == null ? null : documentoEletronico.getVchNumeroProcesso().replaceAll("/", ""));
		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(DocumentoEletronicoTO documentoEletronico,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeUpdate(documentoEletronico);
		this.persistencia.alterar(documentoEletronico, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(DocumentoEletronicoTO documentoEletronico,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.persistencia.excluir(documentoEletronico, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(DocumentoEletronicoTO documentoEletronico,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeInsert(documentoEletronico);
		this.persistencia.incluir(documentoEletronico, usuarioLogado);
	}
	
	public DocumentoEletronicoTO consultar(DocumentoEletronicoTO documentoEletronico) throws BDException {
		return this.persistencia.consultar(documentoEletronico);
	}
	
	public DocumentoEletronicoTO consultarPorNumeroProcesso(DocumentoEletronicoTO documentoEletronico)
		throws RegraNegocioException, BDException {
		this.beforeListar(documentoEletronico);
		return this.persistencia.consultarPorNumeroProcesso(documentoEletronico);
	}

	public List<TransferObject> listar(DocumentoEletronicoTO documentoEletronico)
			throws BDException {
		return this.persistencia.listar(documentoEletronico);
	}

	public List<TransferObject> listar(DocumentoEletronicoTO documentoEletronico, String orderBy)
			throws RegraNegocioException, BDException {
		this.beforeListar(documentoEletronico);
		return this.persistencia.listar(documentoEletronico, orderBy);
	}
	
	public List<TransferObject> listar(DocumentoEletronicoTO documentoEletronico, UsuarioTO usuario) throws BDException {
		return this.persistencia.listar(documentoEletronico, usuario);
	}
	
	public List<TransferObject> listar(Date dataInicial, Date dataFinal, UsuarioTO usuario) 
		throws BDException {
		List<TransferObject> lista = this.persistencia.listar(dataInicial, dataFinal, usuario);
		return this.listar(lista, usuario);
	}
	
	public List<TransferObject> listar(List<TransferObject> documentoEletronicos, UsuarioTO usuario) 
		throws BDException {
		List<TransferObject> lista = new ArrayList<TransferObject>();
		for (Iterator<TransferObject> iterator = documentoEletronicos.iterator(); iterator.hasNext();) {
			DocumentoEletronicoTO documentoEletronico = (DocumentoEletronicoTO) iterator.next();
			lista.addAll(this.listar(documentoEletronico, usuario));
		}
		return lista;
}
	
	public void retirarObjetoSessao(DocumentoEletronicoTO documentoEletronico) {
		this.persistencia.retirarObjetoSessao(documentoEletronico);
	}

}
