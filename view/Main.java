package view;

import model.Cliente;

public class Main {
    public static void main(String[] args) {
        //INSERÇÃO DE CLIENTES
        Cliente cliente1 = new Cliente("Sanclair Clemente", 30, "111.222.333-44");
        cliente1.inserirCliente();

        Cliente cliente2 = new Cliente("Túlio Pragana", 23, "111.222.333-44");
        cliente2.inserirCliente();

        Cliente cliente3 = new Cliente("Demetryus Oliveira", 33, "111.222.333-44");
        cliente3.inserirCliente();

        //Consultar todos os clientes
        Cliente.consultarTodosClientes();

        //Atualizar clientes
        cliente2.setNome("Túlio José Pragana");
        cliente2.setIdade(24);
        cliente2.atualizarCliente();


        //Consulta de cliente especifico por ID
        Cliente clienteConsultado = Cliente.consultarClientesPorId(cliente1.getId());
        if (clienteConsultado != null) {
            System.out.println("\nCliente consultado por ID: " + clienteConsultado);
        }

        //Consultar novamente para ver atualizações
        Cliente.consultarTodosClientes();

        //Remover um cliente
        cliente2.removerCliente();

        //Consultar novamente para ver a remoção
        Cliente.consultarTodosClientes();


    }
}
