package br.org.oabgo.siged.negocio.controle.persistencia;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.org.oabgo.siged.negocio.controle.SIGEDPersistencia;
import br.org.oabgo.siged.negocio.controle.entidade.DocumentoEletronicoArquivoTO;
import br.org.oabgo.siged.negocio.controle.entidade.DocumentoEletronicoTO;
import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

/**
 * <b>Classe:</b> DocumentoEletronicoPO.java <br>
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
@Service("documentoEletronicoPO")
public class DocumentoEletronicoPO extends SIGEDPersistencia {

	public void alterar(DocumentoEletronicoTO documentoEletronico, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(documentoEletronico, usuarioLogado);
	}
	
	public void excluir(DocumentoEletronicoTO documentoEletronico, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(documentoEletronico, usuarioLogado);
	}
	
	public void incluir(DocumentoEletronicoTO documentoEletronico, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(documentoEletronico, usuarioLogado);
	}
	
	public DocumentoEletronicoTO consultar(DocumentoEletronicoTO documentoEletronico) throws BDException{
		return (DocumentoEletronicoTO) this.getDaoHibernate().consultar(DocumentoEletronicoTO.class, documentoEletronico);
	}
	
	public DocumentoEletronicoTO consultarPorNumeroProcesso(DocumentoEletronicoTO documentoEletronico)
		throws BDException {
		Criteria criteria = this.getDaoHibernate().createCriteria(DocumentoEletronicoTO.class);
		criteria.add(Restrictions.eq("vchNumeroProcesso", documentoEletronico.getVchNumeroProcesso()));
		return (DocumentoEletronicoTO) this.getDaoHibernate().buscarObjeto(criteria);
	}
	
	public List<TransferObject> listar(DocumentoEletronicoTO documentoEletronico) throws BDException{
		return this.getDaoHibernate().listar(documentoEletronico);
	}
	
	public List<TransferObject> listar(DocumentoEletronicoTO documentoEletronico, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(documentoEletronico, orderBy);
	}
	
	@SuppressWarnings("unchecked")
	public List<TransferObject> listar(DocumentoEletronicoTO documentoEletronico, UsuarioTO usuario) throws BDException {
		Criteria criteria = this.getDaoHibernate().createCriteria(DocumentoEletronicoArquivoTO.class);
		criteria.add(Restrictions.and(Restrictions.eq("documentoEletronico.id", documentoEletronico.getId()), Restrictions.eq("bitArquivoExcluido", Boolean.FALSE)));
		if (!usuario.getBitMembro()) {
			criteria.add(Restrictions.eq("bitAcessoAnonimo", Boolean.TRUE));
		}
		return criteria.list(); 
	}
	
	@SuppressWarnings("unchecked")
	public List<TransferObject> listar(Date dataInicial, Date dataFinal, UsuarioTO usuario) 
		throws BDException {
		Criteria criteria = this.getDaoHibernate().createCriteria(DocumentoEletronicoTO.class);
		criteria.add(Restrictions.between("datDecisao", dataInicial, dataFinal));
		return criteria.list();
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
