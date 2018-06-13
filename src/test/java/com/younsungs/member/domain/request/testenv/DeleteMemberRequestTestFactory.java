package com.younsungs.member.domain.request.testenv;

import com.younsungs.member.domain.request.ChangePasswordRequest;
import com.younsungs.member.domain.request.DeleteMemberRequest;

import java.util.ArrayList;
import java.util.List;

public enum DeleteMemberRequestTestFactory {
    INSTANCE;

    public List<DeleteMemberRequest> deleteMember_실패(){
        List<DeleteMemberRequest> requests = new ArrayList<>();

        /* password null */requests.add(new DeleteMemberRequestSpy(null));
        /* password blank */requests.add(new DeleteMemberRequestSpy(""));

        return requests;
    }
}
