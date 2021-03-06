package com.project.foret.Model.Member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member_like_comment")
@Getter
@Setter
@NoArgsConstructor
public class MemberLikeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
