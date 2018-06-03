package com.younsungs.member.domain.request.testenv;

import com.younsungs.member.domain.request.CreateMemberRequest;

public class CreateMemberRequestSpy extends CreateMemberRequest {

    public CreateMemberRequestSpy(String email, String password, String phone){
        super.email = email;
        super.password = password;
        super.phone = phone;
    }
}
