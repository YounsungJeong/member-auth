package com.younsungs.member.service;

import com.younsungs.member.domain.Member;
import com.younsungs.member.domain.request.ChangePasswordRequest;
import com.younsungs.member.domain.request.testenv.ChangePasswordRequestSpy;
import com.younsungs.member.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChangePasswordServiceMockTest {
    @InjectMocks ChangePasswordService changePasswordService;
    @Mock MemberRepository memberRepository;

    String email = "test1@test";
    String oldPassword = "pass1";
    String newPassword = "pass2";

    @Test
    public void changePassword_성공(){
        // Given
        ChangePasswordRequest request = new ChangePasswordRequestSpy(email, oldPassword, newPassword);
        when(memberRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new Member(request.getEmail(), request.getOldPassword())));
        ArgumentCaptor<Member> argument = ArgumentCaptor.forClass(Member.class);

        // When
        changePasswordService.changePassword(request);

        // Then
        verify(memberRepository, times(1)).findByEmail(request.getEmail());
        verify(memberRepository, times(1)).save(argument.capture());
        assertThat(argument.getValue().getEmail(), is(email));
        assertThat(argument.getValue().getPassword(), is(newPassword));
    }

    @Test(expected = IllegalArgumentException.class)
    public void changePassword_실패_미가입email(){
        // Given
        ChangePasswordRequest request = new ChangePasswordRequestSpy(email, oldPassword, newPassword);
        when(memberRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty()); // db에 데이터 없음

        // When
        changePasswordService.changePassword(request);

        // Then
        // exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void changePassword_실패_기존password불일치(){
        // Given
        ChangePasswordRequest request = new ChangePasswordRequestSpy(email, oldPassword, oldPassword); // 동일한 비밀번호
        when(memberRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new Member(request.getEmail(), request.getOldPassword())));

        // When
        changePasswordService.changePassword(request);

        // Then
        // exception
    }
}
