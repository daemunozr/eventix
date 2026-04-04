package com.example.eventix.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.eventix.Model.Evento;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Repository
public class EventoRepository {

    private final List<Evento> eventos = new ArrayList<>();

    public List<Evento> obtenerEventos(){
        return eventos;
    }

    public List<Evento> obtenerOrdenadoPorId(){
        List<Evento> tmp_eventos = new ArrayList<>(eventos);
        tmp_eventos.sort(Comparator.comparing(Evento::getId));
        return tmp_eventos;
    }

    public List<Evento> obtenerOrdenadoPorNombre(){
        List<Evento> tmp_eventos = new ArrayList<>(eventos);
        tmp_eventos.sort(Comparator.comparing(Evento::getNombre));
        return tmp_eventos;
    }

    public List<Evento> obtenerOrdenadoPorEntidad(){
        List<Evento> tmp_eventos = new ArrayList<>(eventos);
        tmp_eventos.sort(Comparator.comparing(Evento::getEntidad));
        return tmp_eventos;
    }

    public List<Evento> obtenerOrdenadoPorFecha(){
        List<Evento> tmp_eventos = new ArrayList<>(eventos);
        tmp_eventos.sort(Comparator.comparing(Evento::getFecha));
        return tmp_eventos;
    }

    public List<Evento> obtenerOrdenadoPorParticipantes(){
        List<Evento> tmp_eventos = new ArrayList<>(eventos);
        tmp_eventos.sort(Comparator.comparing(Evento::getParticipantes));
        return tmp_eventos;
    }

    public Evento buscarPorId(int id){
        for(Evento evento: eventos){
            if(evento.getId() == id)
                return evento;
        }
        return null;
    }

    public Evento buscarPorNombre(String nombre){
        for(Evento evento: eventos){
            if(evento.getNombre().equals(nombre))
                return evento;
        }
        return null;
    }

    public List<Evento> buscarPorEntidad(String entidad){
        return eventos.stream().filter(evento->evento.getEntidad().equals(entidad))
            .collect(Collectors.toList());
    }

    public List<Evento> buscarPorTipo(String tipo){
        return eventos.stream().filter(evento->evento.getTipo().equals(tipo))
            .collect(Collectors.toList());
    }

    public List<Evento> buscarPorFecha(int fecha){
        return eventos.stream().filter(evento->evento.getFecha() == fecha)
            .collect(Collectors.toList());
    }

    public List<Evento> buscarPorUbicacion(String ubicacion){
        return eventos.stream().filter(evento->evento.getUbicacion().equals(ubicacion))
            .collect(Collectors.toList());
    }

    public List<Evento> buscarPorParticipantes(int participantes){
        return eventos.stream().filter(evento->evento.getParticipantes() == participantes)
            .collect(Collectors.toList());
    }

    public Evento guardar(Evento evento){
        if(buscarPorId(evento.getId()) == null){
            eventos.add(evento);
            return evento;
        }
        return null;
    }

    public Evento actualizar(Evento evento){

        int posicion = 0;
        for(Evento tmp_evento: eventos){
            if(tmp_evento.getId().equals( evento.getId())){
                eventos.set(posicion, evento);
                return evento;
            }
            posicion++;    
        }
        return null;
    }

    public void eliminar(int id){
        Evento tmp_evento = buscarPorId(id); 
        if(tmp_evento != null)
            eventos.remove(tmp_evento);
    }

    public void eliminar(String nombre){
        Evento tmp_evento = buscarPorNombre(nombre); 
        if(tmp_evento != null)
            eventos.remove(tmp_evento);
    }
    
}
