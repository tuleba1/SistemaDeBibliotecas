package model;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;

public class Cliente {
    private String nome;
    private int idade;
    private String cpf;
    private int id;

    private static ArrayList<Cliente> listaClientes = new ArrayList<>(); //criação de arraylist da lista de clientes
    private static int netxId = 1; //ID dos clientes para ter uma auto-incrementação quando criado novos clientes

    public Cliente(String nome, int idade, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.id = netxId++; //Incrementação para cara clienteID criado
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "ID: " + id + "| Nome: " + nome + "| Idade: " + idade + "| CPF: " + cpf;
    }

    public void inserirCliente() {
        listaClientes.add(this); //Referenciando ao objeto clientes
        System.out.println("Cliente  " + this.nome + " (ID: " + this.id + ") Cadastrado com sucesso!");
    }

    public void atualizarCliente() {
        boolean encontrado = false;
        for(int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getId() == this.id) {
                listaClientes.set(i, this); //Atualizando o cliente
                encontrado = true;
                System.out.println("Cliente: " + this.nome + " (ID: " + this.id + ") Atualizado com sucesso!");
                break;
            }
        }
    }
    public void removerCliente() {
        //Uso de iterator para remover de forma segura durante a iteração
        Iterator<Cliente> iterator = listaClientes.iterator();
        boolean removido = false;
        while(iterator.hasNext()) {
            Cliente cliente = iterator.next();
            if (cliente.getId() == this.id) {
                iterator.remove();
                removido = true;
                System.out.println("Cliente " + this.nome + " (ID: " + this.id + ") Removido com sucesso!");
                break;
            }
        }
        if(!removido) {
            System.out.println("Cliente com ID " + this.id + " não encontrado para remoção");
        }
    }
    public static void consultarTodosClientes() {
        if (listaClientes.size() <= 0){
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        System.out.println("\n ---LISTA DE TODOS OS CLIENTES CADASTRADOS---");
        for(Cliente cliente : listaClientes) {
            System.out.println(cliente);
        }
        System.out.println("-----------------------------------");
    }

    public static Cliente consultarClientesPorId(int id) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        System.out.println("Cliente com ID " + id + " não encontrado.");
        return null;
    }
}