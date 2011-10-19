package br.org.oabgo.saeo.negocio.controle.persistencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.org.oabgo.saeo.negocio.controle.SAEOPersistencia;
import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

@Service("usuarioPO")
public class UsuarioPO extends SAEOPersistencia {

	public void alterar(UsuarioTO usuario, UsuarioTO usuarioLogado)
			throws BDException {
		super.alterar(usuario, usuarioLogado);
	}
	
	public void excluir(UsuarioTO usuario, UsuarioTO usuarioLogado)
			throws BDException {
		super.excluir(usuario, usuarioLogado);
	}
	
	public void incluir(UsuarioTO usuario, UsuarioTO usuarioLogado)
			throws BDException {
		super.incluir(usuario, usuarioLogado);
	}

	public UsuarioTO consultar(UsuarioTO usuario) throws BDException {
		return (UsuarioTO) this.getDaoHibernate().consultar(UsuarioTO.class,
				usuario);
	}
	
	public List<TransferObject> listar(UsuarioTO usuario) throws BDException{
		return this.getDaoHibernate().listar(usuario);
	}
	
	public List<TransferObject> listar(UsuarioTO usuario, String orderBy) throws BDException{
		return this.getDaoHibernate().listar(usuario, orderBy);
	}

	public UsuarioTO buscarUsuarioPorLogin(String userName) throws BDException {
		Criteria criteria = this.getDaoHibernate().createCriteria(
				UsuarioTO.class);
		criteria.add(Restrictions.eq("bitAtivo", Boolean.TRUE));
		criteria.add(Restrictions.eq("vchLogin", userName));
		return (UsuarioTO) this.getDaoHibernate().buscarObjeto(criteria);
	}

	protected String getNomeOperacaoAlteracao() {
		return "Alterar Usuario";
	}

	protected String getNomeOperacaoExclusao() {
		return "Excluir Usuario";
	}

	protected String getNomeOperacaoInclusao() {
		return "Incluir Usuario";
	}
}
