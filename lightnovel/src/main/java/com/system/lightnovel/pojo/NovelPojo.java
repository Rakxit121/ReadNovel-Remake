package com.system.lightnovel.pojo;

import com.system.lightnovel.entity.Novel;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NovelPojo {
    private Integer id;

    private String title;
    private String description;
    private String author;
    private int rating;
    private String genre;
    private String status;
    private String latestChapter;

    @Lob
    private MultipartFile imageData;

    public NovelPojo(Novel novel) {
        this.id = novel.getId();
        this.title = novel.getTitle();
        this.description = novel.getDescription();
        this.author = novel.getAuthor();
        this.rating = novel.getRating();
        this.genre = novel.getGenre();
        this.status = novel.getStatus();
        this.latestChapter = novel.getLatestChapter();
//        this.imageData = novel.getImageData();
    }

}