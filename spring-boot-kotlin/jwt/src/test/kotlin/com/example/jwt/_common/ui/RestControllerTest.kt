package com.example.jwt._common.ui

import com.example.jwt.support.config.TestConfiguration
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.RequestDocumentation.*
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockHttpServletRequestDsl
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter

@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension::class)
@Import(TestConfiguration::class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
abstract class RestControllerTest {
    @Autowired
    lateinit var objectMapper: ObjectMapper

    lateinit var mockMvc: MockMvc

    @BeforeEach
    internal fun setUp(
        webApplicationContext: WebApplicationContext,
        restDocumentationContextProvider: RestDocumentationContextProvider
    ) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilter<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
            .apply<DefaultMockMvcBuilder>(
                MockMvcRestDocumentation.documentationConfiguration(
                    restDocumentationContextProvider
                )
                    .operationPreprocessors()
                    .withRequestDefaults(Preprocessors.prettyPrint())
                    .withResponseDefaults(Preprocessors.prettyPrint())
            )
            .build()
    }

    fun MockHttpServletRequestDsl.jsonContent(value: Any) {
        content = objectMapper.writeValueAsString(value)
        contentType = MediaType.APPLICATION_JSON
    }

    fun MockMvcResultMatchersDsl.success(response: Any) {
        status { is2xxSuccessful() }
        jsonPath("statusCode") { exists() }
        jsonPath("message") { exists() }
        jsonPath("timestamp") { exists() }
        jsonPath("data") { value(objectMapper.convertValue(response, object : TypeReference<Map<String, Any>>() {})) }
    }

    fun MockMvcResultMatchersDsl.failed() {
        status { is4xxClientError() }
        jsonPath("statusCode") { exists() }
        jsonPath("message") { exists() }
        jsonPath("timestamp") { exists() }
    }

    fun MockMvcResultHandlersDsl.document(
        identifier: String,
        queryParameters: List<ParameterDescriptor> = emptyList(),
        requestFields: List<FieldDescriptor> = emptyList(),
        responseFields: List<FieldDescriptor> = emptyList()
    ) {
        handle(
            MockMvcRestDocumentation.document(
                identifier,
                queryParameters(
                    // parameterWithName("d").description("dd")
                    queryParameters
                ),
                requestFields(
                    // fieldWithPath("data.accessToken").description("accessToken"),
                    requestFields
                ),
                responseFields(
                    // fieldWithPath("data.accessToken").description("accessToken"),
                    beneathPath("data").withSubsectionId("data"),
                    responseFields
                )
            )
        )
    }
}