package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.*;

public class Revisiones {

    private final List<Revision> listaRevisiones;

    public Revisiones() {
        listaRevisiones = new ArrayList<>();
    }

    public List<Revision> get() {
        return new ArrayList<>(listaRevisiones);
    }

    public List<Revision> get(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        List<Revision> listaRevisionesCliente = new ArrayList<>();
        for (Revision revision : listaRevisiones) {
            if (revision.getCliente().equals(cliente))
                listaRevisionesCliente.add(revision);
        }
        return listaRevisionesCliente;
    }
    public List<Revision> get(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehiculo no puede ser nulo.");
        List<Revision> listaRevisionesVehiculo = new ArrayList<>();
        for (Revision revision : listaRevisiones) {
            if (revision.getVehiculo().equals(vehiculo))
                listaRevisionesVehiculo.add(revision);
        }
        return listaRevisionesVehiculo;
    }
    public void insertar(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision,"No se puede insertar una revisión nula.");
        comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaFin());
        listaRevisiones.add(revision);
    }
    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws OperationNotSupportedException{
        for (Revision revision : listaRevisiones) {
            if (!revision.estaCerrada() && revision.getCliente().equals(cliente))
                throw new OperationNotSupportedException("El cliente tiene otra revisión en curso.");
            if (!revision.estaCerrada() && revision.getVehiculo().equals(vehiculo))
                throw new OperationNotSupportedException("El vehículo está actualmente en revisión.");
            if (revision.estaCerrada() && false)
                throw new OperationNotSupportedException("comprobarRevision estaCerrada y fechaFinAfter");
        }


    }

    private Revision getRevision(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
        int index = listaRevisiones.indexOf(revision);
        if (index != -1)
            return listaRevisiones.get(index);
        else throw new OperationNotSupportedException("No existe ninguna revisión igual.");
    }

    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
        getRevision(revision).anadirHoras(horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
        getRevision(revision).anadirPrecioMaterial(precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
        getRevision(revision).cerrar(fechaFin);
    }

    public Revision buscar(Revision revision) {
        Objects.requireNonNull(revision, "No se puede buscar una revisión nula.");
        int index = listaRevisiones.indexOf(revision);
        return index != -1 ? listaRevisiones.get(index) : null;
    }

    public void borrar(Revision revision) throws OperationNotSupportedException {
       Objects.requireNonNull(revision, "No se puede borrar una revisión nula.");
        int index = listaRevisiones.indexOf(revision);
        if (index != -1)
            listaRevisiones.remove(revision);
        else throw new OperationNotSupportedException("No existe ninguna revisión igual.");
    }

}
/*
static {
    for (Opcion opcion : values()) {
        opciones.put(opcion.numeroOpcion, opcion)
    }
}
 */