package ru.hse.homecare.service;

import ru.hse.homecare.repository.OrderServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceService {

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    public List<ru.hse.homecare.model.OrderService> findAll() {
        return orderServiceRepository.findAll();
    }

    public ru.hse.homecare.model.OrderService findById(Long id) {
        return orderServiceRepository.findById(id).orElse(null);
    }

    public void save(ru.hse.homecare.model.OrderService orderService) {
        orderServiceRepository.save(orderService);
    }
}
