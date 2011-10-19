package core.excecoes;

import java.util.ArrayList;

import core.mensagem.Mensagem;
import core.mensagem.MensagemLista;



/**
 * <b>Classe:</b> AcessoException.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.excecoes <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
@SuppressWarnings("serial")
public class AcessoException extends Exception {

	private ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();
	private int indice = 0;
	
	/**
	 * Construtor que adiciona apenas uma mensagem
	 * @param causa - Throwable
	 */
	public AcessoException(Throwable causa){
	    super(causa);
	    setMensagem(causa.getMessage());
	}

	/**
	 * Construtor que adiciona apenas uma mensagem
	 * @param mensagem - String
	 */
	public AcessoException(String mensagem){
	    super();
	    setMensagem(mensagem);
	}
	
	public AcessoException(Mensagem mensagemTO){
	    super();
	    setMensagem(mensagemTO);
	}
	
	public AcessoException(MensagemLista mensagemListaTO){
	    super();
	    setMensagens(mensagemListaTO);
	}
	
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
