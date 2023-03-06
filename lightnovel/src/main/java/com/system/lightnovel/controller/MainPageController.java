package com.system.lightnovel.controller;

import com.system.lightnovel.entity.Bookmark;
import com.system.lightnovel.entity.Novel;
import com.system.lightnovel.entity.User;
import com.system.lightnovel.pojo.BookmarkPojo;
import com.system.lightnovel.pojo.UserPojo;
import com.system.lightnovel.repo.BookmarkRepo;
import com.system.lightnovel.repo.NovelRepo;
import com.system.lightnovel.services.BookmarkService;
import com.system.lightnovel.services.NovelService;
import com.system.lightnovel.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
//@RequiredArgsConstructor
@RequestMapping("")
public class MainPageController {
    private final UserService userService;
    private final NovelService novelService;
    private final NovelRepo novelRepo;
//    private final NovelService novelService;

    @Autowired
    private final BookmarkService bookmarkService;

    private final BookmarkRepo bookmarkRepo;

    public MainPageController(UserService userService , NovelService novelService, NovelRepo novelRepo, BookmarkService bookmarkService, BookmarkRepo bookmarkRepo) {
        this.userService = userService;
        this.novelService = novelService;
        this.novelRepo = novelRepo;
        this.bookmarkService = bookmarkService;
        this.bookmarkRepo = bookmarkRepo;
    }

    @GetMapping("/main")
    public String getMainPage(Model model) {
        List<Novel> novels = novelService.fetchAll();
        model.addAttribute("novels", novels);
        return "users/mainpage";
    }

    @GetMapping("/novelSitePage/{id}")
    public String getNovelPage(@PathVariable("id") Integer id, Model model, BookmarkPojo bookmarkPojo) {
        Novel novel = novelService.fetchById(id);
        model.addAttribute("novels", novel);
//        bookmarkService.addBookmark(bookmarkPojo);
        return "users/novelPage";
    }

    @GetMapping("/saveBookmark/{n_id}")
    public String getSaveBookmarkPage(@Valid BookmarkPojo bookmarkPojo, Principal principal, @PathVariable("n_id") Integer n_id) throws ParseException {
        Integer u_id = userService.findByEmail(principal.getName()).getId();
        bookmarkPojo.setUserId(u_id);
        bookmarkPojo.setNovelId(n_id);

//        String b_title = novelService.fetchById(Integer.valueOf(principal.getName())).getTitle();
//        String b_description = novelService.fetchById(Integer.valueOf(principal.getName())).getTitle();
//        String b_author = novelService.fetchById(Integer.valueOf(principal.getName())).getTitle();
//        String b_genre = novelService.fetchById(Integer.valueOf(principal.getName())).getTitle();
//        String b_status = novelService.fetchById(Integer.valueOf(principal.getName())).getTitle();
//        String b_rating = novelService.fetchById(Integer.valueOf(principal.getName())).getTitle();
//
        bookmarkService.addBookmark(bookmarkPojo);
        return "redirect:/novelSitePage/" + n_id;
    }


//    @GetMapping("/saveBookmark/{n_id}")
//    public String getSaveBookmarkPage(@Valid BookmarkPojo bookmarkPojo, Principal principal, @PathVariable("n_id") Integer n_id) throws ParseException {
//        Integer u_id = userService.findByEmail(principal.getName()).getId();
//        bookmarkPojo.setUserId(u_id);
//        bookmarkPojo.setNovelId(n_id);
//        bookmarkService.addBookmark(bookmarkPojo);
//        return "redirect:/novelSitePage/" + n_id;
//    }

    @GetMapping("/bookmarkPage")
    public String getBookmarkPage(Model model){
        List<Bookmark> bookmarks = bookmarkService.getAllBookmarks();
        model.addAttribute("bookmarks", bookmarks);
//        bookmarkService.deleteBookmark(id);
        return "users/Bookmark";
    }

    @GetMapping("deleteBookmark/{id}")
    public ResponseEntity<String> deleteBookmark(@PathVariable("id") Integer id) {
        try {
            bookmarkService.deleteBookmark(id);
            return new ResponseEntity<>("Bookmark with ID " + id + " deleted", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid request body: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Bookmark with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
//        return "redirect:/bookmarkPage";
    }

//        bookmarkService.deleteBookmark(id);
//        return "redirect:/bookmarkPage";
//    }



    @GetMapping("/profile")
    public String getUserProfile(Integer id, Model model, Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        model.addAttribute("update", new UserPojo());
        model.addAttribute("info", userService.findByEmail(principal.getName()));
        return "users/user_profile";
    }
//
//    @GetMapping("/profile")
//    public String getUserProfile(Model model, Integer id) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        User user = userService.fetchById(id);
//        model.addAttribute("user", user);
//        return "users/user_profile";
//    }



//    @GetMapping("/users")
//    public String getAllUsers(Model model) {
//        List<User> users = userService.fetchAll();
//        model.addAttribute("users", users);
//        return "users/user_list";
//    }

//    @GetMapping("/users/profile/{id}")
//    public String getUserProfile(Model model, @PathVariable("id") Integer id) {
//        User users = userService.fetchById(id);
//        model.addAttribute("user_profile", users);
//        return "users/user_profile";
//    }
//




//    @PostMapping("/updateUser")
//    public String updateUser(@Valid UserPojo userpojo) {
//        userService.save(userpojo);
//        return "redirect:/profile";
//    }

//    @PostMapping("/updateProfile/{id}")
//    public String updateUserProfile(@PathVariable("id") Integer id) {
//        userService.update();
//        return "redirect:/users/profile";
//    }

    @PostMapping("/updateProfile/{id}")
    public String updateUserProfile(@PathVariable("id") Integer id, @ModelAttribute("user") UserPojo userPojo) {
        userService.update(userPojo);
        return "redirect:/profile";
    }

}
