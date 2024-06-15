package ru.hse.homecare.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ru.hse.homecare.model.Specialist;
import ru.hse.homecare.repository.SpecialistRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class SpecialistServiceTest {
    @InjectMocks
    private SpecialistService specialistService;

    @Mock
    private SpecialistRepository specialistRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Specialist> specialists = new ArrayList<>();
        Specialist specialist = new Specialist();
        specialist.setId(1L);
        specialist.setName("Sardor");
        specialists.add(specialist);
        when(specialistRepository.findAll()).thenReturn(specialists);
        assertEquals(1, specialistService.findAll().size());
        assertEquals(1, specialistService.findAll().getFirst().getId());
        assertEquals("Sardor", specialistService.findAll().getFirst().getName());
    }

    @Test
    void testFindById() {
        Specialist specialist = new Specialist();
        specialist.setId(1L);
        specialist.setName("Sardor");
        when(specialistRepository.findById(anyLong())).thenReturn(Optional.of(specialist));
        assertEquals(1, specialistService.findById(1L).getId());
        assertEquals("Sardor", specialistService.findById(1L).getName());
    }
}