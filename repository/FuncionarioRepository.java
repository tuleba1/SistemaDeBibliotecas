// src/repository/FuncionarioRepository.java
package repository;

import model.Funcionario; // Importa a classe Funcionario do pacote model
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors; // Para usar streams para buscar por nome

public class FuncionarioRepository {

    // Usamos um HashMap para armazenar os funcionários, onde a chave é o ID do funcionário
    private Map<String, Funcionario> funcionarios = new HashMap<>();

    // Adiciona um novo funcionário ao repositório
    public void adicionarFuncionario(Funcionario funcionario) {
        if (funcionario != null) {
            if (funcionarios.containsKey(funcionario.getId())) {
                System.out.println("Erro: Funcionário com ID '" + funcionario.getId() + "' já existe no repositório.");
            } else {
                funcionarios.put(funcionario.getId(), funcionario);
                System.out.println("Funcionário '" + funcionario.getNome() + "' (ID: " + funcionario.getId() + ") adicionado ao repositório.");
            }
        } else {
            System.out.println("Erro: Não foi possível adicionar funcionário nulo.");
        }
    }

    // Busca um funcionário pelo seu ID
    public Funcionario buscarFuncionarioPorId(String id) {
        return funcionarios.get(id); // Retorna null se não encontrar
    }

    // Busca funcionários por parte do nome (ignorando maiúsculas/minúsculas)
    public List<Funcionario> buscarFuncionarioPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return new ArrayList<>(); // Retorna lista vazia se o nome for inválido
        }
        String nomeLowerCase = nome.toLowerCase();
        return funcionarios.values().stream()
                .filter(funcionario -> funcionario.getNome().toLowerCase().contains(nomeLowerCase))
                .collect(Collectors.toList());
    }

    // Lista todos os funcionários no repositório
    public List<Funcionario> listarTodosFuncionarios() {
        return new ArrayList<>(funcionarios.values());
    }

    // Remove um funcionário pelo seu ID
    public boolean removerFuncionario(String id) {
        if (funcionarios.containsKey(id)) {
            Funcionario funcionarioRemovido = funcionarios.remove(id);
            System.out.println("Funcionário '" + funcionarioRemovido.getNome() + "' (ID: " + id + ") removido do repositório.");
            return true;
        } else {
            System.out.println("Erro: Funcionário com ID '" + id + "' não encontrado para remoção.");
            return false;
        }
    }

    // Atualiza um funcionário existente (se o ID já existir)
    public boolean atualizarFuncionario(Funcionario funcionarioAtualizado) {
        if (funcionarioAtualizado != null && funcionarios.containsKey(funcionarioAtualizado.getId())) {
            funcionarios.put(funcionarioAtualizado.getId(), funcionarioAtualizado); // Sobrescreve o funcionário existente
            System.out.println("Funcionário '" + funcionarioAtualizado.getNome() + "' (ID: " + funcionarioAtualizado.getId() + ") atualizado no repositório.");
            return true;
        } else if (funcionarioAtualizado != null) {
            System.out.println("Erro: Funcionário com ID '" + funcionarioAtualizado.getId() + "' não encontrado para atualização.");
            return false;
        } else {
            System.out.println("Erro: Não foi possível atualizar funcionário nulo.");
            return false;
        }
    }

    // Retorna o número de funcionários no repositório
    public int contarFuncionarios() {
        return funcionarios.size();
    }
}
