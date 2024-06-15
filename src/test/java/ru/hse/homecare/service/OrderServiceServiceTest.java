package ru.hse.homecare.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ru.hse.homecare.model.OrderService;
import ru.hse.homecare.repository.OrderServiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class OrderServiceServiceTest {

    @InjectMocks
    private OrderServiceService orderServiceService;

    @Mock
    private OrderServiceRepository orderServiceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<OrderService> orders = new ArrayList<>();
        OrderService orderService = new OrderService();
        orderService.setId(1L);
        orderService.setCustomerName("Sardor");
        orders.add(orderService);
        when(orderServiceRepository.findAll()).thenReturn(orders);
        assertEquals(1, orderServiceService.findAll().size());
        assertEquals(1, orderServiceService.findAll().getFirst().getId());
        assertEquals("Sardor", orderServiceService.findAll().getFirst().getCustomerName());
    }

    @Test
    void testFindById() {
        OrderService orderService = new OrderService();
        orderService.setId(1L);
        orderService.setCustomerName("Sardor");
        when(orderServiceRepository.findById(anyLong())).thenReturn(Optional.of(orderService));
        assertEquals(1, orderServiceService.findById(1L).getId());
        assertEquals("Sardor", orderServiceService.findById(1L).getCustomerName());
    }
}