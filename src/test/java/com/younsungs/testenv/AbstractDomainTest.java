package com.younsungs.testenv;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public abstract class AbstractDomainTest <T>{
    protected T t;

    @Before
    public void init(){
        t = this.getDomainObject();
    }

    @Test
    public abstract void noArgsConstructor();

    @Test
    public abstract void allArgsConstructor();

    @Test
    public void equalsAndHashCode(){
        T t_ = this.getDomainObject();

        // is not null
        assertThat(t, is(notNullValue()));
        assertThat(t_, is(notNullValue()));
        // hashcode
        assertThat(t.hashCode(), is(t_.hashCode()));
        // equals
        assertThat(t, is(equalTo(t_)));
    }

    public abstract T getDomainObject();
}
