package views;

import controllers.CarroController;
import factories.VeiculoFactory;
import java.util.Scanner;
import model.Carro;

public class CarroView {
    private final Scanner scanner = new Scanner(System.in);
    private final CarroController carroController;

    public CarroView(CarroController carroController) {
        this.carroController = carroController;
    }

    public void exibirMenu() {
        while(true) {
            System.out.println("=== Menu de Carros ===");
            System.out.println("1. Cadastrar Carro");
            System.out.println("2. Deletar Carro");
            System.out.println("3. Listar Carros");
            System.out.println("4. Buscar Carro por ID");
            System.out.println("5. Atualizar Carro");
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
                case 1 -> cadastrarCarro();
                case 2 -> deletarCarro();
                case 3 -> listarCarros();
                case 4 -> buscarCarroPorId();
                case 5 -> atualizarCarro();
                case 0 -> {
                    System.out.println("Saindo do menu de carros...");
                    return;
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private void cadastrarCarro() {
        System.out.println("=== Cadastrar Carro ===\n");
        System.out.print("Digite a marca: ");
        String marca = scanner.nextLine();

        System.out.print("Digite o modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Digite a placa: ");
        String placa = scanner.nextLine();

        System.out.print("Digite a cor: ");
        String cor = scanner.nextLine();

        System.out.print("Digite a quantidade de portas: ");
        int quantidadePortas = scanner.nextInt();
        scanner.nextLine();

        try {
            Carro novoCarro = VeiculoFactory.criarNovoCarro(marca, modelo, placa, cor, quantidadePortas);

            carroController.cadastrar(novoCarro);
            System.out.println("Carro cadastrado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar carro");
        }
    }

    private void deletarCarro() {
        System.out.println("=== Deletar Carro ===\n");
        System.out.print("Digite o ID do carro a ser deletado: ");
        String id = scanner.nextLine();

        try {
            carroController.deletar(id);
            System.out.println("Carro deletado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao deletar carro");
        }
    }

    private void listarCarros() {
        System.out.println("=== Listar Carros ===\n");
        try {
            carroController.listar().forEach(System.out::println);
        } catch (Exception e) {
            System.err.println("Erro ao listar carros");
        }
    }

    private void buscarCarroPorId() {
        System.out.println("=== Buscar Carro por ID ===\n");
        System.out.print("Digite o ID do carro: ");
        String id = scanner.nextLine();

        try {
            System.out.println(carroController.buscarPorId(id));
        } catch (Exception e) {
            System.err.println("Erro ao buscar carro por ID");
        }
    }

    private void atualizarCarro() {
        System.out.println("=== Atualizar Carro ===\n");
        System.out.print("Digite o ID do carro a ser atualizado: ");
        String id = scanner.nextLine();

        System.out.print("Digite a nova marca: ");
        String marca = scanner.nextLine();

        System.out.print("Digite o novo modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Digite a nova placa: ");
        String placa = scanner.nextLine();

        System.out.print("Digite a nova cor: ");
        String cor = scanner.nextLine();

        System.out.print("Digite a nova quantidade de portas: ");
        int quantidadePortas = scanner.nextInt();
        scanner.nextLine();

        try {
            Carro carroAtualizado = VeiculoFactory.criarCarroPersistido(id, marca, modelo, placa, cor, quantidadePortas);
            carroController.atualizar(id, carroAtualizado);

            System.out.println("Carro atualizado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar carro");
        }
    }
}
