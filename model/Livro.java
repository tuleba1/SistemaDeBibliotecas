package model; // Declara que esta classe pertence ao pacote 'modelo'

import java.util.ArrayList;
import java.util.Iterator;

public class Livro {
    private String titulo;
    private String autor;
    private String isbn; 
    private int anoPublicacao;
    private int id;

 
    private static ArrayList<Livro> listaLivros = new ArrayList<>();
    private static int nextId = 1; 

    
    public Livro(String titulo, String autor, String isbn, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.id = nextId++; 
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public int getId() {
        return id;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Título: " + titulo + ", Autor: " + autor + ", ISBN: " + isbn + ", Ano: " + anoPublicacao;
    }


    
    public void inserirLivro() {
        listaLivros.add(this);
        System.out.println("Livro '" + this.titulo + "' (ID: " + this.id + ") inserido com sucesso!");
    }

   
    public void atualizarLivro() {
        boolean encontrado = false;
        for (int i = 0; i < listaLivros.size(); i++) {
            if (listaLivros.get(i).getId() == this.id) {
                listaLivros.set(i, this); 
                encontrado = true;
                System.out.println("Livro '" + this.titulo + "' (ID: " + this.id + ") atualizado com sucesso!");
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Livro com ID " + this.id + " não encontrado para atualização.");
        }
    }

  
    public void removerLivro() {
        Iterator<Livro> iterator = listaLivros.iterator();
        boolean removido = false;
        while (iterator.hasNext()) {
            Livro livro = iterator.next();
            if (livro.getId() == this.id) {
                iterator.remove();
                removido = true;
                System.out.println("Livro '" + this.titulo + "' (ID: " + this.id + ") removido com sucesso!");
                break;
            }
        }
        if (!removido) {
            System.out.println("Livro com ID " + this.id + " não encontrado para remoção.");
        }
    }


    public static void consultarTodosLivros() {
        if (listaLivros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        System.out.println("\n--- Lista de Livros ---");
        for (Livro livro : listaLivros) {
            System.out.println(livro);
        }
        System.out.println("-------------------------");
    }

   
    public static Livro consultarLivroPorId(int id) {
        for (Livro livro : listaLivros) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        System.out.println("Livro com ID " + id + " não encontrado.");
        return null;
    }
}