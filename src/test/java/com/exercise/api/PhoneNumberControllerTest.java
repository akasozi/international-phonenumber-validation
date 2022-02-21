package com.exercise.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */
@SpringBootTest
@AutoConfigureMockMvc
class PhoneNumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void itShouldGetAllPhoneNumbers() throws Exception {

        this.mockMvc.perform( MockMvcRequestBuilders
                .get("/api/v1/phone-numbers")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].phoneNumber").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").isNotEmpty());
    }
}