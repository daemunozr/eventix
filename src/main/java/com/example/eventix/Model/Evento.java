package com.example.eventix.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {

    @NotNull(message="Se requiere id")
    int id;

    @NotBlank(message="Se requiere nombre del evento")
    String nombre;
    
    @NotBlank(message="Se requiere entidad organzidora")
    String entidad;

    @NotBlank(message="Se requiere tipo del evento")
    String tipo;

    @NotNull(message="Se requiere fecha del evento")
    int fecha;

    @NotBlank(message="Se requiere ubicacion del evento")
    String ubicacion;

    @NotNull(message="Se requiere cantidad de participantes")
    int participantes;
    
}
