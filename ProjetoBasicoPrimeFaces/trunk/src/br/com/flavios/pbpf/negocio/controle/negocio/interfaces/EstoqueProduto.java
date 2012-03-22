package br.com.flavios.pbpf.negocio.controle.negocio.interfaces;

import java.util.List;

import br.com.flavios.pbpf.negocio.controle.entidade.EstoqueProdutoTO;
import br.com.flavios.pbpf.negocio.controle.entidade.FilialTO;
import br.com.flavios.pbpf.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

public interface EstoqueProduto {

	public void alterar(EstoqueProdutoTO estoqueProduto, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException;

	public void excluir(EstoqueProdutoTO estoqueProduto, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException;

	public void incluir(EstoqueProdutoTO estoqueProduto, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException;
	
	public List<TransferObject> incluirDataTable(EstoqueProdutoTO estoqueProduto, List<TransferObject> list);

	public EstoqueProdutoTO consultar(EstoqueProdutoTO estoqueProduto, FilialTO filial) throws BDException;

	public EstoqueProdutoTO consultar(EstoqueProdutoTO estoqueProduto)
			throws BDException;

	public List<TransferObject> listar(EstoqueProdutoTO estoqueProduto)
			throws BDException;

	public List<TransferObject> listar(EstoqueProdutoTO estoqueProduto,
			String orderBy) throws BDException;

	public List<TransferObject> listar() throws BDException;

	public void retirarObjetoSessao(EstoqueProdutoTO estoqueProduto);

}
