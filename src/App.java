// Integrantes do projeto
//
// RGM: 37230255 - Daniel Henrique Marcos Rocha
// RGM: 39379655 - Igor Ribeiro Machado
// RGM: 37298135 - João Paulo Bertagia
// RGM: 39107884 - Luis Henrique Silva de Lima
// RGM: 38203235 - Luiz Augusto Santos Gumz

import controllers.CarroController;
import controllers.ClienteController;
import controllers.MotoController;
import controllers.VendaController;
import java.util.Scanner;
import repositories.CarroRepository;
import repositories.ClienteRepository;
import repositories.MotoRepository;
import repositories.VendaRepository;
import views.CarroView;
import views.ClienteView;
import views.MotoView;
import views.VendaView;

public class App {
    public static void main(String[] args) throws Exception {
        CarroRepository carroRepository = new CarroRepository();
        MotoRepository motoRepository = new MotoRepository();
        ClienteRepository clienteRepository = new ClienteRepository();
        VendaRepository vendaRepository = new VendaRepository();

        CarroController carroController = new CarroController(carroRepository);
        MotoController motoController = new MotoController(motoRepository);
        ClienteController clienteController = new ClienteController(clienteRepository);
        VendaController vendaController = new VendaController(vendaRepository);

        CarroView carroView = new CarroView(carroController);
        MotoView motoView = new MotoView(motoController);
        ClienteView clienteView = new ClienteView(clienteController);
        VendaView vendaView = new VendaView(vendaController, clienteController, carroController, motoController);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("=== Menu Principal ===");
                System.out.println("1. Gerenciar Carros");
                System.out.println("2. Gerenciar Motos");
                System.out.println("3. Gerenciar Clientes");
                System.out.println("4. Gerenciar Vendas");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");

                int opcao;
                try {
                    opcao = scanner.nextInt();
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("Entrada inválida, por favor insira um número.");
                    continue;
                }

                switch (opcao) {
                    case 1 -> carroView.exibirMenu();
                    case 2 -> motoView.exibirMenu();
                    case 3 -> clienteView.exibirMenu();
                    case 4 -> vendaView.exibirMenu();
                    case 0 -> {
                        System.out.println("Saindo do programa...");
                        return;
                    }
                    default -> System.out.println("Opção inválida, tente novamente.");
                }
            }   
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o aplicativo: " + e.getMessage());
        }
    }
}
