package com.abfeather.springcloud.controller;

import com.abfeather.springcloud.entities.CommonResult;
import com.abfeather.springcloud.entities.Payment;
import com.abfeather.springcloud.service.PaymentService;
import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Abfeathers
 * @date $ $
 * @Description:
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        Log.info("*****插入结果：" + result);
        if (result > 0){
            return new CommonResult(200,"插入数据 serverPort =" + serverPort,result);
        }else{
            return new CommonResult(400,"插入数据失败 serverPort =" + serverPort,null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        Log.info("*****查询结果：" + payment);
        if (!StringUtils.isEmpty(payment)){
            return new CommonResult(200,"查询成功 serverPort =" + serverPort,payment);
        }else {
            return new CommonResult(400,"没有对应的记录 id = "+ id,null);
        }
    }


    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services){
            log.info("element : " + element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT_SERVICE");
        for (ServiceInstance instance : instances){
            log.info(instance.getServiceId()+"\t"+instance.getHost() +"\t" + instance.getPort() +"\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
