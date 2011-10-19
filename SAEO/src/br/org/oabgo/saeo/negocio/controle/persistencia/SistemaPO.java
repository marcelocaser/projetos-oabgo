package br.org.oabgo.saeo.negocio.controle.persistencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.org.oabgo.saeo.negocio.controle.SAEOPersistencia;
import br.org.oabgo.saeo.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

@Service("sistemaPO")
public class SistemaPO extends SAEOPersistencia {

	public void alterar(SistemaTO sistema, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(sistema, usuarioLogado);
	}
	
	public void excluir(SistemaTO sistema, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(sistema, usuarioLogado);
	}
	
	public void incluir(SistemaTO sistema, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(sistema, usuarioLogado);
	}
	
	public SistemaTO consultar(SistemaTO sistema) throws BDException{
		return (SistemaTO) this.getDaoHibernate().consultar(SistemaTO.class, sistema);
	}
	
	public List<TransferObject> listar(SistemaTO sistema, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(sistema, orderBy);
	}
	
	public SistemaTO buscarObjeto(SistemaTO sistema) throws BDException{
		return (SistemaTO) this.getDaoHibernate().buscarObjeto(sistema);
	}
	
	public void retirarObjetoSessao(SistemaTO sistema) throws BDException{
		this.getDaoHibernate().retirarObjetoSessao(sistema);
	}
	
	public void inicializarObjeto(SistemaTO sistema) throws BDException{
		this.getDaoHibernate().inicializar(sistema);
	}

	public SistemaTO consultarSistemaNome(String nome) throws BDException{
		Criteria criteria = getDaoHibernate().createCriteria(SistemaTO.class);
		//busca atraves do contexto
		criteria.add(Restrictions.eq("vchNome", nome));
		return (SistemaTO) this.getDaoHibernate().buscarObjeto(criteria);
	}

	public SistemaTO consultarSistemaContexto(String contexto) throws BDException{
		SistemaTO sistemaTO = new SistemaTO();
		sistemaTO.setVchContextoAplicacao(contexto);
		return (SistemaTO) this.getDaoHibernate().buscarObjeto(sistemaTO);//.buscarObjeto(criteria);
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
