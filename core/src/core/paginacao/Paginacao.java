package core.paginacao;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import core.Oab;


/**
 * <b>Classe:</b> Paginacao.java <br>
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
public class Paginacao extends Oab implements List {

	private List 			list;
	private Integer 		size;
	private Integer 		rows;
	private Integer 		linhasPorPagina;
	private Integer 		paginaAtual;


	
	public List getList() {
		return list;
	}
	
	public void setList(List list) {
		this.list = list;
	}
	
	public Integer getSize() {
		return size;
	}
	
	public void setSize(Integer size) {
		this.size = size;
	}
	
	public Integer getRows() {
		return rows;
	}
	
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	public Integer getPaginaAtual() {
		return paginaAtual;
	}
	
	public void setPaginaAtual(Integer paginaAtual) {
		this.paginaAtual = paginaAtual;
	}
	
	public Integer getLinhasPorPagina() {
		return linhasPorPagina;
	}
	
	public void setLinhasPorPagina(Integer linhasPorPagina) {
		this.linhasPorPagina = linhasPorPagina;
	}
	
	public Object get(int index) {
		return list.get(getPosicaoAtual(index));
	}
	
	/**
	 * Calcula o indice real a ser retornado pelo objeto com base no item ficticio do
	 * DataModel visto que a paginação baseia-se no total de itens do Banco e não
	 * na quantidade de itens reais existentes na lista.
	 * @param indice - int
	 * @return int
	 */
	private int getPosicaoAtual(int indice){
		if(indice < linhasPorPagina){
			return indice;
		}else{
			return indice - (linhasPorPagina * paginaAtual);
		}
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}

	
	public int indexOf(Object arg0) {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}
	
	public Iterator iterator() {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}
	
	public int lastIndexOf(Object arg0) {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}
	
	public ListIterator listIterator() {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}
	
	public ListIterator listIterator(int arg0) {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}

	public boolean remove(Object arg0) {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}

	public Object remove(int arg0) {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}

	public boolean removeAll(Collection arg0) {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}

	public boolean retainAll(Collection arg0) {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}

	public List subList(int arg0, int arg1) {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}

	public Object set(int arg0, Object arg1) {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}
	
	public Object[] toArray() {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}
	
	public Object[] toArray(Object[] arg0) {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}

	public boolean add(Object arg0) {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}

	public void add(int arg0, Object arg1) {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}

	public boolean addAll(Collection arg0) {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}

	public boolean addAll(int arg0, Collection arg1) {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}

	public void clear() {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}

	public boolean contains(Object arg0) {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}

	public boolean containsAll(Collection arg0) {
		throw new UnsupportedOperationException("Método Não Implementado ");
	}
}
