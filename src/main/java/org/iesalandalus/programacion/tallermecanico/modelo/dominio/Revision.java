package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import com.sun.jdi.ObjectReference;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Revision {

    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_DIA = 10;
    private static final float PRECIO_MATERIAL = 1;
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;
    private Cliente cliente;
    private Vehiculo vehiculo;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
    }
    public Revision(Revision revision) {
        Objects.requireNonNull(revision, "No puede ser una revisión nula.");
        this.cliente = revision.cliente;
        this.vehiculo = revision.vehiculo;
        this.fechaInicio = revision.fechaInicio;
    }

    public Cliente getCliente() {
        return cliente;
    }
    private void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo");
        this.cliente = cliente;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    private void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    private void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a hoy.");
        this.fechaInicio = fechaInicio;
    }
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    private void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (fechaFin.isAfter(fechaInicio) || fechaFin.equals(fechaInicio))
            throw new IllegalArgumentException("La fecha fin no puede ser menor o igual a la fecha inicio.");
        if (fechaFin.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("La fecha de fin no puede ser posterior a hoy.");
        this.fechaFin = fechaFin;
    }
    public int getHoras() {
        return horas;
    }
    public void anadirHoras(int horas) {

    }
    public float getPrecioMaterial() {
        return precioMaterial;
    }
    public void anadirPrecioMaterial(float precioMaterial) {

    }
    public boolean estaCerrada() {

        return false;
    }
    public void cerrar(LocalDate fechaFin) {

    }
    public float getPrecio() {

        return 0;
    }
    private float getDias() {

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Revision revision)) return false;
        return Objects.equals(fechaInicio, revision.fechaInicio) && Objects.equals(cliente, revision.cliente) && Objects.equals(vehiculo, revision.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, cliente, vehiculo);
    }

    @Override
    public String toString() {
        return String.format("Revision[fechaInicio=%s, horas=%s, precioMaterial=%s, cliente=%s, vehiculo=%s]", this.fechaInicio, this.horas, this.precioMaterial, this.cliente, this.vehiculo);
    }
}
