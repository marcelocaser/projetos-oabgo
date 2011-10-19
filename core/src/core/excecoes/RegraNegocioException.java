
package core.excecoes;
import java.util.ArrayList;

import core.mensagem.Mensagem;
import core.mensagem.MensagemLista;


/**
 * <b>Titulo:</b> RegraNegocioException.java <br>
 * <b>Descricao:</b>     <br>
 * <b>Projeto:</b> core <br>
 * 
 * @author Leonardo Peixoto
 */
@SuppressWarnings("serial")
public class RegraNegocioException extends Exception {

	private ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();
	private int indice = 0;
	
	public RegraNegocioException(){
		super();
	}

	/**
	 * Construtor que adiciona apenas uma mensagem
	 * @param mensagem - String
	 */
	public RegraNegocioException(String mensagem){
	    super();
	    setMensagem(mensagem);
	}
	
	/**
	 * Construtor que adiciona apenas uma mensagem
	 * @param mensagem - Mensagem
	 */
	public RegraNegocioException(Mensagem mensagem){
	    super();
	    setMensagem(mensagem);
	}
	
	/**
	 * Construtor que adiciona uma lista de mensagens
	 * @param  mensagemLista - MensagemLista
	 */
	public RegraNegocioException(MensagemLista mensagemLista){
	    super();
	    setMensagens(mensagemLista);
	}
    

	// *** Gets and Sets ***
    public void setMensagem(String mensagem){
    	this.mensagens.add(new Mensagem(mensagem));
    }
    
    public void setMensagem(Mensagem mensagemTO){
        this.mensagens.add(mensagemTO);
    }
    
    public void setMensagens(MensagemLista mensagemListaTO){
        this.mensagens = mensagemListaTO.getMensagens();
    }
    
    public void proximaMensagem(){
        if(indice<this.mensagens.size()){
            indice = indice + 1;
        }
    }
    
    public String getMensagem(){
        if(mensagens.size()>=indice){
            return this.mensagens.get(indice).toString();
        }
        return null;
    }
    
    public ArrayList<Mensagem> getMensagens(){
        if(mensagens.size()>=0){
            return this.mensagens;
        }
        return null;
    }
    
    public int getNumeroMensagens(){
        return mensagens.size();
    }
}
