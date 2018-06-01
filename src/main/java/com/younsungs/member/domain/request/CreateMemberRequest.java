package com.younsungs.member.domain.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class CreateMemberRequest {
    @NotBlank @Email private String email;
    private String phone;
}
