package com.mycode.springsecurityclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// this will be used as form
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String matchingPassword;

}
