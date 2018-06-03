package com.younsungs.member.domain.request;

import com.younsungs.member.domain.request.testenv.CreateMemberRequestSpy;
import com.younsungs.testenv.AbstractDomainTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class CreateMemberRequestTest extends AbstractDomainTest<CreateMemberRequest> {
    String email = "test@eamil.com";
    String password = "12345678";
    String phone = "010-1234-5678";

    @Override
    public void noArgsConstructor() {
        t = new CreateMemberRequest();

        // is not null
        assertThat(t, is(notNullValue()));

        // is null
        assertThat(t.getEmail(), is(nullValue()));
        assertThat(t.getPassword(), is(nullValue()));
        assertThat(t.phone, is(nullValue()));
    }

    @Override
    public void allArgsConstructor() {
        // is not null
        assertThat(t, is(notNullValue()));
        assertThat(t.getEmail(), is(email));
        assertThat(t.getPassword(), is(password));
        assertThat(t.getPhone(), is(phone));
    }

    @Override
    public CreateMemberRequest getDomainObject() {
        return new CreateMemberRequestSpy(email, password, phone);
    }
}
