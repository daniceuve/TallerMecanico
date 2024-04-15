package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

public enum TipoTrabajo {
    MECANICO("Mecánico"),
    REVISION("Revisión");

    private final String nombre;
    private TipoTrabajo(String nombre) {
        this.nombre = nombre;
    }

    static TipoTrabajo get(Trabajo trabajo) {
        if (trabajo instanceof Mecanico)
            return MECANICO;
        else return REVISION;
    }

}
