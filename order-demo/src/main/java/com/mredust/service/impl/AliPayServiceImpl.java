package com.mredust.service.impl;

import com.alipay.api.AlipayApiException;
import com.mredust.config.AliPayConfig;
import com.mredust.model.Order;
import com.mredust.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AliPayServiceImpl implements AliPayService {
    @Autowired
    private AliPayConfig aliPayConfig;
    
    @Override
    public String aliPay(Order order) throws AlipayApiException {
        return aliPayConfig.pay(order);
    }
}
