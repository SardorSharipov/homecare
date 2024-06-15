package ru.hse.homecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hse.homecare.model.HomeService;

import ru.hse.homecare.repository.HomeServiceRepository;

import java.util.List;

@Service
public class HomeServiceService {
    @Autowired
    private HomeServiceRepository homeServiceRepository;

    public List<HomeService> findAll() {
        return homeServiceRepository.findAll();
    }

    public HomeService findById(Long id) {
        return homeServiceRepository.findById(id).orElse(null);
    }
}


