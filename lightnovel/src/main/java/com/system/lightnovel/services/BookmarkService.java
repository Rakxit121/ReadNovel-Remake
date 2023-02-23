package com.system.lightnovel.services;

import com.system.lightnovel.entity.Bookmark;

import java.util.List;

public interface BookmarkService {

    void deleteBookmark(Integer id);

    Bookmark addBookmark(Integer novelId, Integer userId);

    List<Bookmark> getBookmarksByNovelId(Integer id);
}
