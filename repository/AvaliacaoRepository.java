// src/repository/AvaliacaoRepository.java
package repository;

import model.Avaliacao; // Importa a classe Avaliacao do pacote model
import model.Funcionario; // Pode ser útil para buscar avaliações de um funcionário
import model.Cliente;   // Pode ser útil para buscar avaliações feitas por um cliente
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID; // Para gerar IDs únicos para as avaliações
import java.util.stream.Collectors;

public class AvaliacaoRepository {

    // Usamos um HashMap para armazenar as avaliações, onde a chave é um ID único da avaliação
    // Como Avaliacao não tem um ID intrínseco, vamos gerar um UUID para cada uma.
    private Map<String, Avaliacao> avaliacoes = new HashMap<>();

    // Adiciona uma nova avaliação ao repositório
    public void adicionarAvaliacao(Avaliacao avaliacao) {
        if (avaliacao != null) {
            // Gera um ID único para a avaliação
            String idAvaliacao = UUID.randomUUID().toString();
            avaliacoes.put(idAvaliacao, avaliacao);
            System.out.println("Avaliação (ID: " + idAvaliacao + ", Nota: " + avaliacao.getNota() + ") adicionada ao repositório.");
        } else {
            System.out.println("Erro: Não foi possível adicionar avaliação nula.");
        }
    }

    // Busca uma avaliação pelo seu ID (gerado pelo repositório)
    public Avaliacao buscarAvaliacaoPorId(String id) {
        return avaliacoes.get(id); // Retorna null se não encontrar
    }

    // Busca avaliações pela nota
    public List<Avaliacao> buscarAvaliacoesPorNota(int nota) {
        return avaliacoes.values().stream()
                .filter(a -> a.getNota() == nota)
                .collect(Collectors.toList());
    }

    // Lista todas as avaliações no repositório
    public List<Avaliacao> listarTodasAvaliacoes() {
        return new ArrayList<>(avaliacoes.values());
    }

    // Remove uma avaliação pelo seu ID
    public boolean removerAvaliacao(String id) {
        if (avaliacoes.containsKey(id)) {
            Avaliacao avaliacaoRemovida = avaliacoes.remove(id);
            System.out.println("Avaliação (ID: " + id + ", Nota: " + avaliacaoRemovida.getNota() + ") removida do repositório.");
            return true;
        } else {
            System.out.println("Erro: Avaliação com ID '" + id + "' não encontrada para remoção.");
            return false;
        }
    }

    // Atualiza uma avaliação existente (se o ID já existir)
    public boolean atualizarAvaliacao(String id, Avaliacao avaliacaoAtualizada) {
        if (id != null && avaliacaoAtualizada != null && avaliacoes.containsKey(id)) {
            avaliacoes.put(id, avaliacaoAtualizada); // Sobrescreve a avaliação existente
            System.out.println("Avaliação (ID: " + id + ", Nova Nota: " + avaliacaoAtualizada.getNota() + ") atualizada no repositório.");
            return true;
        } else if (id != null && !avaliacoes.containsKey(id)) {
            System.out.println("Erro: Avaliação com ID '" + id + "' não encontrada para atualização.");
            return false;
        } else {
            System.out.println("Erro: Não foi possível atualizar avaliação nula ou com ID nulo.");
            return false;
        }
    }

    // Retorna o número de avaliações no repositório
    public int contarAvaliacoes() {
        return avaliacoes.size();
    }
}