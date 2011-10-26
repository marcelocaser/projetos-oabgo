package br.com.scode;

public class Cotacao {

	private int codcoligada = 1; // Código Identificador da Coligada
	private int codcolcfo = 1; // Coligada do Cliente/Fornecedor
	private int cfovencedor = 0; // Fornecedor Venceu Cotação para Este Item
	private int nseqitmmov; // Número Seq. Interno do Item do Movimento
	private int tipomovcompras = 1; // Tipo do Movimento de Compra
	private int codfilial = 1; // Código da filial
	private String codcotacao; // Código da Cotação
	private String codund; // Código da Unidade
	private String codcomprador = "0001"; // Código Comprador - 0001 - Jorge Luis Caetano Brito
	private String stscotacao = "2"; // Status da Cotação
	private String stsitem = "0"; // Status do Item - 0 - Cotado - 1 - Não Fornece - 2 - Não Cotou - 3 - Desqualificado 
	private String txtobservacao = "Integração SCODE, RM (TOTVS)"; // Campo de observação
	private String codmoeda = "R$"; // Moeda da Cotação
	private String tipojulgamento = "P"; // Tipo de Julgamento da Melhor Oferta
	private String ano = "2011"; // Ano corrente - Usado para gerar o código da cotação "codcotacao";

	public int getCodcoligada() {
		return codcoligada;
	}

	public void setCodcoligada(int codcoligada) {
		this.codcoligada = codcoligada;
	}

	public int getCodcolcfo() {
		return codcolcfo;
	}

	public void setCodcolcfo(int codcolcfo) {
		this.codcolcfo = codcolcfo;
	}

	public int getCfovencedor() {
		return cfovencedor;
	}

	public void setCfovencedor(int cfovencedor) {
		this.cfovencedor = cfovencedor;
	}

	public int getNseqitmmov() {
		return nseqitmmov;
	}

	public void setNseqitmmov(int nseqitmmov) {
		this.nseqitmmov = nseqitmmov;
	}

	public int getTipomovcompras() {
		return tipomovcompras;
	}

	public void setTipomovcompras(int tipomovcompras) {
		this.tipomovcompras = tipomovcompras;
	}

	public int getCodfilial() {
		return codfilial;
	}

	public void setCodfilial(int codfilial) {
		this.codfilial = codfilial;
	}

	public String getCodcotacao() {
		return codcotacao;
	}

	public void setCodcotacao(String codcotacao) {
		this.codcotacao = codcotacao;
	}

	public String getCodund() {
		return codund;
	}

	public void setCodund(String codund) {
		this.codund = codund;
	}

	public String getCodcomprador() {
		return codcomprador;
	}

	public void setCodcomprador(String codcomprador) {
		this.codcomprador = codcomprador;
	}

	public String getStscotacao() {
		return stscotacao;
	}

	public void setStscotacao(String stscotacao) {
		this.stscotacao = stscotacao;
	}

	public String getStsitem() {
		return stsitem;
	}

	public void setStsitem(String stsitem) {
		this.stsitem = stsitem;
	}

	public String getTxtobservacao() {
		return txtobservacao;
	}

	public void setTxtobservacao(String txtobservacao) {
		this.txtobservacao = txtobservacao;
	}

	public String getCodmoeda() {
		return codmoeda;
	}

	public void setCodmoeda(String codmoeda) {
		this.codmoeda = codmoeda;
	}

	public String getTipojulgamento() {
		return tipojulgamento;
	}

	public void setTipojulgamento(String tipojulgamento) {
		this.tipojulgamento = tipojulgamento;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

}
