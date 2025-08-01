// src/repository/CategoriaRepository.java
package repository;

import model.Categoria; // Importa a classe Categoria do pacote model
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CategoriaRepository {

    // Usamos um HashMap para armazenar as categorias, onde a chave é o nome da categoria
    // Assumimos que o nome da categoria é único.
    private Map<String, Categoria> categorias = new HashMap<>();

    // Adiciona uma nova categoria ao repositório
    public void adicionarCategoria(Categoria categoria) {
        if (categoria != null) {
            // Usa o nome da categoria como chave, convertendo para minúsculas para consistência
            String nomeChave = categoria.getNome().toLowerCase();
            if (categorias.containsKey(nomeChave)) {
                System.out.println("Erro: Categoria '" + categoria.getNome() + "' já existe no repositório.");
            } else {
                categorias.put(nomeChave, categoria);
                System.out.println("Categoria '" + categoria.getNome() + "' adicionada ao repositório.");
            }
        } else {
            System.out.println("Erro: Não foi possível adicionar categoria nula.");
        }
    }

    // Busca uma categoria pelo seu nome (ignorando maiúsculas/minúsculas)
    public Categoria buscarCategoriaPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return null;
        }
        return categorias.get(nome.toLowerCase()); // Retorna null se não encontrar
    }

    // Lista todas as categorias no repositório
    public List<Categoria> listarTodasCategorias() {
        return new ArrayList<>(categorias.values());
    }

    // Remove uma categoria pelo seu nome
    public boolean removerCategoria(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Erro: Nome da categoria para remoção não pode ser nulo ou vazio.");
            return false;
        }
        String nomeChave = nome.toLowerCase();
        if (categorias.containsKey(nomeChave)) {
            Categoria categoriaRemovida = categorias.remove(nomeChave);
            System.out.println("Categoria '" + categoriaRemovida.getNome() + "' removida do repositório.");
            return true;
        } else {
            System.out.println("Erro: Categoria '" + nome + "' não encontrada para remoção.");
            return false;
        }
    }

    // Atualiza uma categoria existente (se o nome já existir)
    public boolean atualizarCategoria(Categoria categoriaAtualizada) {
        if (categoriaAtualizada != null) {
            String nomeChave = categoriaAtualizada.getNome().toLowerCase();
            if (categorias.containsKey(nomeChave)) {
                categorias.put(nomeChave, categoriaAtualizada); // Sobrescreve a categoria existente
                System.out.println("Categoria '" + categoriaAtualizada.getNome() + "' atualizada no repositório.");
                return true;
            } else {
                System.out.println("Erro: Categoria '" + categoriaAtualizada.getNome() + "' não encontrada para atualização.");
                return false;
            }
        } else {
            System.out.println("Erro: Não foi possível atualizar categoria nula.");
            return false;
        }
    }

    // Retorna o número de categorias no repositório
    public int contarCategorias() {
        return categorias.size();
    }
}