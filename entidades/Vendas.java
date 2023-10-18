package entidades;
import java.util.List;
import java.util.Scanner;

public class Vendas {
    private int id;
    private int idProduto;
    private String dataVenda;
    private int quantidadeVenda;
    private String nomeCliente;

    public Vendas(int id, int idProduto, String dataVenda, int quantidadeVenda, String nomeCliente) {
        this.id = id;
        this.idProduto = idProduto;
        this.dataVenda = dataVenda;
        this.quantidadeVenda = quantidadeVenda;
        this.nomeCliente = nomeCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getQuantidadeVenda() {
        return quantidadeVenda;
    }

    public void setQuantidadeVenda(int quantidadeVenda) {
        this.quantidadeVenda = quantidadeVenda;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    // Métodos
    public void adicionarVendas(List<Vendas> vendas) {
        vendas.add(this);
    }

    public static void editarVendas(List<Vendas> vendas, int id) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira uma nova data: ");
        String novaData = sc.nextLine();
        vendas.get(id).setDataVenda(novaData);
        System.out.print("Data alterada com sucesso!");
        sc.close();
    }

    public static void mostrarVendas(List<Vendas> vendas) {
        System.out.print("\u001B[1m");
        System.out.println("--------------------------- Lista de Vendas Realizadas ---------------------------\nID\tID Produto\tQuantidade\t\tData\t\tNome do Comprador");
        System.out.print("\u001B[0m");
        for (Vendas venda : vendas) {
            System.out.print("\u001B[30m");
            System.out.printf("%-12d%-15d%-17d %-18s %-15s\n",venda.id,venda.idProduto,venda.quantidadeVenda,venda.dataVenda,venda.nomeCliente);
            //System.out.println(venda.id + "\t     " + venda.idProduto + "\t\t  " + venda.quantidadeVenda + "\t     " + venda.dataVenda + "\t\t" + venda.nomeCliente);
            System.out.print("\u001B[0m");
        }

        System.out.println("\n\n");
    }

    public static void removerVendas(List<Vendas> vendas, int id) {
        vendas.remove(id);
        System.out.println("Venda removida!!");
    }

}
