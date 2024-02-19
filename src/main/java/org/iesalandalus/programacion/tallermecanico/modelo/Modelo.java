package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Modelo {

    Clientes clientes;
    Vehiculos vehiculos;
    Revisiones revisiones;

    public Modelo(){


    }

    public void comenzar() {
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        revisiones = new Revisiones();
    }
    public void terminar() {
        System.out.println("Terminado");
    }
    public void insertar(Cliente cliente) {

    }
    public void insertar(Vehiculo vehiculo) {

    }
    public void insertar(Revision revision) {

    }

    public Cliente buscar(Cliente cliente) throws OperationNotSupportedException{
        if (clientes.buscar(cliente) == null)
            throw new OperationNotSupportedException("buscar cliente");
        return new Cliente(cliente);
    }
    public Vehiculo buscar(Vehiculo vehiculo) throws OperationNotSupportedException{
        if (vehiculos.buscar(vehiculo) == null)
            throw new OperationNotSupportedException("buscar vehiculo");
        return new Vehiculo(vehiculo.marca(), vehiculo.modelo(), vehiculo.matricula());
    }
    public Revision buscar(Revision revision) throws OperationNotSupportedException{
        if (revisiones.buscar(revision) == null)
            throw new OperationNotSupportedException("buscar revision");
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

    }
    public List<Vehiculo> getVehiculo() {

    }
    public List<Revision> getRevision() {

    }
    public List<Revision> getRevisiones(Cliente cliente) {

    }
    public List<Revision> getRevisiones(Vehiculo vehiculo) {

    }

}
