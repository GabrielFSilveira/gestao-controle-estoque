package dao;

import conexao.ConexaoBancoDeDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class VendasDAO {
    private String sql;
    String nomeCliente;
    char alternativa;
    char minusculo = 's';

    public void adicionarVenda() {
        do {
            limparTela();
            listarVendas();
            Scanner sc = new Scanner(System.in);
            Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
            System.out.print("\nID do produto vendido: ");
            int idProduto = sc.nextInt(); // Id do produto
            System.out.print("Quantidade vendida: ");
            int quantidadeVenda = sc.nextInt();
            sc.nextLine();
            System.out.print("Data vendida: ");
            String dataVenda = sc.nextLine();
            System.out.println("ID do cliente: ");
            int idcliente = sc.nextInt();

            try {
                // Obtenha o ID mais alto atual
                String maxIdSql = "SELECT MAX(id) FROM vendas";
                PreparedStatement maxIdInstrucao = conector.prepareStatement(maxIdSql);
                ResultSet maxIdResultado = maxIdInstrucao.executeQuery();

                int novoId = 1; // Valor padrão se não houver registros na tabela

                if (maxIdResultado.next()) {
                    novoId = maxIdResultado.getInt(1) + 1;
                }

                String consultaClienteSQL = "SELECT nome FROM clientes WHERE id = ?";
                PreparedStatement consultaNome = conector.prepareStatement(consultaClienteSQL);
                consultaNome.setInt(1, idcliente);

                ResultSet resultadoConsulta = consultaNome.executeQuery();

                String nomeCliente = null;
                if (resultadoConsulta.next()) {
                    nomeCliente = resultadoConsulta.getString("nome");
                }

                sql = "INSERT INTO vendas (id,idProduto, dataVenda,quantidadeVenda, nomeCliente) VALUES (?,?,?,?,?)";
                PreparedStatement instrucao = conector.prepareStatement(sql);
                instrucao.setInt(1, novoId);
                instrucao.setInt(2, idProduto);
                instrucao.setString(3, dataVenda);
                instrucao.setInt(4, quantidadeVenda);
                instrucao.setString(5, nomeCliente);
                instrucao.executeUpdate();

                String quantidadeSql = "UPDATE produtos SET quantidade_estoque = quantidade_estoque - ? WHERE id = ?";
                PreparedStatement consulta = conector.prepareStatement(quantidadeSql);
                consulta.setInt(1, quantidadeVenda);
                consulta.setInt(2, idProduto);
                consulta.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            // limparTela();
            listarVendas();
            System.out.print("\n\nDeseja cadastrar uma nova venda (s/n): ");
            alternativa = sc.next().charAt(0);
            minusculo = Character.toLowerCase(alternativa);
            minusculo = confirmandoCaractere(minusculo, alternativa);

        } while (minusculo == 's');
    }

    public void mostrarVenda() {
        Scanner sc = new Scanner(System.in);
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        String sql = "SELECT id,idProduto, dataVenda, quantidadeVenda, nomeCliente FROM vendas ORDER BY id";

        try {
            PreparedStatement instrucao = conector.prepareStatement(sql);
            ResultSet resultado = instrucao.executeQuery();
            System.out.println(
                    "--------- |Vendas cadastrados| ---------\nID   idProduto\t Data da venda\t Quantidade Vendida\tNome do Cliente");
            while (resultado.next()) {
                int id = resultado.getInt("id");
                int idProduto = resultado.getInt("idProduto");
                String dataVenda = resultado.getString("dataVenda");
                int quantidadeVenda = resultado.getInt("quantidadeVenda");
                String nomeCliente = resultado.getString("nomeCliente");

                System.out.printf("%-9d%-10d%-20s%-17d%-17s", id, idProduto, dataVenda, quantidadeVenda, nomeCliente);
                System.out.println();
            }
            sc.nextLine();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarVenda() {
        do {
            limparTela();
            listarVendas();
            Scanner sc = new Scanner(System.in);
            Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();

            int id = caractereInvalido();
            try {
                // sc.nextLine();
                System.out.print("Informe o idProduto: ");
                int idproduto = sc.nextInt();
                sc.nextLine();
                System.out.print("Informe o dataVenda: ");
                String datavenda = sc.nextLine();
                System.out.print("Informe o quantidadeVenda: ");
                int quantidadevenda = sc.nextInt();
                System.out.print("Informe o ID do cliente: ");
                int idcliente = sc.nextInt();

                String consultaClienteSQL = "SELECT nome FROM clientes WHERE id = ?";
                PreparedStatement consultaNome = conector.prepareStatement(consultaClienteSQL);
                consultaNome.setInt(1, idcliente);

                ResultSet resultadoConsulta = consultaNome.executeQuery();

                String nomeCliente = null;
                if (resultadoConsulta.next()) {
                    nomeCliente = resultadoConsulta.getString("nome");
                }

                String sql = "UPDATE vendas SET idProduto = ?, dataVenda = ?, quantidadeVenda = ?, nomeCliente = ? WHERE id = ?";
                PreparedStatement instrucao = conector.prepareStatement(sql);

                instrucao.setInt(1, idproduto);
                instrucao.setString(2, datavenda);
                instrucao.setInt(3, quantidadevenda);
                instrucao.setString(4, nomeCliente);
                instrucao.setInt(5, id);
                instrucao.executeUpdate();
/*
                String consultaVendasSQL = "SELECT quantidadeVenda FROM vendas WHERE id = ?";
                PreparedStatement venda = conector.prepareStatement(consultaVendasSQL);
                venda.setInt(1, id);
                ResultSet resultadoVenda = venda.executeQuery();

                // Armazena a quantidade de venda "antiga" para fazer comparações
                int quantidadeAntiga = 0;
                if (resultadoVenda.next()) {
                    quantidadeAntiga = resultadoVenda.getInt("quantidadeVenda");
                }

                if (quantidadevenda > quantidadeAntiga) {
                    int novaQuantidade = quantidadevenda - quantidadeAntiga;
                    String quantidadeSql = "UPDATE produtos SET quantidade_estoque = quantidade_estoque - ? WHERE id = ?";
                    PreparedStatement consultaQuantidade = conector.prepareStatement(quantidadeSql);
                    consultaQuantidade.setInt(1, novaQuantidade);
                    consultaQuantidade.setInt(2, idproduto);
                    consultaQuantidade.executeUpdate();
                } else if (quantidadevenda < quantidadeAntiga) {
                    int novaQuantidade = quantidadeAntiga - quantidadevenda;
                    String quantidadeSql = "UPDATE produtos SET quantidade_estoque = quantidade_estoque + ? WHERE id = ?";
                    PreparedStatement consultaQuantidaded = conector.prepareStatement(quantidadeSql);
                    consultaQuantidaded.setInt(1, novaQuantidade);
                    consultaQuantidaded.setInt(2, idproduto);
                    consultaQuantidaded.executeUpdate();
                }
*/
            } catch (SQLException e) {
                e.printStackTrace();
            }
            limparTela();
            listarVendas();
            System.out.print("\n\nDeseja editar uma nova Venda (s/n): ");
            alternativa = sc.next().charAt(0);
            minusculo = Character.toLowerCase(alternativa);
            minusculo = confirmandoCaractere(minusculo, alternativa);

        } while (minusculo == 's');
    }

    public void removerVenda() {
        do {
            limparTela();
            listarVendas();
            Scanner sc = new Scanner(System.in);
            Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
            String deleteSql = "DELETE FROM vendas WHERE id = ?";
            String updateSql = "UPDATE vendas SET id = id - 1 WHERE id > ?";
            System.out.print("Informe o ID que será deletado: ");
            int id = sc.nextInt();
            try {
                // Exclua a venda com o ID fornecido
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
            listarVendas();
            System.out.print("\n\nDeseja remover uma nova venda (s/n): ");
            alternativa = sc.next().charAt(0);
            minusculo = Character.toLowerCase(alternativa);
            minusculo = confirmandoCaractere(minusculo, alternativa);

        } while (minusculo == 's');
    }

    // METODOS

    public static void listarVendas() {
        Connection conector = ConexaoBancoDeDados.getInstanciador().getConector();
        String sql = "SELECT id,idProduto, dataVenda, quantidadeVenda, nomeCliente FROM vendas ORDER BY id";

        try {
            PreparedStatement instrucao = conector.prepareStatement(sql);
            ResultSet resultado = instrucao.executeQuery();
            System.out.println(
                    "--------- |Vendas cadastrados| ---------\nID   idProduto\t Data da venda\t Quantidade Vendida\tNome do Cliente");
            while (resultado.next()) {
                int id = resultado.getInt("id");
                int idProduto = resultado.getInt("idProduto");
                String dataVenda = resultado.getString("dataVenda");
                int quantidadeVenda = resultado.getInt("quantidadeVenda");
                String nomeCliente = resultado.getString("nomeCliente");

                System.out.printf("%-9d%-10d%-20s%-17d%-17s", id, idProduto, dataVenda, quantidadeVenda, nomeCliente);
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
            listarVendas();
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
                limparTela();
                listarVendas();
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
