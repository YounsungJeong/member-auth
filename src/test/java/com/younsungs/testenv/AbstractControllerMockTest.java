package com.younsungs.testenv;

import com.google.gson.Gson;
import com.younsungs.common.domain.BaseResponse;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.UnsupportedEncodingException;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractControllerMockTest<T> {

    protected MockMvc mockMvc;
    protected Gson gson;

    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(getController()).build();
        gson = new Gson();
    }

    public abstract T getController();

    public BaseResponse parsingResponse(MockHttpServletResponse response){
        try {
            String json = response.getContentAsString();
            return gson.fromJson(json, BaseResponse.class);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
