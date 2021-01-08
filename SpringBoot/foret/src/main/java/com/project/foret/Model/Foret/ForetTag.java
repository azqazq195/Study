package com.project.foret.Model.Foret;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "foret_tag")
@Getter
@Setter
@NoArgsConstructor
public class ForetTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tag_name;

    @Builder
    public ForetTag(String tag_name) {
        this.tag_name = tag_name;
    }
}
