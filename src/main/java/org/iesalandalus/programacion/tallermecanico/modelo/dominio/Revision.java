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
        Objects.requireNonNull(revision, "La revisión no puede ser nula.");
    }

    @Override
    public float getPrecioEspecifico() {
        return 0;
    }


    @Override
    public String toString() {
        String fechaInicioString = fechaInicio != null ? fechaInicio.format(Revision.FORMATO_FECHA) : "";
        String fechaFinString = fechaFin != null ? fechaFin.format(Revision.FORMATO_FECHA) : "";
        String horasString = String.valueOf(horas);
        String precioMaterialString = String.format("%.2f", getPrecio());
        String precioString = getPrecio() != 0.0 ? String.format(", %.2f € total", getPrecio()) : "";
        return cliente + " - " + vehiculo + ": (" + fechaInicioString + " - " + fechaFinString + "), " +
                horasString + " horas, " + precioMaterialString + " € en material" + precioString;
    }
}

