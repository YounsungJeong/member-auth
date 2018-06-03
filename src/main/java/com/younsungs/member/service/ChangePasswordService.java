package com.younsungs.member.service;

import com.younsungs.member.domain.Member;
import com.younsungs.member.domain.request.ChangePasswordRequest;
import com.younsungs.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChangePasswordService {
    @Autowired MemberRepository memberRepository;

    public void changePassword(ChangePasswordRequest request){
        Optional<Member> memberOptional = memberRepository.findByEmail(request.getEmail());
        if(!memberOptional.isPresent())
            throw new IllegalArgumentException("could not find email");

        Member member = memberOptional.get();
        member.changePassword(request.getOldPassword(), request.getNewPassword());
        memberRepository.save(member);
    }
}
