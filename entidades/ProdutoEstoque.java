package entidades;

public class ProdutoEstoque {
    private int id;
    private String nome;
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

}


