package com.younsungs.testenv;

import com.younsungs.common.domain.JpaId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public abstract class AbstractRepositoryTest<T extends JpaId<ID>, R extends JpaRepository<T, ID>, ID extends Serializable>{

    R r;

    @Before
    public void init(){
        r = initRepository();
    }

    protected abstract R initRepository();
    protected abstract T initObject();

    @Test
    public void save(){
        T t = r.saveAndFlush(initObject());
        assertThat(t.getId(), is(notNullValue()));
    }

    @Test
    public void findOne(){
        T t = r.saveAndFlush(initObject());
        ID id = t.getId();
        Optional<T> t_ = r.findById(id);

        assertThat(t_.isPresent(), is(true));
        assertThat(t_.get(), is(notNullValue()));
        assertThat(t_.get(), is(t));
    }
}
