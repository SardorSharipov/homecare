package ru.hse.homecare.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ru.hse.homecare.model.HomeService;
import ru.hse.homecare.repository.HomeServiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class HomeServiceTest {
    @InjectMocks
    private HomeServiceService homeServiceService;

    @Mock
    private HomeServiceRepository homeServiceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<HomeService> homeServices = new ArrayList<>();
        HomeService homeService = new HomeService();
        homeService.setId(1L);
        homeService.setName("Service");
        homeServices.add(homeService);
        when(homeServiceRepository.findAll()).thenReturn(homeServices);
        assertEquals(1, homeServiceService.findAll().size());
        assertEquals(1, homeServiceService.findAll().getFirst().getId());
        assertEquals("Service", homeServiceService.findAll().getFirst().getName());
    }

    @Test
    void testFindById() {
        HomeService homeService = new HomeService();
        homeService.setId(1L);
        homeService.setName("Service");
        when(homeServiceRepository.findById(anyLong())).thenReturn(Optional.of(homeService));
        assertEquals(1, homeServiceService.findById(1L).getId());
        assertEquals("Service", homeServiceService.findById(1L).getName());
    }
}
