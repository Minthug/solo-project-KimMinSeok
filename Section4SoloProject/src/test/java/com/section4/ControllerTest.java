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
                .andExpect(jsonPath("members.[0].name").value("?????????2"))
                .andDo(
                        document("getAllMembersWithPagination", requestParameters(
                                parameterWithName("page").description("Page Number"),
                                parameterWithName("size").description("Content Size for page")
                        ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("member.[]").type(JsonFieldType.ARRAY).description("?????? ?????????"),
                                                fieldWithPath("member.[].id").type(JsonFieldType.NUMBER).description("?????? ??????"),
                                                fieldWithPath("member.[].name").type(JsonFieldType.STRING).description("??????"),
                                                fieldWithPath("member.[].password").type(JsonFieldType.STRING).description("????????????"),
                                                fieldWithPath("member.[].company_name").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("member.[].company_type").type(JsonFieldType.NUMBER).description("?????? ??????"),
                                                fieldWithPath("member.[].company_location").type(JsonFieldType.NUMBER).description("?????? ?????? ??????"),
                                                fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("????????? ?????????"),
                                                fieldWithPath("pageInfo.sort").type(JsonFieldType.OBJECT).description("?????? ??????"),
                                                fieldWithPath("pageInfo.sort.sorted").type(JsonFieldType.BOOLEAN).description("?????? ??????"),
                                                fieldWithPath("pageInfo.sort.unsorted").type(JsonFieldType.BOOLEAN).description("?????? ??????"),
                                                fieldWithPath("pageInfo.sort.empty").type(JsonFieldType.BOOLEAN).description("?????? empty"),
                                                fieldWithPath("pageInfo.pageNumber").type(JsonFieldType.NUMBER).description("????????? ??????"),
                                                fieldWithPath("pageInfo.pageSize").type(JsonFieldType.NUMBER).description("????????? ?????????"),
                                                fieldWithPath("pageInfo.unPaged").type(JsonFieldType.BOOLEAN).description("????????? ??????"),
                                                fieldWithPath("pageInfo.paged").type(JsonFieldType.BOOLEAN).description("????????? ??????")

                                        )
                                )
                                ));
    }
    @Test
    public void findMemberTestsWithPageSize() throws Exception {
        String page = "0";
        String size = "20";

        String name = "?????????";

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
                .andExpect(jsonPath("[0].name").value("?????????"))
                .andDo(
                        document("findMemberTestWithPageSize", requestParameters(
                                parameterWithName("page").description("The Page Number"),
                                parameterWithName("size").description("Content Size for page"),
                                parameterWithName("name").description("Target name")

                        ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("[]").type(JsonFieldType.ARRAY).description("?????? ?????????"),
                                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("?????? ??????"),
                                                fieldWithPath("[].name").type(JsonFieldType.STRING).description("??????"),
                                                fieldWithPath("[].password").type(JsonFieldType.STRING).description("????????????"),
                                                fieldWithPath("[].company_name").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("[].company_type").type(JsonFieldType.NUMBER).description("?????? ?????? ??????"),
                                                fieldWithPath("[].company_location").type(JsonFieldType.NUMBER).description("?????? ?????? ??????")
                                        )
                                )
                                )
                );
    }
}
