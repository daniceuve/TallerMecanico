package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.*;

public class GestorEventos{

    Map<Evento, List<ReceptorEventos>> receptores = new EnumMap<>(Evento.class);

    public GestorEventos(Evento... eventos) {
        Objects.requireNonNull(eventos, "Los eventos no pueden ser nulos.");
        for (Evento evento : eventos)
            receptores.put(evento, new ArrayList<>());
    }
    public void susbribir(ReceptorEventos receptor, Evento... eventos) {
        Objects.requireNonNull(eventos, "Los eventos no pueden ser nulos.");
        for(Evento evento : eventos) {
            List<ReceptorEventos> personas = receptores.get(evento);
            personas.add(receptor);
            receptores.put(evento, personas);
        }
    }

    public void desuscribir(ReceptorEventos  receptor, List<Evento> eventos) {
        Objects.requireNonNull(receptor, "El receptor no puede ser nulo.");
        Objects.requireNonNull(eventos, "Los eventos no pueden ser nulos.");
        for(Evento evento : eventos) {
            List<ReceptorEventos> personas = receptores.get(evento);
            personas.add(receptor);
            receptores.remove(evento, personas);
        }
    }

    public void notificar(Evento evento) {
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");
        for (ReceptorEventos receptor : receptores.get(evento))
            receptor.acturalizar(evento);
    }

}
