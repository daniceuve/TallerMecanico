package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Objects;

public class Mecanico extends Trabajo {

    private static final float FACTOR_HORA = 30;
    private static final float FACTOR_PRECIO_MATERIAL = 1.5F;
    private float precioMaterial;

    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente,vehiculo, fechaInicio);
    }

    public Mecanico(Mecanico mecanico) {
        super(mecanico);
        this.precioMaterial = mecanico.precioMaterial;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    @Override
    public float getPrecioEspecifico() {
        return getHoras() * FACTOR_HORA + precioMaterial * FACTOR_PRECIO_MATERIAL;
    }

    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if (precioMaterial <= 0)
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        if (estaCerrado())
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que el trabajo mecánico está cerrado.");
        this.precioMaterial += precioMaterial;
    }

    @Override
    public String toString() {
        String fechaInicioString = getFechaInicio() != null ? getFechaInicio().format(FORMATO_FECHA) : "";
        String fechaFinString = getFechaFin() != null ? getFechaFin().format(FORMATO_FECHA) : "";
        String horasString = String.valueOf(getHoras());
        String precioMaterialString = String.format("%.2f", precioMaterial);
        String precioString = getPrecio() != 0 ? String.format(", %.2f € total", getPrecio()) : "";
        return "Mecánico -> " + getCliente() + " - " + getVehiculo() + " (" + fechaInicioString + " - " + fechaFinString + "): " +
                horasString + " horas, " + precioMaterialString + " € en material" + precioString;
    }
}

