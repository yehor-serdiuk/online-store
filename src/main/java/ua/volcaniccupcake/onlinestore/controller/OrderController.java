package ua.volcaniccupcake.onlinestore.controller;

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
public class OrderController {
    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDTO orderDTO,
                            @AuthenticationPrincipal User user) {
        orderService.save(user.getCustomer(), orderDTO);
    }

    @GetMapping
    public Set<OrderDTO> getOrdersByCustomerId(@AuthenticationPrincipal User user) {
        return orderService.listOrdersByCustomerId(user.getCustomer().getId());
    }

}