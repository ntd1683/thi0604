package org.example.thi0604.Services;

import org.example.thi0604.Entities.Role;
import org.example.thi0604.Entities.User;
import org.example.thi0604.Repositories.RoleRepository;
import org.example.thi0604.Repositories.UserRepository;
import org.example.thi0604.RequestEntities.UserCreate;
import org.example.thi0604.RequestEntities.UserEdit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserById(String id){
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Khong tim thay ID")
        );
    }

    public User createUser(UserCreate userCreate){
        try{
            User user = new User();
            user.setUsername(userCreate.getUsername());
            user.setFirstName(userCreate.getFirstName());
            user.setLastName(userCreate.getLastName());
            user.setEmail(userCreate.getEmail());
            user.setPassword(userCreate.getPassword());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthday(dateFormat.parse(userCreate.getBirthday()));
            Role role = roleRepository.findById(userCreate.getRole())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user.setRole(role);
            userRepository.save(user);
            return user;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }
    public User UpdateUser(UserEdit userEdit){
        try{
            User user = getUserById(userEdit.getId());
            user.setFirstName(userEdit.getFirstName());
            user.setLastName(userEdit.getLastName());
            user.setEmail(userEdit.getEmail());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthday(dateFormat.parse(userEdit.getBirthday()));
            user.setAge((new Date()).getYear()-user.getBirthday().getYear());
            Role role = roleRepository.findById(userEdit.getRole())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user.setRole(role);
            userRepository.save(user);
            return user;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void DeleteUser(String id){
        User user = getUserById(id);
        user.setIsDeleted(true);
        userRepository.save(user);
    }
}
