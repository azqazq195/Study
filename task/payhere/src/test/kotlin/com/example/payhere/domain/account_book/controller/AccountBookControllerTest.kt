package com.example.payhere.domain.account_book.controller

import com.example.payhere.common.response.ResponseCode
import com.example.payhere.common.response.ResponseDto
import com.example.payhere.config.TestConfiguration
import com.example.payhere.config.WithMockCustomUser
import com.example.payhere.domain.account_book.controller.dto.AccountBookDto
import com.example.payhere.domain.account_book.controller.dto.AccountBookListDto
import com.example.payhere.domain.account_book.controller.dto.CreateAccountBookRequest
import com.example.payhere.domain.account_book.controller.dto.UpdateAccountBookRequest
import com.example.payhere.domain.account_book.service.AccountBookService
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.operation.preprocess.Preprocessors.*
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.*
import java.util.*


@WebMvcTest(AccountBookController::class)
@AutoConfigureRestDocs
@WithMockCustomUser
@Import(TestConfiguration::class)
class AccountBookControllerTest {

    @MockkBean
    private lateinit var service: AccountBookService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun findById() {
        // given
        val dto = AccountBookDto(
            id = 1L,
            note = "Test Account Book",
            amount = 100,
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now(),
        )
        val expectedResponse = ResponseDto.of(ResponseCode.SUCCESS, dto).body
        every { service.findById(dto.id, 1L) } returns dto

        // when
        val resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders
                .get("/account-book/{id}", dto.id)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "accessToken")
        )

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
            .andDo(
                document(
                    "account-book/find-one",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    pathParameters(
                        parameterWithName("id").description("id")
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드"),
                        fieldWithPath("msg").description("응답 메시지"),
                        fieldWithPath("success").description("성공 여부"),
                        subsectionWithPath("data").description("응답 데이터"),
                        fieldWithPath("data.id").description("id"),
                        fieldWithPath("data.note").description("메모"),
                        fieldWithPath("data.amount").description("금액"),
                        fieldWithPath("data.createdAt").description("생성일시"),
                        fieldWithPath("data.modifiedAt").description("수정일시"),
                    )
                )
            )
    }

    @Test
    fun findAll() {
        // given
        val dtos = listOf(
            AccountBookListDto(
                id = 1L,
                note = "Test Account Book 1",
                amount = 100,
            ),
            AccountBookListDto(
                id = 2L,
                note = "Test Account Book 2",
                amount = 200,
            ),
        )
        val expectedResponse = ResponseDto.of(ResponseCode.SUCCESS, dtos).body
        every { service.findAll(1L) } returns dtos

        // when
        val resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders
                .get("/account-book")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "accessToken")
        )

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
            .andDo(
                document(
                    "account-book/find-all",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    responseFields(
                        fieldWithPath("code").description("응답 코드"),
                        fieldWithPath("msg").description("응답 메시지"),
                        fieldWithPath("success").description("성공 여부"),
                        subsectionWithPath("data").description("응답 데이터"),
                        fieldWithPath("data[].id").description("id"),
                        fieldWithPath("data[].note").description("메모"),
                        fieldWithPath("data[].amount").description("금액"),
                    )
                )
            )
    }

    @Test
    @WithMockUser
    fun create() {
        // given
        val request = CreateAccountBookRequest(
            note = "Test Account Book",
            amount = 100,
        )
        val dto = AccountBookDto(
            id = 1L,
            note = request.note!!,
            amount = request.amount!!,
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now(),
        )
        val expectedResponse = ResponseDto.of(ResponseCode.SUCCESS, dto).body
        every { service.create(request) } returns dto

        // when
        val resultActions = this.mockMvc.perform(
            RestDocumentationRequestBuilders
                .post("/account-book")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "accessToken")
                .content(objectMapper.writeValueAsString(request))
        )

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
            .andDo(
                document(
                    "account-book/create",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestFields(
                        fieldWithPath("note").description("메모"),
                        fieldWithPath("amount").description("금액"),
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드"),
                        fieldWithPath("msg").description("응답 메시지"),
                        fieldWithPath("success").description("성공 여부"),
                        subsectionWithPath("data").description("응답 데이터"),
                        fieldWithPath("data.id").description("id"),
                        fieldWithPath("data.note").description("메모"),
                        fieldWithPath("data.amount").description("금액"),
                        fieldWithPath("data.createdAt").description("생성일시"),
                        fieldWithPath("data.modifiedAt").description("수정일시"),
                    )
                )
            )
    }

    @Test
    fun update() {
        // given
        val id = 1L
        val request = UpdateAccountBookRequest(
            note = "Updated note",
            amount = 200
        )
        val dto = AccountBookDto(
            id = id,
            note = request.note!!,
            amount = request.amount!!,
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now(),
        )
        val expectedResponse = ResponseDto.of(ResponseCode.SUCCESS, dto).body
        every { service.update(id, request, 1L) } returns dto

        // when
        val resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders
                .patch("/account-book/{id}", id)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "accessToken")
        )

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
            .andDo(
                document(
                    "account-book/update",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    pathParameters(
                        parameterWithName("id").description("id")
                    ),
                    requestFields(
                        fieldWithPath("note").description("메모"),
                        fieldWithPath("amount").description("금액")
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드"),
                        fieldWithPath("msg").description("응답 메시지"),
                        fieldWithPath("success").description("성공 여부"),
                        subsectionWithPath("data").description("응답 데이터"),
                        fieldWithPath("data.id").description("id"),
                        fieldWithPath("data.note").description("메모"),
                        fieldWithPath("data.amount").description("금액"),
                        fieldWithPath("data.createdAt").description("생성일시"),
                        fieldWithPath("data.modifiedAt").description("수정일시"),
                    ),
                )

            )
    }

    @Test
    fun deleteById() {
        // given
        val id = 1L
        every { service.deleteById(id, 1L) } just Runs

        // when
        val resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders
                .delete("/account-book/{id}", id)
                .header("Authorization", "accessToken")
        )

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(ResponseDto.of(ResponseCode.SUCCESS).body)))
            .andDo(
                document(
                    "account-book/delete-one",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    pathParameters(
                        parameterWithName("id").description("id")
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드"),
                        fieldWithPath("msg").description("응답 메시지"),
                        fieldWithPath("success").description("성공 여부"),
                    )
                )
            )
    }

    @Test
    fun restoreAll() {
        // given
        val expectedResponse = ResponseDto.of(ResponseCode.SUCCESS).body
        every { service.restoreAll(1L) } just Runs

        val resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders
                .patch("/account-book/restore-all")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "accessToken")
        )

        resultActions
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
            .andDo(
                document(
                    "account-book/restore-all",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    responseFields(
                        fieldWithPath("code").description("응답 코드"),
                        fieldWithPath("msg").description("응답 메시지"),
                        fieldWithPath("success").description("성공 여부"),
                    )
                )
            )
    }
}