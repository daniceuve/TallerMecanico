package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public abstract class Trabajo {
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final float PRECIO_DIA = 10;
    protected Cliente cliente;
    protected Vehiculo vehiculo;
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;
    protected int horas;

    protected Trabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
    }
    protected Trabajo(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "La revisión no puede ser nula.");
        this.cliente = new Cliente(trabajo.cliente);
        this.vehiculo = trabajo.vehiculo;
        this.fechaInicio = trabajo.fechaInicio;
        this.fechaFin = trabajo.fechaFin;
        this.horas = trabajo.horas;
    }

    public static Trabajo copiar(Trabajo trabajo) {
        if (trabajo instanceof Revision)
            return new Revision((Revision) trabajo);
        else if (trabajo instanceof Mecanico)
            return new Mecanico((Mecanico) trabajo);
        else throw new IllegalArgumentException("Error Copiar trabajo.");
    }

    public static Trabajo get(Vehiculo vehiculo) {
        return new Revision(new Cliente("Dani", "06562719Z", "123456789"), vehiculo, LocalDate.now());
    }

    public Cliente getCliente() {
        return cliente;
    }

    protected void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    protected void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    protected void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (fechaFin.isBefore(fechaInicio))
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        if (fechaFin.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        this.fechaFin = fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public void anadirHoras(int horas) throws OperationNotSupportedException {
        if (horas <= 0)
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        if (estaCerrada())
            throw new OperationNotSupportedException("No se puede añadir horas, ya que la revisión está cerrada.");
        this.horas += horas;
    }

    public boolean estaCerrada() {
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) throws OperationNotSupportedException {
        if (this.fechaFin != null)
            throw new OperationNotSupportedException("La revisión ya está cerrada.");
        setFechaFin(fechaFin);
    }

    public float getPrecio() {
        float precioTotalHoras = horas * Revision.PRECIO_HORA;
        float precioTotalDias = getDias() * PRECIO_DIA;
        return (precioTotalHoras + precioTotalDias + precioMaterial * Revision.PRECIO_MATERIAL);
    }

    private float getPrecioFijo() {
        return getDias() * 10;
    }

    private float getDias() {
        return fechaFin != null ? ChronoUnit.DAYS.between(fechaInicio, fechaFin) : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trabajo trabajo)) return false;
        return horas == trabajo.horas && Objects.equals(cliente, trabajo.cliente) && Objects.equals(vehiculo, trabajo.vehiculo) && Objects.equals(fechaInicio, trabajo.fechaInicio) && Objects.equals(fechaFin, trabajo.fechaFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, vehiculo, fechaInicio, fechaFin, horas);
    }
}
