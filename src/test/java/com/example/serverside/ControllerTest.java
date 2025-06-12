package com.example.serverside;


import com.example.serverside.Controllers.Controller;
import com.example.serverside.Services.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ControllerTest {

    private MockMvc mockMvc;
    private Service service;
    private Controller controller;

    private final String apiKey = "Bearer rjfghreohgaojfjeorjpw45i945jijJDI3J";

    @BeforeEach
    public void setUp() {
        service = Mockito.mock(Service.class);
        controller = new Controller(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testCheckEndpoint() throws Exception {
        mockMvc.perform(get("/check"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }

    @Test
    public void testGetAllBookings_withValidApiKey_returnsEmptyList() throws Exception {
        Mockito.when(service.getBookings()).thenReturn(List.of());

        mockMvc.perform(get("/allBookings")
                        .header("Authorization", apiKey))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testGetAllBookings_withoutApiKey_returnsForbidden() throws Exception {
        mockMvc.perform(get("/allBookings"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testGetBookingByName_withApiKey_returnsEmptyList() throws Exception {
        Mockito.when(service.getBookingByName("Anna")).thenReturn(List.of());

        mockMvc.perform(get("/getBookingByName")
                        .param("name", "Anna")
                        .header("Authorization", apiKey)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
