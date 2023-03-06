package com.system.lightnovel.services.Impl;

import com.system.lightnovel.entity.Bookmark;
import com.system.lightnovel.pojo.BookmarkPojo;
import com.system.lightnovel.repo.BookmarkRepo;
import com.system.lightnovel.repo.NovelRepo;
import com.system.lightnovel.repo.UserRepo;
import com.system.lightnovel.services.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.system.lightnovel.entity.Novel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    public final NovelRepo novelRepo;
    public final UserRepo userRepo;
    public final BookmarkRepo bookmarkRepo;


    @Override
    public void deleteBookmark(Integer id) {
        novelRepo.deleteById(id);
    }

//    @Override
//    public BookmarkPojo addBookmark(BookmarkPojo bookmarkPojo) {
//        Bookmark bookmark=new Bookmark();
////        bookmark.setId(bookmarkPojo.getId());
////        bookmark.setNovelId(bookmarkPojo.getNovelId());
////        bookmark.setUserId(bookmarkPojo.getUserId());
//        bookmark.setUserId(userRepo.findById(bookmarkPojo.getUserId()).orElseThrow());
//        bookmark.setNovelId(novelRepo.findById(bookmarkPojo.getNovelId()).orElseThrow());
////        bookmark.setTitle(novelRepo.findById(bookmarkPojo.getTitle()).orElseThrow());
////        bookmark.setDescription(novelRepo.findById(bookmarkPojo.getDescription()).orElseThrow());
////        bookmark.setAuthor(novelRepo.findById(bookmarkPojo.getAuthor()).orElseThrow());
////        bookmark.setGenre(novelRepo.findById(bookmarkPojo.getGenre()).orElseThrow());
////        bookmark.setStatus(novelRepo.findById(bookmarkPojo.getStatus()).orElseThrow());
////        bookmark.setRating(novelRepo.findById(bookmarkPojo.getRating()).orElseThrow());
//
//        bookmarkRepo.save(bookmark);
////        return new BookmarkPojo(bookmark);
//            return null;
////        return bookmarkPojo;
//    }


//    @Override
//    public BookmarkPojo addBookmark(BookmarkPojo bookmarkPojo) {
//        Bookmark bookmark = new Bookmark();
//        bookmark.setUserId(userRepo.findById(bookmarkPojo.getUserId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id")));
//        bookmark.setNovelId(novelRepo.findById(bookmarkPojo.getNovelId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid novel Id")));
////        bookmark.setTitle(String.valueOf(novelRepo.findById(bookmarkPojo.getNovelId())
////                .orElseThrow(() -> new IllegalArgumentException("Invalid novel Id"))));
//        bookmark.setTitle(bookmarkPojo.getTitle());
//        bookmark.setDescription(bookmarkPojo.getDescription());
//        bookmark.setAuthor(bookmarkPojo.getAuthor());
//        bookmark.setGenre(bookmarkPojo.getGenre());
//        bookmark.setStatus(bookmarkPojo.getStatus());
//        bookmark.setRating(bookmarkPojo.getRating());
//
//
//        bookmarkRepo.save(bookmark);
//        return new BookmarkPojo(bookmark);
//    }


    @Override
    public BookmarkPojo addBookmark(BookmarkPojo bookmarkPojo) {
        Bookmark bookmark = new Bookmark();
        bookmark.setUserId(userRepo.findById(bookmarkPojo.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id")));
        bookmark.setNovelId(novelRepo.findById(bookmarkPojo.getNovelId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid novel Id")));
        bookmark.setTitle(novelRepo.findById(bookmarkPojo.getNovelId())
                .map(Novel::getTitle)
                .orElseThrow(() -> new IllegalArgumentException("Invalid novel Id")));
        bookmark.setDescription(novelRepo.findById(bookmarkPojo.getNovelId())
                .map(Novel::getDescription)
                .orElseThrow(() -> new IllegalArgumentException("Invalid novel Id")));
        bookmark.setAuthor(novelRepo.findById(bookmarkPojo.getNovelId())
                .map(Novel::getAuthor)
                .orElseThrow(() -> new IllegalArgumentException("Invalid novel Id")));
        bookmark.setGenre(novelRepo.findById(bookmarkPojo.getNovelId())
                .map(Novel::getGenre)
                .orElseThrow(() -> new IllegalArgumentException("Invalid novel Id")));
        bookmark.setStatus(novelRepo.findById(bookmarkPojo.getNovelId())
                .map(Novel::getStatus)
                .orElseThrow(() -> new IllegalArgumentException("Invalid novel Id")));
        bookmark.setRating(novelRepo.findById(bookmarkPojo.getNovelId())
                .map(Novel::getRating)
                .orElseThrow(() -> new IllegalArgumentException("Invalid novel Id")));

        bookmarkRepo.save(bookmark);
        return new BookmarkPojo(bookmark);
    }




    @Override
    public Bookmark getBookmarksByNovelId(Integer id) {
        Optional<Bookmark> optionalBookmark = bookmarkRepo.findById(id);
        if (optionalBookmark.isPresent()) {
            return optionalBookmark.get();
        } else {
            throw new IllegalArgumentException("Bookamrk with ID " + id + " not found.");
        }
    }



    @Override
    public List<Bookmark> getAllBookmarks() {
        return this.bookmarkRepo.findAll();
//        List<Bookmark> allBookmark = bookmarkRepo.findAll();
//        return allBookmark;
    }



    public String getImageBase64(String fileName) {
        if (fileName!=null) {
            String filePath = System.getProperty("user.dir")+"/images/novels/";
            File file = new File(filePath + fileName);
            byte[] bytes;
            try {
                bytes = Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return Base64.getEncoder().encodeToString(bytes);
        }
        return null;
    }
}


