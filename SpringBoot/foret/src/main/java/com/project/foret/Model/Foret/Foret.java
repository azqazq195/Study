package com.project.foret.Model.Foret;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "foret")
@Getter
@Setter
@NoArgsConstructor
public class Foret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "leader_id")
    private int leader_id;

    @Column(name = "name")
    private String name;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "max_member")
    private int max_member;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "reg_date")
    private Date reg_date;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "foret_id", referencedColumnName = "id")
    List<ForetTag> tag = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "foret_id", referencedColumnName = "id")
    List<ForetRegion> region = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "foret_id", referencedColumnName = "id")
    List<ForetPhoto> photo = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "foret_id", referencedColumnName = "id")
    List<ForetBoard> board = new ArrayList<>();

    @Builder
    public Foret(String name, String introduce, int max_member) {
        this.name = name;
        this.introduce = introduce;
        this.max_member = max_member;
    }
}
