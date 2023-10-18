package entidades;

public class Clientes {
    private int id;
    private String nomeCliente;
    private String contato;
    private String endereco;

    public Clientes() {
    }

    public Clientes(int id, String nomeCliente, String contato, String endereco) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.contato = contato;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
