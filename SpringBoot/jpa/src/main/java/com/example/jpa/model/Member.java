package com.example.jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // 양방향
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Profile> profiles = new ArrayList<>();

    public void addProfile(Profile profile) {
        // member에 profile을 추가시키고
        profiles.add(profile);
        // profile에 member를 추가시킨다.
        profile.setMember(this);
    }

    public void removeProfile(Profile profile) {
        // member에 있는 profile을 지우고
        profiles.remove(profile);
        // profile에 있는 member를 지운다.
        profile.setMember(null);
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "member_tag", joinColumns = @JoinColumn(name = "member_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getMembers().add(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getMembers().remove(this);
    }
}
