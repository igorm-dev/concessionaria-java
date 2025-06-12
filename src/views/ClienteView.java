package views;

import controllers.ClienteController;
import factories.ClienteFactory;
import java.util.Scanner;
import model.Cliente;

public class ClienteView {
    private Scanner scanner = new Scanner(System.in);
    private ClienteController clienteController;

    public ClienteView(ClienteController clienteController) {
        this.clienteController = clienteController;
    }

    public void exibirMenu() {
        while (true) {
            System.out.println("=== Menu de Clientes ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Deletar Cliente");
            System.out.println("3. Listar Clientes");
            System.out.println("4. Buscar Cliente por ID");
            System.out.println("5. Atualizar Cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao;

            try {
                opcao = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida, por favor insira um número.");
                continue;
            } finally {
                scanner.nextLine();
            }

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> deletarCliente();
                case 3 -> listarClientes();
                case 4 -> buscarClientePorId();
                case 5 -> atualizarCliente();
                case 0 -> {
                    System.out.println("Saindo do menu de clientes...");
                    return;
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private void cadastrarCliente() {
        System.out.println("=== Cadastrar Cliente ===\n");
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o email: ");
        String email = scanner.nextLine();

        System.out.print("Digite o telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite o cpf: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite a data de nascimento: ");
        String dataNascimento = scanner.nextLine();

        try {
            Cliente cliente = ClienteFactory.criarNovoCliente(nome, email, telefone, cpf, dataNascimento);
            clienteController.salvar(cliente);

            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar cliente");
        }
    }

    private void deletarCliente() {
        System.out.println("=== Deletar Cliente ===\n");
        System.out.print("Digite o ID do cliente a ser deletado: ");
        String id = scanner.nextLine();

        try {
            clienteController.deletar(id);
            System.out.println("Cliente deletado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao deletar cliente");
        }
    }

    private void listarClientes() {
        System.out.println("=== Listar Clientes ===\n");
        try {
            var clientes = clienteController.Listar();
            if (clientes.isEmpty()) {
                System.out.println("Nenhum cliente cadastrado.");
            } else {
                for (Cliente cliente : clientes) {
                    System.out.println(cliente);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar clientes");
        }
    }

    private void buscarClientePorId() {
        System.out.println("=== Buscar Cliente por ID ===\n");
        System.out.print("Digite o ID do cliente: ");
        String id = scanner.nextLine();

        try {
            Cliente cliente = clienteController.buscarPorId(id);
            System.out.println(cliente);
        } catch (Exception e) {
            System.err.println("Erro ao buscar cliente por ID");
        }
    }

    private void atualizarCliente() {
        System.out.println("=== Atualizar Cliente ===\n");
        System.out.print("Digite o ID do cliente a ser atualizado: ");
        String id = scanner.nextLine();

        System.out.print("Digite o novo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o novo email: ");
        String email = scanner.nextLine();

        System.out.print("Digite o novo telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite o novo cpf: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite a nova data de nascimento: ");
        String dataNascimento = scanner.nextLine();

        try {
            Cliente clienteAtualizado = ClienteFactory.criarClientePersistido(id, nome, email, telefone, cpf, dataNascimento);
            clienteController.atualizar(id, clienteAtualizado);
            System.out.println("Cliente atualizado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar cliente");
        }
    }
}
