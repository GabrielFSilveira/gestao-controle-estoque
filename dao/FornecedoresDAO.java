package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;

import conexao.ConexaoBancoDeDados;

public class FornecedoresDAO {
    private String sql;
    char alternativa;
    char minusculo = 's';

    public void adicionarFornecedor() {
        do {
            limparTela();
            listarFornecedores();
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
            limparTela();
            listarFornecedores();
            System.out.print("\n\nDeseja cadastrar um novo Fornecedor (s/n): ");
            alternativa = sc.next().charAt(0);
            minusculo = Character.toLowerCase(alternativa);
            minusculo = confirmandoCaractere(minusculo, alternativa);

        } while (minusculo == 's');
    }

    public void mostrarFornecedor() {
        Scanner sc = new Scanner(System.in);
        limparTela();
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        String sql = "SELECT id, nome, contato, endereco, categoria_id FROM fornecedores";

        try {
            PreparedStatement instrucao = conector.prepareStatement(sql);
            ResultSet resultado = instrucao.executeQuery();

            System.out.println("--------- |Fornecedores cadastrados| ---------\nID   Nome\t\tContato\t\tEndereco\tCategoria");
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String contato = resultado.getString("contato");
                String endereco = resultado.getString("endereco");
                int categoria_id = resultado.getInt("categoria_id");

                System.out.printf("%-5d%-19s%-16s%-20s%-8d", id, nome, contato, endereco, categoria_id);
                System.out.println();
            }
            System.out.printf("Digite alguma tecla para sair ");
            sc.nextLine();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarFornecedor() {
        do {
            limparTela();
            listarFornecedores();
            Scanner sc = new Scanner(System.in);
            Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
            String sql = "UPDATE fornecedores SET nome = ?, contato = ?, endereco = ?, categoria_id = ? WHERE id = ?";

            int id = caractereInvalido();
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
            limparTela();
            listarFornecedores();
            System.out.print("\n\nDeseja editar um novo Fornecedor (s/n): ");
            alternativa = sc.next().charAt(0);
            minusculo = Character.toLowerCase(alternativa);
            minusculo = confirmandoCaractere(minusculo, alternativa);

        } while (minusculo == 's');
    }

    public void removerFornecedor() {
        do {
            limparTela();
            listarFornecedores();
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
            limparTela();
            listarFornecedores();
            System.out.print("\n\nDeseja remover um novo Fornecedor (s/n): ");
            alternativa = sc.next().charAt(0);
            minusculo = Character.toLowerCase(alternativa);
            minusculo = confirmandoCaractere(minusculo, alternativa);

        } while (minusculo == 's');
    }

    // METODOS

    public static void listarFornecedores() {
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        String sql = "SELECT id, nome, contato, endereco, categoria_id FROM fornecedores";

        try {
            PreparedStatement instrucao = conector.prepareStatement(sql);
            ResultSet resultado = instrucao.executeQuery();
            System.out.println(
                    "--------- |Fornecedores cadastrados| ---------\nID   Nome\t\tContato\t\tEndereco\tCategoria");
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String contato = resultado.getString("contato");
                String endereco = resultado.getString("endereco");
                int categoria_id = resultado.getInt("categoria_id");

                System.out.printf("%-5d%-19s%-16s%-20s%-8d", id, nome, contato, endereco, categoria_id);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static int caractereInvalido() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nInforme o ID que será atualizado: ");
        while (!sc.hasNextInt()) {
            String input = sc.next();
            limparTela();
            listarFornecedores();
            System.out.print("\nInsira um número inteiro válido.");
            System.out.print("\nInsira o id: ");
        }
        int valor = sc.nextInt();
        return valor;
    }

    public static char confirmandoCaractere(char minusculo, char alternativa) {
        Scanner sc = new Scanner(System.in);
        char confirmacao = minusculo;
        if (minusculo != 's' && minusculo != 'n') {
            do {
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
