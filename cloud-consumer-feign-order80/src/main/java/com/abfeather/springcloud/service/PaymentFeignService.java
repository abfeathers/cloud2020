package com.abfeather.springcloud.service;

import com.abfeather.springcloud.entities.CommonResult;
import com.abfeather.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Abfeathers
 * @date $ $
 * @Description:
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")  //指定调用哪个微服务
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")    //指定掉用指定为服务的哪个地址
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timout")
    String paymentFeignTimeout();
}
