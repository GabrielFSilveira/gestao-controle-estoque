package entidades;


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
}
