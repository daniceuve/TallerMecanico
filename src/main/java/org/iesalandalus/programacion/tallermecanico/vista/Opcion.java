package org.iesalandalus.programacion.tallermecanico.vista;

import javax.naming.OperationNotSupportedException;
import java.util.HashMap;
import java.util.Map;

public enum Opcion {

    INSERTAR_CLIENTE(1, "Insertar cliente"),
    BUSCAR_CLIENTE(2, "Buscar cliente"),
    BORRAR_CLIENTE(3, "Borrar cliente"),
    LISTAR_CLIENTES(4, "Listar cliente"),
    MODIFICAR_CLIENTE(5, "Modificar cliente"),
    INSERTAR_VEHICULO(6, "Insertar vehículo"),
    BUSCAR_VEHICULO(7, "Buscar vehículo"),
    LISTAR_VEHICULOS(8, "Listar vehículos"),
    INSERTAR_REVISION(9, "Insertar revisión"),
    BUSCAR_REVISION(10, "Buscar revisión"),
    BORRAR_REVISION(11, "Borrar revisión"),
    LISTAR_REVISIONES(12, "Listar revisión"),
    LISTAR_REVISIONES_CLIENTE(13, "Listar revisión de cliente"),
    LISTAR_REVISION_VEHICULO(14, "Buscar revisión de vehículo"),
    ANADIR_HORAS_REVISION(15, "Añadir horas de revisión"),
    ANADIR_PRECIO_MATERIAL_REVISION(16, "Añadir precio material de revisión"),
    CERRAR_REVISION(17, "Cerrar revisión"),
    SALIR(18, "Salir");

    private final int numeroOpcion;
    private final String mensaje;
    private static final Map<Integer, Opcion> opciones = new HashMap<>();

    static {
        for (Opcion opcion : values())
            opciones.put(opcion.numeroOpcion, opcion);
    }

    private Opcion(int numeroOpcion, String mensaje) {
        this.numeroOpcion = numeroOpcion;
        this.mensaje = mensaje;
    }

    public static boolean esValida(int numeroOpcion) {
        return opciones.containsKey(numeroOpcion);
    }

    public static Opcion get(int numeroOpcion) throws OperationNotSupportedException {
        if (!esValida(numeroOpcion))
            throw new OperationNotSupportedException("Opción no válida.");
        else return opciones.get(numeroOpcion);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.numeroOpcion, this.mensaje);
    }
}
