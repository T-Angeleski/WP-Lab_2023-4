package mk.ukim.finki.mk.lab.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.mk.lab.model.User;
import mk.ukim.finki.mk.lab.model.exceptions.InvalidCredentialsException;
import mk.ukim.finki.mk.lab.model.exceptions.PasswordDoesNotMatchException;
import mk.ukim.finki.mk.lab.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("bodyContent", "login");
        return "master-template";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request,
                        Model model) {
        User user;
        try {
            user = authService.login(request.getParameter("username"),
                    request.getParameter("password"));
        } catch (InvalidCredentialsException | IllegalArgumentException exception) {
            model.addAttribute("bodyContent", "login");
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "master-template";
        }

        request.getSession().setAttribute("user", user);
        return "redirect:/movies";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/auth/login";
    }

    @GetMapping("/register")
    public String registerPage(@RequestParam(required = false) String error,
                               Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam LocalDate dateOfBirth) {
        try {
            authService.register(username, password, repeatedPassword, name, surname, dateOfBirth);
            return "redirect:/auth/login";
        } catch (IllegalArgumentException | PasswordDoesNotMatchException e) {
            return "redirect:/auth/register?error=" + e.getMessage();
        }
    }
}
