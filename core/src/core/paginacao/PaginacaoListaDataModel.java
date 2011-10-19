package core.paginacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import core.Oab;


/**
 * <b>Classe:</b> PaginacaoListDataModel.java <br>
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

@SuppressWarnings("serial")
public class PaginacaoListaDataModel extends Oab implements Serializable {

	private DataModel dataModel;
	private int		  size;
	
	public PaginacaoListaDataModel() {
		
	}
	
	@SuppressWarnings("unchecked")
	public PaginacaoListaDataModel(Collection itens) {
		this.size = itens.size();
		this.dataModel = new ListDataModel(new ArrayList(itens));
	}
	
	public DataModel getDataModel() {
		return dataModel;
	}
	
	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public Object getRowData(){
		return getDataModel().getRowData();
	}
}
