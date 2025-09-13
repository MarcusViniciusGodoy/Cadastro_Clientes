package com.santalucia.demo;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;

import javax.swing.*;

public class Principal extends JFrame{

    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JButton btnCadastrar;

    public Principal(){
        setTitle("Cadastro usuário");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; 
        gbc.gridy = 0;

        painel.add(new JLabel("Nome Completo:"), gbc);
        gbc.gridx = 1;
        txtNome = new JTextField(20);
        painel.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        painel.add(new JLabel("E-mail:"), gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        painel.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        painel.add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1;
        txtTelefone = new JTextField(20);
        painel.add(txtTelefone, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(new Color(0, 120, 215));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCadastrar.setFocusPainted(false);
        painel.add(btnCadastrar, gbc);

        add(painel, BorderLayout.CENTER);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(emailValido()){
                    cadastrarUsuario();
                } else {
                    JOptionPane.showMessageDialog(Principal.this, "E-mail inválido. Verifique o formato.", "Erro", JOptionPane.INFORMATION_MESSAGE);
                    retornaPrincipal();
                }
            }
        });
    }
    private boolean emailValido(){
        String email = txtEmail.getText();
        if(email.contains("@") && (email.endsWith(".com")|| email.endsWith(".com.br"))){
            return true;
        }else{
            return false;
        }
    }

    private void cadastrarUsuario(){
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String telefone = txtTelefone.getText();

        salvarCliente(nome, email, telefone);

        JOptionPane.showMessageDialog(
            this,
            "Usuário cadastrado com sucesso!\n\n" +
            "Nome: " + nome + "\n" +
            "Email: " + email + "\n" +
            "Telefone: " + telefone,
            "Cadastro",
            JOptionPane.INFORMATION_MESSAGE
        );

        txtNome.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
    }

    private void salvarCliente(String nome, String email, String telefone) {
        String linha = 
            "Nome: " + nome + System.lineSeparator() +
            "Email: " + email + System.lineSeparator() +
            "Telefone: " + telefone + System.lineSeparator() +
            "----------------------" + System.lineSeparator();
        Path caminho = Path.of("clientes.txt");

        try {
            Files.write(caminho, 
                        Arrays.asList(linha), 
                        StandardOpenOption.CREATE, 
                        StandardOpenOption.APPEND);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                this,
                "Erro ao salvar cliente no arquivo: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void retornaPrincipal() {
        txtNome.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");

        txtNome.requestFocus();
    }

    public static void main(String[] args) {
        Principal tela = new Principal();
        tela.setVisible(true);
    }
}
