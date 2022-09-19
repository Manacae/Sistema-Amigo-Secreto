package com.example;

import java.util.List;
import java.util.LinkedList;

public class SistemaAmigo {
    private List<Mensagem> mensagens;
    private List<Amigo> amigos;

    public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException{
        for(Amigo grupo: this.amigos){
            if (grupo.getEmail().equals(emailAmigo)){
                throw new AmigoJaExisteException("Já existe alguém com o email: "+emailAmigo);
            }
        }

        Amigo amigoNovo = new Amigo(nomeAmigo, emailAmigo);
        this.amigos.add(amigoNovo);
    }

    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexisteException {
        for(Amigo grupo: this.amigos){
            if (grupo.getEmail().equals(emailAmigo)){
                return grupo;
            }
        }

        throw new AmigoInexistenteException("Não foi encontrado no sistema um usuário com o email: "+emailAmigo);
    }
    
    public List<Mensagem> pesquisaTodasAsMensagens(){
        return mensagens;
    }

    public List<Amigo> pesquisaTodosOsAmigos(){
        return amigos;
    } 

    public List<Mensagem> presquisaMensagensAnonimas(){
        
        List<Mensagem> listaMensagens = new LinkedList<>();
        
        for(Mensagem msg : this.mensagens){
            
            if (msg.ehAnonima()){
                listaMensagens.add(msg);
            }
        }
        
        return listaMensagens;
    }

    public void enviarMensagemParaAlguem(String texto, String remetente, boolean ehAnonima){

    }

    public void enviarMensagemParaTodos(String texto, String remetente, String destinatario, boolean ehAnonima){

    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException(){
        return null;

        //throw new AmigoNaoSorteadoException(), throw new AmigoInexistenteException();
    }

    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws AmigoInexistenteException {

        //throw new AmigoInexistenteException();
    }


}   
    

