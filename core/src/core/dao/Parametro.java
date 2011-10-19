package core.dao;

import java.util.Date;
import java.util.HashMap;

import core.excecoes.RegraNegocioException;
import core.utilitario.Data;


/**
 * <b>Titulo:</b> Parametro.java <br>
 * <b>Descricao:</b> Encapsula parâmetros necessários, POJO <br>
 * <b>Projeto:</b> core <br>
 * 
 * @author Leonardo Peixoto
 */
@SuppressWarnings("unchecked")
public class Parametro {

	private HashMap				atributos								= new HashMap();
	
	public static final String  PRM_MEUS_DOCUMENTOS						= "PRM_MEUS_DOCUMENTOS";
	public static final String  PRM_TIPO								= "PRM_TIPO";
	public static final String  PRM_USUARIO_LOGADO						= "PRM_USUARIO_LOGADO";
	public static final String  PRM_CARGO_LOGADO						= "PRM_CARGO_LOGADO";
	public static final String	PRM_NOME_ATRIBUTO1						= "PRM_NOME_ATRIBUTO1";
	public static final String	PRM_NOME_ATRIBUTO2						= "PRM_NOME_ATRIBUTO2";
	public static final String	PRM_NOME_ATRIBUTO3						= "PRM_NOME_ATRIBUTO3";
	public static final String	PRM_DATA								= "PRM_DATA";
	public static final String	PRM_DATA1_INICIO						= "PRM_DATA1_INICIO";
	public static final String	PRM_DATA1_FIM							= "PRM_DATA1_FIM";
	public static final String	PRM_DATA2_INICIO						= "PRM_DATA2_INICIO";
	public static final String	PRM_DATA2_FIM							= "PRM_DATA2_FIM";
	public static final String	PRM_DATA3_INICIO						= "PRM_DATA3_INICIO";
	public static final String	PRM_DATA3_FIM							= "PRM_DATA3_FIM";
	public static final String	PRM_SIM									= "PRM_SIM";
	public static final String	PRM_NAO									= "PRM_NAO";
	public static final String	PRM_SITUACAO							= "PRM_SITUACAO";
	public static final String	PRM_TIPO_DOCUMENTO						= "PRM_TIPO_DOCUMENTO";
	public static final String	PRM_NUMERO_DOCUMENTO					= "PRM_NUMERO_DOCUMENTO";
	public static final String	PRM_ID_PROPRIO_PROCESSO					= "PRM_ID_PROPRIO_PROCESSO";
	public static final String	PRM_ID_PROCESSO_PAI						= "PRM_ID_PROCESSO_PAI";
	public static final String  PRM_VALOR_MINIMO   						= "PRM_VALOR_MINIMO";
	public static final String  PRM_VALOR_MAXIMO  						= "PRM_VALOR_MAXIMO";
	public static final String  PRM_ATRIBUTO_VALOR               		= "PRM_ATRIBUTO_VALOR";   
	public static final String  PRM_SEGUNDA               				= "PRM_SEGUNDA";   
	public static final String  PRM_TERCA               				= "PRM_TERCA";   
	public static final String  PRM_QUARTA               				= "PRM_QUARTA";   
	public static final String  PRM_QUINTA               				= "PRM_QUINTA";   
	public static final String  PRM_SEXTA               				= "PRM_SEXTA";   
	public static final String  PRM_SABADO               				= "PRM_SABADO";   
	public static final String  PRM_DOMINGO               				= "PRM_DOMINGO";   
	public static final String  PRM_SEGUNDOS               				= "PRM_SEGUNDOS";   
	public static final String  PRM_MINUTOS               				= "PRM_MINUTOS";   
	public static final String  PRM_HORAS               				= "PRM_HORAS";   
	public static final String  PRM_DIA	               					= "PRM_DIA";   
	public static final String  PRM_MES	               					= "PRM_MES";   
	public static final String  PRM_ANO	               					= "PRM_ANO";   
	public static final String  PRM_ID_TIPO_DOCUMENTO  					= "PRM_ID_TIPO_DOCUMENTO";   
	public static final String  PRM_FOI_ASSINADO  						= "PRM_FOI_ASSINADO";   
	public static final String  PRM_QTD_ASSINANTES						= "PRM_QTD_ASSINANTES";   
	public static final String  PRM_ID_TIPO_PERSONALIZADO				= "PRM_ID_TIPO_PERSONALIZADO";   
	public static final String  PRM_ID_DOCUMENTO						= "PRM_ID_DOCUMENTO";   
	public static final String  PRM_LISTA_PROP_PRECEDENCIA				= "PRM_LISTA_PROP_PRECEDENCIA";   
	public static final String  PRM_LISTA_ASSINATURAS					= "PRM_LISTA_ASSINATURAS";   
	public static final String  PRM_LISTA_FIELDS						= "PRM_LISTA_FIELDS";   
	public static final String  PRM_CAMPO_CHAVE_PRECEDENCIA				= "PRM_CAMPO_CHAVE_PRECEDENCIA";   
	public static final String  PRM_LISTA_RUBRICAS_DOCUMENTO_PDF		= "PRM_LISTA_RUBRICAS_DOCUMENTO_PDF";   
    public static final String  PRM_VIZUALIZA_TUDO                      = "PRM_VIZUALIZA_TUDO";
    public static final String  PRM_LISTA_VALOR_FIELD                   = "PRM_LISTA_VALOR_FIELD";
    public static final String  PRM_LISTA_PROP                          = "PRM_LISTA_PROP";
    public static final String  PRM_MARCADOR_TABLE_ASS                  = "PRM_MARCADOR_TABLE_ASS";
    public static final String  PRM_COLABORADOR_DEPARTAMENTO_LOGADO     = "PRM_COLABORADOR_DEPARTAMENTO_LOGADO";
    public static final String  PRM_COLABORADOR_LOGADO     				= "PRM_COLABORADOR_LOGADO";
    
    
	/**
	 * Inclui um novo parâmetro.
	 * 
	 * @param parametro     String
	 * @param valor         Object
	 */
	public void setValor(String parametro, Object valor) throws RegraNegocioException{
		this.validar(parametro, valor);
		atributos.put(parametro, valor);
	}
	
