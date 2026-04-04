package com.example.eventix.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.eventix.Model.Evento;
import com.example.eventix.Service.EventoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/eventos")
public class EventoController {

    @Autowired
    private EventoService service;

    @GetMapping
    public ResponseEntity<List<Evento>> listarEventos(){
        return ResponseEntity.ok(service.obtenerEventos());
    }

    @GetMapping("/orden")
    public ResponseEntity<?> listarEventosOrdenadasPorCriterio(@RequestParam(value = "criterio", required=true) String criterio){
        switch (criterio){
            case("id"):
                return ResponseEntity.ok(service.obtenerOrdenadoPorId());
            case("nombre"): 
                return ResponseEntity.ok(service.obtenerOrdenadoPorNombre());
            case("entidad"):
                return ResponseEntity.ok(service.obtenerOrdenadoPorEntidad());
            case("fecha"):
                return ResponseEntity.ok(service.obtenerOrdenadoPorFecha());
            case("participantes"):
                return ResponseEntity.ok(service.obtenerOrdenadoPorParticipantes());
            default:
                return ResponseEntity.badRequest().body("Error: el criterio \"" + criterio + "\" no es valido");
        }
    }
    
    @GetMapping("/buscarId")
    public ResponseEntity<?> buscarEventoPorId(@RequestParam(value= "llave", required=true) int id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/buscarNombre")
    public ResponseEntity<?> buscarEventoPorNombre(@RequestParam(value= "nombre", required=true) String nombre) {
        try {
            return ResponseEntity.ok(service.buscarPorNombre(nombre));    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @GetMapping("/buscarEntidad")
    public ResponseEntity<?> buscarPorEntidad(@RequestParam(value= "nombre", required=true) String entidad) {
        try {
            return ResponseEntity.ok(service.buscarPorEntidad(entidad));    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/buscarFecha")
    public ResponseEntity<?> buscarPorFecha(@RequestParam(value= "fecha", required=true) int fecha) {
        try {
            return ResponseEntity.ok(service.buscarPorFecha(fecha));    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/buscarParticipantes")
    public ResponseEntity<?> buscarPorParticipantes(@RequestParam(value= "cantidad", required=true) int participantes) {
        try {
            return ResponseEntity.ok(service.buscarPorParticipantes(participantes));    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarEvento(@Valid @RequestBody Evento evento) {
        try {
            service.guardar(evento);
            return ResponseEntity.status(HttpStatus.CREATED).body(evento);    
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping
    public ResponseEntity<?> actualizarEvento(@Valid @RequestBody Evento evento) {
        
        try {
            service.actualizar(evento);
            return ResponseEntity.ok(evento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/borrarPorId/{id}")
    public ResponseEntity<?> borrarEventoPorId(@PathVariable int id){
        try {
            String message = service.borrar(id);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @DeleteMapping("/borrarPorNombre/{nombre}")
    public ResponseEntity<?> borrarEventoPorNombre(@PathVariable String nombre){
        try {
            String message = service.borrar(nombre);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> manejarErroresValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }
        return errores;
    }
}
