package com.younsungs.member.web;

import com.google.gson.reflect.TypeToken;
import com.younsungs.common.domain.BaseResponse;
import com.younsungs.common.domain.DefaultCode;
import com.younsungs.member.domain.Member;
import com.younsungs.member.domain.request.ChangePasswordRequest;
import com.younsungs.member.domain.request.CreateMemberRequest;
import com.younsungs.member.domain.request.DeleteMemberRequest;
import com.younsungs.member.domain.request.testenv.*;
import com.younsungs.member.service.ChangePasswordService;
import com.younsungs.member.service.CreateMemberService;
import com.younsungs.member.service.DeleteMemberService;
import com.younsungs.member.service.ViewMemberService;
import com.younsungs.testenv.AbstractControllerMockTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;

public class MemberControllerMockTest extends AbstractControllerMockTest<MemberController> {

    @InjectMocks MemberController memberController;
    @Mock CreateMemberService createMemberService;
    @Mock ChangePasswordService changePasswordService;
    @Mock ViewMemberService viewMemberService;
    @Mock DeleteMemberService deleteMemberService;

    @Override
    public MemberController getController() {
        return memberController;
    }

    @Test
    public void memberCreate() {
        List<CreateMemberRequest> requests = CreateMemberRequestTestFactory.INSTANCE.memberCreate_성공_요청();

        for(CreateMemberRequest request : requests){
            MockHttpServletResponse mockResponse = createMember(request);
            BaseResponse response = super.parsingResponse(mockResponse);

            verify(createMemberService, times(1)).createMember(request.getEmail(), request.getPassword());
            assertThat(mockResponse.getStatus(), is(HttpStatus.OK.value()));
            assertThat(response.getCode(), is(DefaultCode.OK.getCode()));
            assertThat(response.getMessage(), is(DefaultCode.OK.getMessage()));
        }
    }

    @Test
    public void memberCreate_validFail() {
        List<CreateMemberRequest> requests = CreateMemberRequestTestFactory.INSTANCE.memberCreate_실패_요청();

        for(CreateMemberRequest request : requests){
            MockHttpServletResponse mockResponse = createMember(request);

            verify(createMemberService, times(0)).createMember(request.getEmail(), request.getPassword());
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

    @Test
    public void changePassword(){
        List<ChangePasswordRequest> requests = ChangePasswordRequestTestFactory.INSTANCE.chanePassword_성공();

        for(ChangePasswordRequest request : requests){
            MockHttpServletResponse mockResponse = changePassword(request);
            BaseResponse response = super.parsingResponse(mockResponse);

            verify(changePasswordService, times(1)).changePassword(request);
            assertThat(mockResponse.getStatus(), is(HttpStatus.OK.value()));
            assertThat(response.getCode(), is(DefaultCode.OK.getCode()));
            assertThat(response.getMessage(), is(DefaultCode.OK.getMessage()));
        }
    }

    @Test
    public void changePassword_validFail(){
        List<ChangePasswordRequest> requests = ChangePasswordRequestTestFactory.INSTANCE.chanePassword_실패();

        for(ChangePasswordRequest request : requests){
            MockHttpServletResponse mockResponse = changePassword(request);

            verify(changePasswordService, times(0)).changePassword(request);
            assertThat(mockResponse.getStatus(), is(HttpStatus.BAD_REQUEST.value()));
        }
    }

    MockHttpServletResponse changePassword(ChangePasswordRequest changePasswordRequest) {
        try {
            return mockMvc.perform(put("/member/password")
                    .content(gson.toJson(changePasswordRequest))
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(handler().handlerType(MemberController.class))
                    .andExpect(handler().methodName("changePassword"))
                    .andDo(print())
                    .andReturn()
                    .getResponse();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void viewMember() throws UnsupportedEncodingException {
        // Given
        long id = 1L;
        String password = "not include";
        MemberSpy memberSpy = new MemberSpy(id, password, "test2", "test3");
        when(viewMemberService.viewMember(id)).thenReturn(memberSpy);

        // When
        MockHttpServletResponse mockHttpServletResponse = viewMember(id);
        BaseResponse<Member> response = gson.fromJson(mockHttpServletResponse.getContentAsString(),new TypeToken<BaseResponse<Member>>(){}.getType());
        Member member = response.getResponse();

        // Then
        verify(viewMemberService, times(1)).viewMember(id);
        assertThat(member.getId(), is(memberSpy.getId()));
        assertThat(member.getEmail(), is(memberSpy.getEmail()));
        assertThat(member.getPhone(), is(memberSpy.getPhone()));
        assertThat(member.getPassword(), is(nullValue()));
    }

    MockHttpServletResponse viewMember(long id) {
        try {
            return mockMvc.perform(get("/member/"+id)
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(handler().handlerType(MemberController.class))
                    .andExpect(handler().methodName("viewMember"))
                    .andDo(print())
                    .andReturn()
                    .getResponse();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteMember(){
        // Given
        long id = 1L;
        DeleteMemberRequest deleteMemberRequest = new DeleteMemberRequestSpy("password");

        // When
        deleteMember(id, deleteMemberRequest);

        // Then
        verify(deleteMemberService, times(1)).deleteMember(id, deleteMemberRequest);
    }

    @Test
    public void deleteMember_validFail(){
        List<DeleteMemberRequest> requests = DeleteMemberRequestTestFactory.INSTANCE.deleteMember_실패();

        for(DeleteMemberRequest request : requests){
            long id = 1L;
            MockHttpServletResponse mockResponse = deleteMember(id, request);

            verify(deleteMemberService, times(0)).deleteMember(id, request);
            assertThat(mockResponse.getStatus(), is(HttpStatus.BAD_REQUEST.value()));
        }
    }

    MockHttpServletResponse deleteMember(long id, DeleteMemberRequest deleteMemberRequest) {
        try {
            return mockMvc.perform(delete("/member/"+id)
                    .content(gson.toJson(deleteMemberRequest))
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(handler().handlerType(MemberController.class))
                    .andExpect(handler().methodName("deleteMember"))
                    .andDo(print())
                    .andReturn()
                    .getResponse();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
