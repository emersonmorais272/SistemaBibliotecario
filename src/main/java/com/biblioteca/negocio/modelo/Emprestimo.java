package com.biblioteca.negocio.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo implements Serializable {

    private Usuario usuario;
    private Acervo item;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private double multa;

    public Emprestimo(Usuario usuario, Acervo item, int diasPrazo) {

        this.usuario=usuario;
        this.item=item;
        this.dataEmprestimo=LocalDate.now();
        this.dataPrevistaDevolucao=dataEmprestimo.plusDays(diasPrazo);
        this.multa=0.0;
    }

    public void registrarDevolucao() {

        this.dataDevolucao = LocalDate.now();

        if (dataDevolucao.isAfter(dataPrevistaDevolucao)) {
            long diasAtraso= ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataDevolucao);

            this.multa= usuario.calcularMulta(diasAtraso);
        }

    }

    public void renovar(int diasExtras) {
        this.dataPrevistaDevolucao=dataPrevistaDevolucao.plusDays(diasExtras);
    }

    public double getMulta() {
        return multa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Acervo getItem() {
        return item;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    private boolean multaPerdoada = false;

    public boolean isMultaPerdoada() {
        return multaPerdoada;
    }

    public void setMultaPerdoada(boolean multaPerdoada) {
        this.multaPerdoada = multaPerdoada;
    }
}