// src/repository/EmprestimoRepository.java
package repository;

import model.Emprestimo; // Importa a classe Emprestimo do pacote model
import model.Livro;     // Pode ser útil para buscar empréstimos por livro
import model.Cliente;   // Pode ser útil para buscar empréstimos por cliente

import java.util.*;
import java.util.stream.Collectors;

public class EmprestimoRepository {

    // Usamos um HashMap para armazenar os empréstimos, onde a chave é o ID do empréstimo
    private Map<String, Emprestimo> emprestimos = new HashMap<>();

    // Adiciona um novo empréstimo ao repositório
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        if (emprestimo != null) {
            if (emprestimos.containsKey(emprestimo.getId())) {
                System.out.println("Erro: Empréstimo com ID '" + emprestimo.getId() + "' já existe no repositório.");
            } else {
                emprestimos.put(emprestimo.getId(), emprestimo);
                System.out.println("Empréstimo (ID: " + emprestimo.getId() + ") do livro '" + emprestimo.getLivro().getTitulo() +
                        "' para o cliente '" + emprestimo.getCliente().getNome() + "' adicionado ao repositório.");
            }
        } else {
            System.out.println("Erro: Não foi possível adicionar empréstimo nulo.");
        }
    }

    // Busca um empréstimo pelo seu ID
    public Emprestimo buscarEmprestimoPorId(String id) {
        return emprestimos.get(id); // Retorna null se não encontrar
    }

    // Busca empréstimos por um cliente específico
    public List<Emprestimo> buscarEmprestimosPorCliente(Cliente cliente) {
        if (cliente == null) {
            return new ArrayList<>();
        }
        return emprestimos.values().stream()
                .filter(e -> Objects.equals(e.getCliente().getId(), cliente.getId()))
                .collect(Collectors.toList());
    }

    // Busca empréstimos por um livro específico
    public List<Emprestimo> buscarEmprestimosPorLivro(Livro livro) {
        if (livro == null) {
            return new ArrayList<>();
        }
        return emprestimos.values().stream()
                .filter(e -> e.getLivro().getId().equals(livro.getId()))
                .collect(Collectors.toList());
    }

    // Lista todos os empréstimos no repositório
    public List<Emprestimo> listarTodosEmprestimos() {
        return new ArrayList<>(emprestimos.values());
    }

    // Lista todos os empréstimos pendentes (não devolvidos)
    public List<Emprestimo> listarEmprestimosPendentes() {
        return emprestimos.values().stream()
                .filter(e -> e.getStatus() == Emprestimo.StatusEmprestimo.PENDENTE ||
                        e.getStatus() == Emprestimo.StatusEmprestimo.ATRASADO)
                .collect(Collectors.toList());
    }

    // Lista todos os empréstimos atrasados
    public List<Emprestimo> listarEmprestimosAtrasados() {
        return emprestimos.values().stream()
                .filter(e -> e.verificarAtraso()) // O método verificarAtraso() na classe Emprestimo já atualiza o status
                .collect(Collectors.toList());
    }

    // Remove um empréstimo pelo seu ID
    public boolean removerEmprestimo(String id) {
        if (emprestimos.containsKey(id)) {
            Emprestimo emprestimoRemovido = emprestimos.remove(id);
            System.out.println("Empréstimo (ID: " + id + ") removido do repositório.");
            return true;
        } else {
            System.out.println("Erro: Empréstimo com ID '" + id + "' não encontrado para remoção.");
            return false;
        }
    }

    // Atualiza um empréstimo existente (se o ID já existir)
    public boolean atualizarEmprestimo(Emprestimo emprestimoAtualizado) {
        if (emprestimoAtualizado != null && emprestimos.containsKey(emprestimoAtualizado.getId())) {
            emprestimos.put(emprestimoAtualizado.getId(), emprestimoAtualizado); // Sobrescreve o empréstimo existente
            System.out.println("Empréstimo (ID: " + emprestimoAtualizado.getId() + ") atualizado no repositório.");
            return true;
        } else if (emprestimoAtualizado != null) {
            System.out.println("Erro: Empréstimo com ID '" + emprestimoAtualizado.getId() + "' não encontrado para atualização.");
            return false;
        } else {
            System.out.println("Erro: Não foi possível atualizar empréstimo nulo.");
            return false;
        }
    }

    // Retorna o número de empréstimos no repositório
    public int contarEmprestimos() {
        return emprestimos.size();
    }
}