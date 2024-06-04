package org.example.thi0604.Controllers;

import org.example.thi0604.Entities.Role;
import org.example.thi0604.RequestEntities.RoleCreate;
import org.example.thi0604.RequestEntities.RoleEdit;
import org.example.thi0604.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public String getAllRole(Model model){
        List<Role> roles = roleService.getAllRole();
        model.addAttribute("roles", roles);
        return "Role/getAll.html";
    }

    @GetMapping("/new")
    public String addRole(Model model){
        Role role = new Role();
        model.addAttribute("role", role);
        return "Role/add";
    }

    @PostMapping("/save")
    public String saveRole(RoleCreate roleCreate){
        roleService.CreateRole(roleCreate);
        return "redirect:/roles";
    }

    @GetMapping("/detail/{id}")
    public String detailRole(@PathVariable int id, Model model){
        Role role = roleService.getRoleId(id);
        model.addAttribute("role", role);
        return "Role/detail";
    }

    @GetMapping("/edit/{id}")
    public String editRole(@PathVariable int id, Model model){
        Role role = roleService.getRoleId(id);
        model.addAttribute("role", role);
        return "Role/edit.html";
    }

    @PostMapping("/saveedit")
    public String saveRoleEdit(RoleEdit roleEdit){
        roleService.UpdateRole(roleEdit);
        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable int id){
        roleService.DeleteRole(id);
        return "redirect:/roles";
    }

    @GetMapping("/findname")
    public String findname(Model model){
        Role role = roleService.findRoleByName("A1");
        model.addAttribute("role", role);
        return "Role/edit.html";
    }
}
