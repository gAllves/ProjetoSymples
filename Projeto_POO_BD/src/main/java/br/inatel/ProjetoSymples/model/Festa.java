package br.inatel.ProjetoSymples.model;

public class Festa {

    //Atributos
    private String nomeFesta;
    private String tema;
    private String dataHora;
    private String local;
    private int id;

    //Construtor Festa
    public Festa(int id, String nomeFesta, String tema, String dataHora, String local) {
        this.id = id;
        this.nomeFesta = nomeFesta;
        this.tema = tema;
        this.dataHora = dataHora;
        this.local = local;
    }

    //Getter
    public String getNomeFesta() {
        return nomeFesta;
    }

    public String getTema() {
        return tema;
    }

    public String getDataHora() {
        return dataHora;
    }

    public String getLocal() {
        return local;
    }

    public int getId() {
        return id;
    }
}

