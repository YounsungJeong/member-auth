package com.younsungs.member.domain.request.testenv;

import com.younsungs.member.domain.request.ChangePasswordRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public enum ChangePasswordRequestTestFactory {
    INSTANCE;

    public List<ChangePasswordRequest> chanePassword_성공(){
        List<ChangePasswordRequest> requests = new ArrayList<>();

        requests.add(new ChangePasswordRequestSpy("test1@test", "password1", "password2"));
        requests.add(new ChangePasswordRequestSpy("test2@test", UUID.randomUUID().toString(), UUID.randomUUID().toString()));

        return requests;
    }

    public List<ChangePasswordRequest> chanePassword_실패(){
        List<ChangePasswordRequest> requests = new ArrayList<>();

        /* email null */requests.add(new ChangePasswordRequestSpy(null, "password1", "password2"));
        /* email blank */requests.add(new ChangePasswordRequestSpy("", "password1", "password2"));
        /* email format */requests.add(new ChangePasswordRequestSpy("email", "password1", "password2"));
        /* email format */requests.add(new ChangePasswordRequestSpy("email@", "password1", "password2"));
        /* email format */requests.add(new ChangePasswordRequestSpy("email@@email", "password1", "password2"));

        /* oldPassword null */requests.add(new ChangePasswordRequestSpy("email@email", null, "password2"));
        /* oldPassword blank */requests.add(new ChangePasswordRequestSpy("email@email", "", "password2"));

        /* newPassword null */requests.add(new ChangePasswordRequestSpy("email@email", "password1", null));
        /* newPassword blank */requests.add(new ChangePasswordRequestSpy("email@email", "password1", ""));

        return requests;
    }
}
