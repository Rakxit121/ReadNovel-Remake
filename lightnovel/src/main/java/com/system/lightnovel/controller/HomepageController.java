package com.system.lightnovel.controller;

import com.system.lightnovel.pojo.FeedbackPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/homepage")
public class HomepageController {

    @GetMapping("")
    public String geHomepage(Model model) {
        model.addAttribute("feedback", new FeedbackPojo());

        return ("landingpage");
    }

//    @GetMapping("/landing_page")
//    public String getLandingPage() {
//        return "landingpage";
//    }

    @GetMapping("/home")
    public String getMainPage(){
        return "redirect:/main";
    }

    @GetMapping("/register")
    public  String getRegister(){
        return "register_user";
    }

    @GetMapping("/user_profile")
    public String getUserProfile(){return "users/user_profile";}

//    @Autowired
//    private NovelService novelService;
//
//    @GetMapping("/home")
//    public String mainPage(Model model) {
//        List<Novel> latestNovels = novelService.getLatestNovels();
//        model.addAttribute("latestNovels", latestNovels);
//        return "mainpage";
//    }
}
