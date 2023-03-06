package com.system.lightnovel.services.Impl;

import com.system.lightnovel.config.PasswordEncoderUtil;
import com.system.lightnovel.entity.*;
import com.system.lightnovel.entity.User;
import com.system.lightnovel.exception.AppException;
import com.system.lightnovel.pojo.FeedbackPojo;
import com.system.lightnovel.pojo.UserPojo;
import com.system.lightnovel.repo.*;
import com.system.lightnovel.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    public final UserRepo userRepo;

    public final FeedbackRepo feedbackRepo;

    @Override
    public String save(UserPojo userPojo) {
        User user=new User();
        user.setEmail(userPojo.getEmail());
        user.setFullname(userPojo.getFullname());
        user.setUsername(userPojo.getUsername());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userPojo.getPassword()));
        userRepo.save(user);
        return "created";
    }

    public List<User> fetchAllUser() {
        return this.userRepo.findAll();
    }

    @Override
    public List<User> fetchAll() {
        List<User> megaList = new ArrayList<>();
        megaList.addAll(listMapping(userRepo.allUsers("Submitted")));
        megaList.addAll(listMapping(userRepo.allUsers("Rejected")));
        megaList.addAll(listMapping(userRepo.allUsers("Approved")));
        return megaList;
    }



    @Override
    public String update(UserPojo userPojo) {
        User user = userRepo.findById(userPojo.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setEmail(userPojo.getEmail());
        user.setFullname(userPojo.getFullname());
        user.setUsername(userPojo.getUsername());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userPojo.getPassword()));
        userRepo.save(user);
        return "updated";
    }



    public List<User> listMapping(List<User> list){
        Stream<User> allUsersWithImage=list.stream().map(user ->
                User.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .fullname(user.getFullname())
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .build()
        );

        list = allUsersWithImage.toList();
        return list;
    }

    @Override
    public User fetchById(Integer id) {
        User user = userRepo.findById(id).orElseThrow(()->new RuntimeException("Not Found"));

        user= User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullname(user.getFullname())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        return user;
    }


    @Override
    public UserPojo findByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new AppException("Invalid User email", HttpStatus.BAD_REQUEST));
        return new UserPojo(user);
    }


    @Override
    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }
//    ------------------------------------------------------
//              Feedback
//    ------------------------------------------------------

    @Override
    public String submitFeedback(FeedbackPojo feedbackPojo) {
        Feedback feedback = new Feedback();
        feedback.setFullname(feedbackPojo.getFullname());
        feedback.setEmail(feedbackPojo.getEmail());
        feedback.setSubject(feedbackPojo.getSubject());
        feedback.setMessage(feedbackPojo.getMessage());
        feedbackRepo.save(feedback);
        return "sent";
    }
    public List<Feedback> fetchAllFeedback() {
        return this.feedbackRepo.findAll();
    }

//    @Override
//    public void deleteById(Integer id) {
//
//    }


    @Override
    public void deleteFeedback(Integer id) {
        feedbackRepo.deleteById(id);
    }


//    @Override
//    public void deletecomment(Integer id) {
//
//    }


//    @Override
//    public Booking fetchById(Integer id) {
//        return bookingRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
//    }



}
