package br.inatel.ProjetoSymples.view;

import br.inatel.ProjetoSymples.controller.*;
import br.inatel.ProjetoSymples.exceptions.EntradaInvalidaException;
import br.inatel.ProjetoSymples.model.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        //Criando objetos Festa
        Festa jutel = new Festa(1,"Jutel", "Jogos Universitarios","22/10/2022 - 21:00","Cidade do Urso");
        Festa boo = new Festa(2,"Boogaloo", "Festival","10/12/2022 - 16:00","Cidade do Urso");

        //Atributos da pessoa
        String cpf;
        String nome;
        String idade;

        //Atributos Ingresso
        String tamanho;
        int numFesta;
        int preco;
        int tipoIng;

        //Variáveis de controle
        int controle = 0;
        int compra = 0;
        int confirma;

        //Criando os objetos que fazem a ligação com o Banco de dados
        PessoaDAO pessoaDAO = new PessoaDAO();
        VipDAO vipDAO = new VipDAO();
        PistaDAO pistaDAO = new PistaDAO();
        FestaDAO festaDAO = new FestaDAO();

        //Inserindo as festas no Banco
        festaDAO.insertFesta(jutel);
        festaDAO.insertFesta(boo);

        while(compra == 0) {

            //Início dados da Pessoa

            //CPF
            cpf = JOptionPane.showInputDialog("Digite seu CPF:");
            //Verificando se a entrada corresponde ao padrão solicitado
            while (controle == 0) {
                try {
                    controle = 1;
                    VerificaEntrada.verificaCpf(cpf);
                } catch (EntradaInvalidaException e) {
                    JOptionPane.showMessageDialog(null, "CPF Inválido");
                    cpf = JOptionPane.showInputDialog("Digite seu CPF:");
                    controle = 0;
                }
            }controle = 0;

            //Nome
            nome = JOptionPane.showInputDialog("Digite seu Nome:");
            while (controle == 0) {
                try {
                    controle = 1;
                    VerificaEntrada.verificaNome(nome);
                } catch (EntradaInvalidaException e) {
                    JOptionPane.showMessageDialog(null, "Nome Inválido");
                    nome = JOptionPane.showInputDialog("Digite seu Nome:");
                    controle = 0;
                }
            }controle = 0;

            //Idade
            idade = JOptionPane.showInputDialog("Digite sua Idade:");
            while (controle == 0) {
                try {
                    controle = 1;
                    VerificaEntrada.verificaIdade(idade);
                } catch (EntradaInvalidaException e) {
                    JOptionPane.showMessageDialog(null, "Idade Inválida");
                    idade = JOptionPane.showInputDialog("Digite sua Idade:");
                    controle = 0;
                } catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null,"Entre com um número válido!");
                    idade = JOptionPane.showInputDialog("Digite sua Idade:");
                    controle = 0;
                }

            }controle = 0;



            //Criando objeto Pessoa
            Pessoa pessoa = new Pessoa(nome,cpf,idade);

            //FIM DOS DADOS DA PESSOA

            //INICIO DOS DADOS DA FESTA
            //ID DA FESTA
            numFesta = Integer.parseInt(JOptionPane.showInputDialog("Escolha a festa: (1- Jutel, 2- Bogaloo)"));
            while (controle == 0) {
                try {
                    controle = 1;
                    VerificaEntrada.verificaFesta(numFesta);
                } catch (EntradaInvalidaException e) {
                    JOptionPane.showMessageDialog(null, "Festa escolhida não existe!");
                    numFesta = Integer.parseInt(JOptionPane.showInputDialog("Escolha a festa: (1- Jutel, 2- Bogaloo)"));
                    controle = 0;
                }
            }controle = 0;

                //FESTA 1

            if(numFesta == 1){
                preco = 90;
                tipoIng = Integer.parseInt(JOptionPane.showInputDialog("Escolha o tipo: (1- Pista, 2- VIP)"));
                while (controle == 0) {
                    try {
                        controle = 1;
                        VerificaEntrada.verificaTipo(tipoIng);
                    } catch (EntradaInvalidaException e) {
                        JOptionPane.showMessageDialog(null, "Tipo de ingresso não existe!");
                        tipoIng = Integer.parseInt(JOptionPane.showInputDialog("Escolha o tipo: (1- Pista, 2- VIP)"));
                        controle = 0;
                    }
                }controle = 0;

                //FESTA 1 - INGRESSO PISTA

                if(tipoIng == 1) {

                    tamanho = JOptionPane.showInputDialog("Qual tamanho da Camiseta? PP / P / M / G / GG");
                    while (controle == 0) {
                        try {
                            controle = 1;
                            VerificaEntrada.verificaTamanho(tamanho);
                        } catch (EntradaInvalidaException e) {
                            JOptionPane.showMessageDialog(null, "Tamanho não existe!");
                            tamanho = JOptionPane.showInputDialog("Qual tamanho da Camiseta? PP / P / M / G / GG");
                            controle = 0;
                        }
                    }controle = 0;

                    //Transformando a data de "Date" para "String" no formato YYYY-MM-DD
                    java.util.Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    String format = formatter.format(date);

                    //Criando objeto Pista
                    Pista ingresso = new Pista(preco,numFesta,cpf,format,tamanho);

                    //Confirmando a compra
                    confirma = JOptionPane.showConfirmDialog(null,"Total: R$"+preco+" Confirma?");

                    //Se confirmado, as informações são inseridas no BD. Se não for confirmado, nada é inserido no BD e uma mensagem de cancelamento aparecerá
                    if(confirma == 0){
                        pessoaDAO.insertPessoa(pessoa);
                        pistaDAO.insertPista(ingresso);

                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Cancelado!");
                    }

                }

                //FESTA 1 - INGRESSO VIP

                else{

                    if(VIP.verifica(idade)){
                        preco = preco + 100;
                        tamanho = JOptionPane.showInputDialog("Qual tamanho do Abada? PP / P / M / G / GG");
                        while (controle == 0) {
                            try {
                                controle = 1;
                                VerificaEntrada.verificaTamanho(tamanho);
                            } catch (EntradaInvalidaException e) {
                                JOptionPane.showMessageDialog(null, "Tamanho não existe!");
                                tamanho = JOptionPane.showInputDialog("Qual tamanho da Abada? PP / P / M / G / GG");
                                controle = 0;
                            }
                        }controle = 0;

                        //Transformando a data de "Date" para "String" no formato YYYY-MM-DD
                        java.util.Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        String format = formatter.format(date);

                        //Criando objeto VIP
                        VIP ingresso = new VIP(preco,numFesta,cpf,format,tamanho);

                        //Confirmando a compra
                        confirma = JOptionPane.showConfirmDialog(null,"Total: R$"+preco+" Confirma?");

                        //Se confirmado, as informações são inseridas no BD. Se não for confirmado, nada é inserido no BD e uma mensagem de cancelamento aparecerá
                        if(confirma == 0){
                            pessoaDAO.insertPessoa(pessoa);
                            vipDAO.insertVIP(ingresso);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Cancelado!");
                        }
                    }

                    //Caso a pessoa não seja maior de 18 anos, uma mensagem aparecerá na tela
                    else{
                        JOptionPane.showMessageDialog(null,"O pacote VIP só pode ser adquirido por maiores de 18 anos!");
                    }
                }

            }

                //FESTA 2

            else{
                preco = 110;
                tipoIng = Integer.parseInt(JOptionPane.showInputDialog("Escolha o tipo: (1- Pista, 2- VIP)"));
                while (controle == 0) {
                    try {
                        controle = 1;
                        VerificaEntrada.verificaTipo(tipoIng);
                    } catch (EntradaInvalidaException e) {
                        JOptionPane.showMessageDialog(null, "Tipo de ingresso não existe!");
                        tipoIng = Integer.parseInt(JOptionPane.showInputDialog("Escolha o tipo: (1- Pista, 2- VIP)"));
                        controle = 0;
                    }
                }controle = 0;

                //FESTA 2 - INGRESSO PISTA

                if(tipoIng == 1) {

                    //Tamanho Camiseta
                    tamanho = JOptionPane.showInputDialog("Qual tamanho da Camiseta? PP / P / M / G / GG");
                    while (controle == 0) {
                        try {
                            controle = 1;
                            VerificaEntrada.verificaTamanho(tamanho);
                        } catch (EntradaInvalidaException e) {
                            JOptionPane.showMessageDialog(null, "Tamanho não existe!");
                            tamanho = JOptionPane.showInputDialog("Qual tamanho da Camiseta? PP / P / M / G / GG");
                            controle = 0;
                        }
                    }controle = 0;

                    //Transformando a data de "Date" para "String" no formato YYYY-MM-DD
                    java.util.Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    String format = formatter.format(date);

                    //Criando o objeto Pista
                    Pista ingresso = new Pista(preco,numFesta,cpf,format,tamanho);

                    //Confirmando a compra
                    confirma = JOptionPane.showConfirmDialog(null,"Total: R$"+preco+" Confirma?");

                    //Se confirmado, as informações são inseridas no BD. Se não for confirmado, nada é inserido no BD e uma mensagem de cancelamento aparecerá
                    if(confirma == 0){
                        pessoaDAO.insertPessoa(pessoa);
                        pistaDAO.insertPista(ingresso);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Cancelado!");
                    }
                }

                //FESTA 2 - INGRESSO VIP

                else {

                    //Verificando se a idade é maior que 18 (requisito para ser VIP)
                    if(VIP.verifica(idade)){

                        //Aumentando o preço por ser VIP
                        preco = preco + 100;

                        //Tamanho do Abada
                        tamanho = JOptionPane.showInputDialog("Qual tamanho do Abada? PP / P / M / G / GG");
                        while (controle == 0) {
                            try {
                                controle = 1;
                                VerificaEntrada.verificaTamanho(tamanho);
                            } catch (EntradaInvalidaException e) {
                                JOptionPane.showMessageDialog(null, "Tamanho não existe!");
                                tamanho = JOptionPane.showInputDialog("Qual tamanho da Abada? PP / P / M / G / GG");
                                controle = 0;
                            }
                        }controle = 0;

                        //Transformando a data de "Date" para "String" no formato YYYY-MM-DD
                        java.util.Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        String format = formatter.format(date);

                        //Criando o objeto VIP
                        VIP ingresso = new VIP(preco,numFesta,cpf,format,tamanho);

                        //Confirmando a compra
                        confirma = JOptionPane.showConfirmDialog(null,"Total: R$"+preco+" Confirma?");

                        //Se confirmado, as informações são inseridas no BD. Se não for confirmado, nada é inserido no BD e uma mensagem de cancelamento aparecerá
                        if(confirma == 0){
                            pessoaDAO.insertPessoa(pessoa);
                            vipDAO.insertVIP(ingresso);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Cancelado!");
                        }
                    }

                    //Caso a pessoa não seja maior de 18 anos, uma mensagem aparecerá na tela
                    else{
                        JOptionPane.showMessageDialog(null,"O pacote VIP só pode ser adquirido por maiores de 18 anos!");
                    }
                }

            }

            //Comprar novamente
            compra = JOptionPane.showConfirmDialog(null,"Comprar novamente?");
        }

        //UPDATE
        festaDAO.updateIdFesta(1,5);
        //DELETE
        pessoaDAO.deletePessoa("123");

        //SELECT SEM FILTRO
        festaDAO.selectFesta();
        pessoaDAO.selectPessoa();
        pistaDAO.selectPista();
        vipDAO.selectVIP();


    }


}
