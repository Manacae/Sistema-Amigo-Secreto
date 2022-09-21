package com.example;

import javax.swing.JOptionPane;

import com.example.AmigoInexistenteException;
import com.example.AmigoJaExisteException;

public class TestaSistemaAmigoGUI {
    public static void main(String [] args){
        
        int maxMensagens = Integer.parseInt(JOptionPane.showInputDialog("Digite o número máximo de mensagens suportado pelo sistema: "));
        
        SistemaAmigo sistema = new SistemaAmigo(maxMensagens);

        int maxAmigos = Integer.parseInt(JOptionPane.showInputDialog("Digite o número máximo de pessoas participantes da brincadeira: "));

        for (int k = 0; k<maxAmigos; k++) {
            String nome = JOptionPane.showInputDialog("Digite o nome da pessoa: ");
            String email = JOptionPane.showInputDialog("Digite o email da pessoa: ");

            try {
                sistema.cadastraAmigo(nome, email);
                JOptionPane.showMessageDialog(null, "Pessoa cadastrada com sucesso!");
            } catch (AmigoJaExisteException e) {
                JOptionPane.showMessageDialog(null, "Pessoa já existe!");

            }
        }
        
        for (int k = 0; k<maxAmigos; k++) {
            String emailDaPessoa = JOptionPane.showInputDialog("Digite o email da pessoa");
            String emailAmigoSorteado = JOptionPane.showInputDialog("Digite o email do amigo secreto da pessoa");
            
            try {
                sistema.configuraAmigoSecretoDe(emailDaPessoa, emailAmigoSorteado);
            } catch (AmigoInexistenteException e) {
                throw new RuntimeException(e);
            }
        }    
    }
}


