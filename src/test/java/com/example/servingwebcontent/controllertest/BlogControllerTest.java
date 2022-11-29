package com.example.servingwebcontent.controllertest;

import com.example.servingwebcontent.controller.BlogController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("max")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/post-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/post-list-after.sql","/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class BlogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BlogController controller;

    @Test
    public void blogMain() throws Exception{
        this.mockMvc.perform(get("/blog"))
                .andDo(print())
                .andExpect(authenticated());
    }

//    @Test
//    public void postList() throws Exception{
//        this.mockMvc.perform(get("/blog"))
//                .andDo(print())
//                .andExpect(authenticated())
//                .andExpect(xpath("//*[@id='post-list']").nodeCount(4));
//    }



}
