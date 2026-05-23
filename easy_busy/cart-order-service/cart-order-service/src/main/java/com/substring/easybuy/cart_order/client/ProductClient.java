package com.substring.easybuy.cart_order.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.substring.easybuy.cart_order.dto.ProductSnapshot;

@FeignClient(name = "${PRODUCT_SERVICE_NAME}",url = "${PRODUCT_SERVICE_URL:}")
public interface ProductClient {
	@GetMapping("/api/products/{productId}")
	ProductSnapshot getProductById(@PathVariable("productId") UUID productId);
}
