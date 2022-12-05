package br.inatel.ProjetoSymples.model;

public class Pessoa {
    //Atributos
    private String cpf;
    private String nome;
    private String idade;

    //Construtor
    public Pessoa(String nome, String cpf, String idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }

    //getters
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getIdade() {
        return idade;
    }
}
