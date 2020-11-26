package spring_sec_1.controller;

import java.security.Principal;

import spring_sec_1.model.User;
import spring_sec_1.service.UserDetailsServiceImp;
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
    return "login";
  }
  @GetMapping("/user")
  public String showUsers(Model model) {
    model.addAttribute("users", userDetailsService.getAllUsers());
    return "user";
  }
/*
  @GetMapping("/")
  public String showAll(Model model) {
    model.addAttribute("users", userDetailsService.getAllUsers());//возможно этот метод лишний
    return "login";
  }
*/

  @PostMapping("admin/add")
  public String addUser(@ModelAttribute User user) {
    if (userDetailsService.findUserByName(user.getUsername())){
      userDetailsService.saveUser(user);
    } else {
      userDetailsService.editUser(user);
    }
    return "redirect:/showUserForm";
  }

  @GetMapping("/admin")
  public String showUserForm(Model model) {
    model.addAttribute("user", new User());
    model.addAttribute("listUsers", userDetailsService.getAllUsers());
    return "admin";
  }

  @GetMapping("admin/edit/{id}")
  public String editUser(@PathVariable long id, Model model) {

    model.addAttribute("user", userDetailsService.getUserById(id));
    model.addAttribute("listUsers", userDetailsService.getAllUsers());

    return "admin";
  }

  @PostMapping("admin/edit/showUserForm/edit")
  public String editUser(@ModelAttribute("editUser") User user) {
    userDetailsService.editUser(user);
    return "redirect:/showUserForm";
  }


  @GetMapping("admin/delete/{id}")
  public String deleteUser(@PathVariable("id") long id) {
    userDetailsService.deleteUser(id);
    return "redirect:/showUserForm";

  }

  @GetMapping("/{id}")
  public String userData(@PathVariable long id, Model model) {
    model.addAttribute("user", this.userDetailsService.getUserById(id));
    return "user";
  }

}
