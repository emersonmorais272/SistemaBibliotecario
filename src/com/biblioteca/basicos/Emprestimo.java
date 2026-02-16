package com.biblioteca.basicos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {

    private Usuario usuario;
    private Acervo intem;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private double multa;

    public Emprestimo(Usuario usuario, Acervo intem, int diasPrazo) {

        this.usuario=usuario;
        this.intem=intem;
        this.dataEmprestimo=LocalDate.now();
        this.dataPrevistaDevolucao=dataEmprestimo.plusDays(diasPrazo);
        this.multa=0.0;
        intem.setDisponivel(false);
    }

    public void registrarDevoluucao() {

        this.dataDevolucao = LocalDate.now();

        if (dataDevolucao.isAfter(dataPrevistaDevolucao)) {
            long diasAtraso= ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataDevolucao);

            this.multa= usuario.calcularMulta(diasAtraso);
        }
        intem.setDisponivel(true);
    }

    public void renovar(int diasExtras) {
        this.dataPrevistaDevolucao=dataPrevistaDevolucao.plusDays(diasExtras);
    }

    public double getMulta() {
        return multa;
    }
}