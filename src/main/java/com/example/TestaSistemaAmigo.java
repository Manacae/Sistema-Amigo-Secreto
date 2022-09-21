package com.example;

import java.util.List;

import com.example.AmigoInexistenteException;
import com.example.AmigoJaExisteException;
import com.example.AmigoNaoSorteadoException;

public class TestaSistemaAmigo {

    public static void main (String[] args){

        SistemaAmigo sistema = new SistemaAmigo();

    
        try{
            sistema.cadastraAmigo("1","1.aleatorio1@gmail.com");
            sistema.cadastraAmigo("2", "2.aleatorio2@gmail.com");

            System.out.println("Amigos cadastrados com sucesso");

        }catch(AmigoJaExisteException grupo){
            System.out.println(grupo.getMessage());
        }

        try{
            sistema.configuraAmigoSecretoDe("1.aleatorio1@gmail.com", "2.aleatorio2@gmail.com");
            sistema.configuraAmigoSecretoDe("2.aleatorio2@gmail.com", "1.aleatorio1@gmail.com");

            System.out.println("Amigos configurados com sucesso!");

        }catch(AmigoInexistenteException grupo){
            System.out.println(grupo.getMessage());
        }

        sistema.enviarMensagemParaAlguem("Olá", "maria@gmail.com", "jose@gmail.com", true);
        
        sistema.enviarMensagemParaTodos("Ansiosa para começar.", "maria@gmail.com", true);
        
        List<Mensagem> mensagensAnonimas = sistema.pesquisaMensagensAnonimas();
        for(Mensagem msg: mensagensAnonimas) {
            System.out.println(msg.getTextoCompletoAExibir());

            try {
                String emailDoAmigoDeJose = sistema.pesquisaAmigoSecretoDe("1.aleatorio@gmail.com");
                if (emailDoAmigoDeJose.equals("2.aleatorio2@gmail.com")) {
                    System.out.println("OK");
                } else {
                    System.out.println("Erro no amigo de José");
                }
            } catch (AmigoInexistenteException | AmigoNaoSorteadoException grupo) {
                System.out.println("Problema ao ver amigo: "+grupo.getMessage());
            }
     }

    
   }   

}
