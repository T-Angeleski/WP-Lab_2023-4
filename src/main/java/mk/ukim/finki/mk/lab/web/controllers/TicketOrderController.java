package mk.ukim.finki.mk.lab.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.mk.lab.model.Movie;
import mk.ukim.finki.mk.lab.model.TicketOrder;
import mk.ukim.finki.mk.lab.model.User;
import mk.ukim.finki.mk.lab.service.MovieService;
import mk.ukim.finki.mk.lab.service.TicketOrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {

    private final TicketOrderService ticketOrderService;
    private final MovieService movieService;

    public TicketOrderController(TicketOrderService ticketOrderService, MovieService movieService) {
        this.ticketOrderService = ticketOrderService;
        this.movieService = movieService;
    }

    @GetMapping
    public String allOrdersPage(Model model,
                                @RequestParam(required = false) String error,
                                HttpServletRequest req) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        User user = (User) req.getSession().getAttribute("user");


        List<TicketOrder> orders = ticketOrderService.findAllByUserId(user.getId());
        model.addAttribute("orders", orders);
        model.addAttribute("bodyContent", "list-orders");
        return "master-template";
    }

    @PostMapping
    public String placeOrder(@RequestParam Long movieId,
                             @RequestParam Long numTickets,
                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm")
                             LocalDateTime dateCreated,
                             Model model,
                             HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        Movie movie = movieService.findById(movieId).get();

        TicketOrder order = ticketOrderService.placeOrder(user, movie, numTickets, dateCreated);
        model.addAttribute("bodyContent", "orderConfirmation");
        model.addAttribute("order", order);
        return "master-template";
    }

//    @PostMapping("/editRating")
//    public String editMovieRating(@RequestParam String rating,
//                                  @RequestParam String movieTitle) {
//        // Handle ,,,3
//        String[] split = rating.split(",");
//        String srating = split[split.length-1];
//        Double mvRating = Double.valueOf(srating);
//        movieService.findMovieAndSetNewRating(movieTitle, mvRating);
//        return "redirect:/movies";
//    }
}
