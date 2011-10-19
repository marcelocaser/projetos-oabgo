package core.dao;


import java.io.Serializable;

/**
 * <b>Titulo:</b> TransferObject.java <br>
 * <b>Descricao:</b> <br>
 * <b>Projeto:</b> Core <br>
 * 
 * @author Leonardo Peixoto
 */
@SuppressWarnings("serial")
public abstract class TransferObject implements Cloneable, Serializable {


	/**
	 * Metodo que obrigatoriamente deve ser implementado tendo como retorno o
	 * atributo utilizado como chave da entidade.
	 * 
	 * @return Serializable
	 */
	public abstract Serializable getKey();

	/**
	 * Popula o atributo chave da entidade
	 * @param id - Long
	 * @return void
	 */
	public abstract void setId(Serializable id);

	/**
	 * Sobrescrita do metodo equals() visando comparar os objetos descendentes
	 * de TranferObject.
	 * 
	 * @return boolean
	 */
	public boolean equals(Object other) {
		boolean retorno = true;
		if(!this.getClass().isInstance(other)) {
		   retorno = false;
		}else{
           TransferObject castOther = (TransferObject) other;
           if(this.getKey() != null && castOther.getKey() != null){
			  retorno = this.getKey().equals(castOther.getKey());
	       }else{
			  retorno = false;
	       }
		}
		return retorno;
	}

	/**
	 * Sobrescrita de hashCode
	 * @return int
	 */
	public int hashCode() {
		if(getKey() != null){
		   if(getKey() instanceof Long){
			  return (int)(long)(Long)getKey(); 
		   }else {
			  return (int)(Integer) getKey(); 	
		   }
		}else{
			return super.hashCode();
		}
	}

	/**
	 * Retorna um clone do objeto
	 * @return Object
	 */
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException ex) {
			return null;
		}
	}

}

