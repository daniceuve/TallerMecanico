package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;


import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos implements IVehiculos {

    private final List<Vehiculo> listaVehiculos;

    public Vehiculos() {
        listaVehiculos = new ArrayList<>();
    }
    @Override
    public List<Vehiculo> get() {
        return new ArrayList<>(listaVehiculos);
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo,"No se puede insertar un vehículo nulo.");
        if (listaVehiculos.contains(vehiculo)) {
            throw new OperationNotSupportedException("Ya existe un vehículo con esa matrícula.");
        }
        listaVehiculos.add(vehiculo);
    }
    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehículo nulo.");
        int index = listaVehiculos.indexOf(vehiculo);
        if (index != -1)
            return listaVehiculos.get(index);
        else return null;
    }
    @Override
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehículo nulo.");
        if (listaVehiculos.contains(vehiculo)) {
            listaVehiculos.remove(vehiculo);
        } else {
            throw new OperationNotSupportedException("No existe ningún vehículo con esa matrícula.");
        }
    }


}
