package br.inatel.ProjetoSymples.model;

public class Ingresso {

    //Atributos
    private float valorIngresso;
    private String dataCompra;
    private String pessoa_cpf;
    private int festa_idfesta;
    public int id;

    //Construtor Ingresso
    public Ingresso(float valorIngresso, int festa_idfesta, String pessoa_cpf, String dataCompra) {
        this.valorIngresso = valorIngresso;
        this.festa_idfesta = festa_idfesta;
        this.pessoa_cpf = pessoa_cpf;
        this.dataCompra = dataCompra;
    }

    //Getter
    public float getValorIngresso() {
        return valorIngresso;
    }

    public String  getDataCompra() {
        return dataCompra;
    }

    public String getPessoa_cpf() {
        return pessoa_cpf;
    }

    public int getFesta_idfesta() {
        return festa_idfesta;
    }
}