	/**
	 * Retorna um valor do HashMap declarado
	 * 
	 * @param parametro
	 * @return Object
	 */

	public Object getValor(String parametro) {
		return atributos.get(parametro);
	}

	/**
	 * Limpa o HashMap declarado
	 */
	public void limpar() {
		atributos = new HashMap();
	}
	
	/**
	 * Retorna o valor armazenado na variável atributos.
	 * @return HashMap
	 */
	public HashMap getAtributos() {
		return atributos;
	}

	/**
	 * Insere um valor ao atributo atributos
	 * @param atributos - HashMap
	 * 
	 */
	public void setAtributos(HashMap atributos) {
		this.atributos = atributos;
	}
	
	/**
	 * Aplica a validação para o parâmetro do períoro.
	 * 
	 * @throws RegraNegocioException
	 */
	private void validar(String parametro, Object valor) throws RegraNegocioException{
		//se o parâmetro a ser inserido for a 2° data do 1° período e a 1° data existir
		//valida se o a data e maior que a data 1.
		if(parametro.equals(PRM_DATA1_FIM) && getValor(PRM_DATA1_INICIO) != null){
			Date dataFim = (Date)valor;
			Date dataInicio = (Date)getValor(PRM_DATA1_INICIO);
			if (dataFim != null && dataInicio != null) {
				if (Data.comparaDatas(dataInicio, dataFim) == 1) {
					throw new RegraNegocioException("RemotoMsgErroPeriodo");
				}
			}
		}else if(parametro.equals(PRM_DATA2_FIM) && getValor(PRM_DATA2_INICIO) != null){
			Date dataFim = (Date)valor;
			Date dataInicio = (Date)getValor(PRM_DATA2_INICIO);
			if (dataFim != null && dataInicio != null) {
				if (Data.comparaDatas(dataInicio, dataFim) == 1) {
					throw new RegraNegocioException("RemotoMsgErroPeriodo");
				}
			}
		}else if(parametro.equals(PRM_DATA3_FIM) && getValor(PRM_DATA3_INICIO) != null){
			Date dataFim = (Date)valor;
			Date dataInicio = (Date)getValor(PRM_DATA3_INICIO);
			if (dataFim != null && dataInicio != null) {
				if (Data.comparaDatas(dataInicio, dataFim) == 1) {
					throw new RegraNegocioException("RemotoMsgErroPeriodo");
				}
			}
		}
	}
}
