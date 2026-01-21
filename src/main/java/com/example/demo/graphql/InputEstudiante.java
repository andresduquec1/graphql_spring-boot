package com.example.demo.graphql;


public record InputEstudiante(
         Long id,
         String nombres,
         String apellidos,
         Integer edad,
         String materiaId
){}
