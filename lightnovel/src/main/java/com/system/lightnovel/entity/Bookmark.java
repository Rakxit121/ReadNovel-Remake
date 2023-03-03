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

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name = "rating")
    private int rating;

    @Column(name = "genre")
    private String genre;

    @Column(name = "status")
    private String status;

    @Column(name = "latest_chapter")
    private String latestChapter;


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

