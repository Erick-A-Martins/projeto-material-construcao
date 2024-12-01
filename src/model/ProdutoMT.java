package model;

public class ProdutoMT {
	
	//Declarando variáveis
	protected int id_produto;
	protected String descricao_produto;
	protected int quantidade_produto;
	protected double preco_unitario_produto;
	protected double preco_total_produto;
	
	//Construtor
	/*public ProdutoMT(int id_produto, String descricao_produto, String quantidade_produto, String preco_unitario_produto,
			String preco_total_produto) {
		super();
		this.id_produto = id_produto;
		this.descricao_produto = descricao_produto;
		this.quantidade_produto = quantidade_produto;
		this.preco_unitario_produto = preco_unitario_produto;
		this.preco_total_produto = preco_total_produto;
	}*/
	
	//Getters e Setters
	//Getter e Setter --> id_produto
	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	
	//Getter e Setter --> descricao_produto
	public String getDescricao_produto() {
		return descricao_produto;
	}

	public void setDescricao_produto(String descricao_produto) {
		this.descricao_produto = descricao_produto;
	}
	
	//Getter e Setter --> quantidade_produto
	public int getQuantidade_produto() {
		return quantidade_produto;
	}

	public void setQuantidade_produto(int quantidade_produto) {
		this.quantidade_produto = quantidade_produto;
	}
	
	//Getter e Setter --> preco_unitario_produto
	public double getPreco_unitario_produto() {
		return preco_unitario_produto;
	}

	public void setPreco_unitario_produto(double preco_unitario_produto) {
		this.preco_unitario_produto = preco_unitario_produto;
	}
	
	//Getter e Setter --> preco_total_produto
	public double getPreco_total_produto() {
		return preco_total_produto;
	}

	public void setPreco_total_produto(double preco_total_produto) {
		this.preco_total_produto = preco_total_produto;
	}	
	
	
	
}
