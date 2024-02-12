package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Revision {

    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_DIA = 10;
    private static final float PRECIO_MATERIAL = 1;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {

    }
    public Revision(Revision revision) {
        Objects.requireNonNull(revision, "No puede ser una revisión nula.");

    }

    public Cliente getCliente() {

    }
    private void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo");



    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    private void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    private void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    public int getHoras() {
        return horas;
    }
    public void setHoras(int horas) {
        this.horas = horas;
    }
    public float getPrecioMaterial() {
        return precioMaterial;
    }
    public void setPrecioMaterial(float precioMaterial) {
        this.precioMaterial = precioMaterial;
    }



}
