package ru.hse.homecare.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ru.hse.homecare.model.Feedback;
import ru.hse.homecare.repository.FeedbackRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class FeedbackServiceTest {
    @InjectMocks
    private FeedbackService feedbackService;

    @Mock
    private FeedbackRepository feedbackRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Feedback> feedbacks = new ArrayList<>();
        Feedback feedback = new Feedback();
        feedback.setId(1L);
        feedback.setUserName("Sardor");
        feedback.setComment("Bitch");
        feedbacks.add(feedback);
        when(feedbackRepository.findAll()).thenReturn(feedbacks);
        assertEquals(1, feedbackService.findAll().size());
        assertEquals(1, feedbackService.findAll().getFirst().getId());
        assertEquals("Sardor", feedbackService.findAll().getFirst().getUserName());
        assertEquals("Bitch", feedbackService.findAll().getFirst().getComment());
    }
}