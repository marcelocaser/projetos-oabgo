package core.hibernate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

/**
 * <b>Titulo:</b> ClobType.java <br>
 * <b>Descricao:</b>     <br>
 * <b>Projeto:</b> core <br>
 * 
 * @author Leonardo Peixoto
 */
public class ClobType implements UserType {
	
	public ClobType() {
		super();
	}
	
	/**
	 * Retorna um array de inteiros com tipo CLOB.
	 * @return int[]
	 */
	public int[] sqlTypes() {
		return new int[] {Types.CLOB};
	}
	
	@SuppressWarnings("unchecked")
	public Class returnedClass() {
		return String.class;
	}
	
	/**
	 * Implementação do método de comparação de objetos equals.
	 * @return boolean
	 */
	public boolean equals(Object arg0, Object arg1) throws HibernateException {
		boolean ret = false;
		if(arg0 == null || arg1 == null) {
			ret = false;
		}else if(!(arg0 instanceof String) || !(arg1 
				instanceof String)) {
			ret = false;
		}else {
			ret = ((String)arg0).equals((String)
					arg1);
		}
		return ret;
	}
	
	/**
	 * Implementação do método HashCode.
	 * @return int
	 */
	public int hashCode(Object arg0) throws HibernateException {
		return arg0.hashCode();
	}
	
	/**
	 * 
	 * @return Object
	 */
	public Object nullSafeGet(ResultSet arg0, String[] arg1, Object arg2)throws HibernateException, SQLException {
		String ret = null;
		StringBuffer buffer = new StringBuffer();
		try {
			//Primeiro pegamos o stream
			InputStream is = arg0.getAsciiStream(arg1[0]);
			byte[] buf = new byte[1024];
			int read = -1;
			// faz a leitura dos bytes do arquivo
			while((read = is.read(buf)) > 0) {
				buffer.append(new String(buf,0,read));
			}
			is.close();
		}catch(IOException ioe) {
			ioe.printStackTrace();
			throw new HibernateException("Unable to read from resultset",ioe);
		}
		ret = buffer.toString();
		return ret;
	}
	
	/**
	 * 
	 */
	public void nullSafeSet(PreparedStatement pst, Object data, int index) throws HibernateException, SQLException {
		data = data == null? new String() : data;
		String in = (String)data;
		
		byte[] buf = in.getBytes();
		int len = buf.length;
		
		ByteArrayInputStream bais = new 
		ByteArrayInputStream(buf);
		
		pst.setAsciiStream(index,bais,len);
	}
	
	/**
	 * Realiza uma cópia raza do obejto.
	 * @return Object
	 */
	public Object deepCopy(Object arg0) throws HibernateException {
		String ret = null;
		arg0 = arg0 == null? new String() : arg0;
		String in = (String)arg0;
		int len = in.length();
		char[] buf = new char[len];
		
		for(int i=0;i<len;i++) {
			buf[i] = in.charAt(i);
		}
		ret = new String(buf);
		return ret;
	}
	
	public boolean isMutable() {
		return false;
	}
	
	public Serializable disassemble(Object arg0) throws HibernateException {
		return (String)arg0;
	}
	
	public Object assemble(Serializable arg0, Object arg1)throws HibernateException {
		return this.deepCopy(arg0);
	}
	
	public Object replace(Object arg0, Object arg1, Object arg2) throws HibernateException {
		return this.deepCopy(arg0);
	}
	
}