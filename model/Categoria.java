package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Categoria {
    private String nome;
    private List<Livro> livros; 

    // Construtor
    public Categoria(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>(); 
    }

    // Getters
    public String getNome() {
        return nome;
    }

  
    public List<Livro> getLivros() {
        return new ArrayList<>(livros);
    }


    public int getLivrosDisponiveis() {
        // Usa Stream API para contar quantos livros na lista estão disponíveis
        return (int) livros.stream()
                           .filter(Livro::isDisponivel) 
                           .count(); 
    }

  
    public void setNome(String nome) {
        this.nome = nome;
    }


    public void adicionarLivro(Livro livro) {
        if (livro != null && !this.livros.contains(livro)) {
            this.livros.add(livro);
            System.out.println("Livro '" + livro.getNome() + "' adicionado à categoria '" + this.nome + "'.");
        } else if (livro != null) {
            System.out.println("Livro '" + livro.getNome() + "' já existe na categoria '" + this.nome + "'.");
        }
    }

    public boolean removerLivro(Livro livro) {
        if (livro != null && this.livros.remove(livro)) {
            System.out.println("Livro '" + livro.getNome() + "' removido da categoria '" + this.nome + "'.");
            return true;
        } else {
            System.out.println("Livro '" + (livro != null ? livro.getNome() : "Nulo") + "' não encontrado na categoria '" + this.nome + "'.");
            return false;
        }
    }

 
    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado na categoria '" + this.nome + "'.");
            return;
        }
        System.out.println("\n--- Livros na Categoria: " + this.nome + " ---");
        livros.forEach(livro -> System.out.println(livro.toString()));
        System.out.println("------------------------------------");
    }

    public Livro buscarLivro(String tituloOuId) {
        for (Livro livro : livros) {
            if (livro.getNome().equalsIgnoreCase(tituloOuId) || livro.getId().equalsIgnoreCase(tituloOuId)) {
                return livro;
            }
        }
        System.out.println("Livro com título/ID '" + tituloOuId + "' não encontrado na categoria '" + this.nome + "'.");
        return null; 
    }

    @Override
    public String toString() {
        return "Categoria{" +
               "nome='" + nome + '\'' +
               ", totalLivros=" + livros.size() +
               ", livrosDisponiveis=" + getLivrosDisponiveis() +
               '}';
    }
}