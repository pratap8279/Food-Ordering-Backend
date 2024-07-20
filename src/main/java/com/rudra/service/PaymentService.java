package com.rudra.service;

import com.rudra.model.order;
import com.rudra.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {
    public PaymentResponse createPaymentLink(order order) throws StripeException;
}
