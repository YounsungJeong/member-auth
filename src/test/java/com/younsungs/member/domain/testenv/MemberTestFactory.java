package com.younsungs.member.domain.testenv;

import com.younsungs.member.domain.Member;

public enum MemberTestFactory {
    INSTANCE;

    String email = "test@eamil.com";
    String password = "12345678";
    String phone = "010-1234-5678";

    public Member member(){
        return new Member(email, password, phone);
    }
}
