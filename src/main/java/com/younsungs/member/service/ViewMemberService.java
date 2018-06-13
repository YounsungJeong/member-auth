package com.younsungs.member.service;

import com.younsungs.member.domain.Member;
import com.younsungs.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ViewMemberService {
    @Autowired MemberRepository memberRepository;
    public Member viewMember(long id){
        Optional<Member> memberOptional = memberRepository.findById(id);
        if(!memberOptional.isPresent())
            throw new IllegalArgumentException("member is not exist");

        return memberOptional.get();
    }
}
