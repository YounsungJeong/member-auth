package com.younsungs.member.domain.request.testenv;

import com.younsungs.member.domain.request.ChangePasswordRequest;

public class ChangePasswordRequestSpy extends ChangePasswordRequest {

    public ChangePasswordRequestSpy(String email, String oldPassword, String newPassword) {
        super.email = email;
        super.oldPassword = oldPassword;
        super.newPassword = newPassword;
    }
}
