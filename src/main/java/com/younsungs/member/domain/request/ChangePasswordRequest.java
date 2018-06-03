package com.younsungs.member.domain.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class ChangePasswordRequest {
    @NotBlank @Email protected String email;
    @NotBlank protected String oldPassword;
    @NotBlank protected String newPassword;
}
