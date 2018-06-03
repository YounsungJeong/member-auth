package com.younsungs.member.web;

import com.younsungs.common.domain.BaseResponse;
import com.younsungs.member.domain.request.CreateMemberRequest;
import com.younsungs.member.service.CreateMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MemberController {

    @Autowired CreateMemberService createMemberService;

    @PostMapping("/member")
    public BaseResponse createMember(@Valid @RequestBody CreateMemberRequest createMemberRequest){
        createMemberService.createMember(createMemberRequest.getEmail(), createMemberRequest.getPassword(), createMemberRequest.getPhone());
        return BaseResponse.okResponse();
    }
}
