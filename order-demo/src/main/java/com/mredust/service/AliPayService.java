package com.mredust.service;


import com.alipay.api.AlipayApiException;
import com.mredust.model.Order;

public interface AliPayService {
    String aliPay(Order order) throws AlipayApiException;
}
