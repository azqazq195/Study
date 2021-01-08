package com.example.jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.NaturalId;

import lombok.Data;

@Entity
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String name;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Member> members = new ArrayList<>();

    // Getters and setters ommitted for brevity

}