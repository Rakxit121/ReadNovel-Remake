package com.system.lightnovel.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookmark")
public class Bookmark {

    @Id
    @SequenceGenerator(name = "bookmark_seq_gen", sequenceName = "bookmark_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "bookmark_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_userId"))
    private User userId;

    @ManyToOne
    @JoinColumn(name = "novel_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_novelId"))
    private Novel novelId;

//    public void setNovel(Novel novel) {
//        this.novelId = novel;
//    }
//
//    public Novel getNovel() {
//        return novel;
//    }

//    @Column
//    private String novelName;
//
//    @Column
//    private Integer novelChapter;
//
//    @Column
//    private String novelAuthor;
//
//    @Column
//    private Integer novelDescription;

}

