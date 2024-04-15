package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Trabajos implements ITrabajos {

    private static final String TRABAJOS = "";
    private static final DateTimeFormatter FORMATO_FECHA = ;
    private static final String RAIZ = "";
    private static final String TRABAJO = "";
    private static final String CLIENTE = "";
    private static final String VEHICULO = "";
    private static final String FECHA_INICIO = "";
    private static final String FECHA_FIN = "";
    private static final String HORAS = "";
    private static final String PRECIO_MATERIAL = "";
    private static final String TIPO = "";
    private static final String REVISION = "";
    private static final String MECANICO = "";
    private final List<Trabajo> listaTrabajos;
    private Map<TipoTrabajo, Integer> estadisticasMensuales;

    private Trabajos() {
        listaTrabajos = new ArrayList<>();
    }

    static ITrabajos getInstancia() {

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
                throw new OperationNotSupportedException("El cliente tiene otro trabajo en curso.");
            else if (!trabajo.estaCerrado() && trabajo.getVehiculo().equals(vehiculo))
                throw new OperationNotSupportedException("El vehículo está actualmente en el taller.");
            else if (trabajo.estaCerrado() && trabajo.getCliente().equals(cliente) && !fechaRevision.isAfter(trabajo.getFechaFin()))
                throw new OperationNotSupportedException("El cliente tiene otro trabajo posterior.");
            else if (trabajo.estaCerrado() && trabajo.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(trabajo.getFechaFin()))
                throw new OperationNotSupportedException("El vehículo tiene otro trabajo posterior.");
        }
    }
    @Override
    public Map<TipoTrabajo, Integer> getEstadisticasMensuales(LocalDate mes) {

        int estadisticaMecanico = 0;
        int estadisticaRevision = 0;
        for (Trabajo trabajo : get()) {
            if (trabajo.getFechaInicio().getMonth() == mes.getMonth() && trabajo.getFechaInicio().getYear() == mes.getYear()) {
                if (trabajo instanceof Mecanico)
                    estadisticaMecanico++;
                if (trabajo instanceof Revision)
                    estadisticaRevision++;
            }
        }
        estadisticasMensuales.put(TipoTrabajo.REVISION, estadisticaRevision);
        estadisticasMensuales.put(TipoTrabajo.MECANICO, estadisticaMecanico);

        return estadisticasMensuales;
    }

    private Map<TipoTrabajo, Integer> inicializarEstadisticas() {

    }

    private Trabajo getTrabajoAbierto(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No puedo operar sobre un vehiculo nulo.");
        Trabajo trabajo = null;
        for (Trabajo trabajo1 : listaTrabajos)
            if (trabajo1.getVehiculo().equals(vehiculo) && (!trabajo1.estaCerrado()))
                    trabajo = trabajo1;
        if (trabajo == null)
            throw new OperationNotSupportedException("No existe ningún trabajo abierto para dicho vehículo.");
        else return trabajo;
    }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo añadir horas a un trabajo nulo.");
        getTrabajoAbierto(trabajo.getVehiculo()).anadirHoras(horas);
    }

    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo añadir precio del material a un trabajo nulo.");
        Mecanico mecanico;
        Revision revision;
        if (trabajo instanceof Mecanico) {
            mecanico = (Mecanico) getTrabajoAbierto(trabajo.getVehiculo());
            mecanico.anadirPrecioMaterial(precioMaterial);
        } else if (trabajo instanceof Revision) {
            revision = (Revision) getTrabajoAbierto(trabajo.getVehiculo());
            throw new OperationNotSupportedException("No se puede añadir precio al material para este tipo de trabajos.");
        }

    }

    private Element getElemento(Document documentoXml, Trabajo trabajo) {
        Element trabajos = documentoXml.createElement(TRABAJO);
        trabajos.setAttribute(CLIENTE, trabajo.getCliente().getDni());
        trabajos.setAttribute(VEHICULO, trabajo.getVehiculo().matricula());
        trabajos.setAttribute(FECHA_INICIO, trabajo.getFechaInicio().format(FORMATO_FECHA));
        if (trabajo.getFechaFin() != null)
            trabajos.setAttribute(FECHA_FIN, trabajo.getFechaInicio().format(FORMATO_FECHA));
        if (trabajo.getHoras() != 0)
            trabajos.setAttribute(TIPO, REVISION);
        if (trabajo instanceof  Revision)
            trabajos.setAttribute(TIPO, REVISION);
        else if (trabajo instanceof Mecanico mecanico) {
            trabajos.setAttribute(TIPO, MECANICO);
            if (mecanico.getPrecioMaterial() != 0)
                trabajos.setAttribute(PRECIO_MATERIAL, String.format(Locale.US, "%f", mecanico.getPrecioMaterial()));
        }
        return trabajos;
    }

    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo cerrar un trabajo nulo.");
        getTrabajoAbierto(trabajo.getVehiculo()).cerrar(fechaFin);
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
        else throw new OperationNotSupportedException("No existe ningún trabajo igual.");
    }

}