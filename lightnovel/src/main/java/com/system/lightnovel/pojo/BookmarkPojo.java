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
    public BookmarkPojo(Bookmark bookmark) {
        this.id = bookmark.getId();
        this.userId = bookmark.getUserId().getId();
        this.novelId = bookmark.getNovelId().getId();
    }

}
