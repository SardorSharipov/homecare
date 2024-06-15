package ru.hse.homecare.repository;

import org.springframework.stereotype.Repository;
import ru.hse.homecare.model.OrderService;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderServiceRepository extends JpaRepository<OrderService, Long> {
}


