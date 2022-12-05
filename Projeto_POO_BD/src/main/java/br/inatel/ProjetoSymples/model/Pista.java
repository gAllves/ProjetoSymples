package br.inatel.ProjetoSymples.model;

public class Pista extends Ingresso{
    //Atributo específico
    private String tamanhoCamiseta;

    //Construtor VIP herdando de Ingresso e adicionando o atributo específico
    public Pista(float valorIngresso, int festa_idfesta, String pessoa_cpf, String dataCompra, String tamanhoCamiseta) {
        super(valorIngresso, festa_idfesta, pessoa_cpf, dataCompra);
        this.tamanhoCamiseta = tamanhoCamiseta;
    }

    //Getter
    public String getTamanhoCamiseta() {
        return tamanhoCamiseta;
    }
}
