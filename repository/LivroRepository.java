package repository;

import model.Livro; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors; 

public class LivroRepository {


    
    private Map<String, Livro> livros = new HashMap<>();    // Usamos um HashMap para armazenar os livros, onde a chave é o ID do livro

    
    public void adicionarLivro(Livro livro) {
        if (livro != null && !livros.containsKey(livro.getId())) {
            livros.put(livro.getId(), livro);
            System.out.println("Livro '" + livro.getTitulo() + "' (ID: " + livro.getId() + ") adicionado ao repositório.");
        } else if (livro != null) {
            System.out.println("Erro: Livro com ID '" + livro.getId() + "' já existe no repositório.");
        } else {
            System.out.println("Erro: Não foi possível adicionar livro nulo.");
        }
    }

  
    public Livro buscarLivroPorId(String id) {
        return livros.get(id); 
    }

    
    public List<Livro> buscarLivroPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return new ArrayList<>(); 
        }
        String nomeLowerCase = nome.toLowerCase();
        return livros.values().stream()
                     .filter(livro -> livro.getTitulo().toLowerCase().contains(nomeLowerCase))
                     .collect(Collectors.toList());
    }


    public List<Livro> listarTodosLivros() {
        
        return new ArrayList<>(livros.values());
    }

   
    public boolean removerLivro(String id) {
        if (livros.containsKey(id)) {
            Livro livroRemovido = livros.remove(id);
            System.out.println("Livro '" + livroRemovido.getTitulo() + "' (ID: " + id + ") removido do repositório.");
            return true;
        } else {
            System.out.println("Erro: Livro com ID '" + id + "' não encontrado para remoção.");
            return false;
        }
    }

    
    public boolean atualizarLivro(Livro livroAtualizado) {
        if (livroAtualizado != null && livros.containsKey(livroAtualizado.getId())) {
            livros.put(livroAtualizado.getId(), livroAtualizado); 
            System.out.println("Livro '" + livroAtualizado.getTitulo() + "' (ID: " + livroAtualizado.getId() + ") atualizado no repositório.");
            return true;
        } else if (livroAtualizado != null) {
            System.out.println("Erro: Livro com ID '" + livroAtualizado.getId() + "' não encontrado para atualização.");
            return false;
        } else {
            System.out.println("Erro: Não foi possível atualizar livro nulo.");
            return false;
        }
    }


    public int contarLivros() {
        return livros.size();    // Retorna o número de livros no repositório
    }
}