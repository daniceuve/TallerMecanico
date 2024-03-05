package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

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
    public List<Cliente> get() {
        return new ArrayList<>(listaClientes);
    }
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente,"No se puede insertar un cliente nulo.");
        if (listaClientes.contains(cliente)) {
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        listaClientes.add(cliente);
    }
    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        boolean modificado = false;
        Cliente clienteEncontrado = buscar(cliente);
        if (clienteEncontrado == null)
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        int index = listaClientes.indexOf(cliente);
        if (index != -1) {
            if (nombre != null) {
                clienteEncontrado.setNombre(nombre);
                modificado = true;
            }
            if (telefono != null) {
                clienteEncontrado.setTelefono(telefono);
                modificado = true;
            }
            listaClientes.set(index, clienteEncontrado);
        }
        else throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");

        return modificado;
    }
    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        int index = listaClientes.indexOf(cliente);
        if (index != -1)
            return listaClientes.get(index);
        else return null;

    }
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        int index = listaClientes.indexOf(cliente);
        if (index != -1)
            listaClientes.remove(cliente);
        else throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
    }

}
