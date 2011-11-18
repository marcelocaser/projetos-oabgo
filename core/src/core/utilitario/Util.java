package core.utilitario;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.model.SelectItem;
import javax.servlet.ServletException;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.PropertyValueException;
import org.hibernate.validator.InvalidStateException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateSystemException;

import core.dao.TransferObject;
import core.excecoes.AcessoException;
import core.excecoes.BDException;
import core.excecoes.BaseException;
import core.excecoes.RegraNegocioException;
import core.excecoes.SystemException;
import core.paginacao.Paginacao;

/**
 * <b>Titulo:</b> Util.java <br>
 * <b>Descricao:</b>     <br>
 * <b>Projeto:</b> core <br>
 * 
 * @author Leonardo Peixoto
 */
public class Util {

	/**
	 * Formata String conforme parâmetro passado pelo pMask.
	 * apenas o caractere # será substituído pelo texto original
	 * 
	 * Ex.: #####-### => 74000-000
	 *
	 * @param pMask - String
	 * @param pValue - String
	 * @return String
	 */
	public static String formataStringMascara(String pMask, String pValue){
		String retorno = new String();
		// Formata valor com base no marametro pMask 
 		for(int i = 0; i < pValue.length(); i++){
 			pMask = pMask.replaceFirst("#", pValue.substring(i, i + 1));
 		}
 		retorno = pMask.replaceAll("#", "");
 		
 		//Caso o valor seja menor que a máscara, retorna o conteudo original
 		if (retorno.length() < pMask.length())
 			retorno = pValue;
 			
 		return retorno;
 	}
	
    /**
     * Retorna o nome da classe do objeto manipulado
     * @param objeto - TransferObject
     * @return String
     */
    public static String retornaNomeClasse(Object objeto){
		if(objeto.getClass().getName().indexOf("$")>0){
            return objeto.getClass().getName().substring(0,objeto.getClass().getName().indexOf("$"));
        }else{
            return objeto.getClass().getName();
        }
	}
	
    /**
     * Busca os métodos nas classes Java, por Reflexão.
     *
     * @param methods
     * @param nome
     * @return Method
     */
	public static Method getMethod(Method[] methods, String nome){
		for(Method m : methods){
			if(m.getName().equals(nome)){
				return m;
			}
		}
		return null;
	}
    
    /**
     * Cria um Array de Select Itens com as propriedades.
     * 
     * @param lista - List contendo os TO'S a serem copiados
     * @param key - campo a ser copiado
     * @return List<String>
     */
    @SuppressWarnings("unchecked")
	public static List<String> createListStringSelecionada(Set<? extends TransferObject> lista, String key){
    	if(lista != null && !lista.isEmpty()){
    	   return createListStringSelecionada(new ArrayList(lista), key);
    	}
    	return null;
    }
    
