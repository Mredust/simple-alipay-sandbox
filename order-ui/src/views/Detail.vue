<template>
  <div class="container">
    <h2>订单详细</h2>
    <el-form :model="orderInfo" label-width="100px">
      <el-form-item label="订单编号" prop="out_trade_no">
        <el-input v-model="orderInfo.out_trade_no" :readonly="true"></el-input>
      </el-form-item>
      <el-form-item label="商品名称" prop="subject">
        <el-input v-model="orderInfo.subject" :readonly="true"></el-input>
      </el-form-item>
      <el-form-item label="商品价格" prop="total_amount">
        <el-input v-model="orderInfo.total_amount" :readonly="true"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit()" style="width: 40%" round>确认付款</el-button>
        <el-button @click="back()" style="width: 40%" round>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import request from '@/config/request'
import { aliPay } from '@/config/api'
export default {
  name: 'Detail',
  data() {
    return {
      orderInfo: {
        out_trade_no: '',
        subject: '',
        total_amount: 0,
      }
    }
  },
  mounted() {
    // 获取订单信息
    this.orderInfo = this.$route.params.orderInfo
    // 重置订单编号
    this.orderInfo.out_trade_no = new Date().getTime()
  },
  methods: {
    back() {
      this.tempElement = null;
      this.$router.push({
        name: 'shop',
      })
    },
    submit() {

      // 方式一：
      aliPay(this.orderInfo).then(res => {
        this.pay(res)
      }).catch(err => {
        console.log(err);
      });

      // 方式二：
      // request({
      //   url: '/pay/order',
      //   method: 'POST',
      //   data: this.orderInfo
      // }).then(res => {
      //   this.pay(res);
      // }).catch((err) => {
      //   console.log(err)
      // })


    },
    pay(res) {
      const divForm = document.querySelector('div');
      if (divForm) {
        const tempParent = divForm.parentNode;
        const nextSibling = divForm.nextSibling;
        // 移除原数据，替换新数据
        tempParent.removeChild(divForm);
        const div = document.createElement('div');
        div.innerHTML = res.msg;
        document.body.appendChild(div);
        document.forms[0].setAttribute('target', '_blank');
        document.forms[0].submit();
        // 处理重新返回shop点击其他商品付款时跳转页面数据仍为第一次数据
        document.body.removeChild(div);
        // 恢复原数据
        if (nextSibling) {
          tempParent.insertBefore(divForm, nextSibling);
        } else {
          tempParent.appendChild(divForm);
        }
      }
    }
  }
}
</script>
<style scoped>
.container {
  width: 50%;
  margin: 0 auto;
}
</style>
