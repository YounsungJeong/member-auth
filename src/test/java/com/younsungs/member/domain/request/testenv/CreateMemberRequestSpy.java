package com.younsungs.member.domain.request.testenv;

import com.younsungs.member.domain.request.CreateMemberRequest;

public class CreateMemberRequestSpy extends CreateMemberRequest {

    public CreateMemberRequestSpy(String email, String password){
        super.email = email;
        super.password = password;
    }
}
