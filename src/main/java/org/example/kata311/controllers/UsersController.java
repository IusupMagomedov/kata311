package org.example.kata311.controllers;

import org.example.kata311.models.User;
import org.example.kata311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(@RequestParam(value = "limit", required = false) Integer limit, Model model) {
        List<User> users = userService.getUsers(limit);
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/create")
    public String addUser(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password) {
        userService.createUser(name, email, password);
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "/update";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password) {
        userService.updateUser(id, name, email, password);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
