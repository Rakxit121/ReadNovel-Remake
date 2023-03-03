package com.system.lightnovel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "novel")
public class Novel {

    @Id
    @SequenceGenerator(name = "novel_seq_gen", sequenceName = "novel_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "novel_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

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

    @Lob
    @Column(name = "image_data")
    private String imageData;

    @Transient
    private String imageBase64;

}