package ua.volcaniccupcake.onlinestore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ua.volcaniccupcake.onlinestore.model.dto.OrderDTO;
import ua.volcaniccupcake.onlinestore.model.security.User;
import ua.volcaniccupcake.onlinestore.service.OrderService;

import java.util.Set;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Tag(name = "Order Controller", description = "This REST controller provides services to manage orders in the Online Store Application")
public class OrderController {
    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a new order for a specific user")
    public void createOrder(@RequestBody OrderDTO orderDTO,
                            @AuthenticationPrincipal User user) {
        orderService.save(user.getCustomer(), orderDTO);
    }

    @GetMapping
    @Operation(summary = "Provides orders for a specific user")
    public Set<OrderDTO> getOrdersByCustomerId(@AuthenticationPrincipal User user) {
        return orderService.listOrdersByCustomerId(user.getCustomer().getId());
    }

}