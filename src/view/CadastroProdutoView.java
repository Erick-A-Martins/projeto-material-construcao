package view;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;

public class CadastroProdutoView extends JFrame {

    // Construtor da classe
    public CadastroProdutoView() {
        setTitle("Material de Construção");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());

        JLabel Titulo = new JLabel("Cadastro de Produto", SwingConstants.CENTER);
        Titulo.setFont(new Font("Verdana", Font.BOLD, 22));
        Titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(Titulo, BorderLayout.NORTH);

        JPanel Principal = new JPanel();
        Principal.setLayout(new GridLayout(12, 2, 10, 5));

        // Adicionando campos
        JTextField IdProduto = new JTextField(20);
        JTextField Descricao = new JTextField(20);
        JTextField Quantidade = new JTextField(20);
        JTextField PrecoUnidade = new JTextField(20);
        JTextField PrecoTotal = new JTextField(20);

        Principal.add(new JLabel("ID do Produto:") {{
            setFont(new Font("Verdana", Font.BOLD, 16));
        }});
        Principal.add(IdProduto);

        Principal.add(new JLabel("Descrição:") {{
            setFont(new Font("Verdana", Font.BOLD, 16));
        }});
        Principal.add(Descricao);

        Principal.add(new JLabel("Quantidade:") {{
            setFont(new Font("Verdana", Font.BOLD, 16));
        }});
        Principal.add(Quantidade);

        Principal.add(new JLabel("Preço Unitário:") {{
            setFont(new Font("Verdana", Font.BOLD, 16));
        }});
        Principal.add(PrecoUnidade);

        Principal.add(new JLabel("Preço Total:") {{
            setFont(new Font("Verdana", Font.BOLD, 16));
        }});
        Principal.add(PrecoTotal);

        JPanel PainelBotao = new JPanel();
        JButton Enviar = new JButton("Enviar");
        Enviar.setFont(new Font("Arial", Font.BOLD, 16));
        Enviar.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        Enviar.setFocusPainted(false);
        Enviar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Evento para salvar no banco e mudar para próxima tela
        Enviar.addActionListener(e -> {
            // Coleta os dados dos campos
            String idProduto = IdProduto.getText();
            String descricao = Descricao.getText();
            String quantidade = Quantidade.getText();
            String precoUnidade = PrecoUnidade.getText();
            String precoTotal = PrecoTotal.getText();

            // Chama o método para salvar no banco
            inserirProduto(idProduto, descricao, quantidade, precoUnidade, precoTotal);

            // Troca para a próxima tela (opcional)
            this.dispose();
            CompraView TelaCompra = new CompraView();
            TelaCompra.setVisible(true);
        });

        PainelBotao.add(Enviar);

        add(new JScrollPane(Principal), BorderLayout.CENTER);
        add(PainelBotao, BorderLayout.SOUTH);
    }

    private void inserirProduto(String idProduto, String descricao, String quantidade, String precoUnidade, String precoTotal) {
        String url = "jdbc:mysql://localhost:3306/material_construcao";
        String usuario = "root";
        String senha = "Erick465208-";

        String sql = "INSERT INTO produto (id_produto, descricao_produto, quantidade_produto, preco_unitario_produto, preco_total_produto) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(idProduto));
            stmt.setString(2, descricao);
            stmt.setInt(3, Integer.parseInt(quantidade));
            stmt.setDouble(4, Double.parseDouble(precoUnidade));
            stmt.setDouble(5, Double.parseDouble(precoTotal));

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Produto inserido com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao inserir produto: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new CadastroProdutoView();
    }
}
