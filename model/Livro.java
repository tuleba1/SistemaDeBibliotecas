// src/model/Livro.java
package model;

public class Livro {
    private String titulo;
    private String id; // Pode ser ISBN, um código único gerado, etc.
    private boolean disponivel;
    private int anoAquisicao;
    private int vezesEmprestado;

    // Construtor
    public Livro(String nome, String id, int anoAquisicao) {
        this.titulo = titulo;
        this.id = id;
        this.anoAquisicao = anoAquisicao;
        this.disponivel = true; // Por padrão, um livro novo está disponível
        this.vezesEmprestado = 0; // Começa com 0 empréstimos
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getId() {
        return id;
    }

    public boolean isDisponivel() { // Nome comum para getters de boolean
        return disponivel;
    }

    public int getAnoAquisicao() {
        return anoAquisicao;
    }

    public int getVezesEmprestado() {
        return vezesEmprestado;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setAnoAquisicao(int anoAquisicao) {
        this.anoAquisicao = anoAquisicao;
    }

    // O setter para vezesEmprestado geralmente não é usado diretamente de fora,
    // pois o incremento é feito via registrarEmprestimo(). Mas é bom ter.
    public void setVezesEmprestado(int vezesEmprestado) {
        this.vezesEmprestado = vezesEmprestado;
    }

    /**
     * Registra que o livro foi emprestado: o torna indisponível e incrementa o contador de empréstimos.
     */
    public void registrarEmprestimo() {
        if (this.disponivel) {
            this.disponivel = false;
            this.vezesEmprestado++;
            System.out.println("[Livro] '" + this.titulo + "' foi emprestado. Total de empréstimos: " + this.vezesEmprestado);
        } else {
            System.out.println("[Livro] '" + this.titulo + "' já está emprestado e não pode ser registrado novamente.");
        }
    }

    /**
     * Registra que o livro foi devolvido: o torna disponível novamente.
     */
    public void registrarDevolucao() {
        if (!this.disponivel) { // Só pode ser devolvido se não estiver disponível (ou seja, foi emprestado)
            this.disponivel = true;
            System.out.println("[Livro] '" + this.titulo + "' foi devolvido. Agora está disponível.");
        } else {
            System.out.println("[Livro] '" + this.titulo + "' já está disponível e não precisa de devolução.");
        }
    }

    // Método toString() para facilitar a visualização dos dados do livro
    @Override
    public String toString() {
        return "Livro{" +
                "nome='" + titulo + '\'' +
                ", id='" + id + '\'' +
                ", disponivel=" + disponivel +
                ", anoAquisicao=" + anoAquisicao +
                ", vezesEmprestado=" + vezesEmprestado +
                '}';
    }
}