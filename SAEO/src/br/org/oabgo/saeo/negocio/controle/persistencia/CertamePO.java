package br.org.oabgo.saeo.negocio.controle.persistencia;

import java.util.List;

import org.springframework.stereotype.Service;

import br.org.oabgo.saeo.negocio.controle.SAEOPersistencia;
import br.org.oabgo.saeo.negocio.controle.entidade.CertameTO;
import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

@Service("certamePO")
public class CertamePO extends SAEOPersistencia {

	public void alterar(CertameTO certame, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(certame, usuarioLogado);
	}
	
	public void excluir(CertameTO certame, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(certame, usuarioLogado);
	}
	
	public void incluir(CertameTO certame, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(certame, usuarioLogado);
	}
	
	public CertameTO consultar(CertameTO certame) throws BDException{
		return (CertameTO) this.getDaoHibernate().consultar(CertameTO.class, certame);
	}
	
	public List<TransferObject> listar(CertameTO certame) throws BDException{
		return this.getDaoHibernate().listar(certame);
	}
	
	public List<TransferObject> listar(CertameTO certame, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(certame, orderBy);
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
