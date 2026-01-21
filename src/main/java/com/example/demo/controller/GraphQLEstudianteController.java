package com.example.demo.controller;

import com.example.demo.entities.Estudiante;
import com.example.demo.entities.Materia;
import com.example.demo.graphql.InputEstudiante;
import com.example.demo.service.EstudianteService;
import com.example.demo.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLEstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private MateriaService materiaService;

    @QueryMapping(name = "findStudentById")
    public Estudiante findById(@Argument(name = "estudianteId") String id){
        Long studentId = Long.parseLong(id);

        return estudianteService.findById(studentId);
    }

    @QueryMapping(name = "findAllStudents")
    public List<Estudiante> findAll(){
        return estudianteService.findAll();
    }

    @MutationMapping
    public Estudiante createStudent(@Argument InputEstudiante inputEstudiante){

        Materia materia = materiaService.findById(Long.parseLong(inputEstudiante.materiaId()));

        Estudiante estudiante = new Estudiante();
        estudiante.setNombres(inputEstudiante.nombres());
        estudiante.setApellidos(inputEstudiante.apellidos());
        estudiante.setEdad(inputEstudiante.edad());
        estudiante.setMateria(materia);

        estudianteService.createStudent(estudiante);

        return estudiante;
    }

    @MutationMapping
    public Estudiante updateStudent(@Argument InputEstudiante inputEstudiante) {
        Estudiante estudianteExistente = estudianteService.findById(inputEstudiante.id());

        if (inputEstudiante.materiaId() != null) {
            Materia materia = materiaService.findById(Long.parseLong(inputEstudiante.materiaId()));
            estudianteExistente.setMateria(materia);
        }

        if (inputEstudiante.nombres() != null) {
            estudianteExistente.setNombres(inputEstudiante.nombres());
        }

        if (inputEstudiante.apellidos() != null) {
            estudianteExistente.setApellidos(inputEstudiante.apellidos());
        }

        if (inputEstudiante.edad() != null) {
            estudianteExistente.setEdad(inputEstudiante.edad());
        }

        return estudianteService.updateStudent(estudianteExistente);
    }


    @MutationMapping(name = "deleteStudentById")
    public String deleteById(@Argument(name = "estudianteId") String id){
        estudianteService.deleteById(Long.parseLong(id));
        return "El estudiante con id " + id + " ha sido eliminado.";
    }
}
