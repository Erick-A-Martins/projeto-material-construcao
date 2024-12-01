package view;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;

public class ClienteView extends JFrame {

    public ClienteView() {
        this.setTitle("Material de Construção");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLayout(new BorderLayout());

        JLabel Titulo = new JLabel("Cadastro Cliente", SwingConstants.CENTER);
        Titulo.setFont(new Font("Verdana", Font.BOLD, 22));
        Titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        this.add(Titulo, BorderLayout.NORTH);

        JPanel Principal = new JPanel();
        Principal.setLayout(new GridLayout(8, 2, 5, 5));

        // Campos de entrada
        JTextField IdCliente = new JTextField(20);
        JTextField Nome = new JTextField(20);
        JTextField CPF = new JTextField(20);
        JTextField RG = new JTextField(20);
        JTextField Endereco = new JTextField(20);
        JTextField Cidade = new JTextField(20);
        JTextField UF = new JTextField(20);

        // Adiciona os componentes ao painel
        Principal.add(new JLabel("ID:"));
        Principal.add(IdCliente);
        Principal.add(new JLabel("Nome do Cliente:"));
        Principal.add(Nome);
        Principal.add(new JLabel("CPF:"));
        Principal.add(CPF);
        Principal.add(new JLabel("RG:"));
        Principal.add(RG);
        Principal.add(new JLabel("Endereço:"));
        Principal.add(Endereco);
        Principal.add(new JLabel("Cidade:"));
        Principal.add(Cidade);
        Principal.add(new JLabel("UF:"));
        Principal.add(UF);

        JPanel PainelBotao = new JPanel();
        JButton Enviar = new JButton("Enviar");

        Enviar.addActionListener(env -> {
            // Coletar os dados dos campos
            String idCliente = IdCliente.getText();
            String nome = Nome.getText();
            String cpf = CPF.getText();
            String rg = RG.getText();
            String endereco = Endereco.getText();
            String cidade = Cidade.getText();
            String uf = UF.getText();

            // Chama o método para salvar no banco
            inserirCliente(idCliente, nome, cpf, rg, endereco, cidade, uf);

			this.dispose();
			CadastroProdutoView produto = new CadastroProdutoView();
			produto.setVisible(true);
        });

        Enviar.setFont(new Font("Arial", Font.BOLD, 16));
        Enviar.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        Enviar.setFocusPainted(false);
        Enviar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        PainelBotao.add(Enviar);

        this.add(new JScrollPane(Principal), BorderLayout.CENTER);
        this.add(PainelBotao, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    private void inserirCliente(String idCliente, String nome, String cpf, String rg, String endereco, String cidade, String uf) {
        String url = "jdbc:mysql://localhost:3306/material_construcao";
        String usuario = "root";
        String senha = "Erick465208-";

        String sql = "INSERT INTO cliente (id_cliente, nome_cliente, rg_cliente, cpf_cliente, endereco_cliente, cidade_cliente, uf_cliente) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(idCliente));
            stmt.setString(2, nome);
            stmt.setString(3, rg);
            stmt.setString(4, cpf);
            stmt.setString(5, endereco);
            stmt.setString(6, cidade);
            stmt.setString(7, uf);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Cliente inserido com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao inserir cliente: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ClienteView();
    }
}
