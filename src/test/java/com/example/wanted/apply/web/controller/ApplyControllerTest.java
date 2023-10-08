package com.example.wanted.apply.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class ApplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    private final String baseUrl = "/api/apply";

    private final String req = """
            {
              "recruitId" : %d,
              "memberId" : %d
            }
            """;

    @BeforeEach
    void init() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();

        saveCompany();
        saveRecruit();
        saveMember();
    }

    @Nested
    @DisplayName("지원 내역 저장")
    class RecruitSaveApiTest {
        @Test
        @DisplayName("지원 내역 저장 실패 - ID 최소 값")
        void saveFailTestByMinValid() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                            .content(req.formatted(0, 0))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

        @Test
        @DisplayName("지원 내역 저장 실패 - 존재하지 않는 아이디")
        void saveFailTestByIdNotFound() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                            .content(req.formatted(1, 99999))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

        @Test
        @DisplayName("지원 내역 저장 성공")
        void saveSuccessTest() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                            .content(req.formatted(1, 1))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }
    }

    private String saveMember() throws Exception {
        final String mbReq = """
            {
              "username" : "%s",
              "password" : "%s"
            }
            """;

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/member")
                        .content(mbReq.formatted("tester",
                                "abc123"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        return mvcResult.getResponse().getRedirectedUrl();
    }

    private String saveCompany() throws Exception {
        String cpReq = """
                {
                  "name" : "%s",
                  "nation" : "%s",
                  "location" : "%s"
                }
                """;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/company")
                        .content(cpReq.formatted("네이버",
                                "한국",
                                "판교"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        return mvcResult.getResponse().getRedirectedUrl();
    }

    private String saveRecruit() throws Exception {
        final String rcReq = """
            {
              "companyId" : 1,
              "position" : "%s",
              "compensation" : 1000000,
              "content" : "%s",
              "techStack" : "%s"
            }
            """;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/recruit")
                        .content(rcReq.formatted("백엔드 주니어 개발자",
                                "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
                                "Python"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        return mvcResult.getResponse().getRedirectedUrl();
    }

}