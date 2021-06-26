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
      <el-button type="success" @click="kong('sb')">删除</el-button>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import { fetchList, createListItem ,updateListItem,deleteListItem} from '@/api/notice_manage'
export default {
  props: {
    text: String,
    tittle:String,
    tempid: Number
  },
  data() {
    return {
      textarea1: this.text,
      textarea2: this.tittle,
      ts:this.tempid,
      temp:{
        careTime: '',
        content: '',
        id: '',
        tiitle: '',
        userid: 1
      }
    };
  },
  watch: {
    tittle:function (to,from){
      if (to) {
        this.textarea2 = to
      }
    },
    text: function(to, from) {
      if (to) {
        this.textarea1 = to
      }
    },
    tempid :function (to,from){
      if (to) {
        this.ts = to
      }
    }
  },
  methods: {
    edit(detailInfo) {
      // console.log(this.textarea1);
      // console.log(this.textarea1)
      this.temp.tiitle=this.textarea1
      this.temp.content=this.textarea2
      const tempData = Object.assign({}, this.temp)
      this.temp.careTime = +new Date(tempData.time)
      this.temp.id = this.ts
      console.log(this.temp)
      updateListItem(this.temp).then(() => {
        this.$notify({
          title: '成功',
          message: '修改成功',
          type: 'success',
          duration: 2000
        })
      })
    },
    kong(sb) {
      this.temp.tiitle=this.textarea1
      this.temp.content=this.textarea2
      const tempData = Object.assign({}, this.temp)
      this.temp.careTime = +new Date(tempData.time)
      this.temp.id = this.ts
      console.log(this.temp)
      deleteListItem(this.temp).then(() => {
        this.$notify({
          title: '成功',
          message: '删除成功',
          type: 'success',
          duration: 2000
        })
      })
    }
  },
};
</script>
<style>
</style>

