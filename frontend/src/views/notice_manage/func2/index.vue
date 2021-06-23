
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
      <el-button  type="primary" @click="edit('scope.row')">提交</el-button>
      <el-button type="success" @click="kong('sb')">重置</el-button>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import { fetchList, createListItem } from '@/api/notice_manage'
export default {
  data() {
    return {
      textarea1: '',
      textarea2: '',
      temp: {
        careTime: '',
        content: '',
        id: '',
        tiitle: '',
        userid: 1
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      console.log('sb11111111111')
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        console.log('sbppppp')
        this.list = response.data.items
        this.total = response.data.total
        this.tableData = this.list
        console.log(this.list)
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 100)
      })
    },
    edit(detailInfo) {

      var nowDate = new Date();
      let date = {
        year: nowDate.getFullYear(),
        month: nowDate.getMonth() + 1,
        date: nowDate.getDate(),
      }
      let systemDate = date.year + '-' + 0 + date.month + '-' + 0 + date.date;
      console.log(systemDate);
      this.temp.id = parseInt(Math.random() * 100) + 1024 // mock a id
      this.temp.content = this.textarea2
      this.temp.tiitle = this.textarea1
      this.temp.careTime = systemDate
      console.log(this.temp)
      createListItem(this.temp).then(() => {
        this.$notify({
          title: 'Success',
          message: 'Created Successfully',
          type: 'success',
          duration: 2000
        })
      })
      this.$alert('公告发布成功', '标题', {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'info',
            message: `action: ${action}`
          })
        }
      })
    },
    kong(sb){
      this.textarea1=''
      this.textarea2=''
      console.log('sdsd')
    }
  },
};
</script>
<style>
</style>

