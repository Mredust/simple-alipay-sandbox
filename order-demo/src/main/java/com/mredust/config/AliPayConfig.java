package com.mredust.config;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.mredust.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 配置文件读取
 */
@Configuration
public class AliPayConfig {
    
    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    @Value("${alipay.appId}")
    private String appId;
    
    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    @Value("${alipay.privateKey}")
    private String privateKey;
    
    /**
     * 支付宝公钥,
     */
    @Value("${alipay.publicKey}")
    private String publicKey;
    
    /**
     * 服务器异步通知页面路径需http://格式的完整路径，不能加?id=123这类自定义参数
     */
    @Value("${alipay.notifyUrl}")
    private String notifyUrl;
    
    /**
     * 页面跳转同步通知页面路径 需http://格式的完整路径.
     * 支付完成后返回的地址
     */
    @Value("${alipay.returnUrl}")
    private String returnUrl;
    
    /**
     * 签名方式
     */
    @Value("${alipay.signType}")
    private String signType;
    
    /**
     * 字符编码格式
     */
    @Value("${alipay.charset}")
    private String charset;
    
    /**
     * 支付宝网关
     */
    @Value("${alipay.gatewayUrl}")
    private String gatewayUrl;
    
    /**
     * 格式
     */
    @Value("${alipay.format}")
    private String format;
    
    
    public String pay(Order order) throws AlipayApiException {
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, appId, privateKey, format, charset, publicKey, signType);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setReturnUrl(returnUrl);
        request.setNotifyUrl(notifyUrl);
        request.setBizContent(JSON.toJSONString(order));
        String body = alipayClient.pageExecute(request).getBody();
        return body;
    }
}


