package mk.ukim.finki.mk.lab.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.mk.lab.model.Movie;
import mk.ukim.finki.mk.lab.model.TicketOrder;
import mk.ukim.finki.mk.lab.service.MovieService;
import mk.ukim.finki.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

//    @PostMapping
//    public String showTicketOrder(@RequestParam String selectedMovie,
//                                  @RequestParam String numTickets,
//                                  HttpServletRequest req,
//                                  Model model) {
//        TicketOrder order = ticketOrderService.placeOrder(selectedMovie,
//                "Teodor Angeleski",
//                req.getRemoteAddr(),
//                Long.parseLong(numTickets));
//        List<Movie> orderedMovies = ticketOrderService.findClientOrders("Teodor Angeleski");
//        model.addAttribute("movies", orderedMovies);
//        model.addAttribute("order", order);
//        model.addAttribute("bodyContent", "orderConfirmation");
//        return "master-template";
//    }

    @PostMapping("/editRating")
    public String editMovieRating(@RequestParam String rating,
                                  @RequestParam String movieTitle) {
        // Handle ,,,3
        String[] split = rating.split(",");
        String srating = split[split.length-1];
        Double mvRating = Double.valueOf(srating);
        movieService.findMovieAndSetNewRating(movieTitle, mvRating);
        return "redirect:/movies";
    }
}
