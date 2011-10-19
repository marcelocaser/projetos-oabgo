package br.org.oabgo.siged.negocio.controle.persistencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.org.oabgo.siged.negocio.controle.SIGEDPersistencia;
import br.org.oabgo.siged.negocio.controle.entidade.DocumentoEletronicoArquivoTO;
import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

@Service("documentoEletronicoArquivoPO")
public class DocumentoEletronicoArquivoPO extends SIGEDPersistencia {

	public void alterar(DocumentoEletronicoArquivoTO documentoEletronicoArquivo, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(documentoEletronicoArquivo, usuarioLogado);
	}
	
	public void excluir(DocumentoEletronicoArquivoTO documentoEletronicoArquivo, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(documentoEletronicoArquivo, usuarioLogado);
	}
	
	public void incluir(DocumentoEletronicoArquivoTO documentoEletronicoArquivo, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(documentoEletronicoArquivo, usuarioLogado);
	}
	
	public DocumentoEletronicoArquivoTO consultar(DocumentoEletronicoArquivoTO documentoEletronicoArquivo) throws BDException{
		return (DocumentoEletronicoArquivoTO) this.getDaoHibernate().consultar(DocumentoEletronicoArquivoTO.class, documentoEletronicoArquivo);
	}
	
	public DocumentoEletronicoArquivoTO consultarPorNomeDoArquivo(DocumentoEletronicoArquivoTO documentoEletronicoArquivo) throws BDException {
		Criteria criteria = this.getDaoHibernate().createCriteria(DocumentoEletronicoArquivoTO.class);
		criteria.add(Restrictions.or(Restrictions.eq("vchNome", documentoEletronicoArquivo.getVchNome()), Restrictions.eq("intTamanho", documentoEletronicoArquivo.getIntTamanho())));
		return (DocumentoEletronicoArquivoTO) this.getDaoHibernate().buscarObjeto(criteria);
	}
	
	public List<TransferObject> listar(DocumentoEletronicoArquivoTO documentoEletronicoArquivo) throws BDException{
		return this.getDaoHibernate().listar(documentoEletronicoArquivo);
	}
	
	public List<TransferObject> listar(DocumentoEletronicoArquivoTO documentoEletronicoArquivo, String orderBy) throws BDException{
		return this.getDaoHibernate().listar(documentoEletronicoArquivo, orderBy);
	}
	
	@SuppressWarnings("unchecked")
	public List<TransferObject> listar(DocumentoEletronicoArquivoTO documentoEletronicoArquivo, UsuarioTO usuario) 
			throws BDException {
		Criteria criteria = this.getDaoHibernate().createCriteria(DocumentoEletronicoArquivoTO.class);
		criteria.add(Restrictions.eq("bitArquivoExcluido", Boolean.FALSE));
		criteria.add(Restrictions.like("vchConteudo", "%" + documentoEletronicoArquivo.getVchConteudo() + "%"));
		if (!usuario.getBitMembro()) {
			criteria.add(Restrictions.eq("bitAcessoAnonimo", Boolean.TRUE));
		}
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
