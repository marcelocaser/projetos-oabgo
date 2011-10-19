package br.org.oabgo.sati.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.sati.negocio.controle.entidade.EmpresaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Empresa;
import br.org.oabgo.sati.negocio.controle.persistencia.EmpresaPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> EmpresaBO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.negocio <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@Service
public class EmpresaBO implements Empresa {
	
	@Autowired
	private EmpresaPO persistencia;
	
	protected void beforeInsert(TransferObject bean) throws RegraNegocioException {
		EmpresaTO empresa = (EmpresaTO) bean;
		
		//remove mascara dos campos CNPJ e CEP
		empresa.setVchCEP(empresa.getVchCEP().replaceAll("[.-]", ""));
		empresa.setVchCNPJ(empresa.getVchCNPJ().replaceAll("[./-]", ""));
		
	}

	protected void beforeUpdate(TransferObject bean) throws RegraNegocioException {
		EmpresaTO empresa = (EmpresaTO) bean;
		
		//remove mascara dos campos CNPJ e CEP
		empresa.setVchCEP(empresa.getVchCEP().replaceAll("[.-]", ""));
		empresa.setVchCNPJ(empresa.getVchCNPJ().replaceAll("[./-]", ""));
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(EmpresaTO empresa, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeUpdate(empresa);
		this.persistencia.alterar(empresa, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(EmpresaTO empresa, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.persistencia.excluir(empresa, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(EmpresaTO empresa, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeInsert(empresa);
		this.persistencia.incluir(empresa, usuarioLogado);
	}

	public EmpresaTO consultar(EmpresaTO empresa) throws BDException {
		return this.persistencia.consultar(empresa);
	}

	public List<TransferObject> listar(EmpresaTO empresa) throws BDException {
		return this.persistencia.listar(empresa);
	}

	public List<TransferObject> listar(EmpresaTO empresa, String orderBy)
			throws BDException {
		return this.persistencia.listar(empresa, orderBy);
	}

	public void retirarObjetoSessao(EmpresaTO empresa) {
		this.persistencia.retirarObjetoSessao(empresa);
	}

}
