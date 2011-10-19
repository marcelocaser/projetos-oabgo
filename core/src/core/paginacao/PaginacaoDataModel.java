package core.paginacao;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.DataModelEvent;
import javax.faces.model.DataModelListener;

import core.resource.ResourceServiceUtil;


/**
 * <b>Classe:</b> PaginacaoDataModel.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.paginacao <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */

@SuppressWarnings("unchecked")
public class PaginacaoDataModel extends DataModel implements Serializable{

	private static final long serialVersionUID = 2496395580192540659L;
	
	private List		itens;
	private int			indice	= -1;
	private int 		itensPorPagina;
	private int 		qtdTotalRegistros;
	private int 		paginaAtual;
	private String  	rotulo;

	/**
	 * Construtor com os itens a serem apresentados na lista
	 * @param paginacao - Paginacao
	 */
	public PaginacaoDataModel(Paginacao paginacao) {
		super();
		setWrappedData(paginacao.getList());
		this.qtdTotalRegistros = paginacao.getSize();
		this.itensPorPagina = paginacao.getLinhasPorPagina();
		this.paginaAtual = paginacao.getPaginaAtual();
	}


	/**
	 * Método que retorna a quantidade total de registros existentes no banco.
	 * @return Object
	 */
	public int getRowCount() {
		if (itens == null) {
			return -1;
		}
		return qtdTotalRegistros;
	}

	/**
	 * Retorna o objeto que ocupa a posição INDICE no vetor.
	 * @return Object
	 */
	public Object getRowData() {
		if (itens == null) {
			return null;
		}
		if (!isRowAvailable()) {
			throw new IllegalArgumentException("Coluna inexistente");
		}
		Object retorno = null;
		try{
			retorno = itens.get(getPosicaoAtual(indice));
		}catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/**
	 * Retorna o indice atual a ser apresentado na listagem.
	 * @return int
	 */
	public int getRowIndex() {
		return indice;
	}

	/**
	 * Retorna a lista que armazena os itens a serem apresentados.
	 * @return List
	 */
	public Object getWrappedData() {
		return itens;
	}

	/**
	 * Método responsável por percorrer verificar se ja chegou ao final dos registros.
	 * @return boolean
	 */
	public boolean isRowAvailable() {
		if (itens == null) {
			return false;
		}
		return indice >= 0 && indice < qtdTotalRegistros;
	}

	/**
	 * Insere o valor da próxima posição a ser apresentada
	 * @param rowIndex - int
	 */
	public void setRowIndex(int rowIndex) {
		if (rowIndex < -1) {
			throw new IllegalArgumentException("Illegal rowIndex: " + rowIndex);
		}
		int oldRowIndex = rowIndex;
		indice = rowIndex;
		if (itens != null && oldRowIndex != rowIndex) {
			Object data = isRowAvailable() ? getRowData() : null;
			DataModelEvent event = new DataModelEvent(this, rowIndex, data);
			DataModelListener[] listeners = getDataModelListeners();
			for (int i = 0; i < listeners.length; i++) {
				listeners[i].rowSelected(event);
			}
		}
	}

	/**
	 * Preenche a lista de itens a serem apresentados pelo DataModel
	 * @param data - List
	 */
	public void setWrappedData(Object data) {
		itens = (List) data;
		int rowIndex = itens != null ? 0 : -1;
		setRowIndex(rowIndex);
	}


	/**
	 * Retorna o valor armazenado na variável qtdTotalRegistros.
	 * @return int
	 */
	public int getQtdTotalRegistros() {
		return qtdTotalRegistros;
	}


	/**
	 * Insere um valor ao atributo qtdTotalRegistros
	 * @param qtdTotalRegistros - int
	 * 
	 */
	public void setQtdTotalRegistros(int qtdTotalRegistros) {
		this.qtdTotalRegistros = qtdTotalRegistros;
	}

	/**
	 * Retorna o valor armazenado na variável itensPorPagina.
	 * @return int
	 */
	public int getItensPorPagina() {
		return itensPorPagina;
	}

	/**
	 * Insere um valor ao atributo itensPorPagina
	 * @param itensPorPagina - int
	 * 
	 */
	public void setItensPorPagina(int itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	/**
	 * Retorna o valor armazenado na variável paginaAtual.
	 * @return int
	 */
	public int getPaginaAtual() {
		return paginaAtual;
	}

	/**
	 * Insere um valor ao atributo paginaAtual
	 * @param paginaAtual - int
	 * 
	 */
	public void setPaginaAtual(int paginaAtual) {
		this.paginaAtual = paginaAtual;
	}
	
	/**
	 * Calcula o indice real a ser retornado pelo objeto com base no item ficticio do
	 * DataModel visto que a paginação baseia-se no total de itens do Banco e não
	 * na quantidade de itens reais existentes na lista.
	 * 
	 * @param indice - int
	 * @return int
	 */
	private int getPosicaoAtual(int indice){
		if(indice < itensPorPagina){
			return indice;
		}else{
			return indice - (itensPorPagina * paginaAtual);
		}
	}

	/**
	 * Retorna o valor armazenado na variável rotulo.
	 * @return String
	 */
	public String getRotulo() {
		return rotulo;
	}

	/**
	 * Insere um valor ao atributo rotulo
	 * @param rotulo - String
	 * 
	 */
	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}


	/**
	 * Retorna o valor armazenado no atributo registroInicialApresentado.
	 * @return int
	 */
	public int getRegistroInicialApresentado() {
		if(qtdTotalRegistros == 0){
			return qtdTotalRegistros;
		}else if(((itensPorPagina * paginaAtual)+1) > qtdTotalRegistros){
			return 1;
		}else{
			return (itensPorPagina * paginaAtual)+1;			
		}
	}

	/**
	 * Retorna o valor armazenado no atributo registroFinalApresentado.
	 * @return int
	 */
	public int getRegistroFinalApresentado() {
		if(((itensPorPagina * paginaAtual)+itensPorPagina) > qtdTotalRegistros){
			return qtdTotalRegistros;
		}else{
			return ((itensPorPagina * paginaAtual)+itensPorPagina);
		}
	}


	/**
	 * Retorna o valor armazenado no atributo rotuloPaginacao.
	 *
	 * @return String
	 */
	public String getRotuloPaginacao() {
		Object[] parametros = new Object[]{getRegistroInicialApresentado(), getRegistroFinalApresentado(), getQtdTotalRegistros()};
		return ResourceServiceUtil.getMessageResourceString("satiRotuloDataModelPaginacao", parametros);
	}

}