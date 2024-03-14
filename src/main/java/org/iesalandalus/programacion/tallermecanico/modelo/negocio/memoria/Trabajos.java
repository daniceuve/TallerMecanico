package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Mecanico;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.*;

public class Trabajos implements ITrabajos {

    private final List<Trabajo> listaTrabajos;

    public Trabajos() {
        listaTrabajos = new ArrayList<>();
    }

    @Override
    public List<Trabajo> get() {
        return new ArrayList<>(listaTrabajos);
    }

    @Override
    public List<Trabajo> get(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        List<Trabajo> listaTrabajosCliente = new ArrayList<>();
        for (Trabajo trabajo : listaTrabajos) {
            if (trabajo.getCliente().equals(cliente))
                listaTrabajosCliente.add(trabajo);
        }
        return listaTrabajosCliente;
    }
    @Override
    public List<Trabajo> get(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        List<Trabajo> listaTrabajosVehiculo = new ArrayList<>();
        for (Trabajo trabajo : listaTrabajos) {
            if (trabajo.getVehiculo().equals(vehiculo))
                listaTrabajosVehiculo.add(trabajo);
        }
        return listaTrabajosVehiculo;
    }
    @Override
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo,"No se puede insertar un trabajo nulo.");
        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        listaTrabajos.add(trabajo);
    }
    private void comprobarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws OperationNotSupportedException{
        for (Trabajo trabajo : listaTrabajos) {
            if (!trabajo.estaCerrado() && trabajo.getCliente().equals(cliente))
                throw new OperationNotSupportedException("El cliente tiene otra revisión en curso.");
            else if (!trabajo.estaCerrado() && trabajo.getVehiculo().equals(vehiculo))
                throw new OperationNotSupportedException("El vehículo está actualmente en revisión.");
            else if (trabajo.estaCerrado() && trabajo.getCliente().equals(cliente) && !fechaRevision.isAfter(trabajo.getFechaFin()))
                throw new OperationNotSupportedException("El cliente tiene una revisión posterior.");
            else if (trabajo.estaCerrado() && trabajo.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(trabajo.getFechaFin()))
                throw new OperationNotSupportedException("El vehículo tiene una revisión posterior.");
        }
    }

    private Trabajo getRevision(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo operar sobre un trabajo nulo.");
        int index = listaTrabajos.indexOf(trabajo);
        if (index != -1)
            return listaTrabajos.get(index);
        else throw new OperationNotSupportedException("No existe ningún trabajo igual.");
    }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo operar sobre un trabajo nulo.");
        getRevision(trabajo).anadirHoras(horas);
    }

    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo operar sobre un trabajo nulo.");
        trabajo.anadirPrecioMaterial(precioMaterial);
    }

    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo operar sobre un trabajo nulo.");
        Objects.requireNonNull(fechaFin, "cerrar");
        getRevision(trabajo).cerrar(fechaFin);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "No se puede buscar un trabajo nulo.");
        int index = listaTrabajos.indexOf(trabajo);
        return index != -1 ? listaTrabajos.get(index) : null;
    }

    @Override
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
       Objects.requireNonNull(trabajo, "No se puede borrar un trabajo nulo.");
        int index = listaTrabajos.indexOf(trabajo);
        if (index != -1)
            listaTrabajos.remove(trabajo);
        else throw new OperationNotSupportedException("No existe ninguna trabajo igual.");
    }

}