package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModeloCascada implements Modelo {

    private IClientes clientes;
    private IVehiculos vehiculos;
    private ITrabajos trabajos;

    public ModeloCascada(FabricaFuenteDatos fabricaFuenteDatos) {
        IFuenteDatos fuenteDatos = fabricaFuenteDatos.crear();
        clientes = fuenteDatos.crearClientes();
        vehiculos = fuenteDatos.crearVehiculos();
        trabajos = fuenteDatos.crearTrabajos();
    }

    @Override
    public void comenzar() {
        System.out.println("Comenzado");
    }

    @Override
    public void terminar() {
        System.out.println("Terminado");
    }

    @Override
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        clientes.insertar(new Cliente(cliente));
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        vehiculos.insertar(vehiculo);
    }

    @Override
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        if (trabajo instanceof Mecanico)
            trabajos.insertar(new Mecanico(buscar(trabajo.getCliente()), buscar(trabajo.getVehiculo()), trabajo.getFechaInicio()));
        if (trabajo instanceof Revision)
            trabajos.insertar(new Revision(buscar(trabajo.getCliente()), buscar(trabajo.getVehiculo()), trabajo.getFechaInicio()));
    }

    @Override
    public Cliente buscar(Cliente cliente) {
        return new Cliente(clientes.buscar(cliente));
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        trabajo = trabajos.buscar(trabajo);
        if (trabajo instanceof Mecanico mecanico)
            trabajo = new Mecanico(mecanico);
        if (trabajo instanceof Revision revision)
            trabajo = new Revision(revision);
        return trabajo;
    }

    @Override
    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        return clientes.modificar(cliente, nombre, telefono);
    }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException {
        trabajos.anadirHoras(trabajo, horas);
    }

    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {
        trabajos.anadirPrecioMaterial(trabajo, precioMaterial);
    }

    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        trabajos.cerrar(trabajo, fechaFin);
    }

    @Override
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        List<Trabajo> listaRevisionesClienteBorrar = new ArrayList<>(trabajos.get(cliente));
        for (Trabajo trabajo : listaRevisionesClienteBorrar) {
            trabajos.borrar(trabajo);
        }
        clientes.borrar(cliente);
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        List<Trabajo> listaRevisionesVehiculoBorrar = new ArrayList<>(trabajos.get(vehiculo));
        for (Trabajo trabajo : listaRevisionesVehiculoBorrar) {
            trabajos.borrar(trabajo);
        }
        vehiculos.borrar(vehiculo);
    }

    @Override
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "La revisión no puede ser nula.");
        trabajos.borrar(trabajo);
    }

    @Override
    public List<Cliente> getClientes() {

        List<Cliente> listaClientes = new ArrayList<>();
        for (Cliente recorrer : clientes.get())
            listaClientes.add(new Cliente(recorrer));

        return listaClientes;
    }

    @Override
    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos.get());
    }

    @Override
    public List<Trabajo> getTrabajos() {
        List<Trabajo> listaTrabajos = new ArrayList<>();
        for (Trabajo recorrer : trabajos.get()) {
            if (recorrer instanceof Mecanico mecanico)
                listaTrabajos.add(new Mecanico(mecanico));
            if (recorrer instanceof Revision revision)
                listaTrabajos.add(new Revision(revision));
        }
        return listaTrabajos;
    }

    @Override
    public List<Trabajo> getTrabajos(Cliente cliente) {
        List<Trabajo> listaTrabajosCliente = new ArrayList<>();
        for (Trabajo recorrer : trabajos.get(cliente)) {
            if (recorrer instanceof Mecanico mecanico)
                listaTrabajosCliente.add(new Mecanico(mecanico));
            if (recorrer instanceof Revision revision)
                listaTrabajosCliente.add(new Revision(revision));
        }
        return listaTrabajosCliente;
    }

    @Override
    public List<Trabajo> getTrabajos(Vehiculo vehiculo) {
        List<Trabajo> listaTrabajosVehiculo = new ArrayList<>();
        for (Trabajo recorrer : trabajos.get(vehiculo)) {
            if (recorrer instanceof Mecanico mecanico)
                listaTrabajosVehiculo.add(new Mecanico(mecanico));
            if (recorrer instanceof Revision revision)
                listaTrabajosVehiculo.add(new Revision(revision));
        }
        return listaTrabajosVehiculo;
    }

}
