package mk.ukim.finki.mk.lab.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.mk.lab.model.ShoppingCart;
import mk.ukim.finki.mk.lab.model.TicketOrder;
import mk.ukim.finki.mk.lab.model.User;
import mk.ukim.finki.mk.lab.service.ShoppingCartService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String shoppingCartPage(@RequestParam(required = false) String error,
                                   HttpServletRequest req,
                                   Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        User user = (User) req.getSession().getAttribute("user");
        ShoppingCart cart = shoppingCartService.getActiveShoppingCart(user.getUsername());
        List<TicketOrder> orders = shoppingCartService.listAllTicketsInShoppingCart(cart.getId());
        model.addAttribute("orders", orders);
        model.addAttribute("bodyContent", "shopping-cart");
        return "master-template";
    }

    @PostMapping("/add-order/{id}")
    public String addOrderToCart(@PathVariable Long id,
                                 HttpServletRequest req) {
        try {
            User user = (User) req.getSession().getAttribute("user");
            shoppingCartService.addMovieToCart(user.getUsername(), id);
            return "redirect:/movies";
        } catch (RuntimeException e) {
            return "redirect:/shopping-cart?error=" + e.getMessage();
        }
    }
}
