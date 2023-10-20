package src;

import java.util.Scanner;


import dao.ClientesDAO;
import dao.CategoriasDAO;
import dao.FornecedoresDAO;
import dao.ProdutoEstoqueDAO;
import dao.VendasDAO;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ClientesDAO clientesDAO = new ClientesDAO();
        CategoriasDAO categoriasDAO = new CategoriasDAO();
        FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();
        ProdutoEstoqueDAO produtoEstoqueDAO = new ProdutoEstoqueDAO();
        VendasDAO vendasDAO = new VendasDAO();

        limparTela();

        int escolha;

        Scanner sc = new Scanner(System.in);

        while (true) {
            limparTela();
            System.out.println("------------------------------|");
            System.out.println("1. Gerenciar ProdutoEstoque   |");
            System.out.println("2. Gerenciar Fornecedores     |");
            System.out.println("3. Gerenciar Vendas           |");
            System.out.println("4. Gerenciar Clientes         |");
            System.out.println("5. Sair                       |");
            System.out.println("------------------------------|");
            System.out.printf("Escolha uma opção: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // Produtos
                    limparTela();
                    System.out.println("------------------------------|");
                    System.out.println("1. Adicionar Produto          |");
                    System.out.println("2. Editar Produtos            |");
                    System.out.println("3. Mostrar Produtos           |");
                    System.out.println("4. Remover Produtos           |");
                    System.out.println("5. Incrementar Produto        |");
                    System.out.println("6. Categorias                 |");
                    System.out.println("7. Voltar para o menu         |");
                    System.out.println("------------------------------|");
                    System.out.printf("Escolha uma opção: ");
                    escolha = sc.nextInt();
                    switch (escolha) {
                        case 1:
                            produtoEstoqueDAO.adicionarProduto();
                            break;
                        case 2:
                            produtoEstoqueDAO.editarProduto();
                            break;
                        case 3:
                            produtoEstoqueDAO.mostrarProduto();
                            break;
                        case 4:
                            produtoEstoqueDAO.removerProduto();
                            break;
                        case 5:
                            produtoEstoqueDAO.incrementarProduto();
                            break;
                        // Categorias
                        case 6:
                            limparTela();
                            System.out.println("------------------------------|");
                            System.out.println("1. Adicionar Categoria        |");
                            System.out.println("2. Editar Categorias          |");
                            System.out.println("3. Mostrar Categorias         |");
                            System.out.println("4. Remover Categorias         |");
                            System.out.println("5. Voltar para o menu         |");
                            System.out.println("------------------------------|");
                            escolha = sc.nextInt();
                            switch (escolha) {
                                case 1:
                                    categoriasDAO.cadastrarCategoria();
                                    break;
                                case 2:
                                    categoriasDAO.editarCategorias();
                                    break;
                                case 3:
                                    categoriasDAO.listarCategorias();
                                    break;
                                case 4:
                                    categoriasDAO.deletarCategoria();
                                    break;
                                case 5: 
                                //caso 5 voltara para o menu
                                    break;
                                default:
                                    opcaoInvalida();
                                }
                            break;

                        case 7:
                        //volta para o menu
                            break;
                        default:
                            opcaoInvalida();
                    }
                    break;
                case 2:
                    limparTela();
                    System.out.println("------------------------------|");
                    System.out.println("1. Adicionar Fornecedor       |");
                    System.out.println("2. Editar Fornecedor          |");
                    System.out.println("3. Mostrar Fornecedor         |");
                    System.out.println("4. Remover Fornecedor         |");
                    System.out.println("5. Voltar para o Menu         |");
                    System.out.println("------------------------------|");
                    System.out.printf("Escolha uma opção: ");
                    escolha = sc.nextInt();
                    switch (escolha) {
                        case 1:
                            fornecedoresDAO.adicionarFornecedor();
                            break;
                        case 2:
                            fornecedoresDAO.editarFornecedor();
                            break;
                        case 3:
                            fornecedoresDAO.mostrarFornecedor();
                            break;
                        case 4:
                            fornecedoresDAO.removerFornecedor();
                            break;
                        case 5:
                            break;
                        default:
                            opcaoInvalida();
                            break;
                    }
                    break;
                case 3:
                    // Vendas
                    limparTela();
                    System.out.println("------------------------------|");
                    System.out.println("1. Adicionar Vendas           |");
                    System.out.println("2. Editar Vendas              |");
                    System.out.println("3. Mostrar Vendas             |");
                    System.out.println("4. Remover Vendas             |");
                    System.out.println("5. Voltar para o Menu         |");
                    System.out.println("------------------------------|");
                    System.out.printf("Escolha uma opção: ");
                    escolha = sc.nextInt();
                    switch (escolha) {
                        case 1:
                        vendasDAO.adicionarVenda();
                            break;

                        case 2:
                        vendasDAO.editarVenda();
                            break;

                        case 3:
                        vendasDAO.mostrarVenda();
                            break;

                        case 4:
                        vendasDAO.removerVenda();
                            break;

                        case 5:
                            break;

                        default:
                            opcaoInvalida();
                            break;
                    }
                    break;
                case 4:
                    // Clientes
                    limparTela();
                    System.out.println("------------------------------|");
                    System.out.println("1. Adicionar Cliente          |");
                    System.out.println("2. Editar Cliente             |");
                    System.out.println("3. Mostrar Cliente            |");
                    System.out.println("4. Remover Cliente            |");
                    System.out.println("5. Voltar para o Menu         |");
                    System.out.println("------------------------------|");
                    System.out.printf("Escolha uma opção: ");
                    escolha = sc.nextInt();
                    switch (escolha) {
                        case 1:
                            clientesDAO.adicionarCliente();
                            break;
                        case 2:
                            clientesDAO.editarCliente();
                            break;
                        case 3:
                            clientesDAO.mostrarCliente();
                            sc.nextLine();
                            break;
                        case 4:
                            clientesDAO.removerCliente();
                            break;
                        case 5:
                            break;
                        default:
                            opcaoInvalida();

                            break;
                    }
                    break;
                case 5:
                    System.out.println("Saindo do programa.");
                    System.exit(0);
                default:
                    opcaoInvalida();
                    break;
            }
        }

    }

    /* MÉTODO */
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

    public static void opcaoInvalida() throws InterruptedException {
        System.out.print("Opção inválida, voltando ao menu em");
        System.out.print(" 3");
        Thread.sleep(700);
        System.out.print(" 2");
        Thread.sleep(600);
        System.out.print(" 1.");
        Thread.sleep(333);
        System.out.print(".");
        Thread.sleep(333);
        System.out.print(".");
        Thread.sleep(333);
    }

}