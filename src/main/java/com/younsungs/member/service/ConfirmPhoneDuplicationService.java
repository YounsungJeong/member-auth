package com.younsungs.member.service;

import com.younsungs.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmPhoneDuplicationService {
    @Autowired MemberRepository memberRepository;

    public boolean isPhoneDuplication(String phone){
        return memberRepository.findByPhone(phone).isPresent();
    }
}
