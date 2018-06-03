package com.younsungs.member.domain;

import com.younsungs.testenv.AbstractDomainTest;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class MemberTest extends AbstractDomainTest<Member> {
    String email = "test@eamil.com";
    String password = "12345678";
    String phone = "010-1234-5678";

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
        assertThat(t.getPhone(), is(phone));
        assertThat(t.getRegistrationDate(), is(notNullValue()));

        // is null
        assertThat(t.getId(), is(nullValue()));
        assertThat(t.getLastLoginDate(), is(nullValue()));
    }

    @Override
    public Member getDomainObject() {
        return new Member(email, password, phone);
    }
}
