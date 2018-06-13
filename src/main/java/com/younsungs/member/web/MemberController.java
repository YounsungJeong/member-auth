package com.younsungs.member.web;

import com.younsungs.common.domain.BaseResponse;
import com.younsungs.common.domain.DefaultCode;
import com.younsungs.member.domain.request.ChangePasswordRequest;
import com.younsungs.member.domain.request.CreateMemberRequest;
import com.younsungs.member.domain.request.DeleteMemberRequest;
import com.younsungs.member.service.ChangePasswordService;
import com.younsungs.member.service.CreateMemberService;
import com.younsungs.member.service.DeleteMemberService;
import com.younsungs.member.service.ViewMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MemberController {

    @Autowired CreateMemberService createMemberService;
    @PostMapping("/member")
    public BaseResponse createMember(@Valid @RequestBody CreateMemberRequest createMemberRequest){
        createMemberService.createMember(createMemberRequest.getEmail(), createMemberRequest.getPassword());
        return BaseResponse.okResponse();
    }

    @Autowired ViewMemberService viewMemberService;
    @GetMapping("/member/{id}")
    public BaseResponse viewMember(@PathVariable long id){
        return new BaseResponse(DefaultCode.OK, viewMemberService.viewMember(id));
    }

    @Autowired DeleteMemberService deleteMemberService;
    @DeleteMapping("/member/{id}")
    public BaseResponse deleteMember(@PathVariable long id, @Valid @RequestBody DeleteMemberRequest request){
        deleteMemberService.deleteMember(id, request);
        return new BaseResponse(DefaultCode.OK);
    }

    @Autowired ChangePasswordService changePasswordService;
    @PutMapping("/member/password")
    public BaseResponse changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest){
        changePasswordService.changePassword(changePasswordRequest);
        return BaseResponse.okResponse();
    }

    // TODO : 로그인 성공 시 jwt 발행
}
