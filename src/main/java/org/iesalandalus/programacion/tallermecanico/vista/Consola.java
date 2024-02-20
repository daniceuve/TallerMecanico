package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Consola {

    private static final String CADENA_FORMATO_FECHA = "dd/MM/YYYY";

    private Consola() {
    }

    public static void mostrarCabecera(String mensaje) {
        String subrayado = String.format(String.format("%%0%dd", mensaje.length()), 0).replace('0', '-');
        System.out.println(mensaje);
        System.out.println(subrayado);
    }

    public static void mostrarMenu() {
        System.out.println("Cabecera test");
        for (Opcion opcion : Opcion.values())
            System.out.println(opcion);
    }

    public static Opcion elegirOpcion() throws OperationNotSupportedException {
        int numeroOpcion;
        do {
            numeroOpcion = leerEntero("Introduce una opción: ");
        } while (!Opcion.esValida(numeroOpcion));
        return Opcion.get(numeroOpcion);
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
        System.out.print(mensaje);
        DateTimeFormatter fechaFormato = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        LocalDate fecha;
        do {

            System.out.println("Formato válido de fecha " + CADENA_FORMATO_FECHA);
            System.out.print("Día: ");
            int dia = Entrada.entero();
            System.out.print("Día: ");
            int mes = Entrada.entero();
            System.out.print("Día: ");
            int anio = Entrada.entero();
            fecha = LocalDate.of(anio, mes, dia);

        } while (fechaFormato.)





    }





}
