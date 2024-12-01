package model;

import java.util.Date;
import javax.swing.JOptionPane;
import dao.ConnectionFactory;
import java.sql.*;

public class CompraMT {
    
    ClienteMT cliente = new ClienteMT();
    ProdutoMT produto = new ProdutoMT();
    
    private int id_compra;
    private double preco_total_compra;
    private Date data_compra;
    
    // Getters e Setters
    public int getId_compra() {
        return id_compra;
    }
    
    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }
    
    public double getPreco_total_compra() {
        return preco_total_compra;
    }
    
    public void setPreco_total_compra(double preco_total_compra) {
        this.preco_total_compra = preco_total_compra;
    }
    
    public Date getData_compra() {
        return data_compra;
    }
    
    public void setData_compra(Date data_compra) {
        this.data_compra = data_compra;
    }
    
    // Métodos
    public void CadastrarCompra(ClienteMT cliente, ProdutoMT produto) {
        
        String clienteSql = "INSERT INTO material_construcao.cliente (nome_cliente, rg_cliente, cpf_cliente, endereco_cliente, cidade_cliente, uf_cliente) VALUES (?, ?, ?, ?, ?, ?);";
        String produtoSql = "INSERT INTO material_construcao.produto (descricao_produto, quantidade_produto, preco_unitario_produto, preco_total_produto) VALUES (?, ?, ?, ?);";
        String compraSql = "INSERT INTO material_construcao.compra (preco_total_compra, data_compra, id_cliente, id_produto) VALUES (?, ?, ?, ?);";
        
        try (Connection conn = ConnectionFactory.conectar()) {
            try {
            	conn.setAutoCommit(false);
                
                int idCliente;
                try (PreparedStatement stmtCliente = conn.prepareStatement(clienteSql, Statement.RETURN_GENERATED_KEYS)) {
                    stmtCliente.setString(1, cliente.getNome_cliente());
                    stmtCliente.setString(2, cliente.getRg_cliente());
                    stmtCliente.setString(3, cliente.getCpf_cliente());
                    stmtCliente.setString(4, cliente.getEndereco_cliente());
                    stmtCliente.setString(5, cliente.getCidade_cliente());
                    stmtCliente.setString(6, cliente.getUf_cliente());
                    
                    stmtCliente.executeUpdate();
                    try (ResultSet rs = stmtCliente.getGeneratedKeys()) {
                        if (rs.next()) {
                            idCliente = rs.getInt(1);
                        } else {
                            throw new SQLException("Falha ao obter o ID gerado para o cliente.");
                        }
                    }
                }
                
                int idProduto;
                try (PreparedStatement stmtProduto = conn.prepareStatement(produtoSql, Statement.RETURN_GENERATED_KEYS)) {
                    stmtProduto.setString(1, produto.getDescricao_produto());
                    stmtProduto.setInt(2, produto.getQuantidade_produto());
                    stmtProduto.setDouble(3, produto.getPreco_unitario_produto());
                    stmtProduto.setDouble(4, produto.getPreco_total_produto());
                    
                    stmtProduto.executeUpdate();
                    try (ResultSet rs = stmtProduto.getGeneratedKeys()) {
                        if (rs.next()) {
                            idProduto = rs.getInt(1);
                        } else {
                            throw new SQLException("Falha ao obter o ID gerado para o produto.");
                        }
                    }
                }
                
                try (PreparedStatement stmtCompra = conn.prepareStatement(compraSql)) {
                    stmtCompra.setDouble(1, preco_total_compra);
                    stmtCompra.setDate(2, new java.sql.Date(data_compra.getTime()));
                    stmtCompra.setInt(3, idCliente);
                    stmtCompra.setInt(4, idProduto);
                    
                    stmtCompra.executeUpdate();
                }
                
                conn.commit();
                JOptionPane.showMessageDialog(null, "Compra cadastrada com sucesso!");
                
            } catch (SQLException sqle) {
            	conn.rollback();
                sqle.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar compra: " + sqle.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar compra: " + e.getMessage());
        }  
        
    }
    
    public void getDadosFicha(int idCompra) {
    	try (Connection conn = ConnectionFactory.conectar();
    		    PreparedStatement stmt = conn.prepareStatement("SELECT c.*, p.*, co.* FROM compra co JOIN cliente c ON co.id_cliente = c.id_cliente JOIN produto p ON co.id_produto = p.id_produto WHERE co.id_compra = ?;")) {
    		    
    		    // IMPORTANTE: TEM QUE SETAR O ID DA COMPRA RELACIONADA À CONSULTA NO BANCO.
    			// FAZER AS TELAS QUE SETTEM O VALOR DE IDCOMPRA DE ACORDO COM INPUT DO USUARIO
    			// ATENÇÃO QUE IDCOMPRA TÁ SENDO PASSADO COMO PARÂMETRO DO MÉTODO
    		
    			stmt.setInt(1, idCompra);
    			
    		    try (ResultSet rs = stmt.executeQuery()) {
    		    	try {
    		            if (rs.next()) {
    		            	
    		                setId_compra(rs.getInt("id_compra"));
    		                setPreco_total_compra(rs.getDouble("preco_total_compra"));
    		                setData_compra(rs.getDate("data_compra"));

    		                cliente.setId_cliente(rs.getInt("id_cliente"));
    		                cliente.setNome_cliente(rs.getString("nome_cliente"));
    		                cliente.setRg_cliente(rs.getString("rg_cliente"));
    		                cliente.setCpf_cliente(rs.getString("cpf_cliente"));
    		                cliente.setEndereco_cliente(rs.getString("endereco_cliente"));
    		                cliente.setCidade_cliente(rs.getString("cidade_cliente"));
    		                cliente.setUf_cliente(rs.getString("uf_cliente"));

    		                produto.setId_produto(rs.getInt("id_produto"));
    		                produto.setDescricao_produto(rs.getString("descricao_produto"));
    		                produto.setQuantidade_produto(rs.getInt("quantidade_produto"));
    		                produto.setPreco_unitario_produto(rs.getDouble("preco_unitario_produto"));
    		                produto.setPreco_total_produto(rs.getDouble("preco_total_produto"));
    		            }
    		        } catch (SQLException e) {
    		            e.printStackTrace();
    		            JOptionPane.showMessageDialog(null, "Erro ao carregar dados da compra: " + e.getMessage());
    		        }
    		    }
    		} catch (SQLException e) {
    		    e.printStackTrace();
    		    JOptionPane.showMessageDialog(null, "Erro ao carregar compra: " + e.getMessage());
    		}
    }
     
}
