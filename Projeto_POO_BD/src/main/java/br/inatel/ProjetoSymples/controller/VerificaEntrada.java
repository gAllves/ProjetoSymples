package br.inatel.ProjetoSymples.controller;
import br.inatel.ProjetoSymples.exceptions.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificaEntrada {

    //Função usada para verificar se o CPF da pessoa é válido
    public static void verificaCpf(String cpf) throws EntradaInvalidaException {
        Pattern CPF = Pattern.compile("[0-9]+");
        Matcher match = CPF.matcher(cpf);
        boolean matchfound = match.matches();

        if(!matchfound){
            throw new EntradaInvalidaException();
        }
    }

    //Função usada para verificar se o Nome da pessoa é válido
    public static void verificaNome(String nome) throws EntradaInvalidaException {
        Pattern Nome = Pattern.compile("[a-zA-Zà-úÀ-Ú\s]*");
        Matcher match = Nome.matcher(nome);
        boolean matchfound = match.matches();

        if(!matchfound){
            throw new EntradaInvalidaException();
        }
    }

    //Função usada para verificar se a Idade da pessoa é válido
    public static void verificaIdade(String idade) throws EntradaInvalidaException {

        Pattern Idade = Pattern.compile("[0-9]{1,2}");
        Matcher match = Idade.matcher(idade);
        boolean matchfound = match.matches();

        if(!matchfound){
            throw new EntradaInvalidaException();
        }


    }

    //Função usada para verificar se o id da festa é válido
    public static void verificaFesta(int id) throws EntradaInvalidaException {
        if (id != 1 && id != 2){
            throw new EntradaInvalidaException();
        }
    }

    //Função usada para verificar se o tipo de ingresso é válido
    public static void verificaTipo(int id) throws EntradaInvalidaException {
        if (id != 1 && id != 2){
            throw new EntradaInvalidaException();
        }
    }

    public static void verificaTamanho(String tamanho) throws EntradaInvalidaException {
        Pattern Tamanho = Pattern.compile("(PP)|(P)|(M)|(G)|(GG)");
        Matcher match = Tamanho.matcher(tamanho);
        boolean matchfound = match.matches();

        if(!matchfound){
            throw new EntradaInvalidaException();
        }
    }
}
