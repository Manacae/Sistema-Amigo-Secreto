package com.example;

import java.util.List;
import java.util.LinkedList;

public class SistemaAmigo {
    private List<Mensagem> mensagens;
    private List<Amigo> amigos;
    private int maxMensagens;
    private static final int DEFAULT_MAX_MENSAGENS = 50;


    public SistemaAmigo() {
        this(DEFAULT_MAX_MENSAGENS);
    }

    public SistemaAmigo(int mensagensMaximas) {
        this.mensagens = new LinkedList<>();
        this.amigos = new LinkedList<>();
        this.maxMensagens = mensagensMaximas;
    }

    public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException{
        for(Amigo grupo: this.amigos){
            if (grupo.getEmail().equals(emailAmigo)){
                throw new AmigoJaExisteException("Já existe alguém com o email: "+emailAmigo);
            }
        }

        Amigo amigoNovo = new Amigo(nomeAmigo, emailAmigo);
        this.amigos.add(amigoNovo);
    }

    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException {
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

    public List<Mensagem> pesquisaMensagensAnonimas(){
        
        List<Mensagem> listaMensagens = new LinkedList<>();
        
        for(Mensagem msg : this.mensagens){
            
            if (msg.ehAnonima()){
                listaMensagens.add(msg);
            }
        }
        
        return listaMensagens;
    }

    // String mensagem = "olá mundo"
    //  String emailRem = "ex@..."
    // String emailDest = "ex@..."
    // SistemaAmigo.enviarMensagem(mensagem, emailRem, emailDest, true)

    public void enviarMensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean ehAnonima){
        
        MensagemParaAlguem msg =  new MensagemParaAlguem(texto, emailRemetente, emailDestinatario, ehAnonima);
        this.mensagens.add(msg);
    }

    public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean ehAnonima){

        MensagemParaTodos msg = new MensagemParaTodos (texto, emailRemetente, ehAnonima);
        this.mensagens.add(msg);
    }


    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws AmigoInexistenteException {

        for (Amigo grupo: this.amigos){
            
            if (grupo.getEmail().equals(emailDaPessoa)){
                grupo.setEmailAmigoSorteado(emailAmigoSorteado);
                return; 
            }

        }
        
        throw new AmigoInexistenteException ("Não foi localizado o email: "+emailDaPessoa);

    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException{
        
        for(Amigo grupo: this.amigos){

            if(grupo.getEmail().equals(emailDaPessoa)){

                String emailAmigoSorteado = grupo.getEmailAmigoSorteado();

                if(emailAmigoSorteado == null){
                    throw new AmigoNaoSorteadoException ("Ainda não foi sorteado o amigo secreto da pessoa com o email: "+emailDaPessoa);
                }else{
                    return emailAmigoSorteado;
                }
            }
        }
        
        throw new AmigoInexistenteException ("Não foi encontrada nenhuma pessoa com o email: "+emailDaPessoa);

    }
    
    public void sortear() throws AmigoInexistenteException{
        List<Amigo> sorteioAmigo = new LinkedList<>();
        List<Amigo> lista = pesquisaTodosOsAmigos();

        for(Amigo grupo: this.amigos){
            if(grupo.getEmailAmigoSorteado() == null){
                int posicaoDaListaSorteada = (int)(Math.random()*lista.size());
                configuraAmigoSecretoDe(grupo.getEmail(), lista.get(posicaoDaListaSorteada).getEmail());
                sorteioAmigo.add(grupo);
                lista.remove(grupo);
            }
        }
   
    }
}   
    

