<template>
  <div class="func2-container">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="职位名称">
        <el-input
          id="nt"
          v-model="textarea2"
          maxlength="10"
          show-word-limit
        ></el-input>
      </el-form-item>
      <el-form-item label="详细信息">
        <el-input
          v-model="textarea1"
          maxlength="30"
          show-word-limit
          type="textarea"
          :autosize="{ minRows: 6, maxRows: 9}"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="edit('scope.row')">提交</el-button>
        <el-button @click="kong('sb')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { fetchList, createListItem } from '@/api/job_manage'
export default {
  data() {
    return {
      textarea1: '',
      textarea2: '',
      temp: {
        ID: '',
        NAME: '',
        REMAKE: ''
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
      this.temp.ID = parseInt(Math.random() * 100) + 1024 // mock a id
      this.temp.NAME = this.textarea2
      this.temp.REMAKE = this.textarea1
      if( this.temp.NAME==''|| this.temp.REMAKE==''){
        this.$notify.error({
          title: 'Fail',
          message: '职位名称或内容未填写',
          duration: 2000
        })
      }
      else {
      createListItem(this.temp).then(response => {
        // eslint-disable-next-line eqeqeq
        if (response.data == 'sb') {
          this.$notify({
            title: 'Success',
            message: '创建成功',
            type: 'success',
            duration: 2000
          })
        } else {
          this.$notify.error({
            title: 'fail',
            message: '职位重复无法创建',
            duration: 2000
          })
        }
        console.log(response.data)
        this.textarea1 = ''
        this.textarea2 = ''
      })}
    },
    kong(sb) {
      this.textarea1 = ''
      this.textarea2 = ''
      console.log('sdsd')
    }
  }
}
</script>

<style lang="scss" scoped>
.func2 {
  &-container {
    margin: 30px;
  }
  //&-text {
  //  font-size: 30px;
  //  line-height: 46px;
  //}
}
</style>
