// src/model/Avaliacao.java
package model;

import java.time.LocalDate; // Para lidar com datas
import java.time.format.DateTimeFormatter; // Para formatar a saída da data

public class Avaliacao {
    private int nota; // A nota da avaliação (ex: de 1 a 5)
    private LocalDate data; // A data em que a avaliação foi feita
    private String comentario; // Um campo opcional para comentários
    private String idFuncionarioAvaliado; // NOVO ATRIBUTO: ID do funcionário que está sendo avaliado

    // Construtor principal e completo
    public Avaliacao(int nota, LocalDate data, String comentario, String idFuncionarioAvaliado) {
        // Validação básica para a nota
        if (nota < 1 || nota > 5) { // Assumindo uma escala de 1 a 5
            System.out.println("Atenção: Nota da avaliação fora da escala esperada (1-5). Definindo como 1.");
            this.nota = 1;
        } else {
            this.nota = nota;
        }
        this.data = data;
        this.comentario = comentario;
        this.idFuncionarioAvaliado = idFuncionarioAvaliado; // Atribui o ID do funcionário
    }

    // Construtor simplificado sem comentário
    public Avaliacao(int nota, LocalDate data, String idFuncionarioAvaliado) {
        this(nota, data, null, idFuncionarioAvaliado); // Chama o construtor completo com comentário nulo
    }

    // Getters
    public int getNota() {
        return nota;
    }

    public LocalDate getData() {
        return data;
    }

    public String getComentario() {
        return comentario;
    }

    // NOVO GETTER para o ID do funcionário avaliado
    public String getIdFuncionarioAvaliado() {
        return idFuncionarioAvaliado;
    }

    // Setters
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

    // NOVO SETTER para o ID do funcionário avaliado (use com cautela, este ID não deveria mudar após a criação)
    public void setIdFuncionarioAvaliado(String idFuncionarioAvaliado) {
        this.idFuncionarioAvaliado = idFuncionarioAvaliado;
    }


    // Métodos específicos do diagrama (adaptados para o contexto da classe)

    // Método para atribuir/definir a nota (já coberto pelo setter, mas mantido por clareza)
    public void atribuirNota(int novaNota) {
        setNota(novaNota);
    }

    // Método para alterar a avaliação (pode ser a nota ou o comentário)
    public void alterarAvaliacao(int novaNota, String novoComentario) {
        setNota(novaNota);
        setComentario(novoComentario);
        System.out.println("Avaliação alterada para nota " + novaNota + " e comentário: '" + novoComentario + "'");
    }

    // Método toString() para facilitar a visualização dos dados da avaliação
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Avaliacao{" +
                "nota=" + nota +
                ", data=" + data.format(formatter) +
                ", comentario='" + (comentario != null ? comentario : "N/A") + '\'' +
                ", idFuncionarioAvaliado='" + idFuncionarioAvaliado + '\'' + // Incluir no toString
                '}';
    }
}