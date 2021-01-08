package com.example.thymeleaf.controller;

import java.util.List;

import com.example.thymeleaf.model.Board;
import com.example.thymeleaf.model.User;
import com.example.thymeleaf.repository.UserRepository;
import com.example.thymeleaf.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserApiController {

    private UserRepository repository;
    private UserService userService;

    @PostMapping("/test")
    void register(@RequestBody User user) {
        userService.save(user);
    }

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    // Single item
    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        // newUser에는 보드 정보만 담겨 있다
        // id를 통해 user정보를 가져와서 user에 담고
        // user에 newUser에 있는 보드 정보를 담는다.
        return repository.findById(id).map(user -> {
            // user에 있는 보드 정보에는 user_id 아이디가 없기에
            // board.setUser를 통해 user_id를 넣어준다.
            // user.setBoards(newUser.getBoards());
            //
            user.getBoards().clear();
            user.getBoards().addAll(newUser.getBoards());
            for (Board board : user.getBoards()) {
                board.setUser(user);
            }
            return repository.save(user);
        }).orElseGet(() -> {
            newUser.setId(id);
            return repository.save(newUser);
        });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}