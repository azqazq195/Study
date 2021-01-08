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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.project.foret.Model.Board.BoardPhoto;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "foret_board")
@Getter
@Setter
@NoArgsConstructor
public class ForetBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private int type;

    @Column(name = "hit")
    private int hit;

    @Column(name = "subject")
    private String subject;

    @Column(name = "content")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "reg_date")
    private Date reg_date;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "edit_date")
    private Date edit_date;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    List<ForetBoardPhoto> photo = new ArrayList<>();

    @Builder
    public ForetBoard(int type, String subject, String content) {
        this.type = type;
        this.subject = subject;
        this.content = content;
    }

}
