package com.example.demo.service;

import com.example.demo.entities.Materia;
import com.example.demo.respository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    public Materia findById(Long id) {
        return materiaRepository.findById(id).orElseThrow();
    }

    public List<Materia> findAll() {
        return (List<Materia>) materiaRepository.findAll();
    }

    public void createCourse(Materia materia) {
        materiaRepository.save(materia);
    }

    public void deleteById(Long id) {
        materiaRepository.deleteById(id);
    }
}
