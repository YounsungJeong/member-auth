package com.younsungs.member.web;

import com.younsungs.common.domain.BaseResponse;
import com.younsungs.common.domain.DefaultCode;
import com.younsungs.member.domain.request.CreateMemberRequest;
import com.younsungs.member.service.CreateMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired CreateMemberService createMemberService;

    @PostMapping("/member")
    public BaseResponse createMember(@RequestBody CreateMemberRequest createMemberRequest){
        createMemberService.createMember(createMemberRequest.getEmail(), createMemberRequest.getPhone());
        return new BaseResponse(DefaultCode.OK);
    }
}
