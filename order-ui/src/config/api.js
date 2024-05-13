import request from '@/config/request'

// 调用支付接口
export const aliPay = (data) => {
  return request({
    url: '/pay/order',
    method: 'POST',
    data
  })
}