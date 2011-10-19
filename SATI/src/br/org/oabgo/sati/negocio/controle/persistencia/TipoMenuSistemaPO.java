package br.org.oabgo.sati.negocio.controle.persistencia;

import java.util.List;

import org.springframework.stereotype.Service;

import br.org.oabgo.sati.negocio.controle.SATIPersistencia;
import br.org.oabgo.sati.negocio.controle.entidade.TipoMenuSistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

/**
 * <b>Classe:</b> TipoMenuSistemaPO.java <br>
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
@Service("tipoMenuSistemaPO")
public class TipoMenuSistemaPO extends SATIPersistencia {
	
	public void alterar(TipoMenuSistemaTO tipoMenuSistema, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(tipoMenuSistema, usuarioLogado);
	}
	
	public void excluir(TipoMenuSistemaTO tipoMenuSistema, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(tipoMenuSistema, usuarioLogado);
	}
	
	public void incluir(TipoMenuSistemaTO tipoMenuSistema, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(tipoMenuSistema, usuarioLogado);
	}
	
	public TipoMenuSistemaTO consultar(TipoMenuSistemaTO tipoMenuSistema) throws BDException{
		return (TipoMenuSistemaTO) this.getDaoHibernate().consultar(TipoMenuSistemaTO.class, tipoMenuSistema);
	}
	
	public List<TransferObject> listar(TipoMenuSistemaTO tipoMenuSistema) throws BDException{
		return this.getDaoHibernate().listar(tipoMenuSistema);
	}
	
	public List<TransferObject> listar(TipoMenuSistemaTO tipoMenuSistema, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(tipoMenuSistema, orderBy);
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
