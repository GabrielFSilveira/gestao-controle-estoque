package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;

import conexao.ConexaoBancoDeDados;

public class CategoriasDAO {
    private String sql;
    char minusculo = 's';
    char alternativa;
    Scanner sc = new Scanner(System.in);

    public void cadastrarCategoria() {
        
        do{
        limparTela();
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        mostrarCategoria();
        sc.nextLine(); //ajeitar
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
        limparTela();
        mostrarCategoria();
        System.out.print("\n\nDeseja cadastrar uma nova categoria (s/n): ");
            alternativa = sc.next().charAt(0);
            minusculo = Character.toLowerCase(alternativa);
            minusculo = confirmandoCaractere(minusculo, alternativa);

    } while (minusculo == 's');

    }

    public void listarCategorias() {
        limparTela();
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        String sql = "SELECT id, nome FROM categorias ORDER BY id";

        try {
            PreparedStatement instrucao = conector.prepareStatement(sql);
            ResultSet resultado = instrucao.executeQuery();
            System.out.println("--------- |Categorias cadastradas| ---------\nID\tNome");
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");

                System.out.printf("%-8d%-8s\n",id,nome);
            }
            System.out.printf("\nDigite qualquer tecla para sair ");
            sc.nextLine();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarCategorias() {
        do{
        limparTela();
        mostrarCategoria();
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        String sql = "UPDATE categorias SET nome = ? WHERE id = ?";
        
        int id = caractereInvalido();
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
        limparTela();
        mostrarCategoria();
        System.out.print("\n\nDeseja editar uma outra categoria (s/n): ");
            alternativa = sc.next().charAt(0);
            minusculo = Character.toLowerCase(alternativa);
            minusculo = confirmandoCaractere(minusculo, alternativa);

    } while (minusculo == 's');

    }

    public void deletarCategoria() {
        do{
            limparTela();
            mostrarCategoria();
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        String deleteSql = "DELETE FROM categorias WHERE id = ?";
        String updateSql = "UPDATE categorias SET id = id - 1 WHERE id > ?";
        System.out.print("\nInforme o ID que será deletado: ");
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
              limparTela();
        mostrarCategoria();
        System.out.print("\n\nDeseja remover uma nova categoria (s/n): ");
            alternativa = sc.next().charAt(0);
            minusculo = Character.toLowerCase(alternativa);
            minusculo = confirmandoCaractere(minusculo, alternativa);

    } while (minusculo == 's');
    }
    
    //METODOS
    public static void mostrarCategoria(){
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
           String sql = "SELECT id, nome FROM categorias ORDER BY id";
          try {
            PreparedStatement instrucao = conector.prepareStatement(sql);
            ResultSet resultado = instrucao.executeQuery();
            System.out.println("--------- |Categorias cadastradas| ---------\nID\tNome");
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");

                System.out.printf("%-8d%-8s\n",id,nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static int caractereInvalido() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nInforme o ID que será atualizado: ");
        while (!sc.hasNextInt()) {
            String input = sc.next();
            limparTela();
            mostrarCategoria();
            System.out.print("\nInsira um número inteiro válido.");
            System.out.print("\nInsira o id: ");
        }
        int valor = sc.nextInt();
        return valor;
    }
    
    public static void limparTela() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            // Limpar o terminal com base no sistema operacional
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static char confirmandoCaractere(char minusculo, char alternativa) {
        Scanner sc = new Scanner(System.in);
        char confirmacao = minusculo;
        if (minusculo != 's' && minusculo != 'n') {
            do {
                limparTela();
                mostrarCategoria();
                System.out.println("\nCaractere invalido!!");
                System.out.print("Deseja cadastrar um novo cliente (s/n): ");
                alternativa = sc.next().charAt(0);
                minusculo = Character.toLowerCase(alternativa);
            } while (minusculo != 's' && minusculo != 'n');
            confirmacao = minusculo;
        }
        return confirmacao;
    }

}
