<template>
  <div class="container">
    <h2>商品店铺</h2>
    <el-table :data="tableData" style="width: 100%" stripe>
      <el-table-column prop="out_trade_no" label="订单编号"> </el-table-column>
      <el-table-column prop="subject" label="订单名称"> </el-table-column>
      <el-table-column prop="total_amount" label="付款金额"> </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">购买</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "Shop",
  data: function () {
    return {
      tableData: [
        {
          out_trade_no: "1",
          subject: "Java从入门到入土",
          total_amount: 199,
        },
        {
          out_trade_no: "2",
          subject: "Java从新手到骑手",
          total_amount: 299,
        },
        {
          out_trade_no: "3",
          subject: "Java编程思想",
          total_amount: 399,
        },
        {
          out_trade_no: "4",
          subject: "Java设计模式",
          total_amount: 499,
        },
      ],
    };
  },
  methods: {
    handleClick(row) {
      this.$router.push({
        name: "detail",
        params: {
          orderInfo: row,
        },
      });
    },
  },
  created: function () { },
};
</script>

<style></style>
