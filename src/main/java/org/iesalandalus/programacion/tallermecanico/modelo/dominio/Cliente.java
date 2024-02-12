package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {

    private static final String ER_NOMBRE = "([A-ZÁÉÍÓÚ][a-zéáíóú]+)((\\s[A-ZÁÉÍÓÚ][a-zéáíóú]+)?)+";
    private static final String ER_DNI = "^\\d{8}[A-HJ-NP-TV-Z]$";
    private static final String ER_TELEFONO = "\\d{9}";
    private String nombre;
    private String dni;
    private String telefono;


    public Cliente(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente) {
        this(cliente.nombre, cliente.dni, cliente.telefono);
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo.");
        Pattern patron = Pattern.compile(ER_NOMBRE);
        Matcher matcher = patron.matcher(nombre);
        if (!matcher.matches())
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        Objects.requireNonNull(dni, "El DNI no puede ser nulo.");
        Pattern patron = Pattern.compile(ER_DNI);
        Matcher matcher = patron.matcher(dni);
        if (!matcher.matches())
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        if (!comprobarLetraDni(dni))
            throw new IllegalArgumentException("La letra del DNI no es correcta.");
        this.dni = dni;

    }
    private boolean comprobarLetraDni(String dni) {
        String[] letraDNI = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        int numeroDni = Integer.parseInt(dni.substring(0, 8));
        int calculoLetra = (numeroDni % 23);
        return letraDNI[calculoLetra].equals(dni.substring(8));
    }

    public static Cliente get(String dni) {
        Objects.requireNonNull(dni, "El DNI no puede ser nulo.");
        Pattern patron = Pattern.compile(ER_DNI);
        Matcher matcher = patron.matcher(dni);
        if (!matcher.matches())
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        return new Cliente("", dni, "2");
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono, "El teléfono no puede ser nulo.");
        Pattern patron = Pattern.compile(ER_TELEFONO);
        Matcher matcher = patron.matcher(telefono);
        if (!matcher.matches())
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", this.nombre, this.dni, this.telefono);
    }
}
