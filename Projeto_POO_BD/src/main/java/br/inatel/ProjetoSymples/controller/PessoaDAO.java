package br.inatel.ProjetoSymples.controller;
import br.inatel.ProjetoSymples.model.Pessoa;

import java.sql.SQLException;
import java.util.ArrayList;

public class PessoaDAO extends ConnectionDAO {

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertPessoa(Pessoa pessoa) {

        connectToDB(); //Conecta ao banco de dados

        //Comando em SQL:
        String sql = "INSERT INTO pessoa (nome,cpf, idade) values (?,?,?)";
        //O comando recebe paramêtros -> consulta dinâmica (pst)
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, pessoa.getNome()); // 1- refere-se à primeira interrogação
            pst.setString(2, pessoa.getCpf());  // 2- refere-se à segunda interrogação
            pst.setString(3, pessoa.getIdade());   // 3- refere-se à terceira interrogação
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
    /**UPDATE NOME DO PESSOA**/
    public boolean updateNomePessoa(String cpf, String novoNome) {

        connectToDB();

        String sql = "UPDATE pessoa SET nome=? WHERE cpf=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoNome);
            pst.setString(2, cpf);
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

    /**UPDATE IDADE DA PESSOA**/
    public boolean updateIdadePessoa(String cpf, int novaIdade) {

        connectToDB();

        String sql = "UPDATE pessoa SET idade=? WHERE cpf=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, novaIdade);
            pst.setString(2, cpf);
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
    public boolean deletePessoa(String cpf) {

        connectToDB();

        String sql = "DELETE FROM pessoa WHERE cpf=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cpf);
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
    /**SELECT PESSOA SEM FILTRO*/
    public ArrayList<Pessoa> selectPessoa() {
        ArrayList<Pessoa> listaDePessoas = new ArrayList<>();

        connectToDB();

        String sql = "SELECT * FROM pessoa";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            System.out.println("Lista de pessoas: ");
            System.out.println();
            while (rs.next()) {
                Pessoa pessoaTemp = new Pessoa(rs.getString("nome"), rs.getString("cpf"), rs.getString("idade"));
                System.out.println("Nome = " + pessoaTemp.getNome());
                System.out.println("CPF = " + pessoaTemp.getCpf());
                System.out.println("Idade = " + pessoaTemp.getIdade());
                System.out.println("---------------------------------");
                listaDePessoas.add(pessoaTemp);
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
        return listaDePessoas;
    }

    /**SELECT PESSOA POR NOME**/
    public ArrayList<Pessoa> selectPessoaNome(String nome) {
        ArrayList<Pessoa> listaDePessoas = new ArrayList<>();

        connectToDB();

        String sql = "SELECT * FROM pessoa WHERE nome = '" + nome + "'";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            System.out.println("Lista de pessoas: ");
            System.out.println();
            while (rs.next()) {
                Pessoa pessoaTemp = new Pessoa(rs.getString("nome"), rs.getString("cpf"), rs.getString("idade"));
                System.out.println("Nome = " + pessoaTemp.getNome());
                System.out.println("CPF = " + pessoaTemp.getCpf());
                System.out.println("Idade =" + pessoaTemp.getIdade());
                System.out.println("---------------------------------");
                listaDePessoas.add(pessoaTemp);
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
        return listaDePessoas;
    }

    /**SELECT PESSOAS POR IDADE**/
    public ArrayList<Pessoa> selectPessoaIdade(int idade) {
        ArrayList<Pessoa> listaDePessoas = new ArrayList<>();

        connectToDB();

        String sql = "SELECT * FROM pessoa WHERE idade = '" + idade + "'";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            System.out.println("Lista de pessoas: ");
            System.out.println();
            while (rs.next()) {
                Pessoa pessoaTemp = new Pessoa(rs.getString("nome"), rs.getString("cpf"), rs.getString("idade"));
                System.out.println("Nome = " + pessoaTemp.getNome());
                System.out.println("CPF = " + pessoaTemp.getCpf());
                System.out.println("Idade =" + pessoaTemp.getIdade());
                System.out.println("---------------------------------");
                listaDePessoas.add(pessoaTemp);
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
        return listaDePessoas;
    }
}


