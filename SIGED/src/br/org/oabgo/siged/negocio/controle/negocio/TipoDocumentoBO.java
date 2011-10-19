package br.org.oabgo.siged.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.siged.negocio.controle.entidade.TipoDocumentoTO;
import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.TipoDocumento;
import br.org.oabgo.siged.negocio.controle.persistencia.TipoDocumentoPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> TipoDocumentoBO.java <br>
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
public class TipoDocumentoBO implements TipoDocumento {
	
	@Autowired
	private TipoDocumentoPO persistencia;
	
	protected void beforeInsert(TransferObject bean) throws RegraNegocioException {
	}

	protected void beforeUpdate(TransferObject bean) throws RegraNegocioException {
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(TipoDocumentoTO tipoDocumento, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeUpdate(tipoDocumento);
		this.persistencia.alterar(tipoDocumento, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(TipoDocumentoTO tipoDocumento, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.persistencia.excluir(tipoDocumento, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(TipoDocumentoTO tipoDocumento, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeInsert(tipoDocumento);
		this.persistencia.incluir(tipoDocumento, usuarioLogado);
	}
	
	public TipoDocumentoTO consultar(TipoDocumentoTO tipoDocumento)
			throws BDException {
		return this.persistencia.consultar(tipoDocumento);
	}

	public List<TransferObject> listar(TipoDocumentoTO tipoDocumento)
			throws BDException {
		return this.persistencia.listar(tipoDocumento);
	}

	public List<TransferObject> listar(TipoDocumentoTO tipoDocumento,
			String orderBy) throws BDException {
		return this.persistencia.listar(tipoDocumento, orderBy);
	}
	
	public List<TransferObject> listarAtivos(TipoDocumentoTO tipoDocumento) throws BDException {
		return this.persistencia.listarAtivos(tipoDocumento);
	}

	public void retirarObjetoSessao(TipoDocumentoTO tipoDocumento) {
		this.persistencia.retirarObjetoSessao(tipoDocumento);
	}

}
