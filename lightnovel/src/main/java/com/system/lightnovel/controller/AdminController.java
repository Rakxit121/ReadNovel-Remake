package com.system.lightnovel.controller;


import com.system.lightnovel.entity.Feedback;
import com.system.lightnovel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;


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
        return "admin/Dashboard";
    }

    @GetMapping("/novelList")
    public String getNovelList(Model model){
//        List<Novel> novels = novelService.fetchAllNovel();
//        model.addAttribute("novel", novels);
        return "admin/adminBookList";
    }


    @GetMapping("/novelForm")
    public String getNovelForm(){
        return "admin/adminNovelForm";
    }

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






}
