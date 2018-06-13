package com.younsungs.member.domain.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class DeleteMemberRequest {
    @NotBlank protected String password;
}