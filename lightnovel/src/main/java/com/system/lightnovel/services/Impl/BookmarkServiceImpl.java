package com.system.lightnovel.services.Impl;

import com.system.lightnovel.entity.Bookmark;
import com.system.lightnovel.entity.Novel;
import com.system.lightnovel.repo.NovelRepo;
import com.system.lightnovel.services.BookmarkService;

import java.util.List;

public class BookmarkServiceImpl implements BookmarkService {

    public final NovelRepo novelRepo;

    public BookmarkServiceImpl(NovelRepo novelRepo) {
        this.novelRepo = novelRepo;
    }

    @Override
    public void deleteBookmark(Integer id) {
        novelRepo.deleteBookmark(id);
    }

    @Override
    public Bookmark addBookmark(Integer novelId, Integer userId) {
        Novel novel = novelRepo.findById(novelId)
                .orElseThrow(() -> new NotFoundException("Novel not found"));

        Bookmark bookmark = new Bookmark();
        bookmark.setNovelId(novel);
        bookmark.setId(userId);
        return novelRepo.saveBookmark(bookmark);
    }

    @Override
    public List<Bookmark> getBookmarksByNovelId(Integer id) {
        return novelRepo.findBookmarksByNovelId(id);
    }
}
