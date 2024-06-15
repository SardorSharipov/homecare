package ru.hse.homecare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hse.homecare.model.HomeService;

import java.util.List;

@Repository
public interface HomeServiceRepository extends JpaRepository<HomeService, Long> {
}

