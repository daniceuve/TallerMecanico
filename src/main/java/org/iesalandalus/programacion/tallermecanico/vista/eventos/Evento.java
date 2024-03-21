package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.HashMap;
import java.util.Map;

public enum Evento {

    INSERTAR_CLIENTE(1, "Insertar cliente"),
    BUSCAR_CLIENTE(2, "Buscar cliente"),
    BORRAR_CLIENTE(3, "Borrar cliente"),
    LISTAR_CLIENTES(4, "Listar cliente"),
    MODIFICAR_CLIENTE(5, "Modificar cliente"),
    INSERTAR_VEHICULO(6, "Insertar vehículo"),
    BUSCAR_VEHICULO(7, "Buscar vehículo"),
    BORRAR_VEHICULO(8, "Borrar vehículo"),
    LISTAR_VEHICULOS(9, "Listar vehículos"),
    INSERTAR_REVISION(10, "Insertar revisión"),
    BUSCAR_REVISION(11, "Buscar revisión"),
    BORRAR_REVISION(12, "Borrar revisión"),
    LISTAR_REVISIONES(13, "Listar revisión"),
    LISTAR_REVISIONES_CLIENTE(14, "Listar revisión de cliente"),
    LISTAR_REVISION_VEHICULO(15, "Buscar revisión de vehículo"),
    ANADIR_HORAS_REVISION(16, "Añadir horas de revisión"),
    ANADIR_PRECIO_MATERIAL_REVISION(17, "Añadir precio material de revisión"),
    CERRAR_REVISION(18, "Cerrar revisión"),
    SALIR(19, "Salir");

    private final int numeroOpcion;
    private final String mensaje;
    private static final Map<Integer, Evento> opciones = new HashMap<>();

    static {
        for (Evento opcion : values())
            opciones.put(opcion.numeroOpcion, opcion);
    }

    private Evento(int numeroOpcion, String mensaje) {
        this.numeroOpcion = numeroOpcion;
        this.mensaje = mensaje;
    }

    public static boolean esValida(int numeroOpcion) {
        return opciones.containsKey(numeroOpcion);
    }

    public static Evento get(int numeroOpcion) {
        if (!esValida(numeroOpcion))
            throw new IllegalArgumentException("Opción no válida.");
        else return opciones.get(numeroOpcion);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.numeroOpcion, this.mensaje);
    }
}
