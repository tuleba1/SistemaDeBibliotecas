package model;

import java.util.Date;

public class Emprestimo {
    private int idLivro;
    private Date dataEmprestimo;
    private Date dataDevolucaoPrevista;
    private Date dataDevolucaoReal;
    private String status;

    public void registrarEmprestimo() {}
    public void atualizarStatus() {}
    public void verificarAtraso() {}
}