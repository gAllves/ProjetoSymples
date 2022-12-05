package br.inatel.ProjetoSymples.controller;

import br.inatel.ProjetoSymples.model.Festa;
import java.sql.SQLException;
import java.util.ArrayList;

public class FestaDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertFesta(Festa festa) {

        connectToDB(); //Conecta ao banco de dados

        //Comando em SQL:
        String sql = "INSERT INTO festa (idFesta,nomeFesta, tema, dataHora, local) values (?,?,?,?,?)";
        //O comando recebe paramêtros -> consulta dinâmica (pst)
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,festa.getId()); // 1- refere-se à primeira interrogação
            pst.setString(2, festa.getNomeFesta()); // 2- refere-se à segunda interrogação
            pst.setString(3, festa.getTema());  // 3- refere-se à terceira interrogação
            pst.setString(4, festa.getDataHora());   // 4- refere-se à quarta interrogação
            pst.setString(5, festa.getLocal());   // 5- refere-se à quinta interrogação
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
    /**UPDATE ID DA FESTA**/
    public boolean updateIdFesta(int id, int NovoID) {

        connectToDB();

        String sql = "UPDATE festa SET idFesta=? WHERE idFesta=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, NovoID);
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

    /**UPDATE LOCAL DA FESTA**/
    public boolean updateLocalFesta(int id, String novoLocal) {

        connectToDB();

        String sql = "UPDATE festa SET local=? WHERE idFesta=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoLocal);
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
    public boolean deleteFesta(int id) {

        connectToDB();

        String sql = "DELETE FROM festa WHERE idFesta=?";

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
    /**SELECT FESTA SEM FILTRO*/
    public ArrayList<Festa> selectFesta() {
        ArrayList<Festa> listaDeFesta = new ArrayList<>();

        connectToDB();

        String sql = "SELECT * FROM festa";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            System.out.println("Lista de Festas: ");
            System.out.println();
            while (rs.next()) {
                Festa festaTemp = new Festa(rs.getInt("idFesta"),rs.getString("nomeFesta"), rs.getString("tema"), rs.getString("dataHora"), rs.getString("local"));
                System.out.println("ID = "+ festaTemp.getId());
                System.out.println("Nome = " + festaTemp.getNomeFesta());
                System.out.println("Tema = " + festaTemp.getTema());
                System.out.println("DataHora = " + festaTemp.getDataHora());
                System.out.println("Local = " + festaTemp.getLocal());
                System.out.println("---------------------------------");
                listaDeFesta.add(festaTemp);
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
        return listaDeFesta;
    }

    /**SELECT FESTA POR NOME**/
    public ArrayList<Festa> selectNomeFesta(String nome) {
        ArrayList<Festa> listaDeFesta = new ArrayList<>();

        connectToDB();

        String sql = "SELECT * FROM festa WHERE nome = '" + nome + "'";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            System.out.println("Festa: ");
            while (rs.next()) {
                Festa festaTemp = new Festa(rs.getInt("idFesta"),rs.getString("nomeFesta"), rs.getString("tema"), rs.getString("dataHora"), rs.getString("local"));
                System.out.println("ID = "+ festaTemp.getId());
                System.out.println("Nome = " + festaTemp.getNomeFesta());
                System.out.println("Tema = " + festaTemp.getTema());
                System.out.println("DataHora = " + festaTemp.getDataHora());
                System.out.println("Local = " + festaTemp.getLocal());
                System.out.println("---------------------------------");
                listaDeFesta.add(festaTemp);
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
        return listaDeFesta;
    }
}
