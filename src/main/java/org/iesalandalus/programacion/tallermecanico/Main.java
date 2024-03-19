package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;

public class Main {
    public static void main(String[] args) {
        Modelo modelo = new ModeloCascada();  // Modelo modelo = FabricaModelo.CASCADA.crear(FabricaFuenteDatos.memoria);
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo, vista);
        controlador.comenzar();
    }
}
