package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Funcionario {
    private String nome;
    private String cargo;
    private double salario;
    private String cpf;
    public int id;


    private static ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
    private static int nextId = 1; // Para auto-incrementar IDs de funcionários


    public Funcionario(String nome, String cargo, double salario, String cpf) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
        this.cpf = cpf;
        this.id = nextId++; // Atribui ID e depois incrementa para o próximo funcionário
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalario() {
        return salario;
    }

    public String getCpf() {
        return cpf;
    }

    public String getId() {
        return id;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Cargo: " + cargo + ", Salário: R$" + String.format("%.2f", salario) + ", CPF: " + cpf;
    }



    public void inserirFuncionario() {
        listaFuncionarios.add(this);
        System.out.println("Funcionário " + this.nome + " (ID: " + this.id + ") inserido com sucesso!");
    }


    public void atualizarFuncionario() {
        boolean encontrado = false;
        for (int i = 0; i < listaFuncionarios.size(); i++) {
            if (listaFuncionarios.get(i).getId() == this.id) {
                listaFuncionarios.set(i, this);
                encontrado = true;
                System.out.println("Funcionário " + this.nome + " (ID: " + this.id + ") atualizado com sucesso!");
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Funcionário com ID " + this.id + " não encontrado para atualização.");
        }
    }


    public void removerFuncionario() {
        Iterator<Funcionario> iterator = listaFuncionarios.iterator();
        boolean removido = false;
        while (iterator.hasNext()) {
            Funcionario funcionario = iterator.next();
            if (funcionario.getId() == this.id) {
                iterator.remove();
                removido = true;
                System.out.println("Funcionário " + this.nome + " (ID: " + this.id + ") removido com sucesso!");
                break;
            }
        }
        if (!removido) {
            System.out.println("Funcionário com ID " + this.id + " não encontrado para remoção.");
        }
    }


    public static void consultarTodosFuncionarios() {
        if (listaFuncionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        System.out.println("\n--- Lista de Funcionários ---");
        for (Funcionario funcionario : listaFuncionarios) {
            System.out.println(funcionario);
        }
        System.out.println("-------------------------");
    }


    public static Funcionario consultarFuncionarioPorId(int id) {
        for (Funcionario funcionario : listaFuncionarios) {
            if (funcionario.getId() == id) {
                return funcionario;
            }
        }
        System.out.println("Funcionário com ID " + id + " não encontrado.");
        return null;
    }
}