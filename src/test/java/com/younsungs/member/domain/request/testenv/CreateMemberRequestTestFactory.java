package com.younsungs.member.domain.request.testenv;

import com.younsungs.member.domain.request.CreateMemberRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public enum CreateMemberRequestTestFactory {
    INSTANCE;

    String email = "test@eamil.com";
    String password = "12345678";
    String phone = "010-1234-5678";

    public CreateMemberRequest createMemberRequest(String email, String password, String phone){
        return new CreateMemberRequestSpy(email, password, phone);
    }

    public CreateMemberRequest createMemberRequest(){
        return new CreateMemberRequestSpy(email, password, phone);
    }

    public List<CreateMemberRequest> memberCreate_성공_요청(){
        List<CreateMemberRequest> requests = new ArrayList<>();

        requests.add(new CreateMemberRequestSpy("test1@test", "test1", "test1"));
        requests.add(new CreateMemberRequestSpy("test2@test.com", "test2", "test2"));
        requests.add(new CreateMemberRequestSpy("test3@test.com", "test3", null));
        requests.add(new CreateMemberRequestSpy("test4@test.com", "test4", ""));

        return requests;
    }

    public List<CreateMemberRequest> memberCreate_실패_요청(){
        List<CreateMemberRequest> requests = new ArrayList<>();

        /* email null */requests.add(new CreateMemberRequestSpy(null, "password", "phone"));
        /* email blank */requests.add(new CreateMemberRequestSpy("", "password", "phone"));
        /* email format */requests.add(new CreateMemberRequestSpy("email", "password", "phone"));
        /* email format */requests.add(new CreateMemberRequestSpy("email@", "password", "phone"));
        /* email format */requests.add(new CreateMemberRequestSpy("email@@email", "password", "phone"));

        /* password null */requests.add(new CreateMemberRequestSpy("email@email", null, "phone"));
        /* password blank */requests.add(new CreateMemberRequestSpy("email@email", "", "phone"));

        return requests;
    }
}
