package com.ufrrj.smartrent.payment.config;

import com.ufrrj.smartrent.payment.service.PaymentGateway;
import com.ufrrj.smartrent.payment.PaymentGateway.PaymentGatewayMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {

    @Bean
    PaymentGateway paymentGateway() {
        return new PaymentGatewayMock();
    }

}
