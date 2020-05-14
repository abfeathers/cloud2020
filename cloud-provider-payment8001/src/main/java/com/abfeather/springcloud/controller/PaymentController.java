package com.abfeather.springcloud.controller;

import com.abfeather.springcloud.entities.CommonResult;
import com.abfeather.springcloud.entities.Payment;
import com.abfeather.springcloud.service.PaymentService;
import com.oracle.tools.packager.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author Abfeathers
 * @date $ $
 * @Description:
 */
@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        Log.info("*****插入结果：" + result);
        if (result > 0){
            return new CommonResult(200,"插入数据 serverPort =" + serverPort,result);
        }else{
            return new CommonResult(400,"插入数据失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        Log.info("*****查询结果：" + payment);
        if (!StringUtils.isEmpty(payment)){
            return new CommonResult(200,"查询成功 serverPort = "+serverPort,payment);
        }else {
            return new CommonResult(400,"没有对应的记录 id = "+ id,null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping(value = "/payment/zipkin")
    public String paymentZipkin(){
        return "hi, i'am paymentzipkin server fall back,welcome to abfeather";
    }
}
