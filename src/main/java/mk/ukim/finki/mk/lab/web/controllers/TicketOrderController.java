package mk.ukim.finki.mk.lab.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.mk.lab.model.TicketOrder;
import mk.ukim.finki.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {

    private final SpringTemplateEngine springTemplateEngine;
    private final TicketOrderService ticketOrderService;

    public TicketOrderController(SpringTemplateEngine springTemplateEngine, TicketOrderService ticketOrderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.ticketOrderService = ticketOrderService;
    }

    @PostMapping
    public String showTicketOrder(@RequestParam String selectedMovie,
                                  @RequestParam String numTickets,
                                  HttpServletRequest req,
                                  Model model) {
        TicketOrder order = ticketOrderService.placeOrder(selectedMovie,
                "Teodor Angeleski",
                req.getRemoteAddr(),
                Long.parseLong(numTickets));
        model.addAttribute("order", order);
        return "orderConfirmation";
    }
}
