package com.younsungs.member.service;

import com.younsungs.member.domain.Member;
import com.younsungs.member.domain.request.CreateMemberRequest;
import com.younsungs.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateMemberService {
    @Autowired MemberRepository memberRepository;

    public void createMember(String email, String password){
        Member member = new Member(email, password);
        memberRepository.save(member);
    }
}