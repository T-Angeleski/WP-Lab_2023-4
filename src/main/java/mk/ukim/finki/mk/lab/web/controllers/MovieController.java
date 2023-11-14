package mk.ukim.finki.mk.lab.web.controllers;

import mk.ukim.finki.mk.lab.model.Movie;
import mk.ukim.finki.mk.lab.model.Production;
import mk.ukim.finki.mk.lab.service.MovieService;
import mk.ukim.finki.mk.lab.service.ProductionService;
import mk.ukim.finki.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final TicketOrderService ticketOrderService;
    private final ProductionService productionService;

    public MovieController(MovieService movieService, TicketOrderService ticketOrderService, ProductionService productionService) {
        this.movieService = movieService;
        this.ticketOrderService = ticketOrderService;
        this.productionService = productionService;
    }

    @GetMapping
    public String getMoviesPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String searchText,
                                @RequestParam(required = false) Double searchRating,
                                Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        if (searchText == null && searchRating == null) {
            model.addAttribute("searchedMovies", new ArrayList<String>());
        } else if (searchRating == 1.0) {
            model.addAttribute("searchedMovies", movieService.searchMovies(searchText));
        } else {
            model.addAttribute("searchedMovies",
                    movieService.searchByTitleAndRating(searchText, searchRating));
        }

        model.addAttribute("movies", movieService.listAll());
        model.addAttribute("mostPopularMovie", ticketOrderService.findMostPopular());
        model.addAttribute("bodyContent", "listMovies");
        return "master-template";
    }

    @GetMapping("/add-form")
    public String getAddMoviePage(Model model) {
        List<Production> productions = productionService.findAll();
        model.addAttribute("productions", productions);
        model.addAttribute("bodyContent", "add-movie");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveMovie(@RequestParam String movieTitle,
                            @RequestParam String movieSummary,
                            @RequestParam Double movieRating,
                            @RequestParam String productionId,
                            @RequestParam String id) {
        this.movieService.save(movieTitle, movieSummary, movieRating,
                Long.valueOf(productionId), Long.valueOf(id));
        return "redirect:/movies";
    }

    @GetMapping("/edit-form/{movieId}")
    public String editMovie(@PathVariable Long movieId, Model model) {
        if(movieService.findById(movieId).isPresent()) {
            Movie movie = movieService.findById(movieId).get();
            List<Production> productions = productionService.findAll();
            model.addAttribute("productions", productions);
            model.addAttribute("movie", movie);
            model.addAttribute("bodyContent", "add-movie");
            return "master-template";
        }
        return "redirect:/movies?error=MovieNotFound";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteById(id);
        return "redirect:/movies";
    }
}
