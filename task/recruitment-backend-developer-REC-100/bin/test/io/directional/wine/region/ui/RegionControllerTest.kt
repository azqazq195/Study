package io.directional.wine.region.ui

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.directional.wine._fixture.*
import io.directional.wine.region.application.RegionQueryService
import io.directional.wine.region.application.RegionService
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter

@WebMvcTest(RegionController::class)
@ExtendWith(RestDocumentationExtension::class)
@AutoConfigureRestDocs
class RegionControllerTest {
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var regionService: RegionService

    @MockkBean
    private lateinit var regionQueryService: RegionQueryService

    @BeforeEach
    internal fun setUp(
        webApplicationContext: WebApplicationContext,
        restDocumentationContextProvider: RestDocumentationContextProvider
    ) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilter<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
            .apply<DefaultMockMvcBuilder>(
                documentationConfiguration(
                    restDocumentationContextProvider
                )
                    .operationPreprocessors()
                    .withRequestDefaults(Preprocessors.prettyPrint())
                    .withResponseDefaults(Preprocessors.prettyPrint())
            )
            .build()
    }

    @Test
    fun create() {
        val url = "/region"
        val regionRequest = createRegionRequest("한국", "Korea", 1)
        val request = post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(regionRequest))
        every { regionService.create(regionRequest) } just runs

        mockMvc
            .perform(request)
            .andExpect(status().isCreated)
            .andDo(
                document(
                    "region/create",
                    requestFields(
                        fieldWithPath("nameKorean").type(JsonFieldType.STRING).description("지역 이름 - 한글"),
                        fieldWithPath("nameEnglish").type(JsonFieldType.STRING).description("지역 이름 - 영어"),
                        fieldWithPath("parentId").type(JsonFieldType.NUMBER).optional().description("상위 지역 ID"),
                    )
                )
            )
    }

    @Test
    fun update() {
        val url = "/region/{id}"
        val id = 1L
        val regionRequest = createRegionRequest("한국", "Korea", 2)
        val request = put(url, id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(regionRequest))
        every { regionService.update(id, regionRequest) } just runs

        mockMvc
            .perform(request)
            .andExpect(status().isOk)
            .andDo(
                document(
                    "region/update",
                    pathParameters(
                        parameterWithName("id").description("지역 ID")
                    ),
                    requestFields(
                        fieldWithPath("nameKorean").type(JsonFieldType.STRING).description("지역 이름 - 한글"),
                        fieldWithPath("nameEnglish").type(JsonFieldType.STRING).description("지역 이름 - 영어"),
                        fieldWithPath("parentId").type(JsonFieldType.NUMBER).optional().description("상위 지역 ID"),
                    )
                )
            )
    }

    @Test
    fun delete() {
        val url = "/region/{id}"
        val id = 1L
        val request = delete(url, id)
        every { regionService.delete(id) } just runs

        mockMvc
            .perform(request)
            .andExpect(status().isOk)
            .andDo(
                document(
                    "region/delete",
                    pathParameters(
                        parameterWithName("id").description("지역 ID")
                    )
                )
            )
    }

    @Test
    fun get() {
        val url = "/region/{id}"
        val id = 1L
        val request = get(url, id)
        val response = createRegionDetailsResponse(createRegion(parent = createRegion()))
        every { regionQueryService.getById(id) } returns response

        mockMvc
            .perform(request)
            .andExpect(status().isOk)
            .andDo(
                document(
                    "region/get",
                    pathParameters(
                        parameterWithName("id").description("지역 ID")
                    ),
                    responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("지역 ID"),
                        fieldWithPath("nameKorean").type(JsonFieldType.STRING).description("지역 이름 - 한글"),
                        fieldWithPath("nameEnglish").type(JsonFieldType.STRING).description("지역 이름 - 영어"),
                        fieldWithPath("parent.id").type(JsonFieldType.NUMBER).description("상위 지역 ID"),
                        fieldWithPath("parent.nameKorean").type(JsonFieldType.STRING).description("상위 지역 이름 - 한글"),
                        fieldWithPath("parent.nameEnglish").type(JsonFieldType.STRING).description("상위 지역 이름 - 영어"),
                        fieldWithPath("parent.parent").type(JsonFieldType.NULL).description("상위 지역"),
                        fieldWithPath("grapes[].id").type(JsonFieldType.NUMBER).description("포도 ID"),
                        fieldWithPath("grapes[].nameKorean").type(JsonFieldType.STRING).description("포도 이름 - 한글"),
                        fieldWithPath("grapes[].nameEnglish").type(JsonFieldType.STRING).description("포도 이름 - 영어"),
                        fieldWithPath("grapes[].acidity").type(JsonFieldType.NUMBER).description("포도 산도"),
                        fieldWithPath("grapes[].body").type(JsonFieldType.NUMBER).description("포도 바디감"),
                        fieldWithPath("grapes[].sweetness").type(JsonFieldType.NUMBER).description("포도 단맛"),
                        fieldWithPath("grapes[].tannin").type(JsonFieldType.NUMBER).description("포도 타닌"),
                        fieldWithPath("wineries[].id").type(JsonFieldType.NUMBER).description("와이너리 ID"),
                        fieldWithPath("wineries[].nameKorean").type(JsonFieldType.STRING).description("와이너리 이름 - 한글"),
                        fieldWithPath("wineries[].nameEnglish").type(JsonFieldType.STRING).description("와이너리 이름 - 영어"),
                        fieldWithPath("wines[].id").type(JsonFieldType.NUMBER).description("와인 ID"),
                        fieldWithPath("wines[].type").type(JsonFieldType.STRING).description("와인의 종류"),
                        fieldWithPath("wines[].nameKorean").type(JsonFieldType.STRING).description("와인 이름 - 한글"),
                        fieldWithPath("wines[].nameEnglish").type(JsonFieldType.STRING).description("와인 이름 - 영어"),
                        fieldWithPath("wines[].alcohol").type(JsonFieldType.NUMBER).description("와인 알코올 도수"),
                        fieldWithPath("wines[].acidity").type(JsonFieldType.NUMBER).description("와인 산도"),
                        fieldWithPath("wines[].body").type(JsonFieldType.NUMBER).description("와인 바디감"),
                        fieldWithPath("wines[].sweetness").type(JsonFieldType.NUMBER).description("와인 단맛"),
                        fieldWithPath("wines[].tannin").type(JsonFieldType.NUMBER).description("와인 타닌"),
                        fieldWithPath("wines[].servingTemperature").type(JsonFieldType.NUMBER).description("와인 권장섭취온도"),
                        fieldWithPath("wines[].score").type(JsonFieldType.NUMBER).description("와인의 점수"),
                        fieldWithPath("wines[].price").type(JsonFieldType.NUMBER).description("와인의 가격"),
                        fieldWithPath("wines[].style").type(JsonFieldType.STRING).description("와인의 스타일"),
                        fieldWithPath("wines[].grade").type(JsonFieldType.STRING).description("와인의 등급")
                    )
                )
            )
    }

    @Test
    fun search() {
        val url = "/region/search"
        val searchParam = createRegionSearchParam(nameEnglish = "Seoul")
        val page = PageRequest.of(1, 10, Sort.by(Sort.Direction.ASC, "id"))
        val request = get(url)
            .param("nameKorean", searchParam.nameKorean)
            .param("nameEnglish", searchParam.nameEnglish)
            .param("parentNameKorean", searchParam.parentNameKorean)
            .param("parentNameEnglish", searchParam.parentNameEnglish)
            .param("page", page.pageNumber.toString())
            .param("size", page.pageSize.toString())
            .param("sort", page.sort.toString())
        val response = PageImpl(listOf(createRegionSummaryResponse()), page, 1)
        every { regionQueryService.search(searchParam, any()) } returns response

        mockMvc
            .perform(request)
            .andExpect(status().isOk)
            .andDo(
                document(
                    "region/search",
                    queryParameters(
                        parameterWithName("nameKorean").description("지역 이름 - 한글"),
                        parameterWithName("nameEnglish").description("지역 이름 - 영어"),
                        parameterWithName("parentNameKorean").description("상위 지역 이름 - 한글"),
                        parameterWithName("parentNameEnglish").description("상위 지역 이름 - 영어"),
                        parameterWithName("page").description("페이지 번호"),
                        parameterWithName("size").description("페이지 크기"),
                        parameterWithName("sort").description("정렬 항목 및 방향"),
                    ),
                    relaxedResponseFields(
                        fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("지역 ID"),
                        fieldWithPath("content[].nameKorean").type(JsonFieldType.STRING).description("지역 이름 - 한글"),
                        fieldWithPath("content[].nameEnglish").type(JsonFieldType.STRING).description("지역 이름 - 영어"),
                    )
                )
            )
    }
}
