package br.inatel.ProjetoSymples.controller;
import br.inatel.ProjetoSymples.model.Pista;

import java.sql.SQLException;
import java.util.ArrayList;

public class PistaDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertPista(Pista ingresso) {

        connectToDB(); //Conecta ao banco de dados

        //Comando em SQL:
        String sql = "INSERT INTO pista (valorIngresso, festa_idfesta, pessoa_cpf, dataCompra, tamanhoCamiseta) values (?,?,?,?,?)";
        //O comando recebe paramêtros -> consulta dinâmica (pst)
        try {
            pst = con.prepareStatement(sql);
            pst.setFloat(1, ingresso.getValorIngresso()); // 1- refere-se à primeira interrogação
            pst.setInt(2, ingresso.getFesta_idfesta());  // 2- refere-se à segunda interrogação
            pst.setString(3, ingresso.getPessoa_cpf());   // 3- refere-se à terceira interrogação
            pst.setString(4, ingresso.getDataCompra());   // 4- refere-se à quarta interrogação
            pst.setString(5, ingresso.getTamanhoCamiseta());   // 5- refere-se à quinta interrogação
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {   //Encerra a conexão
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return sucesso;
    }

    //UPDATE
    /**UPDATE TAMANHOCAMISETA DO INGRESSO**/
    public boolean updateTamanhoIngresso(int id, String novoTamanho) {

        connectToDB();

        String sql = "UPDATE pista SET tamanhoCamiseta=? WHERE id=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoTamanho);
            pst.setInt(2, id);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }

        }
        return sucesso;
    }


    //DELETE
    public boolean deletePista(int id) {

        connectToDB();

        String sql = "DELETE FROM pista WHERE id=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return sucesso;
    }

    //SELECT
    /**SELECT INGRESSO SEM FILTRO*/
    public ArrayList<Pista> selectPista() {
        ArrayList<Pista> listaDePista = new ArrayList<>();

        connectToDB();

        String sql = "SELECT * FROM pista";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            System.out.println("Lista de Pista: ");
            System.out.println();
            while (rs.next()) {
                Pista ingressoTemp = new Pista(rs.getFloat("ValorIngresso"), rs.getInt("festa_idfesta"), rs.getString("pessoa_cpf"), rs.getString("dataCompra"),rs.getString("tamanhoCamiseta"));
                ingressoTemp.id = Integer.parseInt(rs.getString("id"));
                System.out.println("ID = "+ ingressoTemp.id);
                System.out.println("Valor Ingresso = R$ " + ingressoTemp.getValorIngresso());
                System.out.println("ID Festa = " + ingressoTemp.getFesta_idfesta());
                System.out.println("CPF = " + ingressoTemp.getPessoa_cpf());
                System.out.println("DataHoraCompra = " + ingressoTemp.getDataCompra());
                System.out.println("Tamanho Camiseta = " + ingressoTemp.getTamanhoCamiseta());
                System.out.println("---------------------------------");
                listaDePista.add(ingressoTemp);
            }
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return listaDePista;
    }

    /**SELECT INGRESSO POR CPF**/
    public ArrayList<Pista> selectIngressoCpf(String cpf) {
        ArrayList<Pista> listaDePista = new ArrayList<>();

        connectToDB();

        String sql = "SELECT * FROM pista WHERE cpf = '" + cpf + "'";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            System.out.println("Pista: ");
            while (rs.next()) {
                Pista ingressoTemp = new Pista(rs.getFloat("valorIngresso"), rs.getInt("festa_idfesta"), rs.getString("pessoa_cpf"), rs.getString("dataCompra"),rs.getString("tamanhoCamiseta"));
                System.out.println("Valor Ingresso = R$ " + ingressoTemp.getValorIngresso());
                System.out.println("ID Festa = " + ingressoTemp.getFesta_idfesta());
                System.out.println("CPF = " + ingressoTemp.getPessoa_cpf());
                System.out.println("DataCompra = " + ingressoTemp.getDataCompra());
                System.out.println("Tamanho Camiseta= " + ingressoTemp.getTamanhoCamiseta());
                System.out.println("---------------------------------");
                listaDePista.add(ingressoTemp);
            }
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return listaDePista;
    }
}
