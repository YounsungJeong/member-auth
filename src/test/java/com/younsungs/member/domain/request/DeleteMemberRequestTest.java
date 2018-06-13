package com.younsungs.member.domain.request;

import com.younsungs.member.domain.request.testenv.DeleteMemberRequestSpy;
import com.younsungs.testenv.AbstractDomainTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class DeleteMemberRequestTest extends AbstractDomainTest<DeleteMemberRequest> {
    String password = "12345678";

    @Override
    public void noArgsConstructor() {
        t = new DeleteMemberRequest();

        // is not null
        assertThat(t, is(notNullValue()));

        // is null
        assertThat(t.getPassword(), is(nullValue()));
    }

    @Override
    public void allArgsConstructor() {
        // is not null
        assertThat(t, is(notNullValue()));
        assertThat(t.getPassword(), is(password));
    }

    @Override
    public DeleteMemberRequest getDomainObject() {
        return new DeleteMemberRequestSpy(password);
    }
}
