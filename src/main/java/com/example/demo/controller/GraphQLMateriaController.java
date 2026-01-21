package com.example.demo.controller;

import com.example.demo.entities.Materia;
import com.example.demo.graphql.InputMateria;
import com.example.demo.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLMateriaController {

    @Autowired
    private MateriaService materiaService;

    @QueryMapping(name = "findCourseById")
    public Materia findById(@Argument(name = "metariaId") String id){
        Long courseId = Long.parseLong(id);

        return materiaService.findById(courseId);
    }

    @QueryMapping(name = "findAllCourses")
    public List<Materia> findAll(){
        return materiaService.findAll();
    }


    @MutationMapping
    public Materia createCourse(@Argument InputMateria inputMateria){

        Materia materia = new Materia();
        materia.setNombre(inputMateria.nombre());
        materia.setCategoria(inputMateria.categoria());
        materia.setProfesor(inputMateria.profesor());

        materiaService.createCourse(materia);
        return materia;
    }


    @MutationMapping(name = "deleteCourseById")
    public String deleteById(@Argument(name = "materiaId") String id){

        materiaService.deleteById(Long.parseLong(id));

        return "El curso con id " + id + " ha sido eliminado";
    }
}
