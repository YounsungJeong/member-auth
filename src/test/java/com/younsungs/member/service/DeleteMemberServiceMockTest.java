package com.younsungs.member.service;

import com.younsungs.member.domain.Member;
import com.younsungs.member.domain.request.DeleteMemberRequest;
import com.younsungs.member.domain.request.testenv.DeleteMemberRequestSpy;
import com.younsungs.member.repository.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DeleteMemberServiceMockTest {
    @InjectMocks DeleteMemberService deleteMemberService;
    @Mock MemberRepository memberRepository;
    DeleteMemberRequest request;
    long id;
    String password;

    @Before
    public void setUp(){
        password = "test";
        request = new DeleteMemberRequestSpy(password);
        id = 1L;
    }

    @Test
    public void deleteMember(){
        // GIVEN
        Member member = new Member("test1", password);
        when(memberRepository.findById(id)).thenReturn(Optional.of(member));

        // When
        deleteMemberService.deleteMember(id ,request);

        // Then
        verify(memberRepository, times(1)).findById(id);
        verify(memberRepository, times(1)).delete(member);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteMember_memberIsNotExist(){
        // GIVEN
        when(memberRepository.findById(id)).thenReturn(Optional.empty());

        // When
        deleteMemberService.deleteMember(id ,request);

        // Then
        // exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteMember_passwordIsWrong(){
        // GIVEN
        Member member = new Member("test1", "wrong password");
        when(memberRepository.findById(id)).thenReturn(Optional.of(member));

        // When
        deleteMemberService.deleteMember(id ,request);

        // Then
        // exception
    }
}
