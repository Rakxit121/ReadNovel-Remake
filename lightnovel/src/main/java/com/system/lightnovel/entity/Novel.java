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
    private byte[] imageData;

//    @OneToMany(mappedBy = "novel")
//    private List<Bookmark> bookmarks = new ArrayList<>();
}


//    public Novel() {}
//
//    public Novel(String title, String description, String author, int rating, String genre, String status, String latestChapter, byte[] imageData) {
//        this.title = title;
//        this.description = description;
//        this.author = author;
//        this.rating = rating;
//        this.genre = genre;
//        this.status = status;
//        this.latestChapter = latestChapter;
//        this.imageData = imageData;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public int getRating() {
//        return rating;
//    }
//
//    public void setRating(int rating) {
//        this.rating = rating;
//    }
//
//    public String getGenre() {
//        return genre;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getLatestChapter() {
//        return latestChapter;
//    }
//
//    public void setLatestChapter(String latestChapter) {
//        this.latestChapter = latestChapter;
//    }
//
//    public byte[] getImageData() {
//        return imageData;
//    }
//
//    public void setImageData(byte[] imageData) {
//        this.imageData = imageData;
//    }
//
//    public List<Bookmark> getBookmarks() {
//        return bookmarks;
//    }
//
//    public void setBookmarks(List<Bookmark> bookmarks) {
//        this.bookmarks = bookmarks;
//    }
//}