package br.org.oabgo.saeo.negocio.controle.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.org.oabgo.saeo.negocio.controle.SAEOPersistencia;
import br.org.oabgo.saeo.negocio.controle.entidade.AreaAtuacaoTO;
import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

@Service("areaAtuacaoPO")
public class AreaAtuacaoPO extends SAEOPersistencia {

	public void alterar(AreaAtuacaoTO areaAtuacao, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(areaAtuacao, usuarioLogado);
	}
	
	public void excluir(AreaAtuacaoTO areaAtuacao, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(areaAtuacao, usuarioLogado);
	}
	
	public void incluir(AreaAtuacaoTO areaAtuacao, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(areaAtuacao, usuarioLogado);
	}
	
	public AreaAtuacaoTO consultar(AreaAtuacaoTO areaAtuacao) throws BDException{
		return (AreaAtuacaoTO) this.getDaoHibernate().consultar(AreaAtuacaoTO.class, areaAtuacao);
	}
	
	public List<TransferObject> listar(AreaAtuacaoTO areaAtuacao) throws BDException{
		return this.getDaoHibernate().listar(areaAtuacao);
	}
	
	public List<TransferObject> listar(AreaAtuacaoTO areaAtuacao, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(areaAtuacao, orderBy);
	}
	
	@SuppressWarnings("unchecked")
	public List<TransferObject> listarPorStatus(Boolean status) throws BDException {
		List<TransferObject> areaAtuacao = new ArrayList<TransferObject>();
		areaAtuacao = this.getDaoHibernate().getSession().createCriteria(AreaAtuacaoTO.class).add(Restrictions.like("bitExameDeOrdem", status)).addOrder(Order.asc("vchDescricao")).list();
		return areaAtuacao;
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
