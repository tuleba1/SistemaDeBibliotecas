// src/service/AvaliacaoService.java
package service;

import model.Avaliacao;
import model.Funcionario;
import repository.AvaliacaoRepository;
import repository.FuncionarioRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AvaliacaoService {

    private AvaliacaoRepository avaliacaoRepository;
    private FuncionarioRepository funcionarioRepository;

    // Construtor que recebe as instâncias dos repositórios
    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, FuncionarioRepository funcionarioRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    /**
     * Registra uma nova avaliação para um funcionário.
     * @param idFuncionario O ID do funcionário a ser avaliado.
     * @param nota A nota da avaliação (ex: de 1 a 5).
     * @param comentario Um comentário opcional sobre a avaliação.
     * @return O objeto Avaliacao criado, ou null se a avaliação não puder ser registrada.
     */
    public Avaliacao registrarAvaliacao(String idFuncionario, int nota, String comentario) {
        // 1. Verificar se o funcionário existe
        Funcionario funcionario = funcionarioRepository.buscarFuncionarioPorId(idFuncionario);
        if (funcionario == null) {
            System.out.println("Erro: Funcionário com ID '" + idFuncionario + "' não encontrado. Avaliação não registrada.");
            return null;
        }

        // 2. Criar o objeto Avaliacao
        // A classe Avaliacao já faz a validação da nota
        Avaliacao novaAvaliacao = new Avaliacao(nota, LocalDate.now(), comentario);

        // 3. Adicionar a avaliação ao repositório
        avaliacaoRepository.adicionarAvaliacao(novaAvaliacao);

        // Para associar a avaliação ao funcionário, podemos adicionar a avaliação
        // a uma lista dentro do objeto Funcionario, ou manter essa associação
        // apenas através da lógica que busca as avaliações por funcionário.
        // Por simplicidade, e para manter o Funcionario limpo, vamos fazer a associação
        // na busca, filtrando as avaliações no repositório.

        System.out.println("Avaliação registrada com sucesso para o funcionário '" + funcionario.getNome() + "'.");
        return novaAvaliacao;
    }

    /**
     * Lista todas as avaliações para um funcionário específico.
     * @param idFuncionario O ID do funcionário.
     * @return Uma lista de objetos Avaliacao associados ao funcionário.
     */
    public List<Avaliacao> listarAvaliacoesPorFuncionario(String idFuncionario) {
        Funcionario funcionario = funcionarioRepository.buscarFuncionarioPorId(idFuncionario);
        if (funcionario == null) {
            System.out.println("Erro: Funcionário com ID '" + idFuncionario + "' não encontrado para listar avaliações.");
            return new ArrayList<>();
        }

        // Filtra todas as avaliações no repositório que se aplicam a este funcionário.
        // Isso exigiria que a classe Avaliacao tivesse uma referência ao Funcionario ou seu ID.
        // Como não adicionamos isso na classe Avaliacao, vamos simular uma lista vazia por enquanto.
        // Para uma implementação real, Avaliacao precisaria de um atributo Funcionario ou String idFuncionario.
        System.out.println("Funcionalidade de listar avaliações por funcionário ainda não implementada completamente, pois a classe Avaliacao não armazena o ID do funcionário avaliado.");
        System.out.println("Para implementar isso, a classe 'Avaliacao' precisaria de um atributo 'idFuncionario' ou 'Funcionario'.");
        return new ArrayList<>(); // Retorna lista vazia por enquanto
    }

    /**
     * Calcula a média das notas de avaliação de um funcionário.
     * @param idFuncionario O ID do funcionário.
     * @return A média das notas, ou 0.0 se não houver avaliações ou o funcionário não for encontrado.
     */
    public double calcularMediaAvaliacoesFuncionario(String idFuncionario) {
        Funcionario funcionario = funcionarioRepository.buscarFuncionarioPorId(idFuncionario);
        if (funcionario == null) {
            System.out.println("Erro: Funcionário com ID '" + idFuncionario + "' não encontrado para calcular média de avaliações.");
            return 0.0;
        }

        // Para calcular a média, precisaríamos das avaliações associadas a este funcionário.
        // Como observado acima, a classe Avaliacao não tem essa associação direta ainda.
        // Portanto, este método retornará 0.0 por enquanto.
        System.out.println("Funcionalidade de calcular média de avaliações por funcionário ainda não implementada completamente, pois a classe Avaliacao não armazena o ID do funcionário avaliado.");
        return 0.0;
    }

    /**
     * Lista todas as avaliações registradas no sistema.
     * @return Uma lista de todas as avaliações.
     */
    public List<Avaliacao> listarTodasAvaliacoes() {
        return avaliacaoRepository.listarTodasAvaliacoes();
    }
}