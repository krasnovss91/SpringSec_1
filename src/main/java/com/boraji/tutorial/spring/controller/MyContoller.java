package com.boraji.tutorial.spring.controller;

import java.security.Principal;

import com.boraji.tutorial.spring.model.User;
import com.boraji.tutorial.spring.service.UserDetailsServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyContoller {
  private UserDetailsServiceImp userDetailsService;

  @GetMapping("/")
  public String index(Model model, Principal principal) {
    model.addAttribute("message", "You are logged in as " + principal.getName());
    return "index";
  }
  @GetMapping("/list-of-users")
  public String showUsers(Model model) {
    model.addAttribute("users", userDetailsService.getAllUsers());
    return "list-of-users";
  }

  @GetMapping("/")
  public String showAll(Model model) {
    model.addAttribute("users", userDetailsService.getAllUsers());
    return "navigation";
  }


  @PostMapping("showUserForm/add")
  public String addUser(@ModelAttribute User user) {
    if (user.getId() == 0) { // переписать с существующими методами
      userDetailsService.saveUser(user);
    } else {
      userDetailsService.editUser(user);
    }
    return "redirect:/showUserForm";
  }

  @GetMapping("/showUserForm")
  public String showUserForm(Model model) {
    model.addAttribute("user", new User());
    model.addAttribute("listUsers", userDetailsService.getAllUsers());
    return "user-form";
  }

  @GetMapping("showUserForm/edit/{id}")
  public String editUser(@PathVariable long id, Model model) {

    model.addAttribute("user", userDetailsService.getUserById(id));
    model.addAttribute("listUsers", userDetailsService.getAllUsers());


    return "edit-user";
  }

  @PostMapping("showUserForm/edit/showUserForm/edit")
  public String editUser(@ModelAttribute("editUser") User user) {
    userDetailsService.editUser(user);
    return "redirect:/showUserForm";
  }


  @GetMapping("showUserForm/delete/{id}")
  public String deleteUser(@PathVariable("id") long id) {
    userDetailsService.deleteUser(id);
    return "redirect:/showUserForm";

  }

  @GetMapping("/{id}")
  public String userData(@PathVariable long id, Model model) {
    model.addAttribute("user", this.userDetailsService.getUserById(id));
    return "list-of-users";
  }

}
