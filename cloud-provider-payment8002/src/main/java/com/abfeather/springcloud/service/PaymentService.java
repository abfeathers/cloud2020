package com.abfeather.springcloud.service;

import com.abfeather.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author Abfeathers
 * @date $ $
 * @Description:
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
