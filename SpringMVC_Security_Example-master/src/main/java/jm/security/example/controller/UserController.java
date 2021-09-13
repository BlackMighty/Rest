package jm.security.example.controller;

import jm.security.example.dao.UserRepo;
import jm.security.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.Authenticator;

@Controller
@RequestMapping("")
public class  UserController {

   private final UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping( "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("user")
    public String getUserPage(Model model, Authenticator authenticator) {
        User user = userRepo.findUserByUsername(authenticator.toString());
        model.addAttribute("user", user);
        return "user";
    }
}
