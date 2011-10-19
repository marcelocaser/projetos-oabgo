
package core.mensagem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <b>Titulo:</b> MensagemLista.java <br>
 * <b>Descricao:</b>     <br>
 * <b>Projeto:</b> core <br>
 * 
 * @author Leonardo
 */
@SuppressWarnings("serial")
public class MensagemLista implements Serializable{

    private ArrayList<Mensagem> mensagens;
    
    /**
     * Construtor padrão
     */
    public MensagemLista(){
        mensagens = new ArrayList<Mensagem>();
    }
    
    /**
     * Constroi objeto e adiciona mensagem na lista
     * @param mensagem - String
     */
    public MensagemLista(String mensagem){
        mensagens = new ArrayList<Mensagem>();
        addMensagem(mensagem);
    }
    
    /**
     * Adiciona uma mensagem na lista
     * @param mensagem - String
     * @return void
     */
    public void addMensagem(String mensagem){
    	this.mensagens.add(new Mensagem(mensagem));
    }
    
    // *** Gets and Sets ***
    public ArrayList<Mensagem> getMensagens(){
        return this.mensagens;
    }
    
    public void removeTodosElementos(){
        this.mensagens.clear();
    }
    
    public int getNumeroRegistros(){
        return this.mensagens.size();
    }
    
    public boolean isEmpty(){
    	return this.mensagens.isEmpty();
    }
}
