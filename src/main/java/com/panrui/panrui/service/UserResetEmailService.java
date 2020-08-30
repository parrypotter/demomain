package com.panrui.panrui.service;

public interface UserResetEmailService {

    Boolean checkUserEmailIsSimilar(String username ,String perEmail);

    int updateUserEmail(String username , String email);
}
