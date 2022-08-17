package com.section4;

import com.section4.Section4SoloProject.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@SpringBootTest
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberService memberService;

    @Test
    public void findAllTestWithPageSize() throws Exception {
        String page = "1";
        String size = "3";

        memberService.init();
        memberService.init();
        memberService.init();
        memberService.init();

        ResultActions actions =
                mockMvc.perform(get("/v1/")
                        .param("page", page)
                        .param("size", size)
                );
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("members.[0].name").value("김민석2"))
                .andDo(
                        document("getAllMembersWithPagination", requestParameters(
                                parameterWithName("page").description("Page Number"),
                                parameterWithName("size").description("Content Size for page")
                        ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("member.[]").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                                fieldWithPath("member.[].id").type(JsonFieldType.NUMBER).description("생성 번호"),
                                                fieldWithPath("member.[].name").type(JsonFieldType.STRING).description("이름"),
                                                fieldWithPath("member.[].password").type(JsonFieldType.STRING).description("비밀번호"),
                                                fieldWithPath("member.[].company_name").type(JsonFieldType.STRING).description("회사 이름"),
                                                fieldWithPath("member.[].company_type").type(JsonFieldType.NUMBER).description("회사 타입"),
                                                fieldWithPath("member.[].company_location").type(JsonFieldType.NUMBER).description("회사 위치 번호"),
                                                fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 데이터"),
                                                fieldWithPath("pageInfo.sort").type(JsonFieldType.OBJECT).description("정렬 번호"),
                                                fieldWithPath("pageInfo.sort.sorted").type(JsonFieldType.BOOLEAN).description("정렬 유무"),
                                                fieldWithPath("pageInfo.sort.unsorted").type(JsonFieldType.BOOLEAN).description("정렬 반전"),
                                                fieldWithPath("pageInfo.sort.empty").type(JsonFieldType.BOOLEAN).description("정렬 empty"),
                                                fieldWithPath("pageInfo.pageNumber").type(JsonFieldType.NUMBER).description("페이지 번호"),
                                                fieldWithPath("pageInfo.pageSize").type(JsonFieldType.NUMBER).description("페이지 사이즈"),
                                                fieldWithPath("pageInfo.unPaged").type(JsonFieldType.BOOLEAN).description("페이지 옵션"),
                                                fieldWithPath("pageInfo.paged").type(JsonFieldType.BOOLEAN).description("페이지 옵션")

                                        )
                                )
                                ));
    }
    @Test
    public void findMemberTestsWithPageSize() throws Exception {
        String page = "0";
        String size = "20";

        String name = "김민석";

        memberService.init();
        memberService.init();
        memberService.init();
        memberService.init();

        ResultActions actions =
                mockMvc.perform(get("/v1/find")
                        .param("page", page)
                        .param("size", size)
                        .param("name", name)
                        );
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("김민석"))
                .andDo(
                        document("findMemberTestWithPageSize", requestParameters(
                                parameterWithName("page").description("The Page Number"),
                                parameterWithName("size").description("Content Size for page"),
                                parameterWithName("name").description("Target name")

                        ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("[]").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("생성 번호"),
                                                fieldWithPath("[].name").type(JsonFieldType.STRING).description("이름"),
                                                fieldWithPath("[].password").type(JsonFieldType.STRING).description("비밀번호"),
                                                fieldWithPath("[].company_name").type(JsonFieldType.STRING).description("회사 이름"),
                                                fieldWithPath("[].company_type").type(JsonFieldType.NUMBER).description("회사 타입 번호"),
                                                fieldWithPath("[].company_location").type(JsonFieldType.NUMBER).description("회사 위치 번호")
                                        )
                                )
                                )
                );
    }
}
