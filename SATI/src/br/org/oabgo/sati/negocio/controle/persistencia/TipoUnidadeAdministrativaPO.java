package br.org.oabgo.sati.negocio.controle.persistencia;

import java.util.List;

import org.springframework.stereotype.Service;

import br.org.oabgo.sati.negocio.controle.SATIPersistencia;
import br.org.oabgo.sati.negocio.controle.entidade.TipoUnidadeAdministrativaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;


/**
 * <b>Classe:</b> TipoUnidadeAdministrativaPO.java <br>
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
@Service("tipoUnidadeAdministrativaPO")
public class TipoUnidadeAdministrativaPO extends SATIPersistencia {
	
	public void alterar(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(tipoUnidadeAdministrativa, usuarioLogado);
	}
	
	public void excluir(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(tipoUnidadeAdministrativa, usuarioLogado);
	}
	
	public void incluir(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(tipoUnidadeAdministrativa, usuarioLogado);
	}
	
	public TipoUnidadeAdministrativaTO consultar(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa) throws BDException{
		return (TipoUnidadeAdministrativaTO) this.getDaoHibernate().consultar(TipoUnidadeAdministrativaTO.class, tipoUnidadeAdministrativa);
	}
	
	public List<TransferObject> listar(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa) throws BDException{
		return this.getDaoHibernate().listar(tipoUnidadeAdministrativa);
	}
	
	public List<TransferObject> listar(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(tipoUnidadeAdministrativa, orderBy);
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
