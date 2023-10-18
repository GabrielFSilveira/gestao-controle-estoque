package entidades;

public class Fornecedores {
    private int id;
    private String nome;
    private String contato;
    private String endereco;
    private String categoria;

    public Fornecedores() {
    }

    public Fornecedores(int id, String nome, String contato, String endereco, String categoria) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
        this.endereco = endereco;
        this.categoria = categoria;

    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String setNome(String nome) {
        return this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public String setContato(String contato) {
        return this.contato = contato;
    }

    public String getEndereco() {
        return endereco;
    }

    public String setEndereco(String endereco) {
        return this.endereco = endereco;
    }

    public String getCategoria() {
        return categoria;
    }

    public String setCategoria(String categoria) {
        return this.categoria = categoria;
    }

}
