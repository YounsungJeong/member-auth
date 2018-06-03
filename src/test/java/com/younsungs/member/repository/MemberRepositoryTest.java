package com.younsungs.member.repository;

import com.younsungs.member.domain.Member;
import com.younsungs.member.domain.testenv.MemberTestFactory;
import com.younsungs.testenv.AbstractRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberRepositoryTest extends AbstractRepositoryTest<Member, MemberRepository, Long> {
    @Autowired MemberRepository r;

    @Override
    protected MemberRepository initRepository() {
        return r;
    }

    @Override
    protected Member initObject() {
        return MemberTestFactory.INSTANCE.member();
    }
}
