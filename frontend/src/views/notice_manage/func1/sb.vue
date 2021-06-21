<template>
  <div style="padding:30px;">
    <P>公告标题</P>
    <div style="width: 400px">
      <el-input id="nt"
                type="textarea"
                autosize
                placeholder="请输入内容"
                v-model="textarea1">
      </el-input>
    </div>
    <div style="margin: 20px 0;"></div>
    <P>公告内容</P>
    <div style="width: 600px">
      <el-input
        type="textarea"
        :autosize="{ minRows: 10, maxRows: 20}"
        placeholder="请输入内容"
        maxlength="600"
        show-word-limit
        v-model="textarea2">
      </el-input>
    </div>
    <div id="sbs" style="position: relative;top: 10px">
      <el-button type="primary" @click="edit('scope.row')">修改</el-button>
      <el-button type="success" @click="kong('sb')">重置</el-button>
    </div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  props: {
    text: String,
    tittle:String
  },
  data() {
    return {
      textarea1: this.text,
      textarea2: this.tittle
    };
  },
  watch: {
    tittle:function (to,from){
      if (to) {
        this.textarea2 = to
      }
    },
    text: function(to, from) {
      console.log('1');
      console.log(to);
      console.log(from);
      if (to) {
        this.textarea1 = to
      }
    }
  },
  methods: {
    edit(detailInfo) {
      console.log(this.textarea1);
    },
    kong(sb) {
      this.textarea1 = ''
      this.textarea2 = ''
      console.log('sdsd')
    }
  },
};
</script>
<style>
</style>

