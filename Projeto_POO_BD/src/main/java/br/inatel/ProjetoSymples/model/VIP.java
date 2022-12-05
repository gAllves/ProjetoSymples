package br.inatel.ProjetoSymples.model;
import br.inatel.ProjetoSymples.controller.Verifica;

public class VIP extends Ingresso implements Verifica {

    //Atributo específico
    private String tamanhoAbada;

    //Construtor VIP herdando de Ingresso e adicionando o atributo específico
    public VIP(float valorIngresso, int festa_idfesta, String pessoa_cpf, String dataCompra, String tamanhoAbada) {
        super(valorIngresso, festa_idfesta, pessoa_cpf, dataCompra);
        this.tamanhoAbada = tamanhoAbada;
    }

    //Método da interface "Verifica"
    public static boolean verifica(String idade) {

        int id = Integer.parseInt(idade);

        return id >= 18;
    }

    //Getter
    public String getTamanhoAbada() {
        return tamanhoAbada;
    }
}
