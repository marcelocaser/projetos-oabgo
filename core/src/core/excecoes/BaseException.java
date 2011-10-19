package core.excecoes;

/**
 * <b>Titulo:</b> BaseException.java <br>
 * <b>Descricao:</b>     <br>
 * <b>Projeto:</b> core <br>
 * 
 * @author Leonardo Peixoto
 */
@SuppressWarnings("serial")
public class BaseException extends RuntimeException {
    private String message;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
        setMessage(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}