    /**
     * Cria um Array de Select Itens com as propriedades.
     * 
     * @param lista - List contendo os TO'S a serem copiados
     * @param key - campo a ser copiado
     * @return List<String>
     */
    public static List<String> createListStringSelecionada(List<? extends TransferObject> lista, String key){
    	List<String> itens = new ArrayList<String>();
    	for(TransferObject bean : lista){
    		try{
    			itens.add(PropertyUtils.getNestedProperty(bean, key).toString());
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	return itens;
    }
    
    /**
     * Cria um Array de Select Itens com as propriedades.
     * 
     * @param lista - List contendo os TO'S a serem copiados
     * @param key - campo a ser copiado
     * @param value - valor do atributo do TO
     * @return List<String>
     */
    public static List<SelectItem> createListStringSelecionada(List<? extends TransferObject> lista, String key, String value){
    	List<SelectItem> itens = new ArrayList<SelectItem>();
    	for(TransferObject bean : lista){
    		try{
    			itens.add(new SelectItem(PropertyUtils.getNestedProperty(bean, key).toString(), PropertyUtils.getNestedProperty(bean, value).toString()));
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	return itens;
    }
    
	/**
	 * Retorna TRUE se a string for 1, False de for 0 e Null se for 2
	 * @param str - String
	 * @return Boolean
	 */
	public static Boolean converteBoolean(String str){
	     if(str == null) return null; 
	        else {
	              if(str.equals("1")) return true;
	                 else if(str.equals("0")) return false;
	                         else return null;
	        }
	}
	
    /**
     * Retorna a exceção base da exceção que foi lançada.
     * @param	e - Throwable
     * @return	BaseException
     */
    public static BaseException getBaseException(Throwable e) {
        if (e instanceof BaseException) {
            return (BaseException) e;
        } else if (e instanceof ServletException) {
            if (((ServletException) e).getRootCause() != null) {
                return getBaseException(((ServletException) e).getRootCause());
            } 
            return new SystemException(e);
            
        } else {
            return new SystemException(e);
        }
    }
    
    /**
     * Verifica se o objeto passado é um TransferObject
     * @param object - Object
     * @return boolean
     */
    public static boolean isTO(Object object){
    	boolean result = false;
    	try{
	    	if(object instanceof TransferObject){
	    		result = true;
	    	}
    	}catch (Exception e) {
    		
    	}
    	return result;
    }
	
	/**
	 * 
	 * Retorna um TransferObject com o id setado, se o id passado for valido
	 * Usado para setar o valor de um TransferObject em outro.
	 * Ex.: Quando uma combobox de TO é selelcionada...
	 *
	 */
	public static <T extends TransferObject> T setValueTO(String id, Class<T> to) throws Exception{
		if(Util.stringOk(id)){
		   T obj = to.newInstance();
		   obj.setId(new Long(id));
		   return obj;
		}
		else{
			return null;
		}
	}
	
	/**
	 * Método usado testa se a String é nula ou vazia, se for retorna false
	 * se não true
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean stringOk(String str){
		if(str != null && !str.trim().isEmpty()){
			return true;
		}
		else return false;
	}
	
    /**
     * Verifica se um objeto TO possui propriedades.
     * 
     * @param transferObject - TransferObject
     * @return boolean
     */
    public static boolean possuiAtributos(TransferObject bean){
    		boolean retorno = false;
    		if(bean != null){
    			Field[] fields = bean.getClass().getDeclaredFields();
    			//percorre todos os atributos do objeto
    			for(Field field : fields){
    				try{
    					//pega o nome do atributo
    					String fieldName = field.getName();
    					//pega o valor do atributo
    					Object fieldValue = PropertyUtils.getSimpleProperty(bean, fieldName);
    					//verifica se o atributo possui um valor
    					if(fieldValue != null && !fieldValue.equals("")){
    						retorno = true;
    						break;
    					}
    				}catch (Exception e) {
						e.printStackTrace();
					}
    			}
    		}
    	return retorno;
    }
	
	/**
	 * Converte a List de uma ListaPagina em uma lista de BeanUtil
	 * 
	 * @param listaTO - List<TransferObject>
	 * @return List<CheckBoxBean>
	 */
	public static void criarListaCheckBoxBean(Paginacao paginacao){
		List<BeanUtil> listaRetorno = new ArrayList<BeanUtil>();
		if(paginacao.getList() != null){
			for(int i = 0; i < paginacao.getList().size(); i ++){
				TransferObject bean = (TransferObject) paginacao.getList().get(i);
				listaRetorno.add(new BeanUtil(Boolean.FALSE, bean));
			}
		}
		paginacao.setList(listaRetorno);
	}
	
    /**
	 * Cria um Array de Select Itens com as propriedades.
	 * 
	 * @param lista - List contendo os TO'S a serem copiados
	 * @param key - campo chave do TO
	 * @param value - valor do atributo do TO
	 * @return SelectItem[]
	 */
    @SuppressWarnings("unchecked")
    public static List<SelectItem> createListSelectItens(List lista, String key, String value){
    	List<SelectItem> itens = new ArrayList<SelectItem>();
    	for(int i = 0; i < lista.size(); i ++){
    		try{
    			Object obj = lista.get(i);
    			if(obj instanceof TransferObject){
    				TransferObject to = (TransferObject)lista.get(i);
    				SelectItem item = new SelectItem(PropertyUtils.getNestedProperty(to, key).toString(), PropertyUtils.getNestedProperty(to, value).toString());
    				itens.add(item);
    			}else if(obj instanceof Map){
    				Map<String, Object> map = (Map<String, Object>)lista.get(i);
    				SelectItem item = new SelectItem(map.get(key).toString(), map.get(value).toString());
    				itens.add(item);
    			}else{
    				Object to = (Object)lista.get(i);
    				SelectItem item = new SelectItem(PropertyUtils.getNestedProperty(to, key).toString(), PropertyUtils.getNestedProperty(to, value).toString());
    				itens.add(item);
    			}
    		}catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return itens;
    }
    
	/**
	 * 
	 * Retorna null se a String for null ou vazia
	 * Caso não for retorna o proprio valor dela
	 * 
	 */
	public static String setString(String str){
		if(str != null && !str.trim().isEmpty()){
			return str;
		}
		else return null;
	}
	
	/**
	 * 
	 * Retorna valor numerico se a String for diferente de 
	 * null ou vazia.
	 * 
	 */
	public static Integer setInteger(String str){
		if(stringOk(str)){
			return Integer.parseInt(str);
		}
		else return null;
	}
	
	/**
	 * Realiza um SPLIT, dividindo a String de acordo com o CHAR passado como parâmetro.
	 * 
	 * @param s - String a ser dividida
	 * @param c - char marcador
	 * @return List<String>
	 */
	public static List<String> split(String s, char c){
		String work = s;
		List<String> result = new ArrayList<String>();
		while(work.indexOf(c) != -1){
			if(!work.substring(0 , work.indexOf(c)).equals(""))
				result.add(work.substring(0 , work.indexOf(c)));
			work = work.substring(work.indexOf(c)+1 , work.length());
		}
		result.add(work);
		return result;
	}
	
	/**
	 * Obtêm byte[] de um File.
	 *
	 * @param file
	 * @return
	 * @throws IOException - 
	 * @return byte[]
	 */
	public static byte[] bytesFromFile(File file) throws IOException {
		InputStream inputStream = new FileInputStream(file);

		long length = file.length();
		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;

		if (length > 10485760) {
			throw new IOException("Ops...Arquivo muito grande! Maior que 10mb.");
		}

		try {
			while (offset < bytes.length && (numRead = inputStream.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
		} catch (IOException e) {
			throw e;
		} finally {
			inputStream.close();
		}
		
		if (offset < bytes.length) {
			throw new IOException("Não foi possível completar a leitura do arquivo " + file.getName());
		}
		
		return bytes;
	}
	
    /**
     * Retorna a exceção original na pilha de métodos.
     * @param e - Throwable
     * @return Exception
     */
    public static Exception getException(Throwable e){
    	Exception ex = new Exception(e);
    	if(e instanceof RegraNegocioException){
    		ex = (Exception)e;
    	}else if(e.getCause() instanceof RegraNegocioException 
    			|| e.getCause() instanceof AcessoException){
    		
    		ex = (Exception)e.getCause();
    		
    	}else if(e.getCause().getCause() instanceof RegraNegocioException 
    			|| e.getCause().getCause() instanceof AcessoException
    				|| e.getCause().getCause() instanceof BDException
    					|| e.getCause().getCause() instanceof PropertyValueException
    						|| e.getCause().getCause() instanceof DataIntegrityViolationException
    							|| e.getCause().getCause() instanceof IndexOutOfBoundsException
    								|| e.getCause().getCause() instanceof HibernateSystemException
    									|| e.getCause().getCause() instanceof NullPointerException
    										|| e.getCause().getCause() instanceof HibernateException
    											|| e.getCause().getCause() instanceof InvalidStateException){
    		
    		ex = (Exception)e.getCause().getCause();   
    		
    	}else if(e.getCause().getCause().getCause() instanceof RegraNegocioException 
    			|| e.getCause().getCause().getCause() instanceof AcessoException
    				|| e.getCause().getCause().getCause() instanceof BDException
    					|| e.getCause().getCause().getCause() instanceof PropertyValueException
    						|| e.getCause().getCause().getCause() instanceof DataIntegrityViolationException
    							|| e.getCause().getCause().getCause() instanceof IndexOutOfBoundsException
    								|| e.getCause().getCause().getCause() instanceof HibernateSystemException
    									|| e.getCause().getCause().getCause() instanceof NullPointerException
    										|| e.getCause().getCause().getCause() instanceof HibernateException
    											|| e.getCause().getCause().getCause() instanceof InvalidStateException){
    		
    		ex = (Exception)e.getCause().getCause().getCause();  
    		
    	}else if(e.getCause().getCause().getCause().getCause() instanceof RegraNegocioException 
    			|| e.getCause().getCause().getCause().getCause() instanceof AcessoException
    				|| e.getCause().getCause().getCause().getCause() instanceof BDException
    					|| e.getCause().getCause().getCause().getCause() instanceof PropertyValueException
    						|| e.getCause().getCause().getCause().getCause() instanceof DataIntegrityViolationException
    							|| e.getCause().getCause().getCause().getCause() instanceof IndexOutOfBoundsException
    								|| e.getCause().getCause().getCause().getCause() instanceof HibernateSystemException
    									|| e.getCause().getCause().getCause().getCause() instanceof NullPointerException
    										|| e.getCause().getCause().getCause().getCause() instanceof HibernateException
    											|| e.getCause().getCause().getCause().getCause() instanceof InvalidStateException){
    		
    		ex = (Exception)e.getCause().getCause().getCause().getCause();    		
    	}
    	return ex;
    }
    
}
