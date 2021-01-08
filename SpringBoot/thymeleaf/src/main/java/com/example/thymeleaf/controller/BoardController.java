package com.example.thymeleaf.controller;

import javax.validation.Valid;

import com.example.thymeleaf.model.Board;
import com.example.thymeleaf.repository.BoardRepository;
import com.example.thymeleaf.service.BoardService;
import com.example.thymeleaf.validator.BoardValidator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private BoardRepository boardRepository;
    private BoardValidator boardValidator;
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(page = 0, size = 2) Pageable pageable,
            @RequestParam(required = false, defaultValue = "") String searchText) {
        // 첫번쨰 페이지는 0
        // Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if (id == null) {
            // 새글 작성
            model.addAttribute("board", new Board());
        } else {
            // 수정
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String postForm(@Valid Board board, BindingResult bindingResult, Authentication authentication) {
        boardValidator.validate(board, bindingResult);
        // BoardDTO에 들어있는 NotNull, Size 어노테이션을 만족 시키는가
        if (bindingResult.hasErrors()) {
            return "board/form";
        }
        String username = authentication.getName();
        boardService.save(username, board);
        return "redirect:/board/list";
    }
}
