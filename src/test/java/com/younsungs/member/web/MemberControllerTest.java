package com.younsungs.member.web;

import com.younsungs.common.domain.BaseResponse;
import com.younsungs.common.domain.DefaultCode;
import com.younsungs.member.domain.request.CreateMemberRequest;
import com.younsungs.member.domain.request.testenv.CreateMemberRequestSpy;
import com.younsungs.member.domain.request.testenv.CreateMemberRequestTestFactory;
import com.younsungs.member.service.CreateMemberService;
import com.younsungs.testenv.AbstractMockControllerTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;

public class MemberControllerTest extends AbstractMockControllerTest<MemberController> {

    @InjectMocks MemberController memberController;
    @Mock CreateMemberService createMemberService;

    @Override
    public MemberController getController() {
        return memberController;
    }

    @Test
    public void memberCreate_성공() {
        List<CreateMemberRequest> requests = CreateMemberRequestTestFactory.INSTANCE.memberCreate_성공_요청();

        for(CreateMemberRequest request : requests){
            MockHttpServletResponse mockResponse = createMember(request);
            BaseResponse response = super.parsingResponse(mockResponse);

            verify(createMemberService, times(1)).createMember(request.getEmail(), request.getPassword(), request.getPhone());
            assertThat(mockResponse.getStatus(), is(HttpStatus.OK.value()));
            assertThat(response.getCode(), is(DefaultCode.OK.getCode()));
            assertThat(response.getMessage(), is(DefaultCode.OK.getMessage()));
        }
    }

    @Test
    public void memberCreate_실패() {
        List<CreateMemberRequest> requests = CreateMemberRequestTestFactory.INSTANCE.memberCreate_실패_요청();

        for(CreateMemberRequest request : requests){
            MockHttpServletResponse mockResponse = createMember(request);

            verify(createMemberService, times(0)).createMember(request.getEmail(), request.getPassword(), request.getPhone());
            assertThat(mockResponse.getStatus(), is(HttpStatus.BAD_REQUEST.value()));
        }
    }

    MockHttpServletResponse createMember(CreateMemberRequest createMemberRequest) {
        try {
            return mockMvc.perform(post("/member")
                    .content(gson.toJson(createMemberRequest))
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(handler().handlerType(MemberController.class))
                    .andExpect(handler().methodName("createMember"))
                    .andDo(print())
                    .andReturn()
                    .getResponse();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
