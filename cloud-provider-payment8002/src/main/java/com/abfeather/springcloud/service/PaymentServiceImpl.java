package com.abfeather.springcloud.service;

import com.abfeather.springcloud.dao.PaymentDao;
import com.abfeather.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Abfeathers
 * @date $ $
 * @Description:
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

   public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
   }
}
