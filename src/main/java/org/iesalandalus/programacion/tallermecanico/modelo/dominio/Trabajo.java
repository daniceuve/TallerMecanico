package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public abstract class Trabajo {
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final float FACTOR_DIA = 10F;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;

    protected Trabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
    }
    protected Trabajo(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        this.cliente = new Cliente(trabajo.cliente);
        this.vehiculo = trabajo.vehiculo;
        this.fechaInicio = trabajo.fechaInicio;
        this.fechaFin = trabajo.fechaFin;
        this.horas = trabajo.horas;
    }

    public static Trabajo copiar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        if (trabajo instanceof Revision)
            return new Revision((Revision) trabajo);
        else
            return new Mecanico((Mecanico) trabajo);
    }

    public static Trabajo get(Vehiculo vehiculo) {
        return new Revision(new Cliente("Dani", "06562719Z", "123456789"), vehiculo, LocalDate.now());
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    private void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
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
        if (estaCerrado())
            throw new OperationNotSupportedException("No se puede añadir horas, ya que el trabajo está cerrado.");
        this.horas += horas;
    }

    public boolean estaCerrado() {
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) throws OperationNotSupportedException {
        if (this.fechaFin != null)
            throw new OperationNotSupportedException("El trabajo ya está cerrado.");
        setFechaFin(fechaFin);
    }

    public float getPrecio() {
       return getPrecioFijo() + getPrecioEspecifico();
    }

    private float getPrecioFijo() {
        return getDias() * FACTOR_DIA;
    }

    private float getDias() {
        return fechaFin != null ? ChronoUnit.DAYS.between(fechaInicio, fechaFin) : 0;
    }

    public abstract float getPrecioEspecifico();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trabajo trabajo)) return false;
        return Objects.equals(cliente, trabajo.cliente) && Objects.equals(vehiculo, trabajo.vehiculo) && Objects.equals(fechaInicio, trabajo.fechaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, vehiculo, fechaInicio);
    }
}
