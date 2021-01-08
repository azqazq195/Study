package com.example.thymeleaf.controller;

import java.util.List;

import com.example.thymeleaf.model.Board;
import com.example.thymeleaf.repository.BoardRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BoardApiController {

    private BoardRepository repository;

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/boards")
    List<Board> all(@RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false, defaultValue = "") String content) {
        if (StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            return repository.findAll();
        } else {
            return repository.findByTitleOrContent(title, content);
        }

    }
    // end::get-aggregate-root[]

    @PostMapping("/boards")
    Board newBoard(@RequestBody Board newBoard) {
        return repository.save(newBoard);
    }

    // Single item
    @GetMapping("/boards/{id}")
    Board one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/boards/{id}")
    Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long id) {

        return repository.findById(id).map(board -> {
            board.setTitle(newBoard.getTitle());
            board.setContent(newBoard.getContent());
            return repository.save(board);
        }).orElseGet(() -> {
            newBoard.setId(id);
            return repository.save(newBoard);
        });
    }

    @DeleteMapping("/boards/{id}")
    void deleteBoard(@PathVariable Long id) {
        repository.deleteById(id);
    }
}