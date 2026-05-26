package com.substring.easybuy.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RouteConfig {


    private final String productServiceId;
    private final String cartOrderServiceId;


    public RouteConfig(@Value("${PRODUCT_SERVICE_NAME}") String productServiceId, @Value("${CARD_ORDER_SERVICE_NAME}") String cartOrderServiceId) {
        this.productServiceId = productServiceId;
        this.cartOrderServiceId = cartOrderServiceId;
        System.out.println(this.productServiceId);
        System.out.println(this.cartOrderServiceId);
    }

    @Bean
    public RouteLocator route(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("product-route", route -> route

                        .path("/products/**")
                        .filters(f ->
                                f.addRequestHeader("x-api-gateway", "value from api gateway")
                                        .circuitBreaker(c->
                                                c.setName("productCircuitBreaker")
                                                        .setFallbackUri("forward:/product-fallback")
                                                )
                                        .rewritePath("/products/?(?<remaining>.*)", "/${remaining}"))
                        .uri("lb://" + productServiceId))



                .route("cart-order-route", route -> route
                        .path("/cart-orders/**")
                        .filters(f -> f.rewritePath("/cart-orders/?(?<remaining>.*)", "/${remaining}")).
                        uri("lb://" + cartOrderServiceId))


                .build();
    }


}
