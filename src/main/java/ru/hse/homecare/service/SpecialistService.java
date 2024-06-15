package ru.hse.homecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hse.homecare.model.Specialist;
import ru.hse.homecare.repository.SpecialistRepository;

import java.util.List;

@Service
public class SpecialistService {
    @Autowired
    private SpecialistRepository specialistRepository;

    public List<Specialist> findAll() {
        return specialistRepository.findAll();
    }

    public Specialist findById(Long id) {
        return specialistRepository.findById(id).orElse(null);
    }
}

