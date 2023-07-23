package ua.volcaniccupcake.onlinestore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.volcaniccupcake.onlinestore.model.Customer;
import ua.volcaniccupcake.onlinestore.model.Order;
import ua.volcaniccupcake.onlinestore.model.dto.OrderDTO;
import ua.volcaniccupcake.onlinestore.model.security.User;
import ua.volcaniccupcake.onlinestore.service.OrderService;
import ua.volcaniccupcake.onlinestore.service.OrderServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class OrderControllerTest {

    Set<OrderDTO> orders;

    @Autowired
    MockMvc mvc;

    @MockBean
    OrderService orderService;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        orders = Set.of(
                OrderDTO.builder()
                        .id(1L)
                        .build(),
                OrderDTO.builder()
                        .id(2L)
                        .build()
        );


    }

    @WithMockUser()
    @Test
    void testGetOrdersByCustomerId_whenAuthenticated_returnOrders() throws Exception {
        when(orderService.listOrders(any()))
                .thenReturn(orders);
        mvc.perform(get("/api/order"))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(orders)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetOrdersByCustomerId_whenUnauthorized_returnUnauthorizedStatus() throws Exception {
        when(orderService.listOrders(any()))
                .thenReturn(orders);
        mvc.perform(get("/api/order"))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser()
    @Test
    void testCreateOrder_whenValidParameters_returnCreatedStatus() throws Exception {
        OrderDTO order = OrderDTO.builder()
                .id(1L)
                .items(new HashSet<>())
                .build();
        mvc.perform(post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(order)))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateOrder_whenUnauthorized_returnUnauthorizedStatus() throws Exception {
        OrderDTO order = OrderDTO.builder()
                .id(1L)
                .items(new HashSet<>())
                .build();
        mvc.perform(post("/api/order")
                        .content(new ObjectMapper().writeValueAsString(order)))
                .andExpect(status().isUnauthorized());
    }


}