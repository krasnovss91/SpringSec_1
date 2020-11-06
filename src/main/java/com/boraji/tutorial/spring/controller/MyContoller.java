package com.boraji.tutorial.spring.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyContoller {

  @GetMapping("/")
  public String index(Model model, Principal principal) {
    model.addAttribute("message", "You are logged in as " + principal.getName());
    return "index";
  }
  @GetMapping("/list-of-users")
  public String showUsers(Model model) {
    model.addAttribute("users", userService.getAllUsers());
    return "list-of-users";
  }

  @GetMapping("/")
  public String showAll(Model model) {
    model.addAttribute("users", userService.getAllUsers());
    return "navigation";
  }


  @PostMapping("showUserForm/add")
  public String addUser(@ModelAttribute User user) {
    if (user.getId() == 0) {
      userService.saveUser(user);
    } else {
      userService.editUser(user);
    }
    return "redirect:/showUserForm";
  }

  @GetMapping("/showUserForm")
  public String showUserForm(Model model) {
    model.addAttribute("user", new User());
    model.addAttribute("listUsers", userService.getAllUsers());
    return "user-form";
  }

  @GetMapping("showUserForm/edit/{id}")
  public String editUser(@PathVariable long id, Model model) {

    model.addAttribute("user", userService.getUserById(id));
    model.addAttribute("listUsers", userService.getAllUsers());


    return "edit-user";
  }

  @PostMapping("showUserForm/edit/showUserForm/edit")
  public String editUser(@ModelAttribute("editUser") User user) {
    userService.editUser(user);
    return "redirect:/showUserForm";
  }


  @GetMapping("showUserForm/delete/{id}")
  public String deleteUser(@PathVariable("id") long id) {
    userService.deleteUser(id);
    return "redirect:/showUserForm";

  }

  @GetMapping("/{id}")
  public String userData(@PathVariable long id, Model model) {
    model.addAttribute("user", this.userService.getUserById(id));
    return "list-of-users";
  }

}
