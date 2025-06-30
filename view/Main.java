package view;

import model.Cliente;
import model.Funcionario;

import java.util.Scanner; // Importar a classe Scanner para ler a entrada do usuário

public class Main {
    private static Scanner scanner = new Scanner(System.in); // Scanner estático para uso em toda a classe

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao Sistema de Gerenciamento!");

        int opcaoPrincipal;
        do {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Funcionários");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcaoPrincipal = obterOpcaoValida(); // Chama método para obter opção e tratar erro

            switch (opcaoPrincipal) {
                case 1:
                    gerenciarClientes();
                    break;
                case 2:
                    gerenciarFuncionarios();
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcaoPrincipal != 0);

        scanner.close(); // Fechar o scanner quando a aplicação terminar
    }

    // --- Métodos de Gerenciamento ---

    private static void gerenciarClientes() {
        int opcaoCliente;
        do {
            System.out.println("\n--- Gerenciar Clientes ---");
            System.out.println("1. Inserir Cliente");
            System.out.println("2. Consultar Todos os Clientes");
            System.out.println("3. Consultar Cliente por ID");
            System.out.println("4. Atualizar Cliente");
            System.out.println("5. Remover Cliente");
            System.out.println("9. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcaoCliente = obterOpcaoValida();

            switch (opcaoCliente) {
                case 1:
                    System.out.print("Nome do Cliente: ");
                    String nomeCli = scanner.nextLine();
                    System.out.print("Idade do Cliente: ");
                    int idadeCli = obterOpcaoValida(); // Reutiliza para ler int
                    System.out.print("CPF do Cliente: ");
                    String cpfCli = scanner.nextLine();
                    new Cliente(nomeCli, idadeCli, cpfCli).inserirCliente();
                    break;
                case 2:
                    Cliente.consultarTodosClientes();
                    break;
                case 3:
                    System.out.print("ID do Cliente a consultar: ");
                    int idConsultaCli = obterOpcaoValida();
                    Cliente clienteEncontrado = Cliente.consultarClientesPorId(idConsultaCli);
                    if (clienteEncontrado != null) {
                        System.out.println("Cliente encontrado: " + clienteEncontrado);
                    }
                    break;
                case 4:
                    System.out.print("ID do Cliente a atualizar: ");
                    int idAtualizaCli = obterOpcaoValida();
                    Cliente clienteAtualizar = Cliente.consultarClientesPorId(idAtualizaCli);
                    if (clienteAtualizar != null) {
                        System.out.print("Novo Nome (" + clienteAtualizar.getNome() + "): ");
                        clienteAtualizar.setNome(scanner.nextLine());
                        System.out.print("Nova Idade (" + clienteAtualizar.getIdade() + "): ");
                        clienteAtualizar.setIdade(obterOpcaoValida());
                        System.out.print("Novo CPF (" + clienteAtualizar.getCpf() + "): ");
                        clienteAtualizar.setCpf(scanner.nextLine());
                        clienteAtualizar.atualizarCliente(); // Chama o método de atualização
                    }
                    break;
                case 5:
                    System.out.print("ID do Cliente a remover: ");
                    int idRemoverCli = obterOpcaoValida();
                    // Para remover, precisamos de uma instância de Cliente com o ID para chamar removerCliente()
                    // Criamos uma "instância dummy" apenas para usar o método de instância.
                    // Em um sistema mais robusto, removerCliente poderia ser estático e receber o ID.
                    Cliente clienteRemoverDummy = new Cliente("", 0, ""); // Valores dummy
                    clienteRemoverDummy.setCpf(""); // Apenas para inicializar
                    clienteRemoverDummy.id = idRemoverCli; // Definimos o ID manualmente
                    clienteRemoverDummy.removerCliente();
                    break;
                case 9:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida para gerenciamento de clientes. Tente novamente.");
                    break;
            }
        } while (opcaoCliente != 9);
    }

    private static void gerenciarFuncionarios() {
        int opcaoFuncionario;
        do {
            System.out.println("\n--- Gerenciar Funcionários ---");
            System.out.println("1. Inserir Funcionário");
            System.out.println("2. Consultar Todos os Funcionários");
            System.out.println("3. Consultar Funcionário por ID");
            System.out.println("4. Atualizar Funcionário");
            System.out.println("5. Remover Funcionário");
            System.out.println("9. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcaoFuncionario = obterOpcaoValida();

            switch (opcaoFuncionario) {
                case 1:
                    System.out.print("Nome do Funcionário: ");
                    String nomeFunc = scanner.nextLine();
                    System.out.print("Cargo do Funcionário: ");
                    String cargoFunc = scanner.nextLine();
                    System.out.print("Salário do Funcionário: ");
                    double salarioFunc = obterSalarioValido(); // Novo método para double
                    System.out.print("CPF do Funcionário: ");
                    String cpfFunc = scanner.nextLine();
                    new Funcionario(nomeFunc, cargoFunc, salarioFunc, cpfFunc).inserirFuncionario();
                    break;
                case 2:
                    Funcionario.consultarTodosFuncionarios();
                    break;
                case 3:
                    System.out.print("ID do Funcionário a consultar: ");
                    int idConsultaFunc = obterOpcaoValida();
                    Funcionario funcionarioEncontrado = Funcionario.consultarFuncionarioPorId(idConsultaFunc);
                    if (funcionarioEncontrado != null) {
                        System.out.println("Funcionário encontrado: " + funcionarioEncontrado);
                    }
                    break;
                case 4:
                    System.out.print("ID do Funcionário a atualizar: ");
                    int idAtualizaFunc = obterOpcaoValida();
                    Funcionario funcionarioAtualizar = Funcionario.consultarFuncionarioPorId(idAtualizaFunc);
                    if (funcionarioAtualizar != null) {
                        System.out.print("Novo Nome (" + funcionarioAtualizar.getNome() + "): ");
                        funcionarioAtualizar.setNome(scanner.nextLine());
                        System.out.print("Novo Cargo (" + funcionarioAtualizar.getCargo() + "): ");
                        funcionarioAtualizar.setCargo(scanner.nextLine());
                        System.out.print("Novo Salário (" + String.format("%.2f", funcionarioAtualizar.getSalario()) + "): ");
                        funcionarioAtualizar.setSalario(obterSalarioValido());
                        System.out.print("Novo CPF (" + funcionarioAtualizar.getCpf() + "): ");
                        funcionarioAtualizar.setCpf(scanner.nextLine());
                        funcionarioAtualizar.atualizarFuncionario();
                    }
                    break;
                case 5:
                    System.out.print("ID do Funcionário a remover: ");
                    int idRemoverFunc = obterOpcaoValida();
                    // Criamos uma "instância dummy" apenas para usar o método de instância.
                    Funcionario funcionarioRemoverDummy = new Funcionario("", "", 0.0, "");
                    funcionarioRemoverDummy.id = idRemoverFunc;
                    funcionarioRemoverDummy.removerFuncionario();
                    break;
                case 9:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida para gerenciamento de funcionários. Tente novamente.");
                    break;
            }
        } while (opcaoFuncionario != 9);
    }

    // --- Métodos Auxiliares para Leitura de Entrada ---

    private static int obterOpcaoValida() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            scanner.next(); // Consumir a entrada inválida
            System.out.print("Escolha uma opção: ");
        }
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir o restante da linha após o nextInt()
        return opcao;
    }

    private static double obterSalarioValido() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Entrada inválida. Por favor, digite um número para o salário.");
            scanner.next(); // Consumir a entrada inválida
            System.out.print("Digite o salário: ");
        }
        double salario = scanner.nextDouble();
        scanner.nextLine(); // Consumir o restante da linha
        return salario;
    }
}
