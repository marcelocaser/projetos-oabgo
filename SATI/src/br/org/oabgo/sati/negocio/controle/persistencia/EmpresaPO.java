package br.org.oabgo.sati.negocio.controle.persistencia;

import java.util.List;

import org.springframework.stereotype.Service;

import br.org.oabgo.sati.negocio.controle.SATIPersistencia;
import br.org.oabgo.sati.negocio.controle.entidade.EmpresaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

/**
 * <b>Classe:</b> EmpresaPO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.persistencia <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@Service("empresaPO")
public class EmpresaPO extends SATIPersistencia {

	public void alterar(EmpresaTO empresa, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(empresa, usuarioLogado);
	}
	
	public void excluir(EmpresaTO empresa, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(empresa, usuarioLogado);
	}
	
	public void incluir(EmpresaTO empresa, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(empresa, usuarioLogado);
	}
	
	public EmpresaTO consultar(EmpresaTO empresa) throws BDException{
		return (EmpresaTO) this.getDaoHibernate().consultar(EmpresaTO.class, empresa);
	}
	
	public List<TransferObject> listar(EmpresaTO empresa) throws BDException{
		return this.getDaoHibernate().listar(empresa);
	}
	
	public List<TransferObject> listar(EmpresaTO empresa, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(empresa, orderBy);
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
