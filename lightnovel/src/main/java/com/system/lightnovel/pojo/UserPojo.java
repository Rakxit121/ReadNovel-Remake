package com.system.lightnovel.pojo;

import com.system.lightnovel.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo {
    private Integer id;
    private String email;
    private  String username;
    private  String fullname;
    private  String password;

    public UserPojo(User user) {
        this.id=user.getId();
        this.email=user.getEmail();
        this.username=user.getUsername();
        this.fullname=user.getFullname();
        this.password=user.getPassword();
    }
}
