package jm.security.example.controller;

import jm.security.example.model.User;
import jm.security.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping()
    public String show(Model model, Authentication authentication) {
        User user = userRepo.findUserByUsername(authentication.getName());
        model.addAttribute("user", user);
        return "user/user";
    }
}
