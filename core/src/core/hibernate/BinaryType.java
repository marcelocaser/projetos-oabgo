
package core.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

/**
 * <b>Titulo:</b> BinaryType.java <br>
 * <b>Descricao:</b> Classe responsável por gerenciar a persistencia de Tipos byte[] via Hibernate.  <br>
 * <b>Projeto:</b> core <br>
 * 
 * @author Leonardo Peixoto
 */
public class BinaryType implements UserType{ 
	
	/**
	 * Retorna um Array de Tipo SQL
	 * @return int[] 
	 */
	public int[] sqlTypes(){ 
		return new int[] { Types.VARBINARY }; 
	}
	
	@SuppressWarnings("unchecked")
	public Class returnedClass(){ 
		return byte[].class; 
	}
	
	public boolean equals(Object x, Object y){ 
		return (x == y) 
		|| (x != null 
				&& y != null 
				&& java.util.Arrays.equals((byte[]) x, (byte[]) y)); 
	} 
	
	/**
	 * Adiciona um array de bytes no preparedStatement para realizar a inserção.
	 * 
	 * @throws HibernateException
	 * @throws SQLException
	 */
	public void nullSafeSet(PreparedStatement st, Object value, int index)	throws HibernateException, SQLException	{ 
		st.setBytes(index, (byte[])value);
	} 
	
	/**
	 * Retorna o Array de bytes que representa o arquivo recuperado pelo ResultSet.
	 * @return byte[]
	 * @throws HibernateException
	 * @throws SQLException
	 */
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException{
		byte[] bytes = rs.getBytes(names[0]);
		return bytes;
	}
	
	public Object deepCopy(Object value){ 
		if (value == null) return null; 
		byte[] bytes = (byte[]) value; 
		byte[] result = new byte[bytes.length]; 
		System.arraycopy(bytes, 0, result, 0, bytes.length); 
		
		return result; 
	}
	
	public boolean isMutable(){ 
		return true;
	}
	
	public Object assemble(Serializable cached, Object arg1) throws HibernateException {
		return cached;
	}
	
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable)value; 
	}
	
	public int hashCode(Object arg0) throws HibernateException {
		return 0;
	}
	
	public Object replace(Object orignial, Object arg1, Object arg2)throws HibernateException {
		return orignial;
	}
}
