package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Opcion;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {

    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola() {
    }

    public static void mostrarCabecera(String mensaje) {
        String subrayado = String.format(String.format("%%0%dd", mensaje.length()), 0).replace('0', '-');
        System.out.println(mensaje);
        System.out.println(subrayado);
    }

    public static void mostrarMenu() {
        mostrarCabecera("Taller mecánico");
        for (Opcion opcion : Opcion.values())
            System.out.println(opcion);
    }

    public static Opcion elegirOpcion() {
        Opcion opcion = null;
        do {
            try {
                opcion = Opcion.get(leerEntero("\nElige una opción: "));
            } catch (IllegalAccessError e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion == null);
        return opcion;
    }

    private static float leerReal(String mensaje) {
        System.out.print(mensaje);
        return Entrada.real();
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Entrada.entero();
    }

    private static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    private static LocalDate leerFecha(String mensaje) {
       LocalDate fecha;
       DateTimeFormatter fechaFormato = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
       mensaje = String.format("%s (%s): ", mensaje, CADENA_FORMATO_FECHA);
       try {
           fecha = LocalDate.parse(leerCadena(mensaje), fechaFormato);
       } catch (DateTimeParseException e) {
           fecha = null;
       }
       return fecha;
    }
    public static Cliente leerCliente() {
        String nombre = leerCadena("Introduce el nombre: ");
        String dni = leerCadena("Introduce el DNI: ");
        String telefono = leerCadena("Introduce el teléfono: ");
        return new Cliente(nombre, dni, telefono);
    }

    public static Cliente leerClienteDni() {
        return Cliente.get(leerCadena("Introduce el DNI: "));
    }
    public static String  leerNuevoNombre() {
        return leerCadena("Introduce el nuevo nombre: ");
    }
    public static String  leerNuevoTelefono() {
        return leerCadena("Introduce el nuevo teléfono: ");
    }
    public static Vehiculo leerVehiculo() {
        String marca = leerCadena("Introduce la marca: ");
        String modelo = leerCadena("Introduce el modelo: ");
        String matricula = leerCadena("Introduce la matrícula: ");
        return new Vehiculo(marca, modelo, matricula);
    }

    public static Vehiculo leerVehiculoMatricula() {
        return Vehiculo.get(leerCadena("Introduce la matrícula: "));
    }

    public static Revision leerRevision() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio");
        return new Revision(cliente, vehiculo, fechaInicio);
    }

    public static int leerHoras() {
        return leerEntero("Introduce las horas a añadir: ");
    }

    public static float leerPrecioMaterial() {
        return leerReal("Introduce el precio del material a añadir: ");
    }

    public static LocalDate leerFechaCierre() {
        return leerFecha("Introduce la fecha de cierre");
    }





}
