package model;

import java.time.LocalDate; 
import java.time.format.DateTimeFormatter; 

public class Emprestimo {

  
    public enum StatusEmprestimo {
        PENDENTE,    
        DEVOLVIDO,   
        ATRASADO     
    }

    private String id;
    private Livro livro; 
    private Cliente cliente; 
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal; 
    private StatusEmprestimo status;

    // Construtor
    public Emprestimo(String id, Livro livro, Cliente cliente, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        this.id = id;
        this.livro = livro;
        this.cliente = cliente;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = null;
        this.status = StatusEmprestimo.PENDENTE; 
    }


    public String getId() {
        return id;
    }

    public Livro getLivro() {
        return livro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

  
    public void setId(String id) {
        this.id = id;
    }

  

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }

 
    public void registrarDevolucao() {
        this.dataDevolucaoReal = LocalDate.now(); 
        this.status = StatusEmprestimo.DEVOLVIDO; 
       
    }

   
    public boolean verificarAtraso() {
        if (status == StatusEmprestimo.PENDENTE && LocalDate.now().isAfter(dataDevolucaoPrevista)) {
            this.status = StatusEmprestimo.ATRASADO; 
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Emprestimo{" +
               "id='" + id + '\'' +
               ", livro=" + (livro != null ? livro.getNome() : "N/A") +
               ", cliente=" + (cliente != null ? cliente.getNome() : "N/A") + 
               ", dataEmprestimo=" + dataEmprestimo.format(formatter) +
               ", dataDevolucaoPrevista=" + dataDevolucaoPrevista.format(formatter) +
               ", dataDevolucaoReal=" + (dataDevolucaoReal != null ? dataDevolucaoReal.format(formatter) : "Pendente") +
               ", status=" + status +
               '}';
    }
}