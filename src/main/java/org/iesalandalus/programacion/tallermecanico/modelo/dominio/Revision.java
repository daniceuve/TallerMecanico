package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Revision extends Trabajo {

    private static final float FACTOR_HORA = 30;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente,vehiculo, fechaInicio);
    }

    public Revision(Revision revision) {
        super(revision);
    }

    @Override
    public float getPrecioEspecifico() {

        return getHoras() *  35;
    }

    @Override
    public String toString() {
        String fechaInicioString = getFechaInicio() != null ? getFechaInicio().format(Revision.FORMATO_FECHA) : "";
        String fechaFinString = getFechaFin() != null ? getFechaFin().format(Revision.FORMATO_FECHA) : "";
        String horasString = String.valueOf(getHoras());
        String precioString = getPrecio() != 0 ? String.format(", %.2f € total", getPrecio()) : "";
        return "Revisión -> " + getCliente() + " - " + getVehiculo() + " (" + fechaInicioString + " - " + fechaFinString + "): " +
                horasString + " horas" + precioString;
    }
}

