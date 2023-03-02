package com.system.lightnovel.controller;


import com.system.lightnovel.entity.Feedback;
import com.system.lightnovel.entity.Novel;
import com.system.lightnovel.pojo.NovelPojo;
import com.system.lightnovel.services.NovelService;
import com.system.lightnovel.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "admin/Dashboard";
    }

    @GetMapping("/novelList")
    public String getNovelList(Model model){
        List<Novel> novels = novelService.fetchAll();
        model.addAttribute("novel", novels);
        return "admin/adminBookList";
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
    public String saveNovel(@Valid NovelPojo novelpojo) {
        novelService.save(novelpojo);
        return "redirect:admin/novelList";
    }
//     ----------------------------------------
//    CommentsSection or Feedback
//     ----------------------------------------

    @GetMapping("/userComments")
    public String getUserComments(Model model){
        List<Feedback> feedbacks = userService.fetchAllFeedback();
        model.addAttribute("feedback", feedbacks);
        return "redirect:admin/novelForm";
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
