package com.substring.easybuy.cart_order.controller;

import com.substring.easybuy.cart_order.dto.OrderCreateRequest;
import com.substring.easybuy.cart_order.dto.ProductResponse;
import com.substring.easybuy.cart_order.dto.ProductSnapshot;
import com.substring.easybuy.cart_order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test/orders")
@RequiredArgsConstructor
public class OrderTestController {

    private Logger logger = LoggerFactory.getLogger(OrderTestController.class);
    private final OrderService orderService;

    //array product ids:
    @PostMapping
    public ResponseEntity<ProductSnapshot> createOrder(@RequestBody OrderCreateRequest request) {

        logger.info("order created request  received {}", request);

//        return ResponseEntity.ok("order created");

        //order place logic
        return ResponseEntity.ok(orderService.createOrder(request));

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
