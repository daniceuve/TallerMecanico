package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Objects;

public class Mecanico extends Trabajo {

    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_MATERIAL = 1.5F;
    private float precioMaterial;

    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente,vehiculo, fechaInicio);
    }

    public Mecanico(Mecanico mecanico) {
        super(mecanico);
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if (precioMaterial <= 0)
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        if (estaCerrada())
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que la revisión está cerrada.");
        this.precioMaterial += precioMaterial;
    }

    @Override
    public String toString() {
        String fechaInicioString = fechaInicio != null ? fechaInicio.format(Mecanico.FORMATO_FECHA) : "";
        String fechaFinString = fechaFin != null ? fechaFin.format(Mecanico.FORMATO_FECHA) : "";
        String horasString = String.valueOf(horas);
        String precioMaterialString = String.format("%.2f", precioMaterial);
        String precioString = getPrecio() != 0.0 ? String.format(", %.2f € total", getPrecio()) : "";
        return cliente + " - " + vehiculo + ": (" + fechaInicioString + " - " + fechaFinString + "), " +
                horasString + " horas, " + precioMaterialString + " € en material" + precioString;
    }
}

