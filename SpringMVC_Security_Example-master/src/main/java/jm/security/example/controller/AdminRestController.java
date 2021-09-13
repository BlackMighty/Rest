package jm.security.example.controller;

import jm.security.example.model.User;
import jm.security.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminRestController {
    private UserService service;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("allUsers", service.allUsers());
        model.addAttribute("newUser", new User());
        return "admin";
    }
    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "")
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/admin");
        return modelAndView;
    }

    @GetMapping("/rest")
    public List<User> allUsersPage() {
        return service.allUsers();
    }

    @PostMapping("/add/rest")
    public void addUser(@RequestBody User user) {
        User user1 = new User(user.getUsername(), user.getPassword());
        Long role = user.getRoles().iterator().next().getId();
        service.setRole(user1, role);
        service.addUser(user1);
    }

    @GetMapping("/edit/rest/{id}")
    public User getUserByID(@PathVariable Integer id) {
        return service.getUserById(id);
    }

    @PostMapping("/edit/rest")
    public void updateUser(@RequestBody User user) {
        User user1 = new User(user.getId(), user.getUsername(), user.getPassword());
        Long role = user.getRoles().iterator().next().getId();
        service.setRole(user1, role);
        service.updateUser(user1);
    }

    @GetMapping("/delete/rest/{id}")
    public void deleteUserByID(@PathVariable("id") Integer id) {
        User user = service.getUserById(id);
        service.deleteUser(user);
    }
}
