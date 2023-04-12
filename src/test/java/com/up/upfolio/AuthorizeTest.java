package com.up.upfolio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.up.upfolio.exceptions.ErrorDescriptor;
import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
import com.up.upfolio.model.errors.GenericApiError;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthorizeTest {
    @Autowired
    private AuthHelper authHelper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private JwtSuccessAuthResponse testAccount;

    @BeforeAll
    public void makeAccount() {
        testAccount = authHelper.makeAccount();
    }

    @Test
    void authorizeUnknownOrKnownNumber_unknownPassword() throws Exception {
        ErrorDescriptor[] errors = new ErrorDescriptor[]{ErrorDescriptor.ACCOUNT_NOT_FOUND, ErrorDescriptor.INCORRECT_PASSWORD};
        String[] phoneNumbers = new String[]{"71111111111", "70000000000"};

        for (int i = 0; i < errors.length; i++) {
            MvcResult result = mockMvc.perform(post("/v1/authorize/byPassword")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"phoneNumber\": \"" + phoneNumbers[i] + "\", \"password\": \"1\"}")
                    ).andDo(print())
                    .andExpect(status().is4xxClientError()).andReturn();

            String content = result.getResponse().getContentAsString();
            GenericApiError error = objectMapper.readValue(content, GenericApiError.class);

            assertEquals(errors[i], error.getError());
        }
    }
}
