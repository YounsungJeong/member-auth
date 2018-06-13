package com.younsungs.member.domain.request.testenv;

import com.younsungs.member.domain.Member;

public class MemberSpy extends Member {
    public MemberSpy(Long id, String password, String email, String phone){
        super.id = id;
        super.password = password;
        super.email = email;
        super.phone = phone;
    }
}
