package view;

import java.awt.*;
import javax.swing.*;

public class ValidacaoView extends JFrame {

    public static void main(String[] args) {
        // Configuração da tela principal
        JFrame Tela = new JFrame("Material de Construção");
        Tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tela.setSize(450, 350);
        Tela.setLayout(new BorderLayout());

        // Título estilizado
        JLabel Titulo = new JLabel("Validação de Entrada", SwingConstants.CENTER);
        Titulo.setFont(new Font("Verdana", Font.BOLD, 22));
        Titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        Tela.add(Titulo, BorderLayout.NORTH);

        // Painel central para os dois botões
        JPanel PainelCentral = new JPanel();
        PainelCentral.setLayout(new GridLayout(2, 1, 10, 10));
        PainelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Botão Visualizar Ficha
        JButton BotaoVisualizar = new JButton("Visualizar Ficha");
        BotaoVisualizar.setFont(new Font("Arial", Font.BOLD, 18));
        BotaoVisualizar.setPreferredSize(new Dimension(200, 50));
        PainelCentral.add(BotaoVisualizar);

        // Botão Inserir Ficha
        JButton BotaoInserir = new JButton("Inserir Ficha");
        BotaoInserir.setFont(new Font("Arial", Font.BOLD, 18));
        BotaoInserir.setPreferredSize(new Dimension(200, 50));
        PainelCentral.add(BotaoInserir);

        // Adiciona o painel central à tela
        Tela.add(PainelCentral, BorderLayout.CENTER);

        // Painel inferior para o botão "Sair"
        JPanel PainelInferior = new JPanel();
        PainelInferior.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Botão Sair
        JButton BotaoSair = new JButton("Sair");
        BotaoSair.setFont(new Font("Arial", Font.BOLD, 14));
        BotaoSair.setPreferredSize(new Dimension(100, 40));
        PainelInferior.add(BotaoSair);

        // Adiciona o painel inferior à tela
        Tela.add(PainelInferior, BorderLayout.SOUTH);

        // Ações dos botões
        BotaoVisualizar.addActionListener(e -> {
        	String senhaAdmin = "123senha";
        	boolean repeat = true;
        	while (repeat == true) {
        		String senhaAdminInput = JOptionPane.showInputDialog(null, "Insira a senha de administrador:");
            	if (senhaAdminInput.equals(senhaAdmin)) {
            		repeat = false;
                    JOptionPane.showMessageDialog(Tela, "Abrindo visualizar ficha\n Aperte OK para abrir.", "Ação", JOptionPane.INFORMATION_MESSAGE);
                    Tela.dispose();
                    BuscaId busca = new BuscaId();
                    busca.setVisible(true);
            	} else {
            		JOptionPane.showMessageDialog(Tela, "Senha incorreta!\n Aperte OK para fechar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            		
            	}
        	}
        });

        BotaoInserir.addActionListener(e -> {
            JOptionPane.showMessageDialog(Tela, "Abrindo inserir ficha\n Aperte OK para abrir.", "Ação", JOptionPane.INFORMATION_MESSAGE);
            Tela.dispose();
            ClienteView TelaFicha = new ClienteView();
            TelaFicha.setVisible(true);
            // ClienteView.main(new String[]{});            
        });

        BotaoSair.addActionListener(e -> {
            System.exit(0);
        });

        // Torna a tela visível e centraliza na tela
        Tela.setLocationRelativeTo(null);
        Tela.setVisible(true);
    }

    // public static void main(String[] args) {
    //     new ValidacaoView();
    // }

    
}
