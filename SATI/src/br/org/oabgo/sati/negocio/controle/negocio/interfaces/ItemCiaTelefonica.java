package br.org.oabgo.sati.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.sati.negocio.controle.entidade.ItemCiaTelefonicaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> ItemCiaTelefonica.java <br>
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
public interface ItemCiaTelefonica {
	
	public void alterar(ItemCiaTelefonicaTO itemCiaTelefonica,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void excluir(ItemCiaTelefonicaTO itemCiaTelefonica,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void incluir(ItemCiaTelefonicaTO itemCiaTelefonica,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public ItemCiaTelefonicaTO consultar(ItemCiaTelefonicaTO itemCiaTelefonica)
			throws BDException;

	public List<TransferObject> listar(ItemCiaTelefonicaTO itemCiaTelefonica)
			throws BDException;

	public List<TransferObject> listar(ItemCiaTelefonicaTO itemCiaTelefonica,
			String orderBy) throws BDException;
	
	public void retirarObjetoSessao(ItemCiaTelefonicaTO itemCiaTelefonica);

}
