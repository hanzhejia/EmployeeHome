<template>
  <div class="func2-container">
  <el-form ref="formName" :model="form" label-width="80px">
    <el-form-item label="姓 名：" >
      <el-col :span="8">
      <el-input width="200px" v-model="form.username" placeholder="请输入用户姓名"></el-input>
      </el-col>
    </el-form-item>
    <el-form-item label="状 态：">
      <el-select v-model="form.status" placeholder="请选择状态">
        <el-option label="管理员" value="1"></el-option>
        <el-option label="普通用户" value="2"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="登录名：">
      <el-col :span="8">
      <el-input v-model="form.loginname" placeholder="请输入登录名"></el-input>
      </el-col>
    </el-form-item>
    <el-form-item label="密 码：">
      <el-col :span="8">
      <el-input v-model="form.password" placeholder="请输入登录密码"></el-input>
      </el-col>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="add('formName')">添加</el-button>
      <el-button type="primary" @click="cancel('c')">取消</el-button>
    </el-form-item>
  </el-form>
  </div>
</template>
<script>
import { createListItem } from '@/api/user_manage'

export default {
  data() {
    return {
      form: {
        username:'',
        status: '',
        loginname:'',
        password:''
      }
    }
  },
  methods: {
    /*添加用户*/
    add(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          createListItem(tempData).then(() => {
            const index = this.list.findIndex(v => v.id === this.temp.id)
            this.list.splice(index, 1, this.temp)
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Update Successfully',
              type: 'success',
              duration: 2000
            })
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
      console.log('add!')
    },
    cancel(c) {
      console.log('cancel!')
    }
  }
}
</script>
<style lang="scss" scoped>
.func2 {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>

