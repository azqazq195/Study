package com.example.demo.Controller;

import com.example.demo.Model.Comment;
import com.example.demo.Model.Post;
import com.example.demo.Repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/insert")
    public void insert() {
        Post post = new Post("JPA", "JPA blabla");

        Comment comment1 = new Comment("good");
        Comment comment2 = new Comment("bad");
        Comment comment3 = new Comment("soso");

        post.getComment().add(comment1);
        post.getComment().add(comment2);
        post.getComment().add(comment3);

        this.postRepository.save(post);
    }

    @GetMapping("/delete")
    public void delete(@RequestParam("id") long id) {
        this.postRepository.deleteById(id);
    }

    @GetMapping("/get")
    public Post get(@RequestParam("id") long id) {
        return postRepository.findPostById(id);
    }
}
