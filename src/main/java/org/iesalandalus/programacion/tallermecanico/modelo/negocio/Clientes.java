package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import java.util.ArrayList;
import java.util.List;

public class Clientes {

    private final List<Cliente> clientes;

    public Clientes() {
         clientes = new ArrayList<>();
    }
    public List<Cliente> get(){
        return new ArrayList<>(clientes);
    }

    public void insertar(Cliente cliente) {
        if (cliente != null || clientes.indexOf(cliente))
            throw new IllegalArgumentException("No se puede agregar.");
        clientes.add(cliente);
    }





}
