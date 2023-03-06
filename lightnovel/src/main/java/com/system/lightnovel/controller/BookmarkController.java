package com.system.lightnovel.controller;

import com.system.lightnovel.entity.Bookmark;
import com.system.lightnovel.pojo.BookmarkPojo;
import com.system.lightnovel.services.BookmarkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ch.qos.logback.core.model.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;


@Controller
@RequiredArgsConstructor
@RequestMapping("/bookmarks")
public class BookmarkController {

//    @Autowired
    private BookmarkService bookmarkService;



//    @GetMapping("/bookmark/{id}")
//    public String getBookmarkById(@PathVariable Integer id) {
//        try {
//            Bookmark bookmark = bookmarkService.getBookmarksByNovelId(id);
//            return String.valueOf(new ResponseEntity<>(bookmark, HttpStatus.OK));
//        } catch (NoSuchElementException e) {
//            return String.valueOf(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        }
//    }

    @GetMapping("/bookmark/{id}")
    public String addBookmark(@PathVariable Integer id, @Valid BookmarkPojo bookmarkPojo, Principal principal, RedirectAttributes redirectAttributes) throws ParseException {
//        try {
//            Bookmark bookmark = bookmarkService.getBookmarksByNovelId(id);
//            return String.valueOf(new ResponseEntity<>(bookmark, HttpStatus.OK));
//        } catch (NoSuchElementException e) {
//            return String.valueOf(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        }
//        Integer id = userService.findByEmail(principal.getName()).getId();
        bookmarkPojo.setUserId(id);
        bookmarkPojo.setNovelId(bookmarkPojo.getNovelId());
        bookmarkService.addBookmark(bookmarkPojo);

        redirectAttributes.addFlashAttribute("message", "Your Bookmark was placed") ;
        return "redirect:/home";
    }

//    @PostMapping("/")
//    public String addBookmark(@ModelAttribute("bookmark") BookmarkPojo bookmarkPojo) {
//        bookmarkService.addBookmark(bookmarkPojo);
//        return "redirect:/";
////        return null;
//    }


//    @DeleteMapping("deleteBookmark/{id}")
//    public String deleteBookmark(@PathVariable("id") Integer id) {
////        try {
////            bookmarkService.deleteBookmark(id);
////            return new ResponseEntity<>("Bookmark with ID " + id + " deleted", HttpStatus.OK);
////        } catch (IllegalArgumentException e) {
////            return new ResponseEntity<>("Invalid request body: " + e.getMessage(), HttpStatus.BAD_REQUEST);
////        } catch (NoSuchElementException e) {
////            return new ResponseEntity<>("Bookmark with ID " + id + " not found", HttpStatus.NOT_FOUND);
////        }
//////        return "redirect:/bookmarkPage";
////    }
//
//        bookmarkService.deleteBookmark(id);
//        return "redirect:/bookmarkPage";
//    }

}

