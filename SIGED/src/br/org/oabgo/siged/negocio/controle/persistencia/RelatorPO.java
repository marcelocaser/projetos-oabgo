package br.org.oabgo.siged.negocio.controle.persistencia;

import java.util.List;

import org.springframework.stereotype.Service;

import br.org.oabgo.siged.negocio.controle.SIGEDPersistencia;
import br.org.oabgo.siged.negocio.controle.entidade.RelatorTO;
import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

@Service("relatorPO")
public class RelatorPO extends SIGEDPersistencia {

	public void alterar(RelatorTO relator, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(relator, usuarioLogado);
	}
	
	public void excluir(RelatorTO relator, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(relator, usuarioLogado);
	}
	
	public void incluir(RelatorTO relator, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(relator, usuarioLogado);
	}
	
	public RelatorTO consultar(RelatorTO relator) throws BDException{
		return (RelatorTO) this.getDaoHibernate().consultar(RelatorTO.class, relator);
	}
	
	public List<TransferObject> listar(RelatorTO relator) throws BDException{
		return this.getDaoHibernate().listar(relator);
	}
	
	public List<TransferObject> listar(RelatorTO relator, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(relator, orderBy);
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
