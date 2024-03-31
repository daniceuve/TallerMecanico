package org.iesalandalus.programacion.tallermecanico.vista.texto;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class VistaTexto implements Vista {

    public static final String DNI_T = "12345678A";
    public static final String MATRICULA_T = "1234CVB";
    private final GestorEventos gestorEventos = new GestorEventos(Evento.values());

    @Override
    public GestorEventos getGestorEventos() {
        return gestorEventos;
    }

    @Override
    public void comenzar() {
        Evento evento;
        do {
            Consola.mostrarMenu();
            evento = Consola.elegirOpcion();
            Consola.mostrarCabecera(evento.name());
            ejecutar(evento);
        } while (evento != Evento.SALIR);
        terminar();
    }

    private void ejecutar(Evento opcion) {
        getGestorEventos().notificar(opcion);
    }

    @Override
    public void terminar() {
        System.out.println("adios");
    }

    @Override
    public Cliente leerCliente() {
        Cliente cliente = null;
        boolean clienteCorrecto = false;
        do {
            try {
                cliente = new Cliente(Consola.leerCadena("Nombre del cliente: "), Consola.leerCadena("Dime el dni del cliente: "), Consola.leerCadena("Dime el teléfono del cliente: "));
                clienteCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!clienteCorrecto);
        return cliente;
    }

    @Override
    public Cliente leerClienteDni() {
        Cliente cliente = null;
        boolean clienteCorrecto = false;
        do {
            try {
                cliente = new Cliente(Cliente.get(Consola.leerCadena("DNI del cliente: ")));
                clienteCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!clienteCorrecto);
        return cliente;
    }

    @Override
    public String leerNuevoNombre() {
        String nombre;
        boolean nombreCorrecto = false;
        do {
            nombre = Consola.leerCadena("Nuevo nombre del cliente: ");
            if (!nombre.isBlank()) {
                try {
                    new Cliente(nombre, VistaTexto.DNI_T, "123456789");
                    nombreCorrecto = true;
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                nombreCorrecto = true;
            }
        } while (!nombreCorrecto);
        return nombre;
    }

    @Override
    public String leerNuevoTelefono() {
        String telefono;
        boolean telefonoCorrecto = false;
        do {
            telefono = Consola.leerCadena("Teléfono del cliente: ");
            if (!telefono.isBlank()) {
                try {
                    new Cliente("Dani", VistaTexto.DNI_T, telefono);
                    telefonoCorrecto = true;
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                telefonoCorrecto = true;
            }

        } while (!telefonoCorrecto);
        return telefono;
    }

    @Override
    public Vehiculo leerVehiculo() {
        Vehiculo vehiculo = null;
        boolean vehiculoCorrecto = false;

        do {
            try {
                vehiculo = new Vehiculo(Consola.leerCadena("Marca del vehículo: "), Consola.leerCadena("DModelo del vehículo: "), Consola.leerCadena("Matrícula del vehículo: "));
                vehiculoCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!vehiculoCorrecto);
        return vehiculo;
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        Vehiculo vehiculo = null;
        boolean vehiculoCorrecto = false;
        do {
            try {
                vehiculo = Vehiculo.get(Consola.leerCadena("Matrícula del vehículo: "));
                vehiculoCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!vehiculoCorrecto);
        return vehiculo;
    }

    @Override
    public Trabajo leerRevision() {
        Revision revision = null;
        boolean trabajoCorrecto = false;

        do {
            try {
                revision = new Revision(leerClienteDni(), leerVehiculoMatricula(), Consola.leerFecha("Fecha de inicio del trabajo: "));
                trabajoCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!trabajoCorrecto);
        return revision;
    }

    @Override
    public Trabajo leerMecanico() {
        Trabajo mecanico = null;
        boolean trabajoCorrecto = false;

        do {
            try {
                mecanico = new Mecanico(leerClienteDni(), leerVehiculoMatricula(), Consola.leerFecha("Fecha de inicio del trabajo: "));
                trabajoCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!trabajoCorrecto);
        return mecanico;
    }

    @Override
    public Trabajo leerTrabajoVehiculo() {
        return Trabajo.get(leerVehiculo());
    }

    @Override
    public int leerHoras() {
        int horas;
        boolean horasCorrectas = false;
        do {
            horas = Consola.leerEntero("Horas a añadir: ");
            try {
                Revision revision = new Revision(Cliente.get(VistaTexto.DNI_T), Vehiculo.get(VistaTexto.MATRICULA_T), LocalDate.now());
                revision.anadirHoras(horas);
                horasCorrectas = true;
            } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
                System.out.println(e.getMessage());
            }
        } while (!horasCorrectas);
        return horas;
    }

    @Override
    public float leerPrecioMaterial() {
        float precioMaterial;
        boolean precioCorrecto = false;
        do {
            precioMaterial = Consola.leerReal("Precio a añadir: ");
            try {
                Mecanico mecanico = new Mecanico(Cliente.get(VistaTexto.DNI_T), Vehiculo.get(VistaTexto.MATRICULA_T), LocalDate.now());
                mecanico.anadirPrecioMaterial(precioMaterial);
                precioCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
                System.out.println(e.getMessage());
            }
        } while (!precioCorrecto);
        return precioMaterial;
    }

    @Override
    public LocalDate leerFechaCierre() {
        LocalDate fechaCierre;
        boolean fechaCierreCorrecta = false;
        do {
            fechaCierre = Consola.leerFecha("Fecha de cierre: ");
            try {
                Revision revision = new Revision(Cliente.get(VistaTexto.DNI_T), Vehiculo.get(VistaTexto.MATRICULA_T), LocalDate.of(2003, 10, 1));
                revision.cerrar(fechaCierre);
                fechaCierreCorrecta = true;
            } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
                System.out.println(e.getMessage());
            }
        } while (!fechaCierreCorrecta);
        return fechaCierre;
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) {
        Consola.mostrarCabecera(evento.name());
        if (exito)
            System.out.println(texto);
        else
            System.out.println("Error: " + texto);
    }

    @Override
    public void mostrarCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        System.out.println(cliente);
    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehiculo no puede ser nulo.");
        System.out.println(vehiculo);
    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El vehiculo no puede ser nulo.");
        System.out.println(trabajo);
    }

    @Override
    public void mostrarClientes(List<Cliente> clientes) {
        Objects.requireNonNull(clientes, "Los clientes no pueden ser nulos.");
        System.out.println(clientes);
    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {
        Objects.requireNonNull(vehiculos, "Los vehículos no pueden ser nulos.");
        System.out.println(vehiculos);
    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {
        Objects.requireNonNull(trabajos, "Los trabajos no pueden ser nulos.");
        System.out.println(trabajos);
    }
}
