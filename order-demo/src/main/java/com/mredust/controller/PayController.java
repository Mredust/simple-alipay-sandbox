package com.mredust.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.mredust.model.Order;
import com.mredust.service.impl.AliPayServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@RestController
@RequestMapping("/pay")
@Slf4j
@CrossOrigin
public class PayController {
    @Autowired
    private AliPayServiceImpl aliPayService;
    
    @PostMapping("/order")
    public Map<String, Object> doPost(@RequestBody Order order) throws AlipayApiException {
        log.info("order: {}", order);
        HashMap<String, Object> map = new HashMap<>();
        String res = aliPayService.aliPay(order);
        log.info("res: {}", res);
        map.put("msg", res);
        map.put("code", 200);
        return map;
    }
}

