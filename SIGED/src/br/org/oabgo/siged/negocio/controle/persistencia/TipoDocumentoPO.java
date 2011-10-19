package br.org.oabgo.siged.negocio.controle.persistencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.org.oabgo.siged.negocio.controle.SIGEDPersistencia;
import br.org.oabgo.siged.negocio.controle.entidade.TipoDocumentoTO;
import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

/**
 * <b>Classe:</b> TipoDocumentoPO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SIGED <br>
 * <b>Pacote:</b> br.org.oabgo.siged.negocio.controle.persistencia <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@Service("tipoDocumentoPO")
public class TipoDocumentoPO extends SIGEDPersistencia {

	public void alterar(TipoDocumentoTO tipoDocumento, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(tipoDocumento, usuarioLogado);
	}
	
	public void excluir(TipoDocumentoTO tipoDocumento, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(tipoDocumento, usuarioLogado);
	}
	
	public void incluir(TipoDocumentoTO tipoDocumento, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(tipoDocumento, usuarioLogado);
	}
	
	public TipoDocumentoTO consultar(TipoDocumentoTO tipoDocumento) throws BDException{
		return (TipoDocumentoTO) this.getDaoHibernate().consultar(TipoDocumentoTO.class, tipoDocumento);
	}
	
	public List<TransferObject> listar(TipoDocumentoTO tipoDocumento) throws BDException{
		return this.getDaoHibernate().listar(tipoDocumento);
	}
	
	public List<TransferObject> listar(TipoDocumentoTO tipoDocumento, String orderBy) throws BDException{
		return this.getDaoHibernate().listar(tipoDocumento, orderBy);
	}
	
	public List<TransferObject> listarAtivos(TipoDocumentoTO tipoDocumento) throws BDException{
		Criteria criteria = this.getDaoHibernate().createCriteria(
				TipoDocumentoTO.class);
		criteria.add(Restrictions.eq("bitAtivo", Boolean.TRUE));
		criteria.addOrder(Order.asc("vchDescricao"));
		return (List<TransferObject>) this.getDaoHibernate().executar(criteria);
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
