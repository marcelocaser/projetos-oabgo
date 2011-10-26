package br.com.scode;

public class ClienteFornecedor {

	private String pais = "Brasil";
	private int codpais = 1; // 1 - Brasil
	private String usuario = "mestre"; // Usuário padrão.
	private int codcoligada = 1; // Representa código da coligada.
	private int pagrec = 2; // 1 - Cliente; 2 - Fornecedor; 3 - Ambos(Cliente/Fornecedor)
	private int ativo = 1; // 1 - Ativo; 0 - Inativo;
	private String pessoafisoujur = "J"; // J - Pessoa Juridica; F - Pessoa Física
	private int tipoopcombustivel = 3; // Tipo de operação com combustível
	private String codmunicipio;
	private String codcfo; // Código do Cliente/Fornecedor
	private int idcfo; // ID do Cliente/Fornecedor

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getCodpais() {
		return codpais;
	}

	public void setCodpais(int codpais) {
		this.codpais = codpais;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getCodcoligada() {
		return codcoligada;
	}

	public void setCodcoligada(int codcoligada) {
		this.codcoligada = codcoligada;
	}

	public int getPagrec() {
		return pagrec;
	}

	public void setPagrec(int pagrec) {
		this.pagrec = pagrec;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public String getPessoafisoujur() {
		return pessoafisoujur;
	}

	public void setPessoafisoujur(String pessoafisoujur) {
		this.pessoafisoujur = pessoafisoujur;
	}

	public int getTipoopcombustivel() {
		return tipoopcombustivel;
	}

	public void setTipoopcombustivel(int tipoopcombustivel) {
		this.tipoopcombustivel = tipoopcombustivel;
	}

	public void setCodmunicipio(String codmunicipio) {
		this.codmunicipio = codmunicipio;
	}

	public String getCodmunicipio() {
		return codmunicipio;
	}

	public void setCodcfo(String codcfo) {
		this.codcfo = codcfo;
	}

	public String getCodcfo() {
		return codcfo;
	}

	public void setIdcfo(int idcfo) {
		this.idcfo = idcfo;
	}

	public int getIdcfo() {
		return idcfo;
	}

}
