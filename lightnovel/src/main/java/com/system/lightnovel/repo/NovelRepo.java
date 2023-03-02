package com.system.lightnovel.repo;


import com.system.lightnovel.entity.Bookmark;
import com.system.lightnovel.entity.Novel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NovelRepo extends JpaRepository<Novel, Integer> {

//    Optional<Novel> findBookmarksByNovelIdndNovelByTitle(String title);

//    List<Bookmark> findBookmarksByNovelId(Integer id);

//    Bookmark saveBookmark(Bookmark bookmark);

//    void deleteBookmark(Integer id);
}
