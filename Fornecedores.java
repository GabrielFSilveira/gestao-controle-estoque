import java.util.List;
import java.util.Scanner;

import javax.sound.midi.Soundbank;

public class Fornecedores {
    // Aqui começa a definição da classe Fornecedores.
    private int id;
    private String nome;
    private String contato;
    private String endereco;
    private String categoria;
    // Estas linhas declaram atributos privados que representam informações sobre um
    // fornecedor, como seu ID, nome, contato, endereço e categoria.

    public Fornecedores(int id, String nome, String contato, String endereco, String categoria) {
        // Este é o construtor da classe Fornecedores, usado para criar um novo objeto
        // Fornecedores.
        this.id = id;
        this.nome = nome;
        this.contato = contato;
        this.endereco = endereco;
        this.categoria = categoria;
        // Neste construtor, os atributos do objeto são inicializados com os valores
        // passados como argumentos.
    }

    public int getId() {
        return id;
    }
    // Este é um método "getter" que permite obter o valor do atributo "id" de um
    // objeto Fornecedores.

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


    public void adicionarFornecedores(List<Fornecedores> fornecedor) {
        // Este é um método estático para adicionar um fornecedor a uma lista de
        // fornecedores a uma List
        fornecedor.add(this);
        System.out.println("Fornecedor adicionado com sucesso");
    }

    public static void editarFornecedor(List<Fornecedores> fornecedor, int id, int opcao, List<String>categoria) {
        // Este é um método estático para editar um fornecedor na lista com base no ID.

        Scanner sc = new Scanner(System.in);

        String novaAlteracao;
        if (opcao == 1) {
            System.out.print("Insira um novo nome: ");
            novaAlteracao = sc.nextLine();
            fornecedor.get(id).setNome(novaAlteracao);
            System.out.printf("Nome alterado!");

        } else if (opcao == 2) {
            System.out.print("Insira um novo contato: ");
            novaAlteracao = sc.nextLine();
            fornecedor.get(id).setContato(novaAlteracao);
            System.out.println("Contato alterado!");

        } else if (opcao == 3) {
            System.out.print("Insira um novo endereco: ");
            novaAlteracao = sc.nextLine();
            fornecedor.get(id).setEndereco(novaAlteracao);
            System.out.println("Endereço alterado!");

        } else if (opcao == 4) {
            int i = 0;
            System.out.println("Lista de categorias\nID\tCategoria");
            for (String categorias : categoria) {
                System.out.println(i + "\t" + categoria.get(i));
                i++;
            }
            System.out.print("\nSelecione uma nova categoria: ");
            int select = sc.nextInt();
            String novaCategoria = categoria.get(select);
            fornecedor.get(id).setCategoria(novaCategoria);
        } else {
            System.out.println("Opção invalida!!");
        }
    }

    public static void mostrarFornecedores(List<Fornecedores> fornecedores) {
        System.out.println("Lista de Fornecedores\nID\tNome\t   Contato\t    Endereço\t    Categoria");
        for (Fornecedores fornecedor : fornecedores) {
            System.out.println(fornecedor.id + "\t" + fornecedor.nome + "\t  " + fornecedor.contato + "\t\t    " + fornecedor.endereco + "\t\t     " + fornecedor.categoria);
        }
        System.out.println("\n\n");
    }

    public static void removerFornecedor(List<Fornecedores> fornecedores, int id) {
        // Este é um método estático para remover um fornecedor da lista com base no ID.
        fornecedores.remove(id);
        System.out.println("Fornecedor removido com sucesso!");
    }
}
