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

public class ProdutoEstoqueDAO {
    private String sql;

    public void adicionarProduto() {
        Scanner sc = new Scanner(System.in);
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        System.out.print("Informe o nome: ");
        String nome = sc.nextLine();
        System.out.print("Informe o ID da categoria: ");
        int idCategoria = sc.nextInt();
        System.out.print("Informe a quantidade em estoque: ");
        int quantidadeEstoque = sc.nextInt();
        System.out.print("Valor unitário do produto: ");
        double valor = sc.nextDouble();
        sc.nextLine();
        System.out.print("Informe a data da compra DD/MM/AAAA: ");
        String dataCompra = sc.nextLine();

        try {
            // Obtenha o ID mais alto atual
            String maxIdSql = "SELECT MAX(id) FROM produtos";
            PreparedStatement maxIdInstrucao = conector.prepareStatement(maxIdSql);
            ResultSet maxIdResultado = maxIdInstrucao.executeQuery();

            int novoId = 1; // Valor padrão se não houver registros na tabela

            if (maxIdResultado.next()) {
                novoId = maxIdResultado.getInt(1) + 1;
            }

            sql = "INSERT INTO produtos (id, nome, categoria_id, quantidade_estoque, valor_unitario, data_compra) VALUES (?,?,?,?,?,?)";
            PreparedStatement instrucao = conector.prepareStatement(sql);
            instrucao.setInt(1, novoId);
            instrucao.setString(2, nome);
            instrucao.setInt(3, idCategoria);
            instrucao.setInt(4, quantidadeEstoque);
            instrucao.setDouble(5, valor);
            instrucao.setString(6, dataCompra);
            instrucao.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarProduto() {
        Scanner sc = new Scanner(System.in);
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        String sql = "SELECT id, nome, categoria_id, quantidade_estoque, valor_unitario, data_compra FROM produtos";

        try {
            PreparedStatement instrucao = conector.prepareStatement(sql);
            ResultSet resultado = instrucao.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                int idCategoria = resultado.getInt("categoria_id");
                int quantidadeEstoque = resultado.getInt("quantidade_estoque");
                double valor = resultado.getDouble("valor_unitario");
                String dataCompra = resultado.getString("data_compra");

                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("ID Categoria: " + idCategoria);
                System.out.println("Quantidade em estoque: " + quantidadeEstoque);
                System.out.println("Valor unitário: " + valor);
                System.out.println("Data da compra: " + dataCompra);
                System.out.println();
            }
            sc.nextLine();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarProduto() {
        Scanner sc = new Scanner(System.in);
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        String sql = "UPDATE produtos SET nome = ?, categoria_id = ?, quantidade_estoque = ?, valor_unitario = ?, data_compra = ? WHERE id = ?";
        System.out.print("Informe o ID que será atualizado: ");
        int id = sc.nextInt();
        try {
            PreparedStatement instrucao = conector.prepareStatement(sql);
            System.out.print("Informe o nome: ");
            String nome = sc.nextLine();
            System.out.print("Informe o ID da categoria: ");
            int idCategoria = sc.nextInt();
            System.out.print("Informe a quantidade em estoque: ");
            int quantidadeEstoque = sc.nextInt();
            System.out.print("Valor unitário do produto: ");
            double valor = sc.nextDouble();
            System.out.print("Informe a data da compra DD/MM/AAAA: ");
            String dataCompra = sc.nextLine();
            instrucao.setString(1, nome);
            instrucao.setInt(2, idCategoria);
            instrucao.setInt(3, quantidadeEstoque);
            instrucao.setDouble(4, valor);
            instrucao.setString(5, dataCompra);
            instrucao.setInt(6, id);
            instrucao.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerProduto() {
        Scanner sc = new Scanner(System.in);
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        String deleteSql = "DELETE FROM produtos WHERE id = ?";
        String updateSql = "UPDATE produtos SET id = id - 1 WHERE id > ?";
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

    public void incrementarProduto() {
        Scanner sc = new Scanner(System.in);
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        System.out.print("Informe o ID que será incrementado: ");
        int id = sc.nextInt();
        System.out.print("Informe a quantidade que será incrementado: ");
        int quantidadeIncrementada = sc.nextInt();

        sql = "UPDATE produtos SET quantidade_estoque = quantidade_estoque + ? WHERE id = ?";

        try {
            PreparedStatement consulta = conector.prepareStatement(sql);
            consulta.setInt(1, quantidadeIncrementada);
            consulta.setInt(2, id);
            consulta.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
