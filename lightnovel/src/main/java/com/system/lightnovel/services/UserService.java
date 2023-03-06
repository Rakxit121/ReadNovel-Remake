package com.system.lightnovel.services;

import com.system.lightnovel.entity.Feedback;
import com.system.lightnovel.entity.User;
import com.system.lightnovel.pojo.FeedbackPojo;
import com.system.lightnovel.pojo.UserPojo;

import java.util.List;


public interface UserService {

    //Retrieve ----------------------------------------------------
    List<User> fetchAll();

    String update(UserPojo userPojo);

    User fetchById(Integer id);

    UserPojo findByEmail(String email);

    //Create ------------------------------------------------------
    String save(UserPojo userpojo);


    String submitFeedback(FeedbackPojo feedbackPojo);
    List<Feedback> fetchAllFeedback();


    //Delete -------------------------------------------------------


//    void deleteById(Integer id);
    void deleteFeedback(Integer id);


    void deleteUser(Integer id);

    List<User> fetchAllUser();
}
