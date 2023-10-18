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

public class CategoriasDAO {
    private String sql;
    Scanner sc = new Scanner(System.in);

    public void cadastrarCategoria() {
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        System.out.print("Insira o nome da categoria: ");
        String nomeCategoria = sc.nextLine();

        try {
            // Obtenha o ID mais alto atual
            String maxIdSql = "SELECT MAX(id) FROM categorias";
            PreparedStatement maxIdInstrucao = conector.prepareStatement(maxIdSql);
            ResultSet maxIdResultado = maxIdInstrucao.executeQuery();

            int novoId = 1; // Valor padrão se não houver registros na tabela

            if (maxIdResultado.next()) {
                novoId = maxIdResultado.getInt(1) + 1;
            }

            sql = "INSERT INTO categorias (id, nome) VALUES (?,?)";
            PreparedStatement instrucao = conector.prepareStatement(sql);
            instrucao.setInt(1, novoId);
            instrucao.setString(2, nomeCategoria);
            instrucao.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarCategorias() {
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        String sql = "SELECT id, nome FROM categorias";

        try {
            PreparedStatement instrucao = conector.prepareStatement(sql);
            ResultSet resultado = instrucao.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");

                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println();
            }
            sc.nextLine();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarCategorias() {
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        String sql = "UPDATE categorias SET nome = ? WHERE id = ?";
        System.out.print("Informe o ID que será atualizado: ");
        int id = sc.nextInt();
        try {
            PreparedStatement instrucao = conector.prepareStatement(sql);
            sc.nextLine();
            System.out.print("Informe o nome: ");
            String nome = sc.nextLine();
            instrucao.setString(1, nome);
            instrucao.setInt(2, id);
            instrucao.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarCategoria() {
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        String deleteSql = "DELETE FROM categorias WHERE id = ?";
        String updateSql = "UPDATE categorias SET id = id - 1 WHERE id > ?";
        System.out.print("Informe o ID que será deletado: ");
        int id = sc.nextInt();
        try {
            // Excluindo a categoria com o ID fornecido
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
