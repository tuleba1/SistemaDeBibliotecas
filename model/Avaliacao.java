package model;

import java.time.LocalDate; 
import java.time.format.DateTimeFormatter; 

public class Avaliacao {
    private int nota; 
    private LocalDate data;
    private String comentario; 

    // Construtor
    public Avaliacao(int nota, LocalDate data, String comentario) {
       
        if (nota < 1 || nota > 5) { 
            System.out.println("Atenção: Nota da avaliação fora da escala esperada (1-5). Definindo como 1.");
            this.nota = 1;
        } else {
            this.nota = nota;
        }
        this.data = data;
        this.comentario = comentario;
    }

   
    public Avaliacao(int nota, LocalDate data) {
        this(nota, data, null); 
    }

   
    public int getNota() {
        return nota;
    }

    public LocalDate getData() {
        return data;
    }

    public String getComentario() {
        return comentario;
    }

   
    public void setNota(int nota) {
        if (nota < 1 || nota > 5) {
            System.out.println("Atenção: Tentativa de definir nota fora da escala (1-5). Nota não alterada.");
        } else {
            this.nota = nota;
        }
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

   
    public void atribuirNota(int novaNota) {
        setNota(novaNota);
    }

    public void alterarAvaliacao(int novaNota, String novoComentario) {
        setNota(novaNota);
        setComentario(novoComentario);
        System.out.println("Avaliação alterada para nota " + novaNota + " e comentário: '" + novoComentario + "'");
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Avaliacao{" +
               "nota=" + nota +
               ", data=" + data.format(formatter) +
               ", comentario='" + (comentario != null ? comentario : "N/A") + '\'' +
               '}';
    }
}