package com.younsungs.member.domain.request;

import com.younsungs.member.domain.request.testenv.ChangePasswordRequestSpy;
import com.younsungs.testenv.AbstractDomainTest;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class ChangePasswordRequestTest extends AbstractDomainTest<ChangePasswordRequest> {
    String email = "test@test.com";
    String oldPassword = "test";
    String newPassword = "test2";


    @Override
    public void noArgsConstructor() {
        t = new ChangePasswordRequest();

        // is not null
        assertThat(t, is(notNullValue()));

        // is null
        assertThat(t.getEmail(), is(nullValue()));
        assertThat(t.getOldPassword(), is(nullValue()));
        assertThat(t.getNewPassword(), is(nullValue()));
    }

    @Override
    public void allArgsConstructor() {
        // is not null
        assertThat(t, is(notNullValue()));
        assertThat(t.getEmail(), is(email));
        assertThat(t.getOldPassword(), is(oldPassword));
        assertThat(t.getNewPassword(), is(newPassword));
    }

    @Override
    public ChangePasswordRequest getDomainObject() {
        return new ChangePasswordRequestSpy(email, oldPassword, newPassword);
    }
}
