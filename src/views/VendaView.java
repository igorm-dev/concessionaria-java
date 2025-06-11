package views;

import controllers.CarroController;
import controllers.ClienteController;
import controllers.MotoController;
import controllers.VendaController;
import factories.VendaFactory;
import java.util.Scanner;
import model.Cliente;
import model.MetodoPagamento;
import model.Veiculo;
import model.Venda;

public class VendaView {
    private final Scanner scanner = new Scanner(System.in);
    
    private final VendaController vendaController;
    private final ClienteController clienteController;

    private final CarroController carroController;
    private final MotoController motoController;

    public VendaView(VendaController vendaController, ClienteController clienteController, CarroController carroController, MotoController motoController) {
        this.clienteController = clienteController;
        this.vendaController = vendaController;
        this.carroController = carroController;
        this.motoController = motoController;
    }

    public void exibirMenu() {
        while (true) {
            System.out.println("=== Menu de Vendas ===");
            System.out.println("1. Cadastrar Venda");
            System.out.println("2. Deletar Venda");
            System.out.println("3. Listar Vendas");
            System.out.println("4. Buscar Venda por ID");
            System.out.println("5. Atualizar Venda");
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
                case 1 -> cadastrarVenda();
                case 2 -> deletarVenda();
                case 3 -> listarVendas();
                case 4 -> buscarVendaPorId();
                case 5 -> atualizarVenda();
                case 0 -> {
                    System.out.println("Saindo do menu de vendas...");
                    return;
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private void cadastrarVenda() {
        System.out.println("=== Cadastrar Venda ===\n");
        try {
            Veiculo veiculo = this.lerVeiculo();


            System.out.print("Digite o ID do cliente: ");
            String clienteId = scanner.nextLine();

            System.out.print("Digite o valor da venda: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            MetodoPagamento metodoPagamento = this.lerMetodoPagamento();
        
            Cliente cliente = clienteController.buscarPorId(clienteId);

            Venda venda = VendaFactory.criarNovaVenda(cliente, veiculo, metodoPagamento, valor);
            vendaController.salvar(venda);

            System.out.println("Venda cadastrada com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar venda");
        }
    }

    private void deletarVenda() {
        System.out.println("=== Deletar Venda ===\n");
        System.out.print("Digite o ID da venda a ser deletada: ");
        String id = scanner.nextLine();

        try {
            vendaController.deletar(id);
            System.out.println("Venda deletada com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao deletar venda: " + e.getMessage());
        }
    }

    private void listarVendas() {
        System.out.println("=== Listar Vendas ===\n");
        try {
            vendaController.listar().forEach(System.out::println);
        } catch (Exception e) {
            System.err.println("Erro ao listar vendas: " + e.getMessage());
        }
    }
    private void buscarVendaPorId() {
        System.out.println("=== Buscar Venda por ID ===\n");
        System.out.print("Digite o ID da venda a ser buscada: ");
        String id = scanner.nextLine();

        try {
            Venda venda = vendaController.buscarPorId(id);
            System.out.println("Venda encontrada: " + venda);
        } catch (Exception e) {
            System.err.println("Erro ao buscar venda: " + e.getMessage());
        }
    }
    
    private void atualizarVenda() {
        System.out.println("=== Atualizar Venda ===\n");
        System.out.print("Digite o ID da venda a ser atualizada: ");
        String id = scanner.nextLine();

        try {
            Veiculo veiculo = this.lerVeiculo();


            System.out.print("Digite o ID do cliente: ");
            String clienteId = scanner.nextLine();

            System.out.print("Digite o valor da venda: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            MetodoPagamento metodoPagamento = this.lerMetodoPagamento();
        
            Cliente cliente = clienteController.buscarPorId(clienteId);

            Venda novaAtualizada = VendaFactory.criarVendaPersistida(id, cliente, veiculo, metodoPagamento, valor);
            vendaController.atualizar(id, novaAtualizada);

            System.out.println("Venda atualizada com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar venda: " + e.getMessage());
        }
    }

    private Veiculo lerVeiculo() throws Exception {
        int tipoVeiculo;

        while (true) {
            System.out.println("Selecione o tipo de veículo:");
            System.out.println("1. Carro");
            System.out.println("2. Moto");
            System.out.print("Escolha uma opção: ");
            
            try {
                tipoVeiculo = scanner.nextInt();
                
                if (tipoVeiculo < 1 || tipoVeiculo > 2) {
                    System.out.println("Opção inválida, por favor escolha 1 ou 2.");
                }
                
                break;
            } catch (Exception e) {
                System.out.println("Entrada inválida, por favor insira uma opção válida.");
            } finally {
                scanner.nextLine();
            }
        }
        System.out.print("Digite o ID do veículo: ");
        String veiculoId = scanner.nextLine();
        if (tipoVeiculo == 1) {
            return carroController.buscarPorId(veiculoId);
        } else {
            return motoController.buscarPorId(veiculoId);
        }
    }

    private MetodoPagamento lerMetodoPagamento() {
        MetodoPagamento metodoPagamento;

        while (true) { 
            System.out.println("Selecione o método de pagamento:");
            System.out.println("1. Dinheiro");
            System.out.println("2. Cartão de Crédito");
            System.out.println("3. Cartão de Débito");
            System.out.print("Escolha uma opção: ");
            
            try {
                int opcaoPagamento = scanner.nextInt();

                if (opcaoPagamento < 1 || opcaoPagamento > 3) {
                    System.out.println("Opção inválida, por favor escolha 1, 2 ou 3.");
                    continue;
                }

                switch (opcaoPagamento) {
                    case 1 -> metodoPagamento = MetodoPagamento.DINHEIRO;
                    case 2 -> metodoPagamento = MetodoPagamento.CARTAO_CREDITO;
                    case 3 -> metodoPagamento = MetodoPagamento.CARTAO_DEBITO;
                    default -> throw new IllegalArgumentException("Opção inválida");
                }

                return metodoPagamento;
            }
            catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida, por favor insira uma opção válida.");
            }
            catch (Exception e) {
                System.out.println("Entrada inválida, por favor insira uma opção válida.");
            } finally {
                scanner.nextLine();
            }
        }
    }
}
