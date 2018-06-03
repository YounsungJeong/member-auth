package com.younsungs.member.domain;

import com.younsungs.testenv.AbstractDomainTest;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class MemberTest extends AbstractDomainTest<Member> {
    String email = "test@eamil.com";
    String password = "12345678";

    @Override
    public void noArgsConstructor() {
        t = new Member();

        // is not null
        assertThat(t, is(notNullValue()));

        // is null
        assertThat(t.getId(), is(nullValue()));
        assertThat(t.getEmail(), is(nullValue()));
        assertThat(t.getPassword(), is(nullValue()));
        assertThat(t.getPhone(), is(nullValue()));
        assertThat(t.getRegistrationDate(), is(nullValue()));
        assertThat(t.getLastLoginDate(), is(nullValue()));
    }

    @Override
    public void allArgsConstructor() {
        // is not null
        assertThat(t, is(notNullValue()));
        assertThat(t.getEmail(), is(email));
        assertThat(t.getPassword(), is(password));
        assertThat(t.getRegistrationDate(), is(notNullValue()));

        // is null
        assertThat(t.getPhone(), is(nullValue()));
        assertThat(t.getId(), is(nullValue()));
        assertThat(t.getLastLoginDate(), is(nullValue()));
    }

    @Override
    public Member getDomainObject() {
        return new Member(email, password);
    }

    /** changePassword **/
    @Test
    public void changePassword_성공(){
        // Given
        String newPassword = UUID.randomUUID().toString();

        // When
        t.changePassword(password, newPassword);

        // Then
        assertThat(t.getPassword(), is(newPassword));
    }

    @Test(expected = IllegalArgumentException.class)
    public void changePassword_실패_기존패스워드인증(){
        t.changePassword(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void changePassword_실패_새패스워드가이전과동일(){
        t.changePassword(password, password);
    }
}
