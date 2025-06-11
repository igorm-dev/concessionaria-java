package views;

import controllers.MotoController;
import factories.VeiculoFactory;
import java.util.Scanner;
import model.Moto;

public class MotoView {
    private final Scanner scanner = new Scanner(System.in);
    private final MotoController motoController;

    public MotoView(MotoController motoController) {
        this.motoController = motoController;
    }

    public void exibirMenu() {
        while(true) {
            System.out.println("=== Menu de Motos ===");
            System.out.println("1. Cadastrar Moto");
            System.out.println("2. Deletar Moto");
            System.out.println("3. Listar Motos");
            System.out.println("4. Buscar Moto por ID");
            System.out.println("5. Atualizar Moto");
            System.out.println("6. Sair");
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
                case 1 -> cadastrarMoto();
                case 2 -> deletarMoto();
                case 3 -> listarMotos();
                case 4 -> buscarMotoPorId();
                case 5 -> atualizarMoto();
                case 6 -> {
                    System.out.println("Saindo do menu de motos...");
                    return;
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private void cadastrarMoto() {
        System.out.println("=== Cadastrar Moto ===\n");
        System.out.print("Digite a marca: ");
        String marca = scanner.nextLine();

        System.out.print("Digite o modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Digite a placa: ");
        String placa = scanner.nextLine();

        System.out.print("Digite a cor: ");
        String cor = scanner.nextLine();

        System.out.println("Digite se tem descanso lateral (s/n): ");
        String descansoLateralInput = scanner.nextLine().trim().toLowerCase();
        boolean temDescansoLateral = descansoLateralInput.equals("s");
        

        try {
            motoController.cadastrar(VeiculoFactory.criarNovaMoto(marca, modelo, placa, cor, temDescansoLateral));
            System.out.println("Moto cadastrado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar moto");
        }
    }

    private void deletarMoto() {
        System.out.println("=== Deletar Moto ===\n");
        System.out.print("Digite o ID da moto a ser deletada: ");
        String id = scanner.nextLine();

        try {
            motoController.deletar(id);
            System.out.println("Moto deletada com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao deletar moto");
        }
    }
    
    private void listarMotos() {
        System.out.println("=== Listar Motos ===\n");
        try {
            motoController.listar().forEach(System.out::println);
        } catch (Exception e) {
            System.err.println("Erro ao listar motos");
        }
    }

    private void buscarMotoPorId() {
        System.out.println("=== Buscar Moto por ID ===\n");
        System.out.print("Digite o ID da moto: ");
        String id = scanner.nextLine();

        try {
            Moto moto = motoController.buscarPorId(id);
            
            System.out.println("Moto encontrada: " + moto);
        } catch (Exception e) {
            System.err.println("Erro ao buscar moto");
        }
    }

    private void atualizarMoto() {
        System.out.println("=== Atualizar Moto ===\n");
        System.out.print("Digite o ID da moto a ser atualizada: ");
        String id = scanner.nextLine();

        System.out.print("Digite a nova marca: ");
        String marca = scanner.nextLine();

        System.out.print("Digite o novo modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Digite a nova placa: ");
        String placa = scanner.nextLine();

        System.out.print("Digite a nova cor: ");
        String cor = scanner.nextLine();

        System.out.println("Digite se tem descanso lateral (s/n): ");
        String descansoLateralInput = scanner.nextLine().trim().toLowerCase();
        boolean temDescansoLateral = descansoLateralInput.equals("s");

        try {
            Moto motoAtualizada = VeiculoFactory.criarMotoPersistida(id, marca, modelo, cor, temDescansoLateral);
            motoController.atualizar(id, motoAtualizada);

            System.out.println("Moto atualizada com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar moto: " + e.getMessage());
        }
    }
}
