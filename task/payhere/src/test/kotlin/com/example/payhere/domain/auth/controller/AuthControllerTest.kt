package com.example.payhere.domain.auth.controller

import com.example.payhere.common.response.ResponseCode
import com.example.payhere.common.response.ResponseDto
import com.example.payhere.config.TestConfiguration
import com.example.payhere.config.WithMockCustomUser
import com.example.payhere.domain.auth.controller.dto.SignInRequest
import com.example.payhere.domain.auth.controller.dto.SignInResponse
import com.example.payhere.domain.auth.controller.dto.SignUpRequest
import com.example.payhere.domain.auth.service.AuthService
import com.example.payhere.domain.user.controller.dto.UserDto
import com.example.payhere.domain.user.entity.User
import com.example.payhere.domain.user.entity.repository.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.operation.preprocess.Preprocessors.*
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.snippet.Attributes.*
import org.springframework.security.core.Authentication
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDateTime

@WebMvcTest(AuthController::class)
@AutoConfigureRestDocs
@WithMockCustomUser
@Import(TestConfiguration::class)
class AuthControllerTest {

    @MockkBean
    private lateinit var service: AuthService

    @MockkBean
    private lateinit var repository: UserRepository

    @MockkBean
    private lateinit var authentication: Authentication

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun signIn() {
        // given
        val request = SignInRequest(
            email = "email",
            password = "password"
        )
        val response = SignInResponse(
            accessToken = "accessToken"
        )
        val expectedResponse = ResponseDto.of(ResponseCode.SUCCESS, response).body
        every { service.signIn(request) } returns response

        // when
        val resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders
                .post("/auth/sign-in")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
            .andDo(
                MockMvcRestDocumentation.document(
                    "auth/sign-in",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestFields(
                        fieldWithPath("email").description("이메일"),
                        fieldWithPath("password").description("비밀번호")
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드"),
                        fieldWithPath("msg").description("응답 메시지"),
                        fieldWithPath("success").description("성공 여부"),
                        subsectionWithPath("data").description("응답 데이터"),
                        fieldWithPath("data.accessToken").description("토큰"),
                    )
                )
            )
    }

    @Test
    fun signOut() {
        // given
        val token = "accessToken"
        val expectedResponse = ResponseDto.of(ResponseCode.SUCCESS).body
        every { service.signOut(token) } just Runs
        // when
        val resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders
                .get("/auth/sign-out")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
        )

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
            .andDo(
                MockMvcRestDocumentation.document(
                    "auth/sign-out",
                    preprocessResponse(prettyPrint()),
                    responseFields(
                        fieldWithPath("code").description("응답 코드"),
                        fieldWithPath("msg").description("응답 메시지"),
                        fieldWithPath("success").description("성공 여부"),
                    ),
                )
            )
    }

    @Test
    fun signUp() {
        // given
        val request = SignUpRequest(
            email = "email",
            password = "password"
        )
        val expectedResponse = ResponseDto.of(ResponseCode.SUCCESS).body
        every { service.signUp(request) } just Runs
        every { repository.findByEmail(any()).isPresent } returns false

        // when
        val resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders
                .post("/auth/sign-up")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
            .andDo(
                MockMvcRestDocumentation.document(
                    "auth/sign-up",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestFields(
                        fieldWithPath("email").description("이메일")
                            .attributes(key("constraints").value("unique")),
                        fieldWithPath("password").description("비밀번호")

                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드"),
                        fieldWithPath("msg").description("응답 메시지"),
                        fieldWithPath("success").description("성공 여부"),
                    ),
                )
            )
    }

    @Test
    fun me() {
        // given
        val user = User(
            id = 1L,
            password = "password",
            email = "email",
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now()
        )
        val dto = UserDto(
            id = 1L,
            email = "email",
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now()
        )
        val expectedResponse = ResponseDto.of(ResponseCode.SUCCESS, dto).body
        every { service.me(user) } returns dto

        // when
        val resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders
                .get("/auth/me")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "accessToken")
        )

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
            .andDo(
                MockMvcRestDocumentation.document(
                    "auth/me",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    responseFields(
                        fieldWithPath("code").description("응답 코드"),
                        fieldWithPath("msg").description("응답 메시지"),
                        fieldWithPath("success").description("성공 여부"),
                        subsectionWithPath("data").description("응답 데이터"),
                        fieldWithPath("data.id").description("id"),
                        fieldWithPath("data.email").description("이메일"),
                        fieldWithPath("data.createdAt").description("생성일시"),
                        fieldWithPath("data.modifiedAt").description("수정일시"),
                    )
                )
            )
    }
}