package com.example.wanted.recruit.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
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
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class RecruitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    private final String baseUrl = "/api/recruit";

    private final String req = """
            {
              "companyId" : 1,
              "position" : "%s",
              "compensation" : 1000000,
              "content" : "%s",
              "techStack" : "%s"
            }
            """;

    @BeforeEach
    void init() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
        saveCompany();
    }

    @Nested
    @Order(1)
    @DisplayName("채용 공고 저장")
    class RecruitSaveApiTest {
        @Test
        @DisplayName("채용 공고 저장 실패 - 데이터 누락")
        void saveFailTest() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                            .content(req.formatted("", "", ""))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

        @Test
        @DisplayName("채용 공고 저장 성공")
        void saveSuccessTest() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                            .content(req.formatted("백엔드 주니어 개발자",
                                    "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
                                    "Python"))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }
    }

    @Nested
    @Order(2)
    @DisplayName("채용 공고 수정")
    class RecruitUpdateApiTest {
        @Test
        @DisplayName("채용 공고 수정 실패 - 존재하지 않는 아이디")
        void updateFailTestByIdNotFound() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/999999")
                            .content(req.formatted("백엔드 주니어 개발자",
                                    "원티드랩에서 백엔드 주니어 개발자를 '적극' 채용합니다. 자격요건은..",
                                    "Python"))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

        @Test
        @DisplayName("채용 공고 수정 실패 - 데이터 누락")
        void updateFailTestByValid() throws Exception {
            String saveUrl = saveRecruit();
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(saveUrl)
                            .content(req.formatted("", "", ""))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

        @Test
        @DisplayName("채용 공고 수정 성공")
        void updateSuccessTest() throws Exception {
            String saveUrl = saveRecruit();
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(saveUrl)
                            .content(req.formatted("백엔드 시니어 개발자",
                                    "원티드랩에서 백엔드 개발자를 '적극' 채용합니다. 자격요건은..",
                                    "Java"))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

    }

    @Nested
    @Order(4)
    @DisplayName("채용 공고 삭제")
    class RecruitDeleteApiTest {
        @Test
        @DisplayName("채용 공고 삭제 실패 - 존재하지 않는 공고")
        void deleteFailTestByIdNotFound() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/999999")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

        @Test
        @DisplayName("채용 공고 삭제 성공")
        void deleteSuccessTest() throws Exception {
            String saveUrl = saveRecruit();
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(saveUrl)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }
    }

    @Nested
    @Order(3)
    @DisplayName("채용 공고 조회")
    class RecruitGetApiTest {
        @Test
        @DisplayName("전체 채용 공고 조회 성공")
        void getAllRecruitSuccessTest() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(baseUrl)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }
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
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                        .content(req.formatted("백엔드 주니어 개발자",
                                "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
                                "Python"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        return mvcResult.getResponse().getRedirectedUrl();
    }

}