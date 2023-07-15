package com.mycode.springsecurityclient.service;

import com.mycode.springsecurityclient.entity.User;
import com.mycode.springsecurityclient.entity.VerificationToken;
import com.mycode.springsecurityclient.model.UserModel;

import java.util.Optional;

public interface UserService {

    User registerUser(UserModel userModel);

    void saveVerificationTokenForUSer(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);

    boolean checkIfValidOldPassword(User user, String oldPassword);
}
