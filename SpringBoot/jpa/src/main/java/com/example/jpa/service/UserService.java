package com.example.jpa.service;

import com.example.jpa.entity.Role;
import com.example.jpa.entity.User;
import com.example.jpa.model.RoleModel;
import com.example.jpa.model.UserModel;
import com.example.jpa.repository.RoleRepository;
import com.example.jpa.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Create a new User
     */
    public ResponseEntity<Object> createUser(User model) {
        User user = new User();
        if (userRepository.findByEmail(model.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("The Email is already Present, Failed to Create new User");
        } else {
            user.setFirstName(model.getFirstName());
            user.setLastName(model.getLastName());
            user.setMobile(model.getMobile());
            user.setEmail(model.getEmail());
            if (model.getRoles() != null) {
                for (Role role : model.getRoles()) {
                    user.addRole(roleRepository.findByName(role.getName()).get());
                }
            }
            // user.setRoles(model.getRoles());

            User savedUser = userRepository.save(user);
            if (userRepository.findById(savedUser.getId()).isPresent())
                return ResponseEntity.ok("User Created Successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed Creating User as Specified");
        }
    }

    /**
     * Update an Existing User
     */
    @Transactional
    public ResponseEntity<Object> updateUser(User user, Long id) {
        if (userRepository.findById(id).isPresent()) {
            User newUser = userRepository.findById(id).get();
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setMobile(user.getMobile());
            newUser.setEmail(user.getEmail());
            newUser.setRoles(user.getRoles());
            User savedUser = userRepository.save(newUser);
            if (userRepository.findById(savedUser.getId()).isPresent())
                return ResponseEntity.accepted().body("User updated successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed updating the user specified");
        } else return ResponseEntity.unprocessableEntity().body("Cannot find the user specified");
    }

    /**
     * Delete an User
     */
    public ResponseEntity<Object> deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            if (userRepository.findById(id).isPresent())
                return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified User");
            else return ResponseEntity.ok().body("Successfully deleted the specified user");
        } else return ResponseEntity.badRequest().body("Cannot find the user specified");
    }

    public UserModel getUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            UserModel userModel = new UserModel();
            userModel.setFirstName(user.getFirstName());
            userModel.setLastName(user.getLastName());
            userModel.setEmail(user.getEmail());
            userModel.setMobile(user.getMobile());
            userModel.setRoles(getRoleList(user));
            return userModel;
        } else return null;
    }

    public List<UserModel> getUserByRole(Long id) {
        if (userRepository.findByRoles_id(id).get(0).isPresent()) {
            List<Optional<User>> users = userRepository.findByRoles_id(id);
            List<UserModel> userModels = new ArrayList<>();
            for (Optional<User> user : users) {
                UserModel userModel = new UserModel();
                userModel.setFirstName(user.get().getFirstName());
                userModel.setLastName(user.get().getLastName());
                userModel.setEmail(user.get().getEmail());
                userModel.setMobile(user.get().getMobile());
                userModel.setRoles(getRoleList(user.get()));
                userModels.add(userModel);
            }
            return userModels;
        } else return null;
    }

    public List<UserModel> getUsers() {
        List<User> userList = userRepository.findAll();
        if (userList.size() > 0) {
            List<UserModel> userModels = new ArrayList<>();
            for (User user : userList) {
                UserModel model = new UserModel();
                model.setFirstName(user.getFirstName());
                model.setLastName(user.getLastName());
                model.setMobile(user.getMobile());
                model.setEmail(user.getEmail());
                model.setRoles(getRoleList(user));
                userModels.add(model);
            }
            return userModels;
        } else return new ArrayList<>();
    }

    private List<RoleModel> getRoleList(User user) {
        List<RoleModel> roleList = new ArrayList<>();
        for (int i = 0; i < user.getRoles().size(); i++) {
            RoleModel roleModel = new RoleModel();
            roleModel.setName(user.getRoles().get(i).getName());
            roleModel.setDescription(user.getRoles().get(i).getDescription());
            roleList.add(roleModel);
        }
        return roleList;
    }
}




