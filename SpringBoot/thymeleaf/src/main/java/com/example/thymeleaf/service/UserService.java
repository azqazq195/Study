package com.example.thymeleaf.service;

import java.util.ArrayList;
import java.util.List;

import com.example.thymeleaf.model.Role;
import com.example.thymeleaf.model.Tag;
import com.example.thymeleaf.model.User;
import com.example.thymeleaf.repository.TagRepository;
import com.example.thymeleaf.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private TagRepository tagRepository;
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);

        Role role = new Role();
        role.setId(1L);
        user.getRoles().add(role);

        List<Tag> tags = new ArrayList<>();
        for (Tag tag : user.getTags()) {
            tags.add(tagRepository.findByName(tag.getName()));
        }

        user.setTags(tags);
        return userRepository.save(user);
    }

}
