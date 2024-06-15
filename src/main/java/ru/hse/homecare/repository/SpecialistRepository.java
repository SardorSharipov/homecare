package ru.hse.homecare.repository;

import org.springframework.stereotype.Repository;
import ru.hse.homecare.model.Specialist;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
}

