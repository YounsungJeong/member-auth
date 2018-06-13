package com.younsungs.member.service;

import com.younsungs.member.domain.Member;
import com.younsungs.member.domain.request.DeleteMemberRequest;
import com.younsungs.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteMemberService {
    @Autowired MemberRepository memberRepository;

    public void deleteMember(long id, DeleteMemberRequest request){
        Optional<Member> memberOptional = memberRepository.findById(id);
        if(!memberOptional.isPresent())
            throw new IllegalArgumentException("member is not exist");

        Member member = memberOptional.get();
        if(!member.isCurrentPassword(request.getPassword()))
            throw new IllegalArgumentException("password is wrong");

        memberRepository.delete(member);
    }
}
