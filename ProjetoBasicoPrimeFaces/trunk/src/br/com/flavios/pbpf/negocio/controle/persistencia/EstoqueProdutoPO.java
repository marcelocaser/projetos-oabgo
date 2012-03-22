package br.com.flavios.pbpf.negocio.controle.persistencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.com.flavios.pbpf.negocio.controle.PBPFPersistencia;
import br.com.flavios.pbpf.negocio.controle.entidade.EstoqueProdutoTO;
import br.com.flavios.pbpf.negocio.controle.entidade.FilialTO;
import br.com.flavios.pbpf.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

@Service("estoqueProdutoPO")
public class EstoqueProdutoPO extends PBPFPersistencia {

	public void alterar(EstoqueProdutoTO estoqueProduto, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		super.alterar(estoqueProduto, usuarioLogado);
	}

	public void excluir(EstoqueProdutoTO estoqueProduto, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		super.excluir(estoqueProduto, usuarioLogado);
	}

	public void incluir(EstoqueProdutoTO estoqueProduto, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		super.incluir(estoqueProduto, usuarioLogado);
	}

	public EstoqueProdutoTO consultar(EstoqueProdutoTO estoqueProduto, FilialTO filial) throws BDException {
		Criteria criteria = this.getDaoHibernate().createCriteria(EstoqueProdutoTO.class);
		criteria.add(Restrictions.eq("proBarNovo", estoqueProduto.getProBarNovo()));
		criteria.add(Restrictions.eq("estoqueProdutoChaveComposta.filial", filial));
		return (EstoqueProdutoTO) this.getDaoHibernate().buscarObjeto(criteria);
	}

	public EstoqueProdutoTO consultar(EstoqueProdutoTO estoqueProduto)
			throws BDException {
		return (EstoqueProdutoTO) this.getDaoHibernate().consultar(EstoqueProdutoTO.class, estoqueProduto);
	}

	public List<TransferObject> listar(EstoqueProdutoTO estoqueProduto)
			throws BDException {
		return this.getDaoHibernate().listar(estoqueProduto);
	}

	public List<TransferObject> listar(EstoqueProdutoTO estoqueProduto,
			String orderBy) throws BDException {
		return this.getDaoHibernate().listar(estoqueProduto, orderBy);
	}

	public List<TransferObject> listar() throws BDException {
		Criteria criteria = this.getDaoHibernate().createCriteria(
				EstoqueProdutoTO.class);
		return (List<TransferObject>) this.getDaoHibernate().executar(criteria);
	}

	protected String getNomeOperacaoAlteracao() {
		return "Alterar Estoque";
	}

	protected String getNomeOperacaoExclusao() {
		return "Excluir Estoque";
	}

	protected String getNomeOperacaoInclusao() {
		return "Incluir Estoque";
	}

}
