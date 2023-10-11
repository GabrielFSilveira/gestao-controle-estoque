import java.util.List;
import java.util.Scanner;

public class Clientes {
    private int id;
    private String nomeCliente;
    private String contato;
    private String endereco;

    public Clientes(int id, String nomeCliente, String contato, String endereco) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.contato = contato;
        this.endereco = endereco;
    }

    public int setId(int id){
        return this.id = id;
    }
    public int getId(){
        return id;
    }
    
    public String setNomeCliente(String nomeCliente) {
        return this.nomeCliente = nomeCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
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

    /* Metodo ppara adicionar clientes */
    public void adicionarCliente(List<Clientes> clientes) {
        clientes.add(this);
        System.out.println("Cliente adicionado com sucesso!");
    }

    /* Metodo para editar cliente */
    public static void editarCliente(List<Clientes> cliente, int id, int opcao) {
        Scanner sc = new Scanner(System.in);

        String novaAlteracao;
        if (opcao == 1) {
            System.out.print("Insira um novo nome: ");
            novaAlteracao = sc.nextLine();
            cliente.get(id).setNomeCliente(novaAlteracao);
            System.out.printf("Nome alterado com sucesso!");

        } else if (opcao == 2) {
            System.out.print("Insira um novo contato: ");
            novaAlteracao = sc.nextLine();
            cliente.get(id).setContato(novaAlteracao);
            System.out.println("Contato alterado com sucesso!");

        } else if (opcao == 3) {
            System.out.print("Insira um novo endereco: ");
            novaAlteracao = sc.nextLine();
            cliente.get(id).setEndereco(novaAlteracao);
            System.out.println("Endereço alterado com sucesso!");

        } else {
            System.out.println("Opção invalida!!");
        }
    }

    /* Metodo para mostrar os clientes */
    public static void mostrarClientes(List<Clientes> clientes) {
        System.out.println("--------------------Lista de Clientes--------------------\nID\tNome\t\t\tContato\t\tEndereço");

        for (Clientes cliente : clientes) {
            System.out.print("\u001B[30m");
            System.out.printf("%-8d%-24s%-15s %-15s\n",cliente.id,cliente.nomeCliente,cliente.contato,cliente.endereco);
            //System.out.println(cliente.id + "\t" + cliente.nomeCliente + "\t   " + cliente.contato + "\t\t" + cliente.endereco);
            System.out.print("\u001B[0m");
        }
        System.out.println("\n\n");
    }

    /* Metodo para remover clientes */
    public static void removerCliente(List<Clientes> clientes, int id) {
        clientes.remove(id);
        System.out.println("Cliente removido com sucesso!");
    }
}
