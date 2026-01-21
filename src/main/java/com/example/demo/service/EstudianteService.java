package com.example.demo.service;

import com.example.demo.entities.Estudiante;
import com.example.demo.respository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;


    public Estudiante findById(Long id) {
        return estudianteRepository.findById(id).orElseThrow();
    }

    public List<Estudiante> findAll() {
        return (List<Estudiante>) estudianteRepository.findAll();
    }

    public void createStudent(Estudiante estudiante) {
        estudianteRepository.save(estudiante);
    }

    public Estudiante updateStudent(Estudiante estudiante) {
        estudianteRepository.save(estudiante);
        return estudiante;
    }

    public void deleteById(Long id) {
        estudianteRepository.deleteById(id);
    }
}
