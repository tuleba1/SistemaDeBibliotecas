// src/service/EmprestimoService.java
package service;

import model.Emprestimo;
import model.Livro;
import model.Cliente;
import repository.EmprestimoRepository;
import repository.LivroRepository;
import repository.ClienteRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID; // Para gerar IDs únicos para os empréstimos

public class EmprestimoService {

    private LivroRepository livroRepository;
    private ClienteRepository clienteRepository;
    private EmprestimoRepository emprestimoRepository;

    // Construtor que recebe as instâncias dos repositórios
    public EmprestimoService(LivroRepository livroRepository, ClienteRepository clienteRepository, EmprestimoRepository emprestimoRepository) {
        this.livroRepository = livroRepository;
        this.clienteRepository = clienteRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    /**
     * Realiza o empréstimo de um livro para um cliente.
     * @param idLivro O ID do livro a ser emprestado.
     * @param idCliente O ID do cliente que está pegando o livro.
     * @param diasEmprestimo O número de dias que o livro será emprestado.
     * @return O objeto Emprestimo criado, ou null se o empréstimo não puder ser realizado.
     */
    public Emprestimo realizarEmprestimo(String idLivro, String idCliente, int diasEmprestimo) {
        // 1. Buscar o livro
        Livro livro = livroRepository.buscarLivroPorId(idLivro);
        if (livro == null) {
            System.out.println("Erro: Livro com ID '" + idLivro + "' não encontrado.");
            return null;
        }
        if (!livro.isDisponivel()) {
            System.out.println("Erro: Livro '" + livro.getTitulo() + "' não está disponível para empréstimo.");
            return null;
        }

        // 2. Buscar o cliente
        Cliente cliente = clienteRepository.buscarClientePorId(idCliente);
        if (cliente == null) {
            System.out.println("Erro: Cliente com ID '" + idCliente + "' não encontrado.");
            return null;
        }

        // 3. Gerar um ID único para o empréstimo
        String idEmprestimo = UUID.randomUUID().toString();
        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucaoPrevista = dataEmprestimo.plusDays(diasEmprestimo); // Adiciona os dias de empréstimo

        // 4. Criar o objeto Emprestimo
        Emprestimo novoEmprestimo = new Emprestimo(idEmprestimo, livro, cliente, dataEmprestimo, dataDevolucaoPrevista);

        // 5. Adicionar o empréstimo ao repositório de empréstimos
        emprestimoRepository.adicionarEmprestimo(novoEmprestimo);

        // 6. Atualizar o status do livro (marcar como indisponível e incrementar vezesEmprestado)
        livro.registrarEmprestimo(); // Este método já atualiza o status e o contador
        livroRepository.atualizarLivro(livro); // Salva a mudança no repositório de livros

        System.out.println("Empréstimo realizado com sucesso! Detalhes: " + novoEmprestimo);
        return novoEmprestimo;
    }

    /**
     * Registra a devolução de um livro.
     * @param idEmprestimo O ID do empréstimo a ser devolvido.
     * @return true se a devolução foi registrada com sucesso, false caso contrário.
     */
    public boolean registrarDevolucao(String idEmprestimo) {
        Emprestimo emprestimo = emprestimoRepository.buscarEmprestimoPorId(idEmprestimo);
        if (emprestimo == null) {
            System.out.println("Erro: Empréstimo com ID '" + idEmprestimo + "' não encontrado.");
            return false;
        }
        if (emprestimo.getStatus() == Emprestimo.StatusEmprestimo.DEVOLVIDO) {
            System.out.println("Erro: Empréstimo (ID: " + idEmprestimo + ") já foi devolvido anteriormente.");
            return false;
        }

        // 1. Atualizar o status do empréstimo
        emprestimo.registrarDevolucao(); // Este método já atualiza dataDevolucaoReal e status
        emprestimoRepository.atualizarEmprestimo(emprestimo); // Salva a mudança no repositório de empréstimos

        // 2. Atualizar o status do livro (marcar como disponível)
        Livro livro = emprestimo.getLivro();
        if (livro != null) {
            livro.registrarDevolucao(); // Este método já atualiza a disponibilidade
            livroRepository.atualizarLivro(livro); // Salva a mudança no repositório de livros
        } else {
            System.out.println("Aviso: Livro associado ao empréstimo não encontrado no repositório de livros.");
        }

        System.out.println("Devolução do empréstimo (ID: " + idEmprestimo + ") registrada com sucesso.");
        return true;
    }

    /**
     * Lista todos os empréstimos que estão atualmente pendentes ou atrasados.
     * @return Uma lista de objetos Emprestimo pendentes/atrasados.
     */
    public List<Emprestimo> listarEmprestimosAtivos() {
        // O método no repositório já faz o filtro de status
        List<Emprestimo> ativos = emprestimoRepository.listarEmprestimosPendentes();
        ativos.forEach(Emprestimo::verificarAtraso); // Garante que o status de atraso seja atualizado
        return ativos;
    }

    /**
     * Lista todos os empréstimos (pendentes, devolvidos ou atrasados) de um cliente específico.
     * @param idCliente O ID do cliente.
     * @return Uma lista de objetos Emprestimo associados ao cliente.
     */
    public List<Emprestimo> listarHistoricoEmprestimosCliente(String idCliente) {
        Cliente cliente = clienteRepository.buscarClientePorId(idCliente);
        if (cliente == null) {
            System.out.println("Erro: Cliente com ID '" + idCliente + "' não encontrado para listar histórico de empréstimos.");
            return new ArrayList<>();
        }
        return emprestimoRepository.buscarEmprestimosPorCliente(cliente);
    }

    /**
     * Lista todos os empréstimos atrasados.
     * @return Uma lista de objetos Emprestimo que estão atrasados.
     */
    public List<Emprestimo> listarEmprestimosAtrasados() {
        return emprestimoRepository.listarEmprestimosAtrasados();
    }

    // Outros métodos auxiliares podem ser adicionados conforme a necessidade
}