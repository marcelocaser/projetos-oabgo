package br.org.oabgo.saeo.negocio.controle.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import br.org.oabgo.saeo.negocio.controle.SAEOPersistencia;
import br.org.oabgo.saeo.negocio.controle.entidade.CandidatoAnteriorExameOrdemTO;
import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.enumeration.OrderType;
import core.excecoes.BDException;

@Service("candidatoAnteriorExameOrdemPO")
public class CandidatoAnteriorExameOrdemPO extends SAEOPersistencia {

	public void alterar(CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(candidatoAnteriorExameOrdem, usuarioLogado);
	}
	
	public void excluir(CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(candidatoAnteriorExameOrdem, usuarioLogado);
	}
	
	public void incluir(CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(candidatoAnteriorExameOrdem, usuarioLogado);
	}
	
	public CandidatoAnteriorExameOrdemTO consultar(CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem) throws BDException{
		return (CandidatoAnteriorExameOrdemTO) this.getDaoHibernate().consultar(CandidatoAnteriorExameOrdemTO.class, candidatoAnteriorExameOrdem);
	}
	
	public List<TransferObject> listar(CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem) throws BDException{
		return this.getDaoHibernate().listar(candidatoAnteriorExameOrdem);
	}
	
	public List<TransferObject> listar(CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(candidatoAnteriorExameOrdem, orderBy);
	}
	
	public List<TransferObject> listar(CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem, String orderBy, OrderType orderType) throws BDException {
		return this.getDaoHibernate().listar(candidatoAnteriorExameOrdem, orderBy, orderType);
	}
	
	@SuppressWarnings("unchecked")
	public List<TransferObject> listarDezUltimos() throws BDException {
		List<TransferObject> candidatoAnteriorExameOrdem = new ArrayList<TransferObject>();
		candidatoAnteriorExameOrdem = this.getDaoHibernate().getSession().createCriteria(CandidatoAnteriorExameOrdemTO.class).addOrder(Order.desc("id")).setMaxResults(10).list();
		return candidatoAnteriorExameOrdem;
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
