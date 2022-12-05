package br.inatel.ProjetoSymples.controller;
import br.inatel.ProjetoSymples.model.VIP;

import java.sql.SQLException;
import java.util.ArrayList;

public class VipDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertVIP(VIP ingresso) {

        connectToDB(); //Conecta ao banco de dados

        //Comando em SQL:
        String sql = "INSERT INTO vip (valorIngresso, festa_idfesta, pessoa_cpf, dataCompra, tamanhoAbada) values (?,?,?,?,?)";
        //O comando recebe paramêtros -> consulta dinâmica (pst)
        try {
            pst = con.prepareStatement(sql);
            pst.setFloat(1, ingresso.getValorIngresso()); // 1- refere-se à primeira interrogação
            pst.setInt(2, ingresso.getFesta_idfesta());  // 2- refere-se à segunda interrogação
            pst.setString(3, ingresso.getPessoa_cpf());   // 3- refere-se à terceira interrogação
            pst.setString(4, ingresso.getDataCompra());   // 4- refere-se à quarta interrogação
            pst.setString(5, ingresso.getTamanhoAbada());   // 5- refere-se à quinta interrogação
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
    /**UPDATE TAMANHOABADA DO INGRESSO**/
    public boolean updateTamanhoIngresso(int id, String novoTamanho) {

        connectToDB();

        String sql = "UPDATE vip SET tamanhoabada=? WHERE id=?";

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
    public boolean deleteVIP(int id) {

        connectToDB();

        String sql = "DELETE FROM vip WHERE id=?";

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
    public ArrayList<VIP> selectVIP() {
        ArrayList<VIP> listaDeVIP = new ArrayList<>();

        connectToDB();

        String sql = "SELECT * FROM vip";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            System.out.println("Lista de VIPs: ");
            System.out.println();
            while (rs.next()) {
                VIP ingressoTemp = new VIP(rs.getFloat("ValorIngresso"), rs.getInt("festa_idfesta"), rs.getString("pessoa_cpf"), rs.getString("DataCompra"),rs.getString("TamanhoAbada"));
                ingressoTemp.id = Integer.parseInt(rs.getString("id"));
                System.out.println("ID = "+ ingressoTemp.id);
                System.out.println("Valor Ingresso = R$ " + ingressoTemp.getValorIngresso());
                System.out.println("ID Festa = " + ingressoTemp.getFesta_idfesta());
                System.out.println("CPF = " + ingressoTemp.getPessoa_cpf());
                System.out.println("DataHoraCompra = " + ingressoTemp.getDataCompra());
                System.out.println("Tamanho Abada = " + ingressoTemp.getTamanhoAbada());
                System.out.println("---------------------------------");
                listaDeVIP.add(ingressoTemp);
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
        return listaDeVIP;
    }

    /**SELECT INGRESSO POR CPF**/
    public ArrayList<VIP> selectIngressoCpf(String cpf) {
        ArrayList<VIP> listaDeVIP = new ArrayList<>();

        connectToDB();

        String sql = "SELECT * FROM vip WHERE cpf = '" + cpf + "'";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            System.out.println("VIP: ");
            System.out.println();
            while (rs.next()) {
                VIP ingressoTemp = new VIP(rs.getFloat("valorIngresso"), rs.getInt("festa_idfesta"), rs.getString("pessoa_cpf"), rs.getString("dataCompra"),rs.getString("tamanhoAbada"));
                System.out.println("Valor Ingresso = R$ " + ingressoTemp.getValorIngresso());
                System.out.println("ID Festa = " + ingressoTemp.getFesta_idfesta());
                System.out.println("CPF = " + ingressoTemp.getPessoa_cpf());
                System.out.println("DataCompra = " + ingressoTemp.getDataCompra());
                System.out.println("Tamanho Abada= " + ingressoTemp.getTamanhoAbada());
                System.out.println("---------------------------------");
                listaDeVIP.add(ingressoTemp);
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
        return listaDeVIP;
    }

}
