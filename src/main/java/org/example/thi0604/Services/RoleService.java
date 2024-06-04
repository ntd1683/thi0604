package org.example.thi0604.Services;

import org.example.thi0604.Entities.Role;
import org.example.thi0604.Repositories.RoleRepository;
import org.example.thi0604.RequestEntities.RoleCreate;
import org.example.thi0604.RequestEntities.RoleEdit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }

    public Role getRoleId(int id){
        return roleRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Khong tim thay ID")
        );
    }
    public Role CreateRole(RoleCreate roleCreate){
        try{
            Role role = new Role();
            role.setName(roleCreate.getName());
            roleRepository.save(role);
            return role;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    public Role UpdateRole(RoleEdit roleEdit){
        try{
            Role role = getRoleId(roleEdit.getRole_id());
            role.setName(roleEdit.getName());
            roleRepository.save(role);
            return role;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }
    public Role findRoleByName(String name){
        return roleRepository.findRolesByName(name);
    }
    public void DeleteRole(int id){
        roleRepository.deleteById(id);
    }
}
