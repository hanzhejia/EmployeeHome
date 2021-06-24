<template>
  <div class="func2-container">
  <el-form ref="form" :rules="rules" :model="form" label-width="80px">
    <el-form-item label="姓 名：" prop="username">
      <el-col :span="8">
      <el-input width="200px" v-model="form.username" placeholder="请输入用户姓名"></el-input>
      </el-col>
    </el-form-item>
    <el-form-item label="状 态：" prop="status">
      <el-select v-model="form.status" placeholder="请选择状态">
        <el-option label="管理员" value="1"></el-option>
        <el-option label="普通用户" value="2"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="登录名：" prop="loginname">
      <el-col :span="8">
      <el-input v-model="form.loginname" placeholder="请输入登录名"></el-input>
      </el-col>
    </el-form-item>
    <el-form-item label="密 码：" prop="password">
      <el-col :span="8">
      <el-input v-model="form.password" placeholder="请输入登录密码"></el-input>
      </el-col>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="add">添加</el-button>
      <el-button type="primary" @click="cancel">取消</el-button>
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
        username: '',
        status: '',
        loginname: '',
        password: ''
      },
      rules: {
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 6, message: '密码长度为6位', trigger: 'blur' }
        ]
      }
    }

  },

    methods: {
      /*添加用户*/
      add() {
        this.$refs['form'].validate((valid)=>{
          if (valid) {
          // this.form.id = parseInt(Math.random() * 100) + 1024
          console.log(this.form)
          createListItem(this.form).then(() => {
            this.$notify({
              title: 'Success',
              message: 'Update Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }})
    },
      cancel() {
        this.form = {
          username: '',
          status: '',
          loginname: '',
          password: ''
        }
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

