package org.example.thi0604.Controllers;


import org.example.thi0604.Entities.Role;
import org.example.thi0604.Entities.User;
import org.example.thi0604.RequestEntities.UserCreate;
import org.example.thi0604.RequestEntities.UserEdit;
import org.example.thi0604.Services.RoleService;
import org.example.thi0604.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public String getAllUser(Model model) {
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "User/getAll.html";
    }

    @GetMapping("/new")
    public String addUser(Model model) {
        User std = new User();
        List<Role> roles = roleService.getAllRole();
        model.addAttribute("user", std);
        model.addAttribute("roles", roles);
        System.out.println(roles.getFirst().getName());
        return "User/add";
    }

    @PostMapping("/save")
    public String saveUser(@Valid UserCreate userCreate, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("roles", roleService.getAllRole());
            return "/Users/add";
        }
        userService.createUser(userCreate);
        return "redirect:/users";
    }

    @GetMapping("/detail/{id}")
    public String detailUser(@PathVariable String id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "User/detail";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable String id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("roles", roleService.getAllRole());
        model.addAttribute("user", user);
        return "User/edit";
    }

    @PostMapping("/saveedit")
    public String saveUserEdit(UserEdit userEdit) {
        userService.UpdateUser(userEdit);
        return "redirect:/users";
    }

    @GetMapping("/findemail")
    public String findemail(Model model) {
        User user = userService.findUserByEmail("bm.toan@hutech.edu.vn");
        model.addAttribute("user", user);
        return "User/edit.html";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.DeleteUser(id);
        return "redirect:/users";
    }
}
