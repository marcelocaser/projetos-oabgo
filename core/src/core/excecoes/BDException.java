package core.excecoes;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.dao.DataAccessException;

/**
 * <b>Titulo:</b> BDException.java <br>
 * <b>Descricao:</b>     <br>
 * <b>Projeto:</b> core <br>
 * 
 * @author Leonardo Peixoto
 */
@SuppressWarnings("serial")
public class BDException extends DataAccessException {
	
    protected static final String NIVEL_ERROR = "ERROR";
	private static String classe = "";
	private static String metodo = "";
	private String nivel = NIVEL_ERROR;
	
	public BDException(Exception e) {
		super(e.getMessage());
		this.logarException(e);
	}
	
	 /**
     * Método que irá montar o log para o caso de lançamento de exceptions
     * @param e - Exception
     */
    private void logarException(Exception e) {
        System.out.println("[" + classe + ", " + nivel + "] " +
                           new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        System.out.println("Detalhamento do erro: ");
        System.out.println(this.montaMensagem(e));
        e.printStackTrace(System.out);
        System.out.println("");
    }
    
    /**
     * Monta a mensagem a ser gravada no log
     * @param e Exception
     * @return String
     */
    private String montaMensagem(Exception e) {
        classe = e.getStackTrace()[0].getClassName();
        metodo = e.getStackTrace()[0].getMethodName();
        return(classe + "." + metodo + "() - " + e.getMessage());
    }
}
