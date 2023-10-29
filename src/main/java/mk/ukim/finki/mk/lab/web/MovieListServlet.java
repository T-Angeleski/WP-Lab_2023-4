package mk.ukim.finki.mk.lab.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.mk.lab.service.MovieService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(urlPatterns = "")
public class MovieListServlet extends HttpServlet {

    private final MovieService movieService;
    private final SpringTemplateEngine springTemplateEngine;

    public MovieListServlet(MovieService movieService, SpringTemplateEngine springTemplateEngine) {
        this.movieService = movieService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        context.setVariable("movies", movieService.listAll());

        springTemplateEngine.process(
                "listMovies.html",
                context, resp.getWriter());

        // TODO: SEARCH MOVIES
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String selectedMovie = req.getParameter("selected-movie");
//        int numTickets = Integer.parseInt(req.getParameter("numTickets"));
//
//        req.setAttribute("selected-movie", selectedMovie);
//        req.setAttribute("num-tickets", numTickets);
//
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/ticketOrder");
//        dispatcher.forward(req, resp);

    }
}
