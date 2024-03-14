package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.*;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Modelo {

    IClientes clientes;
    IVehiculos vehiculos;
    ITrabajos revisiones;

    public Modelo() {

    }

    public void comenzar() {
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        revisiones = new Trabajos();
    }

    public void terminar() {
        System.out.println("Terminado");
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Revision revision) throws OperationNotSupportedException {
        revisiones.insertar(new Revision(buscar(revision.getCliente()), buscar(revision.getVehiculo()), revision.getFechaInicio()));
    }

    public Cliente buscar(Cliente cliente) {
        return clientes.buscar(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo);
    }

    public Revision buscar(Revision revision) {
        return revisiones.buscar(revision);
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        return clientes.modificar(cliente, nombre, telefono);
    }

    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException {
        revisiones.anadirHoras(revision, horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws OperationNotSupportedException {
        revisiones.anadirPrecioMaterial(revision, precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException {
        revisiones.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        List<Revision> listaRevisionesClienteBorrar = new ArrayList<>(revisiones.get(cliente));
        for (Revision revision : listaRevisionesClienteBorrar) {
            revisiones.borrar(revision);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        List<Revision> listaRevisionesVehiculoBorrar = new ArrayList<>(revisiones.get(vehiculo));
        for (Revision revision : listaRevisionesVehiculoBorrar) {
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }

    public void borrar(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "La revisión no puede ser nula.");
        revisiones.borrar(revision);
    }

    public List<Cliente> getClientes() {

        List<Cliente> listaClientes = new ArrayList<>();
        for (Cliente recorrer : clientes.get())
            listaClientes.add(new Cliente(recorrer));

        return listaClientes;
    }

    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos.get());
    }

    public List<Revision> getRevisiones() {
        List<Revision> listaRevisiones = new ArrayList<>();
        for (Revision recorrer : revisiones.get())
            listaRevisiones.add(new Revision(recorrer));
        return listaRevisiones;
    }

    public List<Revision> getRevisiones(Cliente cliente) {
        List<Revision> listaRevisionesCliente = new ArrayList<>();
        for (Revision recorrer : revisiones.get(cliente))
            listaRevisionesCliente.add(new Revision(recorrer));
        return listaRevisionesCliente;
    }

    public List<Revision> getRevisiones(Vehiculo vehiculo) {
        List<Revision> listaRevisionesVehiculo = new ArrayList<>();
        for (Revision recorrer : revisiones.get(vehiculo))
            listaRevisionesVehiculo.add(new Revision(recorrer));
        return listaRevisionesVehiculo;
    }

}
