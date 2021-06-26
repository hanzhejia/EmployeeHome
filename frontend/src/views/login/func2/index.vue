<template>
  <div class="app-container common-list-page">
    <el-form
      ref="resetForm"
      :model="resetForm"
      :rules="resetFormRules"
      status-icon
      label-width="100px"
    >
      <el-form-item label="旧密码：" prop="password">
        <el-input v-model="resetForm.password" type="password" show-password auto-complete="off" />
      </el-form-item>
      <el-form-item label="新密码：" prop="newpwd">
        <el-input v-model="resetForm.newpwd" type="password" show-password auto-complete="off" />
      </el-form-item>
      <el-form-item label="确认密码：" prop="newpassword1">
        <el-input v-model="resetForm.newpassword1" type="password" show-password auto-complete="off" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click.native.prevent="toAmend">确认修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { updatePwd } from '@/api/user'
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入新密码'))
      } else if (value.toString().length < 6 || value.toString().length > 18) {
        callback(new Error('密码长度为6-18位'))
      } else {
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.resetForm.newpwd) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      resetForm: {
        // 传给后台所需要的参数
        newpassword1: '',
        password: '',
        username: ''// 此处只是后台需要的字段而已，如果前期有公用cookie里面有获取并且保存过，现在需要另外调用进来，具体的获取方法就看个人了
      },
      resetFormRules: {
        password: [
          { required: true, message: '请输入旧密码', trigger: 'blur' }
        ],
        newpwd: [
          { required: true, validator: validatePass, trigger: 'blur' }
        ],
        newpassword1: [
          { required: true, validator: validatePass2, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    toAmend() {
      this.$refs.resetForm.validate(valid => {
        if (valid) {
          this.resetForm.username = this.$store.getters.name
          updatePwd(this.resetForm).then(response => {
            console.log('updateok', response)
            window.alert('ok')
            this.logout()
          }).catch(response => {
            console.log('updatenotok', response)
            window.alert('password error')
          })
        }
      })
    },
    // 这是修改成功后重新返回登陆页的方法，看个人需要自行调整
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login`)
    },
    open() {
      this.$alert('修改成功', '提示')
    }
  }
}
</script>

<style lang="scss" scoped>
.el-form {
  width: 60%;
  margin: 50px auto 0;
  text-align: center;
  button {
    margin: 20px 0 0;
  }
}
</style>
