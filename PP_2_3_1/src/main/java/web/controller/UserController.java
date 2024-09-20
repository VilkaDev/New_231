package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping
//    public String show(Model model){
//        model.addAttribute(userService.getListUsers());
//        return "users";
//    }

    @GetMapping
    public String showAllUsers(Model model) {
        // получим всех людей из дао и передадим на отображение в view
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "users";
    }

    @GetMapping("/add")
    public String addNewUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "user_info";
    }

    @GetMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/updateInfo")
    public String updateUser(@RequestParam("userId") long id, Model model){
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user_info";

    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
