
package core.mensagem;

import java.io.Serializable;

/**
 * <b>Titulo:</b> Mensagem.java <br>
 * <b>Descricao:</b>     <br>
 * <b>Projeto:</b> core <br>
 * 
 * @author Leonardo Peixoto
 */

@SuppressWarnings("serial")
public class Mensagem implements Serializable{
	
	
    private String mensagem = new String();
    
    public Mensagem(){}
    
    
    public Mensagem(String mensagem){
    	this.mensagem = mensagem;
    }
    
    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }
	
    public String getMensagem(){
        return mensagem;
    }
	
}
