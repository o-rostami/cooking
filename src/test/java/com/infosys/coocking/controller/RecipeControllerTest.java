package com.infosys.coocking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infosys.coocking.model.dto.JwtRequest;
import com.infosys.coocking.model.dto.UserDto;
import com.infosys.coocking.model.entity.RecipeEntity;
import com.infosys.coocking.service.recipe.RecipeService;
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
public class RecipeControllerTest {

    private static RecipeEntity RECORD;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private RecipeService recipeService;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    private String token;

    @BeforeAll
    static void init() {
        RECORD = new RecipeEntity();
        RECORD.setName("Recipe1");
        RECORD.setInstructions("add some cup of sugar to the water");
        RECORD.setId(1L);

    }

    private void registerUser(String userName, String password) {
        UserDto userDto = new UserDto(userName, password, "test@mail.com");
        userDetailsService.register(userDto);
    }

    private void loginUser(String userName, String password) {
        JwtRequest request = new JwtRequest(userName, password);
        token = userDetailsService.login(request).getToken();
    }


    @Test
    @Order(1)
    public void getRecipeById_success() throws Exception {
        registerUser("test1", "test1");
        loginUser("test1", "test1");
        Mockito.when(recipeService.getRecipeById(RECORD.getId())).thenReturn((RECORD));
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/recipe/1")
                        .header("Authorization", "Bearer " + token)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Recipe1")));
    }

    @Test
    @Order(2)
    public void deleteRecipeById_success() throws Exception {
        registerUser("test2", "test2");
        loginUser("test2", "test2");

        Mockito.doNothing().when(recipeService).deleteRecipeById(1L);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/recipe/1")
                        .header("Authorization", "Bearer " + token)
        )
                .andExpect(status().isOk());

    }

    @Test
    @Order(3)
    public void getAllRecipe_success() throws Exception {

        registerUser("test3", "test3");
        loginUser("test3", "test3");

        Mockito.when(recipeService.getAllRecipe()).thenReturn((Arrays.asList(RECORD)));
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/recipe/")
                        .header("Authorization", "Bearer " + token)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(1)));
    }

    @Test
    @Order(4)
    public void createRecipe_success() throws Exception {
        registerUser("test4", "test4");
        loginUser("test4", "test4");

        Mockito.when(recipeService.createRecipe(RECORD)).thenReturn(RECORD.getId());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/recipe/")
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
    public void updateRecipe_success() throws Exception {
        registerUser("test5", "test5");
        loginUser("test5", "test5");
        RECORD.setName("a new one");
        RECORD.setVersion(1L);
        Mockito.when(recipeService.updateRecipe(RECORD)).thenReturn(RECORD);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/recipe/")
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
