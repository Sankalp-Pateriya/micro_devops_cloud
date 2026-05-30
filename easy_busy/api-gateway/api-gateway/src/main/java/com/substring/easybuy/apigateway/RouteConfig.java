package com.substring.easybuy.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;

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

                        .path("/products/**").filters(f -> f.addRequestHeader("x-api-gateway", "value from api gateway").requestRateLimiter(rateLimitConfig -> rateLimitConfig
                                .setKeyResolver(keyResolver())
                                .setRateLimiter(redisRateLimiter())

                        ).circuitBreaker(c -> c.setName("productCircuitBreaker").setFallbackUri("forward:/product-fallback")).rewritePath("/products/?(?<remaining>.*)", "/${remaining}")).uri("lb://" + productServiceId))


                .route("cart-order-route", route -> route.path("/cart-orders/**").filters(f ->

                        f.rewritePath("/cart-orders/?(?<remaining>.*)", "/${remaining}").retry(retryConfig -> retryConfig.setRetries(3).setMethods(HttpMethod.GET, HttpMethod.POST).setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)

                        )


                ).uri("lb://" + cartOrderServiceId))


                .build();
    }

    @Bean
    public KeyResolver keyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getHeaders().getFirst("user"));
    }


    @Bean
    public RedisRateLimiter redisRateLimiter(){
         return new RedisRateLimiter(4,4,1);
    }

}
