package model;

public class ClienteMT {
	
	//Declarando variáveis
	protected int id_cliente;
	protected String nome_cliente;
	protected String rg_cliente;
	protected String cpf_cliente;
	protected String endereco_cliente;
	protected String cidade_cliente;
	protected String uf_cliente;
	
	//Construtor
	/*public ClienteMT(int id_cliente, String nome_cliente, String cpf_cliente, String endereco_cliente,
			String cidade_cliente, String uf_cliente) {
		super();
		this.id_cliente = id_cliente;
		this.nome_cliente = nome_cliente;
		this.cpf_cliente = cpf_cliente;
		this.endereco_cliente = endereco_cliente;
		this.cidade_cliente = cidade_cliente;
		this.uf_cliente = uf_cliente;
	}*/
	
	//Getters e Setters
	//Getter e Setter --> id_cliente
	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	//Getter e Setter --> nome_cliente
	public String getNome_cliente() {
		return nome_cliente;
	}

	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}
	
	public String getRg_cliente() {
		return rg_cliente;
	}
	
	public void setRg_cliente(String rg_cliente) {
		this.rg_cliente = rg_cliente;
	}
	
	
	//Getter e Setter --> cpf_cliente
	public String getCpf_cliente() {
		return cpf_cliente;
	}

	public void setCpf_cliente(String cpf_cliente) {
		this.cpf_cliente = cpf_cliente;
	}
	
	//Getter e Setter --> endereco_cliente
	public String getEndereco_cliente() {
		return endereco_cliente;
	}

	public void setEndereco_cliente(String endereco_cliente) {
		this.endereco_cliente = endereco_cliente;
	}
	
	//Getter e Setter --> cidade_cliente
	public String getCidade_cliente() {
		return cidade_cliente;
	}

	public void setCidade_cliente(String cidade_cliente) {
		this.cidade_cliente = cidade_cliente;
	}
	
	//Getter e Setter --> uf_cliente
	public String getUf_cliente() {
		return uf_cliente;
	}

	public void setUf_cliente(String uf_cliente) {
		this.uf_cliente = uf_cliente;
	}
	
	
	
}
