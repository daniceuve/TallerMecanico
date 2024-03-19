package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;

public enum FabricaModelo {

    CASCADA {

        public Modelo crear(FabricaFuenteDatos fabricaFuenteDatos) {
            return new ModeloCascada;
        }

    };
    public abstract Modelo crear(FabricaFuenteDatos  fabricaFuenteDatos);

}
