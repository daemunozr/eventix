package com.example.eventix.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eventix.Model.Evento;
import com.example.eventix.Repository.EventoRepository;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repo;

    public List<Evento> obtenerEventos(){
        return repo.obtenerEventos();
    }

    public List<Evento> obtenerOrdenadoPorId(){
        return repo.obtenerOrdenadoPorId();
    }
    
    public List<Evento> obtenerOrdenadoPorNombre(){
        return repo.obtenerOrdenadoPorNombre();
    }
    
    public List<Evento> obtenerOrdenadoPorEntidad(){
        return repo.obtenerOrdenadoPorEntidad();
    }

    public List<Evento> obtenerOrdenadoPorFecha(){
        return repo.obtenerOrdenadoPorFecha();
    }

    public List<Evento> obtenerOrdenadoPorParticipantes(){
        return repo.obtenerOrdenadoPorParticipantes();
    }
    
    public Evento buscarPorId(int id) throws Exception{
        Evento tmp_evento = repo.buscarPorId(id); 
        if(tmp_evento == null)
            throw new Exception("Error: evento no encontrado por id \"" + id + "\"");
        return tmp_evento;
    }
    
    public Evento buscarPorNombre(String nombre) throws Exception{
        Evento tmp_evento = repo.buscarPorNombre(nombre); 
        if(tmp_evento == null)
            throw new Exception("Error: evento no encontrado por nombre \"" + nombre + "\"");
        return tmp_evento;
    }
    
    public List<Evento> buscarPorEntidad(String entidad) throws Exception{
        List<Evento> tmp_evento = repo.buscarPorEntidad(entidad);
        if(tmp_evento == null)
            throw new Exception("Error: evento no encontrado por entidad \"" + entidad + "\"");
        return tmp_evento;
    }
    
    public List<Evento> buscarPorTipo(String tipo) throws Exception{
        List<Evento> tmp_evento = repo.buscarPorTipo(tipo);
        if(tmp_evento == null)
            throw new Exception("Error: evento no encontrado por tipo \"" + tipo + "\"");
        return tmp_evento;
    }

    public List<Evento> buscarPorFecha(int fecha) throws Exception{
        List<Evento> tmp_evento = repo.buscarPorFecha(fecha);
        if(tmp_evento == null)
            throw new Exception("Error: evento no encontrado por fecha \"" + fecha + "\"");
        return tmp_evento;
    }

    public List<Evento> buscarPorUbicacion(String ubicacion) throws Exception{
        List<Evento> tmp_evento = repo.buscarPorUbicacion(ubicacion);
        if(tmp_evento == null)
            throw new Exception("Error: evento no encontrado por ubicacion \"" + ubicacion + "\"");
        return tmp_evento;
    }

    public List<Evento> buscarPorParticipantes(int participantes) throws Exception{
        List<Evento> tmp_evento = repo.buscarPorParticipantes(participantes);
        if(tmp_evento == null)
            throw new Exception("Error: evento no encontrado por participantes \"" + participantes + "\"");
        return tmp_evento;
    }
    
    public Evento guardar(Evento evento) throws Exception{
       if(repo.guardar(evento) == null)
            throw new Exception("Error: evento con id \"" + evento.getId() + "\" ya se encuentra guardado");
        return evento;
    }
    
    public Evento actualizar(Evento evento) throws Exception{
        if(repo.actualizar(evento) == null)
            throw new Exception("Error: evento con id \"" + evento.getId() + "\" no encontrado para actualizar");
        return evento;
    }

    public String borrar(int id) throws Exception{
                if(repo.buscarPorId(id) == null)
                        throw new Exception("Error: evento con id \"" + id + "\" no encontrado para borrar");
                repo.eliminar(id);
                return "Evento eliminado";
        }
    
    public String borrar(String nombre) throws Exception{
                if(repo.buscarPorNombre(nombre) == null)
                        throw new Exception("Error: evento con nombre \"" + nombre + "\" no encontrado para borrar");
                repo.eliminar(nombre);
                return "Evento eliminado";
        }
}
