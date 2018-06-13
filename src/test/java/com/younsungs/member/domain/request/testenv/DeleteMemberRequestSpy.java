package com.younsungs.member.domain.request.testenv;

import com.younsungs.member.domain.request.DeleteMemberRequest;

public class DeleteMemberRequestSpy extends DeleteMemberRequest {
    public DeleteMemberRequestSpy(String password){
        super.password = password;
    }
}
