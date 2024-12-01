package view;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;

public class CompraView extends JFrame {
    public CompraView() {
        setTitle("Material de Construção");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());

        JLabel Titulo = new JLabel("Detalhes Compra", SwingConstants.CENTER);
        Titulo.setFont(new Font("Verdana", Font.BOLD, 22));
        Titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        this.add(Titulo, BorderLayout.NORTH);

        JPanel Principal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Campos de entrada
        gbc.gridx = 0;
        gbc.gridy = 0;
        Principal.add(new JLabel("ID da Compra:") {{
            setFont(new Font("Verdana", Font.BOLD, 16));
        }}, gbc);

        gbc.gridx = 1;
        JTextField IdCompra = new JTextField(20);
        Principal.add(IdCompra, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        Principal.add(new JLabel("Preço total da compra:") {{
            setFont(new Font("Verdana", Font.BOLD, 16));
        }}, gbc);

        gbc.gridx = 1;
        JTextField PrecoCompra = new JTextField(20);
        Principal.add(PrecoCompra, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        Principal.add(new JLabel("Data da Compra:") {{
            setFont(new Font("Verdana", Font.BOLD, 16));
        }}, gbc);

        gbc.gridx = 1;
        JTextField DataCompra = new JTextField(20);
        Principal.add(DataCompra, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        Principal.add(new JLabel("Confirme ID do Cliente:") {{
            setFont(new Font("Verdana", Font.BOLD, 16));
        }}, gbc);

        gbc.gridx = 1;
        JTextField IdCliente = new JTextField(20);
        Principal.add(IdCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        Principal.add(new JLabel("Confirme ID do Produto:") {{
            setFont(new Font("Verdana", Font.BOLD, 16));
        }}, gbc);

        gbc.gridx = 1;
        JTextField IdProduto = new JTextField(20);
        Principal.add(IdProduto, gbc);

        JPanel PainelBotao = new JPanel();
        JButton Enviar = new JButton("Enviar");
        Enviar.setFont(new Font("Arial", Font.BOLD, 16));
        Enviar.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        Enviar.setFocusPainted(false);
        Enviar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        PainelBotao.add(Enviar);

        // Evento para inserir dados no banco
        Enviar.addActionListener(env -> {
            // Coleta os dados dos campos
            String idCompra = IdCompra.getText();
            String precoCompra = PrecoCompra.getText();
            String dataCompra = DataCompra.getText();
            String idCliente = IdCliente.getText();
            String idProduto = IdProduto.getText();

            // Chama o método para salvar no banco
            inserirCompra(idCompra, precoCompra, dataCompra, idCliente, idProduto);
            JOptionPane.showMessageDialog(this, "Compra registrada!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            // Troca de tela (opcional)
            // ValidacaoView TelaValidacao = new ValidacaoView();
            // TelaValidacao.setVisible(true);
        });

        this.add(new JScrollPane(Principal), BorderLayout.CENTER);
        this.add(PainelBotao, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void inserirCompra(String idCompra, String precoCompra, String dataCompra, String idCliente, String idProduto) {
        String url = "jdbc:mysql://localhost:3306/material_construcao";
        String usuario = "root";
        String senha = "Erick465208-";

        String sql = "INSERT INTO compra (id_compra, preco_total_compra, data_compra, id_cliente, id_produto) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(idCompra));
            stmt.setDouble(2, Double.parseDouble(precoCompra));
            stmt.setString(3, dataCompra); // Certifique-se de que o formato da data é aceito pelo banco
            stmt.setInt(4, Integer.parseInt(idCliente));
            stmt.setInt(5, Integer.parseInt(idProduto));

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Compra inserida com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao inserir compra: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new CompraView();
    }
}
