package core.utilitario;

import java.io.Serializable;
import java.util.List;

import core.dao.TransferObject;

/**
 * <b>Classe:</b> BeanUtil.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.utilitario <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author leonardop
 * @version Revision: $ Date: $
 */
@SuppressWarnings("serial")
public class BeanUtil implements Serializable {

	private Boolean 			estado;
	private String	 			descricao;
	private Byte				situacao;
	private TransferObject		bean;
	private List<BeanUtil>		listaItens;
	
	public BeanUtil() {}
	
	
	public BeanUtil(TransferObject bean) {
		this.bean   = bean;
	}

	public BeanUtil(Boolean estado, TransferObject bean) {
		this.estado	= estado;
		this.bean   = bean;
	}
	
	public BeanUtil(Boolean estado, Byte situacao, TransferObject bean) {
		this.estado 	= estado;
		this.situacao 	= situacao;
		this.bean   	= bean;
	}
	
	public BeanUtil(Boolean estado, Boolean renderizar, TransferObject bean) {
		this.estado 		= estado;
		this.bean   		= bean;
	}

	public BeanUtil(Byte situacao, TransferObject bean) {
		this.situacao = situacao;
		this.bean   = bean;
	}

	public BeanUtil(String descricao, TransferObject bean) {
		this.descricao 	= descricao;
		this.bean   	= bean;
	}

	public BeanUtil(TransferObject bean, List<BeanUtil> listaItens) {
		this.listaItens = listaItens;
		this.bean   	= bean;
	}

	public BeanUtil(String descricao, Byte situacao, TransferObject bean) {
		this.descricao = descricao;
		this.situacao = situacao;
		this.bean   = bean;
	}
	
	/**
	 * Usado para comparar de forma conceitual se 2 objetos BeanUtil são iquais
	 * 
	 * Se os Objetos 'bean', que geralmente são TransferObject forem iquais em 2 BeanUtil diferentes,
	 * então esses 2 BeanUtil devem ser considerados iquais pelo metodo equals
	 * TODO: Já existe codigo que considera essa forma de funcionamento para esse metodo para funcionar
	 *       corretamente
	 */
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof BeanUtil)) return false;
		if(((BeanUtil) obj).getBean() == null) return false;
		if((((BeanUtil) obj).getBean() instanceof TransferObject)){
		   TransferObject to = (TransferObject)((BeanUtil) obj).getBean();
		   if(to.getKey() == null){
			  return super.equals(obj);
		   }
		}
		return bean.equals(((BeanUtil)obj).getBean());
	}
	
	@Override
	public int hashCode(){
		return bean.hashCode();
	}
	
	public Boolean getEstado() {
		return estado;
	}
	
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	public Object getBean() {
		return bean;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<T> classe) {
		return (T)bean;
	}
	
	public void setBean(TransferObject bean) {
		this.bean = bean;
	}

	public Byte getSituacao() {
		return situacao;
	}

	public void setSituacao(Byte situacao) {
		this.situacao = situacao;
	}
	
	public List<BeanUtil> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<BeanUtil> listaItens) {
		this.listaItens = listaItens;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
