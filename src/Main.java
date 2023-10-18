package src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Clientes;
import entidades.Fornecedores;
import entidades.ProdutoEstoque;
import entidades.Vendas;


import dao.ClientesDAO;
import dao.CategoriasDAO;
import dao.FornecedoresDAO;


public class Main {

    public static void main(String[] args) throws InterruptedException {

        ClientesDAO clientesDAO = new ClientesDAO();
        CategoriasDAO categoriasDAO = new CategoriasDAO();
        FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();

        limparTela();
        List<Clientes> clientes = new ArrayList();
        List<ProdutoEstoque> produtos = new ArrayList();
        List<Vendas> vendas = new ArrayList();
        List<Fornecedores> fornecedores = new ArrayList();
        ArrayList<String> categorias = new ArrayList();

        char alternativa;
        char minusculo;

        int escolha;
        int id;
        String nomeCliente;
        String contato;
        String endereco;

        String nomeProduto;
        String descricao;
        int qtdTotal;
        double valorCompra;
        String dataCompra;

        int quantidadeVenda;
        String dataVenda;
        int idVenda;

        String nomeFornecedor;

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
                            if (categorias.size() != 0) { // verifica se existe uma categoria
                                minusculo = 's';
                                do {
                                    limparTela();

                                    System.out.println("\nCategorias cadastradas: \nID\tNome"); // mostra as categorias
                                                                                                // cadastradas
                                    int i = 0;
                                    for (String categoria : categorias) {
                                        System.out.println(i + "\t" + categorias.get(i));
                                        i++;
                                    }

                                    id = caractereInvalido(); // verifica se o caractere foi um inteiro ou uma string

                                    sc.nextLine();

                                    if (id >= 0 && id < categorias.size()) { // verifica se o id existe
                                        
                                        
                                        System.out.printf("Nome do Produto: ");
                                        nomeProduto = sc.nextLine();

                                        descricao = categorias.get(id);

                                        System.out.print("Quantidade em estoque: ");
                                        qtdTotal = sc.nextInt();

                                        System.out.print("Valor unitário do item: ");
                                        valorCompra = sc.nextDouble();

                                        sc.nextLine();
                                        System.out.print("Data da compra DD/MM/AA: ");
                                        dataCompra = sc.nextLine();

                                        id = produtos.size();

                                        ProdutoEstoque produto = new ProdutoEstoque(id, nomeProduto, descricao,
                                                qtdTotal, valorCompra, dataCompra);

                                        produto.adicionarProduto(produtos);
                                        Thread.sleep(700);

                                    } else {
                                        categoriaInvalida();
                                    }
                                    limparTela();
                                    ProdutoEstoque.mostrarProdutos(produtos);
                                    System.out.print("\n\nDeseja cadastrar um novo produto (s/n): ");
                                    alternativa = sc.next().charAt(0);
                                    minusculo = Character.toLowerCase(alternativa);
                                    minusculo = confirmandoCaractere(minusculo, alternativa); // metodo para fazer o
                                                                                              // caractere ficar
                                                                                              // minusculo e comparar de
                                                                                              // m jeito mais pratico
                                } while (minusculo == 's');
                            } else {
                                categoriaInvalida();
                            }
                            break;
                        case 2:
                            if (categorias.size() != 0) { // verifica se existe alguma categoria, caso não o programa
                                                          // pede para criar primeiro
                                minusculo = 's';
                                while (minusculo == 's') {
                                    limparTela();
                                    ProdutoEstoque.mostrarProdutos(produtos); // mostra todos os produtos
                                    id = caractereInvalido();

                                    if (id >= 0 && id < produtos.size()) { // verifica se existe o id
                                        if (produtos.get(id) != null) {
                                            limparTela();
                                            System.out.println("1. Nome do produto");
                                            System.out.println("2. Descrição do produto");
                                            System.out.println("3. Quantidade em estoque: ");
                                            System.out.println("4. Valor unitário: ");
                                            System.out.println("5. Data da compra: ");
                                            System.out.print("\nO que você quer editar: ");
                                            int opcao = sc.nextInt();

                                            if (opcao == 1 || opcao == 2 || opcao == 3 || opcao == 4 || opcao == 5) {
                                                ProdutoEstoque.editarProduto(produtos, id, opcao);
                                                System.out.print("\nDeseja editar outro produto (s/n): ");
                                                alternativa = sc.next().charAt(0); // recebe o caractere
                                                minusculo = Character.toLowerCase(alternativa);

                                                minusculo = confirmandoCaractere(minusculo, alternativa); // compara o
                                                                                                          // caractere
                                            } else {
                                                System.out.println("Opção invalida!!");
                                            }
                                        } else {
                                            System.out.println("Opção invalida!");
                                            System.out.print("\nDeseja editar outro produto (s/n): ");
                                            alternativa = sc.next().charAt(0);
                                            minusculo = Character.toLowerCase(alternativa);

                                            minusculo = confirmandoCaractere(minusculo, alternativa);
                                        }
                                    } else {
                                        minusculo = 'n';
                                        idInvalida();
                                    }
                                }
                            } else {
                                produtoVazio();
                            }
                            break;
                        case 3:
                            if (produtos.size() != 0) { // se não existir produto o programa mostra erro
                                limparTela();
                                ProdutoEstoque.mostrarProdutos(produtos);
                                System.out.print("\nDigite alguma tecla para sair : ");
                                sc.next().charAt(0);
                            } else {
                                produtoVazio();
                            }
                            break;
                        case 4:
                            if (produtos.size() != 0) {
                                minusculo = 's';
                                while (minusculo == 's') {
                                    int i = 0;
                                    ProdutoEstoque.mostrarProdutos(produtos);

                                    id = caractereInvalido();

                                    if (id >= 0 && id < produtos.size()) {
                                        
                                            int ver = id;
                                            i = 0;
                                            for (ProdutoEstoque produto : produtos) { // percorre o array
                                                if (i == id) {
                                                    int guardaIdProduto = produto.getId();
                                                    int armazena = produtos.get(guardaIdProduto).getQtdTotal();
                                                    produtos.get(guardaIdProduto).setQtdTotal(armazena);
                                                }else  if (i > id) {
                                                    produto.setId(ver);
                                                    ver++;
                                                }
                                                i++;
                                            }
                                            ProdutoEstoque.removerProduto(produtos, id);
                                            limparTela();
                                            ProdutoEstoque.mostrarProdutos(produtos);
                                            System.out.print("\nDeseja remover outro produto (s/n): ");
                                            alternativa = sc.next().charAt(0);
                                            minusculo = Character.toLowerCase(alternativa);
                                            minusculo = confirmandoCaractere(minusculo, alternativa);
                                        
                                    } else {
                                        minusculo = 'n';
                                        idInvalida();
                                    }
                                }
                            } else {
                                produtoVazio();
                            }
                            break;
                        case 5:
                            if (produtos.size() != 0) {
                                limparTela();
                                ProdutoEstoque.mostrarProdutos(produtos);
                                int incrementarQuantidade;

                                id = caractereInvalido();

                                System.out.print("\nInforme a quantidade que será incrementada: ");
                                incrementarQuantidade = sc.nextInt();

                                ProdutoEstoque.incrementarProduto(produtos, id, incrementarQuantidade);
                            } else {
                                produtoVazio();
                            }
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
                            if (produtos.size() != 0 && clientes.size() != 0) {

                                // Cadastrar venda
                                do {
                                    limparTela();

                                    System.out.println("Lista de produtos: \nID\tNome");
                                    for (ProdutoEstoque produto : produtos) {
                                        System.out.println(produto.getId() + "\t" + produto.getNome());
                                    }

                                    System.out.print("\nID do produto vendido: ");
                                    id = sc.nextInt(); // Id do produto
                                    idVenda = vendas.size(); // Id da venda

                                    if (id >= 0 && id < produtos.size()) { // Verifica se o ID existe
                                        ProdutoEstoque produtoVendido = produtos.get(id);
                                        System.out.print("Quantidade vendida: ");
                                        quantidadeVenda = sc.nextInt();

                                        if (produtoVendido.getQtdTotal() >= quantidadeVenda) { // Verifica se há estoque
                                                                                               // para venda

                                            sc.nextLine();
                                            System.out.print("Data vendida: ");
                                            dataVenda = sc.nextLine();

                                            System.out.println("\nLista de Clientes:\nID\tNome");
                                            int i = 0;
                                            for (Clientes cliente : clientes) {
                                                System.out.println(i + "\t" + cliente.getNomeCliente());
                                                System.out.println("-----------------------------");
                                                i++;
                                            }

                                            System.out.print("Informe o ID do cliente: ");
                                            int idCliente = sc.nextInt();

                                            i = 0;
                                            for (Clientes cliente : clientes) {
                                                if (i == idCliente) {

                                                    int novaQuantidade = produtoVendido.getQtdTotal() - quantidadeVenda;
                                                    produtoVendido.setQtdTotal(novaQuantidade);
                                                    Vendas venda = new Vendas(idVenda, id, dataVenda, quantidadeVenda,
                                                            cliente.getNomeCliente());
                                                    venda.adicionarVendas(vendas); // Adiciona a venda para puxar no
                                                                                   // relatório
                                                    System.out.println("Venda realizada!");
                                                }
                                                i++;
                                            }
                                        } else {
                                            System.out.println("Sem estoque para venda!");
                                        }
                                    } else {
                                        System.out.println("Produto não encontrado!");
                                    }
                                    System.out.print("\nDeseja realizar uma nova venda (s/n): ");
                                    alternativa = sc.next().charAt(0);
                                    minusculo = Character.toLowerCase(alternativa);

                                    minusculo = confirmandoCaractere(minusculo, alternativa);
                                } while (minusculo == 's');
                            } else {
                                produtoouclieteVazio();
                            }
                            break;
                        case 2:
                            if (produtos.size() != 0 && clientes.size() != 0) {
                                // Editar venda
                                do {
                                    limparTela();
                                    Vendas.mostrarVendas(vendas);
                                    System.out.print("ID da venda: ");
                                    idVenda = sc.nextInt(); // Id da venda

                                    if (idVenda >= 0 && idVenda < vendas.size()) { // Verifica se o ID existe
                                        System.out.println("1 - Quantidade da Venda");
                                        System.out.println("2 - Data da Venda");
                                        System.out.print("O que você quer editar: ");
                                        escolha = sc.nextInt();
                                        switch (escolha) {
                                            case 1:
                                                limparTela();
                                                System.out.print("Insira a nova quantidade de venda: ");
                                                quantidadeVenda = sc.nextInt();

                                                System.out.println("Quantidade alterada com sucesso!");

                                                if (quantidadeVenda > vendas.get(idVenda).getQuantidadeVenda()) {
                                                    int novaQuantidade = quantidadeVenda - vendas.get(idVenda).getQuantidadeVenda();
                                                    vendas.get(idVenda).setQuantidadeVenda(quantidadeVenda);
                                                    int quantidadeEstoque = produtos.get(vendas.get(idVenda).getIdProduto()).getQtdTotal();
                                                    produtos.get(vendas.get(idVenda).getIdProduto()).setQtdTotal(quantidadeEstoque - novaQuantidade);

                                                } else if (quantidadeVenda < vendas.get(idVenda).getQuantidadeVenda()) {
                                                    int novaQuantidade = vendas.get(idVenda).getQuantidadeVenda() - quantidadeVenda;
                                                    vendas.get(idVenda).setQuantidadeVenda(quantidadeVenda);
                                                    int quantidadeEstoque = produtos.get(vendas.get(idVenda).getIdProduto()).getQtdTotal();
                                                    produtos.get(vendas.get(idVenda).getIdProduto()).setQtdTotal(quantidadeEstoque + novaQuantidade);
                                                }
                                                break;
                                            case 2:
                                                Vendas.editarVendas(vendas, idVenda);
                                                break;
                                            default:
                                        }

                                    } else {
                                        System.out.println("Venda não encontrada!");
                                    }

                                    System.out.print("\nDeseja editar uma nova venda (s/n): ");
                                    alternativa = sc.next().charAt(0);
                                    minusculo = Character.toLowerCase(alternativa);

                                    minusculo = confirmandoCaractere(minusculo, alternativa);
                                } while (minusculo == 's');
                            } else {
                                produtoouclieteVazio();
                            }
                            break;
                        case 3:
                            if (produtos.size() != 0 && clientes.size() != 0) {
                                // Mostrar vendas
                                limparTela();
                                    limparTela();
                                    Vendas.mostrarVendas(vendas);
                                    System.out.print("\nDigite alguma tecla para sair : ");
                                    sc.next().charAt(0);
                            } else {
                                produtoouclieteVazio();
                            }
                            break;
                        case 4:
                            if (produtos.size() != 0 && clientes.size() != 0) {
                                // Excluir venda
                                minusculo = 's';
                                while (minusculo == 's') {
                                    limparTela();
                                    Vendas.mostrarVendas(vendas);
                                    System.out.printf("Insira o ID que será removido: ");
                                    id = sc.nextInt();
                                    if (id >= 0 && id < vendas.size()) {
                                        if (vendas.get(id) != null) {

                                            int ver = id;
                                            int i = 0;

                                            for (Vendas venda : vendas) {
                                                if (i == id) {
                                                    int guardaIdProduto = venda.getIdProduto(); // armazena o id daquela
                                                                                                // venda
                                                    int armazena = produtos.get(guardaIdProduto).getQtdTotal(); // quantidade
                                                                                                                // total
                                                                                                                // de
                                                                                                                // estoque
                                                                                                                // naquele
                                                                                                                // id
                                                    produtos.get(guardaIdProduto)
                                                            .setQtdTotal(armazena + venda.getQuantidadeVenda()); //
                                                } else if (i > id) {
                                                    venda.setId(ver);
                                                    ver++;
                                                }
                                                i++;
                                            }
                                            Vendas.removerVendas(vendas, id);

                                            System.out.print("\nDeseja remover outro produto (s/n): ");
                                            alternativa = sc.next().charAt(0);
                                            minusculo = Character.toLowerCase(alternativa);
                                            minusculo = confirmandoCaractere(minusculo, alternativa);
                                        }
                                    } else {
                                        minusculo = 'n';
                                        idInvalida();
                                    }
                                }
                            } else {
                                produtoouclieteVazio();
                            }
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

    public static void idInvalida() throws InterruptedException {
        System.out.print("Ninguem cadastrado com esse id, Voltando ao menu em");
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

    public static int caractereInvalido() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nInforme o ID: ");
        while (!sc.hasNextInt()) {
            String input = sc.next();
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

    public static void categoriaInvalida() throws InterruptedException {
        System.out.print("Nenhuma categoria cadastrada, voltando em");
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

    public static void produtoVazio() throws InterruptedException {
        System.out.print("Nenhuma produto cadastrado, voltando em");
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

    public static void produtoouclieteVazio() throws InterruptedException {
        System.out.print("Fornecedor ou cliente não cadastrado, voltando em");
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

    public static void clienteVazio() throws InterruptedException {
        System.out.print("Nenhuma cliente cadastrado, voltando em");
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

    public static void fornecedorVazio() throws InterruptedException {
        System.out.print("Nenhum fornecedor cadastrado, voltando em");
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