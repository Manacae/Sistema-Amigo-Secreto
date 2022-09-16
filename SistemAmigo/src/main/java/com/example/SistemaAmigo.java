package com.example;

import java.util.List;
import java.util.LinkedList;

public class SistemaAmigo {
    private List<Mensagem> mensagens;
    private List<Amigo> amigos;

    public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException{
        for(Amigo a: this.amigos){
            if (a.getEmail().equals(emailAmigo)){
                throw new AmigoJaExisteException("Já existe alguém com o email: "+emailAmigo);
            }
        }

        Amigo amigoNovo = new Amigo(nomeAmigo, emailAmigo);
        this.amigos.add(amigoNovo);
    }

    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexisteException {
        for(Amigo a: this.amigos){
            if (a.getEmail().equals(emailAmigo)){
                return a;
            }
        }

        throw new AmigoInexisteException("Não foi encontrado no sistema um usuário com o email: "+emailAmigo);
    }

}
