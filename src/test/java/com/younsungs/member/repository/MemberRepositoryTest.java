package com.younsungs.member.repository;

import com.younsungs.member.domain.Member;
import com.younsungs.testenv.AbstractRepositoryTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MemberRepositoryTest extends AbstractRepositoryTest<Member, MemberRepository, Long> {
    @Autowired MemberRepository r;

    String email = UUID.randomUUID().toString()+"@test";
    String password = UUID.randomUUID().toString();
    String phone = UUID.randomUUID().toString();

    @Override
    protected MemberRepository initRepository() {
        return r;
    }

    @Override
    protected Member initObject() {
        return new Member(email, password, phone);
    }

    /** jpql **/

    @Test
    public void findByEmail_데이터존재(){
        // Given
        save();

        // When
        Optional<Member> t_ = r.findByEmail(t.getEmail());

        // Then
        assertThat(t_.isPresent(), is(true));
        assertThat(t_.get(), is(t));
    }

    @Test
    public void findByEmail_데이터없음(){
        // Given
        // save nothing
        t = initObject();

        // When
        Optional<Member> t_ = r.findByEmail(t.getEmail());

        // Then
        assertThat(t_.isPresent(), is(false));
    }

    /** 제약조건 **/
    @Test(expected = DataIntegrityViolationException.class)
    public void email_널(){
        r.saveAndFlush(new Member(null, password, phone));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void email_중복(){
        super.save();
        r.saveAndFlush(new Member(email, password, UUID.randomUUID().toString()));
    }

    @Test(expected = ConstraintViolationException.class)
    public void email_형식(){
        r.saveAndFlush(new Member("test", password, phone));
    }

    @Test(expected = ConstraintViolationException.class)
    public void password_널(){
        r.saveAndFlush(new Member(email, null, phone));
    }

    @Test(expected = ConstraintViolationException.class)
    public void password_공백(){
        r.saveAndFlush(new Member(email, "", phone));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void phone_중복(){
        super.save();
        r.saveAndFlush(new Member(UUID.randomUUID().toString()+"@email", password, phone));
    }
}
