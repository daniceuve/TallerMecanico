package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.texto.VistaTexto;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Controlador implements IControlador {

    private final Modelo modelo;
    private final VistaTexto vista;

    public Controlador(Modelo modelo, VistaTexto vista) {
        Objects.requireNonNull(modelo, "ERROR: El modelo no puede ser nulo.");
        Objects.requireNonNull(vista, "ERROR: La vista no puede ser nula.");
        this.modelo = modelo;
        this.vista = vista;
    }

    @Override
    public void comenzar() {
        vista.comenzar();
    }

    @Override
    public void terminar() {
        vista.terminar();
    }

    @Override
    public void actualizar(Evento evento) {
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");
        String textoF = "";
        boolean salir;
        try {
            switch (evento) {
                case INSERTAR_CLIENTE -> {
                    modelo.insertar(vista.leerCliente());
                    textoF = "Se ha insertado el cliente correctamente.";
                }
                case BUSCAR_CLIENTE -> {
                    vista.mostrarCliente(modelo.buscar(vista.leerCliente()));
                    textoF = "Se ha encontrado el cliente.";
                }
                case BORRAR_CLIENTE -> {
                    modelo.borrar(vista.leerCliente());
                    textoF = "Se ha borrado el cliente.";
                }
                case LISTAR_CLIENTES -> vista.mostrarClientes(modelo.getClientes());

                case MODIFICAR_CLIENTE -> {
                    boolean modificado = modelo.modificar(vista.leerCliente(), vista.leerNuevoNombre(), vista.leerNuevoTelefono());
                    if (modificado) {
                        textoF = "Se ha modificado el cliente.";
                    } else {
                        textoF = "El cliente no se ha modificado.";
                    }
                }
                case INSERTAR_VEHICULO -> {
                    modelo.insertar(vista.leerVehiculo());
                    textoF = "Se ha insertado el vehículo correctamente.";
                }
                case BUSCAR_VEHICULO -> {
                    vista.mostrarVehiculo(modelo.buscar(vista.leerVehiculo()));
                    textoF = "Se ha encontrado el vehículo.";
                }
                case BORRAR_VEHICULO -> {
                    modelo.borrar(vista.leerVehiculo());
                    textoF = "Se ha borrado el vehiculo.";
                }
                case LISTAR_VEHICULOS -> vista.mostrarVehiculos(modelo.getVehiculos());

                case INSERTAR_REVISION -> {
                    modelo.insertar(vista.leerRevision());
                    textoF = "Se ha insertado la revisión correctamente.";
                }
                case INSERTAR_MECANICO -> {
                    modelo.insertar(vista.leerMecanico());
                    textoF = "Se ha insertado el trabajo mecánico correctamente.";
                }
                case BUSCAR_TRABAJO -> {
                    modelo.buscar(vista.leerMecanico());
                    textoF = "Se ha encontrado el trabajo mecanico.";
                }
                case BORRAR_TRABAJO -> {
                    modelo.borrar(modelo.buscar(vista.leerTrabajoVehiculo()));
                    textoF = "Se ha borrado el trabajo.";
                }
                case LISTAR_TRABAJOS -> vista.mostrarTrabajos(modelo.getTrabajos());

                case LISTAR_TRABAJOS_CLIENTE -> vista.mostrarTrabajos(modelo.getTrabajos(vista.leerCliente()));

                case LISTAR_TRABAJOS_VEHICULO -> vista.mostrarTrabajos(modelo.getTrabajos(vista.leerVehiculo()));

                case ANADIR_HORAS_TRABAJO -> {
                    modelo.anadirHoras(modelo.buscar(vista.leerTrabajoVehiculo()), vista.leerHoras());
                    textoF = "Se han añadido las horas al trabajo.";
                }
                case ANADIR_PRECIO_MATERIAL_TRABAJO -> {
                    modelo.anadirPrecioMaterial(modelo.buscar(vista.leerMecanico()), vista.leerPrecioMaterial());
                    textoF = "Se ha añadido el precio material al trabajo.";
                }
                case CERRAR_TRABAJO -> {
                    modelo.cerrar(modelo.buscar(vista.leerTrabajoVehiculo()), vista.leerFechaCierre());
                    textoF = "Se ha cerrado el trabajo correctamente.";
                }
                case SALIR -> terminar();
            }
            salir = true;
        } catch (Exception e) {
            textoF = e.getMessage();
            salir = false;
        }
        vista.notificarResultado(evento,textoF,salir);


    }

}
