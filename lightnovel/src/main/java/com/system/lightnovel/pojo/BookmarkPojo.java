package com.system.lightnovel.pojo;

import com.system.lightnovel.entity.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkPojo {
    private Integer id;
    private Integer userId;
    private Integer novelId;
    private String title;
    private String description;
    private String author;
    private int rating;
    private String genre;
    private String status;
    private String latestChapter;

    public BookmarkPojo(Bookmark bookmark) {
        this.id = bookmark.getId();
        this.userId = bookmark.getUserId().getId();
        this.novelId = bookmark.getNovelId().getId();
        this.title = bookmark.getTitle();
        this.description = bookmark.getDescription();
        this.author = bookmark.getAuthor();
        this.rating = bookmark.getRating();
        this.genre = bookmark.getGenre();
        this.status = bookmark.getStatus();
        this.latestChapter = bookmark.getLatestChapter();
    }

}
