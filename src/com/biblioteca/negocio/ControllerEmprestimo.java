package com.biblioteca.negocio;

import com.biblioteca.basicos.Emprestimo;
import com.biblioteca.basicos.Usuario;
import com.biblioteca.basicos.Acervo;
import java.util.ArrayList;
import java.util.List;

public class ControllerEmprestimo {

    private List<Emprestimo> listaEmprestimos;

    public ControllerEmprestimo() {
        this.listaEmprestimos = new ArrayList<>();
    }

    public Emprestimo realizarEmprestimo(Usuario usuario, Acervo item) {

        if (!item.isDisponivel()) {
            System.out.println("Item indisponível!");
            return null;
        }

        int prazo = usuario.getPrazoEmprestimo();
        Emprestimo emprestimo = new Emprestimo(usuario, item, prazo);
        listaEmprestimos.add(emprestimo);

        return emprestimo;

    }
}

