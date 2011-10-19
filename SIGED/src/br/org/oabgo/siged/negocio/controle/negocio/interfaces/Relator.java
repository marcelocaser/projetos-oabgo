package br.org.oabgo.siged.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.siged.negocio.controle.entidade.RelatorTO;
import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

public interface Relator {
	
	public void alterar(RelatorTO relator, UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void excluir(RelatorTO relator, UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void incluir(RelatorTO relator, UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public RelatorTO consultar(RelatorTO relator) throws BDException;

	public List<TransferObject> listar(RelatorTO relator) throws BDException;

	public List<TransferObject> listar(RelatorTO relator, String orderBy) throws BDException;

	public void retirarObjetoSessao(RelatorTO relator);

}
