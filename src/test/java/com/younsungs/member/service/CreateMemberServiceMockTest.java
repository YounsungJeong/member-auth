package com.younsungs.member.service;

import com.younsungs.member.domain.Member;
import com.younsungs.member.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class CreateMemberServiceMockTest {
    @InjectMocks CreateMemberService createMemberService;
    @Mock MemberRepository memberRepository;

    String email = "test@eamil.com";
    String password = "12345678";

    @Test
    public void createMember(){
        // GIVEN
        ArgumentCaptor<Member> argument = ArgumentCaptor.forClass(Member.class);

        // When
        createMemberService.createMember(email, password);

        // Then
        verify(memberRepository, times(1)).save(argument.capture());
        assertThat(argument.getValue().getEmail(), is(email));
        assertThat(argument.getValue().getPassword(), is(password));
        assertThat(argument.getValue().getRegistrationDate(), is(notNullValue()));
    }
}
