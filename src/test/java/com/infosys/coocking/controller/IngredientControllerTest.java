package com.infosys.coocking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infosys.coocking.model.dto.JwtRequest;
import com.infosys.coocking.model.entity.IngredientEntity;
import com.infosys.coocking.service.ingredient.IngredientService;
import com.infosys.coocking.service.user.JwtUserDetailsService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IngredientControllerTest {

    private static IngredientEntity RECORD;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private IngredientService ingredientService;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    private String token;

    @BeforeAll
    public static void init() {
        RECORD = new IngredientEntity();
        RECORD.setName("salt");
        RECORD.setId(1L);
    }




    @BeforeEach
    private void loginUser() {
        JwtRequest request = new JwtRequest("admin", "password");
        token = userDetailsService.login(request).getToken();
    }


    @Test
    @Order(1)
    public void getIngredientById_success() throws Exception {

        Mockito.when(ingredientService.getIngredientById(RECORD.getId())).thenReturn((RECORD));
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/ingredient/1")
                        .header("Authorization", "Bearer " + token)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("salt")));
    }

    @Test
    @Order(2)
    public void deleteIngredientById_success() throws Exception {

        Mockito.doNothing().when(ingredientService).deleteIngredientById(1L);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/ingredient/1")
                        .header("Authorization", "Bearer " + token)
        )
                .andExpect(status().isOk());

    }

    @Test
    @Order(3)
    public void getAllIngredient_success() throws Exception {

        Mockito.when(ingredientService.getAllIngredient()).thenReturn((Arrays.asList(RECORD)));
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/ingredient/")
                        .header("Authorization", "Bearer " + token)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(1)));
    }

    @Test
    @Order(4)
    public void createIngredient_success() throws Exception {
        Mockito.when(ingredientService.createIngredient(RECORD)).thenReturn(RECORD.getId());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/ingredient/")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(RECORD));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", is(1)));
    }

    @Test
    @Order(5)
    public void updateIngredient_success() throws Exception {
        RECORD.setName("a new one");
        RECORD.setVersion(1L);
        Mockito.when(ingredientService.updateIngredient(RECORD)).thenReturn(RECORD);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/ingredient/")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(RECORD));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.version", is(1)))
                .andExpect(jsonPath("$.name", is("a new one")));
    }


}
