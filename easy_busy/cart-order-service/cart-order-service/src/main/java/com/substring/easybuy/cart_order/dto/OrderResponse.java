package com.substring.easybuy.cart_order.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.substring.easybuy.cart_order.entity.OrderStatus;
import com.substring.easybuy.cart_order.entity.PaymentMethod;
import com.substring.easybuy.cart_order.entity.PaymentStatus;

public record OrderResponse(
		Long id,
		String billingName,
		String billingPhone,
		String orderNumber,
		String userId,
		String shippingAddress,
		PaymentStatus paymentStatus,
		String extraInformation,
		PaymentMethod paymentMethod,
		OrderStatus status,
		BigDecimal totalAmount,
		List<OrderItemResponse> items,
		Instant createdAt,
		Instant updatedAt,
		Instant cancelledAt) {
}
