package br.org.oabgo.sati.negocio.controle.persistencia;

import java.util.List;

import org.springframework.stereotype.Service;

import br.org.oabgo.sati.negocio.controle.SATIPersistencia;
import br.org.oabgo.sati.negocio.controle.entidade.ItemCiaTelefonicaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

/**
 * <b>Classe:</b> ItemCiaTelefonicaPO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.persistencia <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@Service("itemCiaTelefonicaPO")
public class ItemCiaTelefonicaPO extends SATIPersistencia {
	
	public void alterar(ItemCiaTelefonicaTO itemCiaTelefonica, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(itemCiaTelefonica, usuarioLogado);
	}
	
	public void excluir(ItemCiaTelefonicaTO itemCiaTelefonica, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(itemCiaTelefonica, usuarioLogado);
	}
	
	public void incluir(ItemCiaTelefonicaTO itemCiaTelefonica, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(itemCiaTelefonica, usuarioLogado);
	}
	
	public ItemCiaTelefonicaTO consultar(ItemCiaTelefonicaTO itemCiaTelefonica) throws BDException{
		return (ItemCiaTelefonicaTO) this.getDaoHibernate().consultar(ItemCiaTelefonicaTO.class, itemCiaTelefonica);
	}
	
	public List<TransferObject> listar(ItemCiaTelefonicaTO itemCiaTelefonica) throws BDException{
		return this.getDaoHibernate().listar(itemCiaTelefonica);
	}
	
	public List<TransferObject> listar(ItemCiaTelefonicaTO itemCiaTelefonica, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(itemCiaTelefonica, orderBy);
	}
	
	protected String getNomeOperacaoAlteracao() {
		return "Alterar Sistema";
	}

	protected String getNomeOperacaoExclusao() {
		return "Excluir Sistema";
	}

	protected String getNomeOperacaoInclusao() {
		return "Incluir Sistema";
	}

}
