package com.system.lightnovel.services;

import com.system.lightnovel.entity.Bookmark;
import com.system.lightnovel.pojo.BookmarkPojo;

import java.util.List;

public interface BookmarkService {

    BookmarkPojo addBookmark(BookmarkPojo bookmarkPojo);

    List<Bookmark> getAllBookmarks();

    void deleteBookmark(Integer id);

    Bookmark getBookmarksByNovelId(Integer id);
}
