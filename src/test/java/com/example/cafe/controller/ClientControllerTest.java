package com.example.cafe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/test.properties")
@AutoConfigureMockMvc
class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @NoArgsConstructor
    static
    class sentData{
        HashMap<String, String> data = new HashMap<>();

        public sentData put(String key, String value){
            this.data.put(key, value);
            return this;
        }

        public String asJsonString(){
            try {
                return new ObjectMapper().writeValueAsString(this.data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    void readTest() throws Exception {
        this.mockMvc.perform(get("/clients"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

    }

    @Test
    @Sql(value = {"/delete-from-client-table.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void correctCreateTest() throws Exception {
        this.mockMvc.perform(post("/clients").content(
                new sentData()
                        .put("name", "Misha")
                        .put("email", "misha@misha.ru")
                        .put("birthDate", "2000-01-01")
                        .asJsonString()
                ).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void badCreateTest() throws Exception {
        this.mockMvc.perform(post("/clients").content(
                        new sentData()
                                .put("name", "Adil")
                                .put("email", "misha@misha.ru")
                                .put("birthDate", "2000-01-01")
                                .asJsonString()
                ).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(post("/clients").content(
                        new sentData()
                                .put("name", "Misha")
                                .put("email", "misha@misharu")
                                .put("birthDate", "2000-01-01")
                                .asJsonString()
                ).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(post("/clients").content(
                        new sentData()
                                .put("name", "Misha")
                                .put("email", "misha@misha.ru")
                                .put("birthDate", "2030-01-01")
                                .asJsonString()
                ).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(post("/clients").content(
                        new sentData()
                                .put("name", "Misha")
                                .put("email", "misha@misha.ru")
                                .put("birthDate", "2023-01-01")
                                .asJsonString()
                ).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(value = {"/delete&fill-client-table.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/delete-from-client-table.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void correctUpdateTest() throws Exception {
        this.mockMvc.perform(post("/clients").content(
                        new sentData()
                                .put("name", "Misha")
                                .put("email", "misha@misha.ru")
                                .put("birthDate", "2000-01-01")
                                .asJsonString()
                ).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @Sql(value = {"/delete&fill-client-table.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/delete-from-client-table.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void correctDeleteTest() throws Exception {
        this.mockMvc.perform(delete("/clients/1"))
                .andExpect(status().isOk());
    }

    @Test
    @Sql(value = {"/delete&fill-client-table.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/delete-from-client-table.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void badDeleteTest() throws Exception {
        this.mockMvc.perform(delete("/clients/3"))
                .andExpect(status().isBadRequest());
    }
}


