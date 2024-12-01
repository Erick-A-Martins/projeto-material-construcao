package view;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class BuscaId extends JFrame {

    public BuscaId() {
        // Configuração da tela principal
        setTitle("Material de Construção");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 350);
        this.setLayout(new BorderLayout());

        // Título estilizado
        JLabel Titulo = new JLabel("Busca por ID", SwingConstants.CENTER);
        Titulo.setFont(new Font("Verdana", Font.BOLD, 22));
        Titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        this.add(Titulo, BorderLayout.NORTH);

        // Painel central para o formulário com espaçamento ajustado
        JPanel PainelCentral = new JPanel();
        PainelCentral.setLayout(new GridLayout(4, 2, 10, 10));
        PainelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Campo para ID
        JLabel LabelID = new JLabel("Insira o ID:");
        LabelID.setFont(new Font("Verdana", Font.BOLD, 16));
        PainelCentral.add(LabelID);

        JTextField CampoID = new JTextField(15);
        CampoID.setFont(new Font("Verdana", Font.PLAIN, 16));
        PainelCentral.add(CampoID);

        PainelCentral.add(new JLabel()); // Espaço vazio para alinhamento
        JButton BotaoEnviar = new JButton("Buscar");
        BotaoEnviar.setFont(new Font("Arial", Font.BOLD, 14));
        BotaoEnviar.setFocusPainted(false);
        PainelCentral.add(BotaoEnviar);

        // Adiciona o painel central à tela
        this.add(PainelCentral, BorderLayout.CENTER);

        // Ação do botão de busca
        BotaoEnviar.addActionListener(e -> {
            String id = CampoID.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, insira um ID válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            buscarPorId(id);
        });

        // Torna a tela visível e centraliza na tela
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void buscarPorId(String id) {
        String url = "jdbc:mysql://localhost:3306/material_construcao";
        String usuario = "root";
        String senha = "Erick465208-";

        // Consultas SQL específicas para cada tabela
        String sqlCliente = "SELECT * FROM cliente WHERE id_cliente = ?";
        String sqlProduto = "SELECT * FROM produto WHERE id_produto = ?";
        String sqlCompra = "SELECT * FROM compra WHERE id_compra = ?";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {

            // Verifica em cada tabela
            if (buscarDados(conexao, sqlCliente, id, "Cliente")) return;
            if (buscarDados(conexao, sqlProduto, id, "Produto")) return;
            if (buscarDados(conexao, sqlCompra, id, "Compra")) return;

            // Se não encontrar em nenhuma tabela
            JOptionPane.showMessageDialog(this, "ID não encontrado em nenhuma tabela.", "Erro", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean buscarDados(Connection conexao, String sql, String id, String tipo) {
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Monta os detalhes com base no tipo
                String detalhes = "";
                switch (tipo) {
                    case "Cliente":
                        detalhes = "ID: " + rs.getInt("id_cliente") + "\n" +
                                   "Nome: " + rs.getString("nome_cliente") + "\n" +
                                   "RG: " + rs.getString("rg_cliente") + "\n" +
                                   "CPF: " + rs.getString("cpf_cliente") + "\n" +
                                   "Endereço: " + rs.getString("endereco_cliente") + "\n" +
                                   "Cidade: " + rs.getString("cidade_cliente") + "\n" +
                                   "UF: " + rs.getString("uf_cliente");
                        break;

                    case "Produto":
                        detalhes = "ID: " + rs.getInt("id_produto") + "\n" +
                                   "Descrição: " + rs.getString("descricao_produto") + "\n" +
                                   "Quantidade: " + rs.getInt("quantidade_produto") + "\n" +
                                   "Preço Unitário: " + rs.getDouble("preco_unitario_produto") + "\n" +
                                   "Preço Total: " + rs.getDouble("preco_total_produto");
                        break;

                    case "Compra":
                        detalhes = "ID: " + rs.getInt("id_compra") + "\n" +
                                   "Preço Total: " + rs.getDouble("preco_total_compra") + "\n" +
                                   "Data da Compra: " + rs.getString("data_compra") + "\n" +
                                   "ID do Cliente: " + rs.getInt("id_cliente") + "\n" +
                                   "ID do Produto: " + rs.getInt("id_produto");
                        break;
                }

                // Exibe os detalhes em um JOptionPane
                JOptionPane.showMessageDialog(this, detalhes, tipo + " Encontrado", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        new BuscaId();
    }
}
