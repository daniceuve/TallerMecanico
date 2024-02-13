package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes {

    private final List<Cliente> listaClientes;

    public Clientes() {
        listaClientes = new ArrayList<>();
    }
    public List<Cliente> get(){

        List<Cliente> listaClientesGet= new ArrayList<>();
        for (Cliente listaCliente : listaClientes) {
            listaClientesGet.add(new Cliente(listaCliente));
        }

        return listaClientesGet;
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException{
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        if (listaClientes.contains(cliente))
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        listaClientes.add(cliente);
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) {
        boolean modificado = false;
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        if (listaClientes.contains(cliente)) {
            if (!nombre.isBlank()) {
                cliente.setNombre(nombre);
                modificado = true;
            }
            if (!telefono.isBlank()) {
                cliente.setNombre(telefono);
                modificado = true;
            }

        }
        return modificado;
    }

    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        if (listaClientes.contains(cliente))
            return cliente;
        else return null;
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException{
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        if (listaClientes.contains(cliente))
            listaClientes.remove(cliente);
        else throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
    }



}
