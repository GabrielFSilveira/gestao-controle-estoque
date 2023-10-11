import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProdutoEstoque {
    private String nome;
    private int id;
    private String descricao;
    private int qtdTotal;
    private double valorCompra;
    private String dataCompra;

    public ProdutoEstoque(int id,String nome, String descricao, int qtdTotal, double valorCompra, String dataCompra) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.qtdTotal = qtdTotal;
        this.valorCompra = valorCompra;
        this.dataCompra = dataCompra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
     public int getId() {
     return id;
     }
     
     public void setId(int id) {
     this.id = id;
     }
     

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtdTotal() {
        return qtdTotal;
    }

    public void setQtdTotal(int qtdTotal) {
        this.qtdTotal = qtdTotal;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    // Iniciando métodos
    public void adicionarProduto(List<ProdutoEstoque> produtos) {
        produtos.add(this);
        System.out.println("Produto adicionado com sucesso!");
    }

    /* Método para mostrar os produtos */
    public static void mostrarProdutos(List<ProdutoEstoque> produtos) {
        System.out.println("\nLista de Produtos no Estoque\n\nID\tNome do Produto\t\tCategoria\tQuantidade em estoque\tValor unítario \t  Data da compra");
        for (ProdutoEstoque produto : produtos) {
            System.out.println(produto.id + "\t    " + produto.nome + "\t\t"+ produto.descricao+"\t\t     "+ produto.qtdTotal+"\t\t     "+ produto.valorCompra+"\t       "+produto.valorCompra);
        }
        System.out.println("\n\n");
    }

    /* Método para editar os produtos */
    public static void editarProduto(List<ProdutoEstoque> produto, int id, int opcao) {
        Scanner sc = new Scanner(System.in);
        String novaAlteracao;
        int novaQuantidade;
        double novoValor;
        if (opcao == 1) {
            System.out.print("Insira o novo nome do produto: ");
            novaAlteracao = sc.nextLine();
            produto.get(id).setNome(novaAlteracao);
            System.out.printf("Nome alterado!");

        } else if (opcao == 2) {
            System.out.print("Insira a nova descrição do produto: ");
            novaAlteracao = sc.nextLine();
            produto.get(id).setDescricao(novaAlteracao);
            System.out.println("Descrição alterada!");

        } else if (opcao == 3) {
            System.out.print("Insira a nova quantidade em estoque do produto: ");
            novaQuantidade = sc.nextInt();
            produto.get(id).setQtdTotal(novaQuantidade);
            System.out.println("Quantidade alterada!");

        } else if (opcao == 4) {
            System.out.print("Insira o novo valor unitário do produto: ");
            novoValor = sc.nextDouble();
            produto.get(id).setValorCompra(novoValor);
            System.out.println("Quantidade alterada!");

        } else if (opcao == 5) {
            System.out.print("Insira a nova data de cadastro do produto: ");
            novaAlteracao = sc.nextLine();
            produto.get(id).setDataCompra(novaAlteracao);
            System.out.println("Quantidade alterada!");
        } else {
            System.out.println("Opção invalida!!");
        }
    }

    /* Método para remover os produtos */
    public static void removerProduto(List<ProdutoEstoque> produtos,int id) {
        produtos.remove(id);
        System.out.println("Produto removido com sucesso!");
    }

    /* Método para incrementar os produtos */
    public static void incrementarProduto(List<ProdutoEstoque> produto, int id, int incrementarQuantidade) {
        int resultado = produto.get(id).getQtdTotal() + incrementarQuantidade;
        produto.get(id).setQtdTotal(resultado);
        System.out.print("Produto incrementado com sucesso!");
    }
}


