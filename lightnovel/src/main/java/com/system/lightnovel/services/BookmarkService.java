package com.system.lightnovel.services;

import com.system.lightnovel.entity.Bookmark;
import com.system.lightnovel.pojo.BookmarkPojo;

import java.util.List;

public interface BookmarkService {

    void deleteBookmark(Integer id);

    BookmarkPojo addBookmark(BookmarkPojo bookmarkPojo);

    List<Bookmark> getBookmarksByNovelId();
}
