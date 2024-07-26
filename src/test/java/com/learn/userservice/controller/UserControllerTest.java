package com.learn.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.userservice.model.dto.request.RegisterRequest;
import com.learn.userservice.model.dto.response.RegisterResponse;
import com.learn.userservice.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Mock
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("POST /api/v1/register - Success")
    void testRegister() throws Exception {
        var firstName = "test";
        var lastName = "name";
        var email = "test-email@mail.com";
        var password = "test-password";

        var request = RegisterRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .build();
        var response = RegisterResponse.builder()
                .id("xyz")
                .email(email)
                .build();

        when(userService.register(request)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/register")
                        .content(new ObjectMapper().writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id", Matchers.is(response.id())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email", Matchers.is(response.email())));
    }
}
