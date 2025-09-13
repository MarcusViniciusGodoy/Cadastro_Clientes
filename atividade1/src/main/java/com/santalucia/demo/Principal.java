package com.santalucia.demo;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Principal extends JFrame{

    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JButton btnCadastrar;

    public Principal(){
        setTitle("Cadastro usuário");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridLayout(4, 2, 10,10));
        ((JPanel)getContentPane()).setBorder(BorderFactory.
            createEmptyBorder(10, 10, 10, 10));
        add(new JLabel("Nome Completo: "));
        txtNome = new JTextField();
        add(txtNome);
        add(new JLabel("E-mail: "));
        txtEmail = new JTextField();
        add(txtEmail);
        add(new JLabel("Telefone: "));
        txtTelefone = new JTextField();
        add(txtTelefone);
        btnCadastrar = new JButton("Cadastrar");
        add(btnCadastrar);

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
