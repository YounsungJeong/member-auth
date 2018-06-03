package com.younsungs.member.domain.request.testenv;

import com.younsungs.member.domain.request.CreateMemberRequest;

import java.util.ArrayList;
import java.util.List;

public enum CreateMemberRequestTestFactory {
    INSTANCE;

    String email = "test@eamil.com";
    String password = "12345678";

    public CreateMemberRequest createMemberRequest(String email, String password, String phone){
        return new CreateMemberRequestSpy(email, password);
    }

    public CreateMemberRequest createMemberRequest(){
        return new CreateMemberRequestSpy(email, password);
    }

    public List<CreateMemberRequest> memberCreate_성공_요청(){
        List<CreateMemberRequest> requests = new ArrayList<>();

        requests.add(new CreateMemberRequestSpy("test1@test", "test1"));
        requests.add(new CreateMemberRequestSpy("test2@test.com", "test2"));
        requests.add(new CreateMemberRequestSpy("test3@test.com", "test3"));
        requests.add(new CreateMemberRequestSpy("test4@test.com", "test4"));

        return requests;
    }

    public List<CreateMemberRequest> memberCreate_실패_요청(){
        List<CreateMemberRequest> requests = new ArrayList<>();

        /* email null */requests.add(new CreateMemberRequestSpy(null, "password"));
        /* email blank */requests.add(new CreateMemberRequestSpy("", "password"));
        /* email format */requests.add(new CreateMemberRequestSpy("email", "password"));
        /* email format */requests.add(new CreateMemberRequestSpy("email@", "password"));
        /* email format */requests.add(new CreateMemberRequestSpy("email@@email", "password"));

        /* password null */requests.add(new CreateMemberRequestSpy("email@email", null));
        /* password blank */requests.add(new CreateMemberRequestSpy("email@email", ""));

        return requests;
    }
}
