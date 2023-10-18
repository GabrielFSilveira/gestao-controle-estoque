package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import conexao.ConexaoBancoDeDados;

public class FornecedoresDAO {
    private String sql;

    public void adicionarFornecedor() {
        Scanner sc = new Scanner(System.in);
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        System.out.print("Insira o nome: ");
        String nome = sc.nextLine();
        System.out.print("Insira o contato: ");
        String contato = sc.nextLine();
        System.out.print("Insira o endereço: ");
        String endereco = sc.nextLine();
        System.out.println("Informe o ID da categoria: ");
        int idCategoria = sc.nextInt();

        try {
            // Obtenha o ID mais alto atual
            String maxIdSql = "SELECT MAX(id) FROM fornecedores";
            PreparedStatement maxIdInstrucao = conector.prepareStatement(maxIdSql);
            ResultSet maxIdResultado = maxIdInstrucao.executeQuery();

            int novoId = 1; // Valor padrão se não houver registros na tabela

            if (maxIdResultado.next()) {
                novoId = maxIdResultado.getInt(1) + 1;
            }

            sql = "INSERT INTO fornecedores (id, nome, contato, endereco, categoria_id) VALUES (?,?,?,?,?)";
            PreparedStatement instrucao = conector.prepareStatement(sql);
            instrucao.setInt(1, novoId);
            instrucao.setString(2, nome);
            instrucao.setString(3, contato);
            instrucao.setString(4, endereco);
            instrucao.setInt(5, idCategoria);
            instrucao.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarFornecedor() {
        Scanner sc = new Scanner(System.in);
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        String sql = "SELECT id, nome, contato, endereco, categoria_id FROM fornecedores";

        try {
            PreparedStatement instrucao = conector.prepareStatement(sql);
            ResultSet resultado = instrucao.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String contato = resultado.getString("contato");
                String endereco = resultado.getString("endereco");
                int categoria_id = resultado.getInt("categoria_id");

                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Contato: " + contato);
                System.out.println("Endereço: " + endereco);
                System.out.println("Categoria: " + categoria_id);
                System.out.println();
            }
            sc.nextLine();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarFornecedor() {
        Scanner sc = new Scanner(System.in);
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        String sql = "UPDATE fornecedores SET nome = ?, contato = ?, endereco = ?, categoria_id = ? WHERE id = ?";
        System.out.print("Informe o ID que será atualizado: ");
        int id = sc.nextInt();
        try {
            PreparedStatement instrucao = conector.prepareStatement(sql);
            sc.nextLine();
            System.out.print("Informe o nome: ");
            String nome = sc.nextLine();
            System.out.print("Informe o contato: ");
            String contato = sc.nextLine();
            System.out.print("Informe o endereco: ");
            String endereco = sc.nextLine();
            System.out.print("Informe o ID da categoria: ");
            int categoria_id = sc.nextInt();
            instrucao.setString(1, nome);
            instrucao.setString(2, contato);
            instrucao.setString(3, endereco);
            instrucao.setInt(4, categoria_id);
            instrucao.setInt(5, id);
            instrucao.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerFornecedor() {
        Scanner sc = new Scanner(System.in);
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        String deleteSql = "DELETE FROM fornecedores WHERE id = ?";
        String updateSql = "UPDATE fornecedores SET id = id - 1 WHERE id > ?";
        System.out.print("Informe o ID que será deletado: ");
        int id = sc.nextInt();
        try {
            // Exclua o cliente com o ID fornecido
            PreparedStatement deleteInstrucao = conector.prepareStatement(deleteSql);
            deleteInstrucao.setInt(1, id);
            deleteInstrucao.executeUpdate();

            // Atualize os IDs subsequentes
            PreparedStatement updateInstrucao = conector.prepareStatement(updateSql);
            updateInstrucao.setInt(1, id);
            updateInstrucao.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
