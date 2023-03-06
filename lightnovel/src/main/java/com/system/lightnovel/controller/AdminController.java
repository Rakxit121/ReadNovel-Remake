package com.system.lightnovel.controller;


import com.system.lightnovel.entity.Feedback;
import com.system.lightnovel.entity.Novel;
import com.system.lightnovel.entity.User;
import com.system.lightnovel.pojo.NovelPojo;
import com.system.lightnovel.services.NovelService;
import com.system.lightnovel.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    private final NovelService novelService;


//    private NovelService novelService;

//    public AdminController(NovelService novelService) {
//        this.novelService = novelService;
//    }

//    public AdminController(NovelService novelService) {
//        this.novelService = novelService;
//    }

    @Autowired
//    private NovelService novelService;

    @GetMapping("")
    public String getAdminDashboard(){
        return "redirect:/admin/novelList";
    }

    @GetMapping("/novelList")
    public String getNovelList(Model model){
        List<Novel> novels = novelService.fetchAll();
        model.addAttribute("novel", novels);
        return "admin/adminBookList";
    }

    @GetMapping("/admin/edit")
    public String editBookForm(@PathVariable("id") Integer id, Model model, NovelPojo novelPojo) {
        novelService.update(novelPojo);
//        model.addAttribute("book", novel);
        return "redirect:/novelForm";
    }

    @GetMapping("/deletenovel/{id}")
    public String deleteNovel(@PathVariable("id") Integer id) {
        userService.deleteFeedback(id);
        return "redirect:/admin/novelList";
    }



    @GetMapping("/novelForm")
    public String getNovelForm(Model model){
        model.addAttribute("addnovel", new NovelPojo());
        return "admin/adminNovelForm";
    }
    @PostMapping("/save")
    public String saveNovel(@Valid NovelPojo novelpojo, @RequestParam("image") MultipartFile image) throws IOException {
        novelService.save(novelpojo);
        return "redirect:/admin/novelList";
    }
//     ----------------------------------------
//    CommentsSection or Feedback
//     ----------------------------------------

    @GetMapping("/userComments")
    public String getUserComments(Model model){
        List<Feedback> feedbacks = userService.fetchAllFeedback();
        model.addAttribute("feedback", feedbacks);
        return "admin/AdminUserComment";
    }

    @GetMapping("/deletefeed/{id}")
    public String deleteFeedback(@PathVariable("id") Integer id) {
        userService.deleteFeedback(id);
        return "redirect:/admin/userComments";
    }
//    @GetMapping("/deletecom/{id}")
//    public String deleteComment(@PathVariable("id") Integer id) {
//        userService.deletecomment(id);
//        return "redirect:/admin/contactfetch";
//    }

//-------------------------------------------------------------
//    User or Customer
//-------------------------------------------------------------



    @GetMapping("/userList")
    public String getUserLit(Model model){
        List<User> users = userService.fetchAllUser();
        model.addAttribute("user", users);
        return "admin/CustomerList";
    }


    @GetMapping("/deleteUser/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin/userList";
    }
}
