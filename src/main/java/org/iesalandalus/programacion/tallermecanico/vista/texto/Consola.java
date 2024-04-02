package org.iesalandalus.programacion.tallermecanico.vista.texto;


import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {

    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola() {
    }

    static void mostrarCabecera(String mensaje) {
        String subrayado = String.format(String.format("%%0%dd", mensaje.length()), 0).replace('0', '-');
        System.out.println(mensaje);
        System.out.println(subrayado);
    }

    static void mostrarMenu() {
        mostrarCabecera("Taller mecánico");
        for (Evento opcion : Evento.values())
            System.out.printf("%d.- %s%n", opcion.getCodigo(), opcion);
    }

    static Evento elegirOpcion() {
        Evento opcion = null;
        do {
            try {
                opcion = Evento.get(leerEntero("\nElige una opción: "));
            } catch (IllegalAccessError e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion == null);
        return opcion;
    }

    static float leerReal(String mensaje) {
        System.out.print(mensaje);
        return Entrada.real();
    }

    static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Entrada.entero();
    }

    static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    static LocalDate leerFecha(String mensaje) {
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





}
