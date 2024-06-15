package ru.hse.homecare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.hse.homecare.model.Feedback;
import ru.hse.homecare.model.OrderService;
import ru.hse.homecare.service.FeedbackService;
import ru.hse.homecare.service.HomeServiceService;
import ru.hse.homecare.service.OrderServiceService;
import ru.hse.homecare.service.SpecialistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HomeServiceService homeServiceService;

    @Autowired
    private SpecialistService specialistService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private OrderServiceService orderServiceService;

    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${telegram.bot.chatId}")
    private String chatId;


    @GetMapping("/homecare")
    public String homecare(Model model) {
        logger.info("Main page started");
        model.addAttribute("services", homeServiceService.findAll());
        model.addAttribute("specialists", specialistService.findAll());
        model.addAttribute("feedbacks", feedbackService.findAll().stream().limit(10));
        return "index";
    }

    @PostMapping("/feedback")
    public String addFeedback(@RequestParam String userName, @RequestParam String comment, Model model) {
        logger.info("Feedback started");
        Feedback feedback = new Feedback();
        feedback.setUserName(userName);
        feedback.setComment(comment);
        feedbackService.save(feedback);
        logger.info("Feedback saved");
        return "redirect:/homecare#feedback-1";
    }

    @PostMapping("/order")
    public String submitOrder(@RequestParam("customerName") String customerName,
                              @RequestParam("address") String address,
                              @RequestParam("price") double price,
                              @RequestParam("serviceId") Long serviceId,
                              @RequestParam("specialistId") Long specialistId, Model model,
                              RedirectAttributes redirectAttributes) {
        logger.info("Order started");
        OrderService orderService = new OrderService();
        orderService.setCustomerName(customerName);
        orderService.setAddress(address);
        orderService.setPrice(price);
        orderService.setService(homeServiceService.findById(serviceId));
        orderService.setSpecialist(specialistService.findById(specialistId));
        this.orderServiceService.save(orderService);
        logger.info("Order Saved");
        redirectAttributes.addFlashAttribute("message", "Заказ успешно оформлен!");
        sendMessageToTelegram(orderService);
        logger.info("Message sent");
        return "redirect:/homecare#form02-6";
    }

    private void sendMessageToTelegram(OrderService orderService) {
        String apiUrl = "https://api.telegram.org/bot" + botToken + "/sendMessage";
        String text = "New form submission:\nCustomerName: " + orderService.getCustomerName()
                + "\nAddress: " + orderService.getAddress()
                + "\nPrice: " + orderService.getPrice()
                + "\nService: " + orderService.getService().getName()
                + "\nSpecialist: " + orderService.getSpecialist().getName();
        String requestUrl = apiUrl + "?chat_id=" + chatId + "&text=" + text;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(requestUrl, String.class);
    }
}
