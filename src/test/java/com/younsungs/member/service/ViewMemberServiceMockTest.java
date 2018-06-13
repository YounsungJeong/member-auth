package com.younsungs.member.service;

import com.younsungs.member.domain.Member;
import com.younsungs.member.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ViewMemberServiceMockTest {
    @InjectMocks ViewMemberService viewMemberService;
    @Mock MemberRepository memberRepository;

    long id = 1L;

    @Test
    public void viewMember(){
        // GIVEN
        Member member = new Member("test1", "test2");
        when(memberRepository.findById(id)).thenReturn(Optional.of(member));

        // When
        Member member_ = viewMemberService.viewMember(id);

        // Then
        verify(memberRepository, times(1)).findById(id);
        assertThat(member, is(member_));
    }

    @Test(expected = IllegalArgumentException.class)
    public void viewMember_memberIsNotExist(){
        // GIVEN
        when(memberRepository.findById(id)).thenReturn(Optional.empty());

        // When
        viewMemberService.viewMember(id);

        // Then
        // exception
    }
}
