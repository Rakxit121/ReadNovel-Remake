package com.system.lightnovel.controller;

import com.system.lightnovel.entity.Novel;
import com.system.lightnovel.pojo.UserPojo;
import com.system.lightnovel.repo.NovelRepo;
import com.system.lightnovel.services.NovelService;
import com.system.lightnovel.services.UserService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("")
public class MainPageController {
    private final UserService userService;
//    private final NovelService novelService;
    private final NovelRepo novelRepo;
    private final NovelService novelService;

    public MainPageController(UserService userService , NovelService novelService, NovelRepo novelRepo) {
        this.userService = userService;
        this.novelService = novelService;
        this.novelRepo = novelRepo;
    }

    @GetMapping("/main")
    public String getMainPage(Model model) {
//        List<Novel> novels = novelService.findAllNovel();
//        model.addAttribute("novels", novels);
        return "users/mainpage";
    }

    @GetMapping("/novelSitePage")
    public String getNovelPage(Model model) {
        List<Novel> novels = novelService.fetchAll();
        model.addAttribute("novels", novels);
        return "users/novelPage";
    }

    @GetMapping("/bookmarkPage")
    public String getBookmark(){
        return "users/Bookmark";
    }

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

    @PostMapping("/updateUser")
    public String updateUser(@Valid UserPojo userpojo) {
        userService.save(userpojo);
        return "redirect:/homepage/profile";
    }
}
