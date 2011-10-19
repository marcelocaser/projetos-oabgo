package br.org.oabgo.sati.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import br.org.oabgo.sati.negocio.controle.entidade.ItemCiaTelefonicaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.ItemCiaTelefonica;
import br.org.oabgo.sati.negocio.controle.persistencia.ItemCiaTelefonicaPO;

/**
 * <b>Classe:</b> ItemCiaTelefonicaBO.java <br>
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
public class ItemCiaTelefonicaBO implements ItemCiaTelefonica {
	
	@Autowired
	private ItemCiaTelefonicaPO persistencia;
	
	protected void beforeInsert(TransferObject bean) throws RegraNegocioException {
		ItemCiaTelefonicaTO itemCiaTelefonica = (ItemCiaTelefonicaTO) bean;
		
		//remove mascara do campo TELEFONE
		itemCiaTelefonica.setVchNumrTelefone(itemCiaTelefonica.getVchNumrTelefone().replaceAll("[()-]", ""));
	}

	protected void beforeUpdate(TransferObject bean) throws RegraNegocioException {
		ItemCiaTelefonicaTO itemCiaTelefonica = (ItemCiaTelefonicaTO) bean;
		
		//remove mascara do campo TELEFONE
		itemCiaTelefonica.setVchNumrTelefone(itemCiaTelefonica.getVchNumrTelefone().replaceAll("[ ()-]", ""));
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(ItemCiaTelefonicaTO itemCiaTelefonica,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeUpdate(itemCiaTelefonica);
		this.persistencia.alterar(itemCiaTelefonica, usuarioLogado);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(ItemCiaTelefonicaTO itemCiaTelefonica,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.persistencia.excluir(itemCiaTelefonica, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(ItemCiaTelefonicaTO itemCiaTelefonica,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeInsert(itemCiaTelefonica);
		this.persistencia.incluir(itemCiaTelefonica, usuarioLogado);
	}

	public ItemCiaTelefonicaTO consultar(ItemCiaTelefonicaTO itemCiaTelefonica)
			throws BDException {
		return this.persistencia.consultar(itemCiaTelefonica);
	}

	public List<TransferObject> listar(ItemCiaTelefonicaTO itemCiaTelefonica)
			throws BDException {
		return this.persistencia.listar(itemCiaTelefonica);
	}

	public List<TransferObject> listar(ItemCiaTelefonicaTO itemCiaTelefonica,
			String orderBy) throws BDException {
		return this.persistencia.listar(itemCiaTelefonica, orderBy);
	}

	public void retirarObjetoSessao(ItemCiaTelefonicaTO itemCiaTelefonica) {
		this.persistencia.retirarObjetoSessao(itemCiaTelefonica);
	}

}
