package com.system.lightnovel.services.Impl;

import com.system.lightnovel.entity.Bookmark;
import com.system.lightnovel.pojo.BookmarkPojo;
import com.system.lightnovel.repo.BookmarkRepo;
import com.system.lightnovel.repo.NovelRepo;
import com.system.lightnovel.services.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookmarkServiceImpl implements BookmarkService {

    public final NovelRepo novelRepo;
    public final BookmarkRepo bookmarkRepo;

    @Override
    public void deleteBookmark(Integer id) {
        novelRepo.deleteById(id);
    }

    @Override
    public BookmarkPojo addBookmark(BookmarkPojo bookmarkPojo) {
        Bookmark bookmark=new Bookmark();
//        Novel novel = novelRepo.findById(novelId)
//                .orElseThrow(() -> new NotFoundException("Novel not found"));
//
//        Bookmark bookmark = new Bookmark();
//        bookmark.setNovelId(novel);
//        bookmark.setId(userId);
        bookmarkRepo.save(bookmark);
        return null;

    }

    @Override
    public List<Bookmark> getBookmarksByNovelId() {
        return this.bookmarkRepo.findAll();
    }
}
