package com.substring.easybuy.cart_order.client;

import java.util.UUID;

import com.substring.easybuy.cart_order.client.fallbacks.ProductClientFallback;
import com.substring.easybuy.common.payload.ProductSnapshot;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${PRODUCT_SERVICE_NAME}",url = "${PRODUCT_SERVICE_URL:}",fallback = ProductClientFallback.class)
public interface ProductClient {
	@GetMapping("/api/products/{productId}")
    ProductSnapshot getProductById(@PathVariable("productId") UUID productId);
}
