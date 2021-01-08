package com.example.thymeleaf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 2, max = 30, message = "제목은 2자 이상 30자 이하입니다.")
    private String title;
    private String content;

    @ManyToOne
    // user 에 @Id 설정 되었기 떄문에 referencedColumnName 생략 가능
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
}
