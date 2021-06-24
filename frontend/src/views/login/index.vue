<template>
  <div class="box">
    <div class="login-container">
      <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">
        <div class="title-container">
          <h3 class="title">员工之家</h3>
        </div>

        <el-form-item prop="username">
          <span class="svg-container">
            <svg-icon icon-class="user" />
          </span>
          <el-input
            ref="username"
            v-model="loginForm.username"
            placeholder="Username"
            name="username"
            type="text"
            tabindex="1"
            auto-complete="on"
          />
        </el-form-item>

        <el-form-item prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password" />
          </span>
          <el-input
            :key="passwordType"
            ref="password"
            v-model="loginForm.password"
            :type="passwordType"
            placeholder="Password"
            name="password"
            tabindex="2"
            auto-complete="on"
            @keyup.enter.native="handleLogin"
          />
          <span class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
          </span>
        </el-form-item>
        <el-checkbox v-model="checked" class="remeberPwd">记住密码</el-checkbox><p></p>
        <el-button :loading="loading" type="primary" style="width:48%;margin-bottom:30px;" @click.native.prevent="handleLogin">登录</el-button>
        <el-button :loading="loading" type="primary" style="width:48%;margin-bottom:30px;" @click="dialogVisible = true">刷脸</el-button>
        <div class="tips">
          <span style="margin-right:20px;">username: admin</span>
          <span> password: 111111</span>
        </div>
      </el-form>
    </div>
    <el-dialog
      title="请正对屏幕"
      :visible.sync="dialogVisible"
      width="50%"
      @opened="opening"
      @closed="close"
    >
      <section>
        <video id="video" />
      </section>
      <section>
        <canvas v-show="false" id="canvas" />
      </section>
      <section><img v-show="false" id="img" src="" alt=""></section>
      <el-button type="primary" class="func1-facelog" @click="dialogVisible = false;tackcapture()">登录</el-button>
      <!--        </span>-->
    </el-dialog>
  </div>
</template>

<script>
import { validUsername } from '@/utils/validate'
import { faceLogin } from '@/api/face'

export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        // callback(new Error('Please enter the correct user name'))
        callback()
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('The password can not be less than 6 digits'))
      } else {
        callback()
      }
    }
    return {
      ID: 1,
      facedatalist: null,
      base64: null,
      dialogVisible: false,
      nowface: {
        id: 0,
        base64: ''
      },
      loginForm: {
        username: 'admin',
        password: '111111'
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined,
      timeInterval: null,
      loginin: false,
      checked: false
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  created() {
    console.log('create')
    var vm = this
    // 在页面加载时从cookie获取登录信息
    const userName = vm.getCookie('userName')
    const userPwd = vm.getCookie('userPwd')
    // 如果存在赋值给表单，并且将记住密码勾选
    console.log('username', userName, 'pwd', userPwd)
    if (userName) {
      vm.loginForm.username = userName
      vm.loginForm.password = userPwd
      this.checked = true
    }
  },
  methods: {
    setUserInfo() {
      console.log('setinfo')
      var vm = this
      // 判断用户是否勾选记住密码，如果勾选，向cookie中储存登录信息，
      // 如果没有勾选，储存的信息为空
      if (vm.checked) {
        vm.setCookie('userName', vm.loginForm.username)
        vm.setCookie('userPwd', vm.loginForm.password)
        vm.setCookie('checked', vm.checked)
      } else {
        vm.setCookie('userName', '')
        vm.setCookie('userPwd', '')
      }
      console.log('name', vm.getCookie('userName'))
    },
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          // this.setCookie(this.loginForm.username, this.loginForm.password, 7)
          this.setUserInfo()
          this.$store.dispatch('user/login', this.loginForm).then(() => {
            console.log('登录成功')
            this.$router.push({ path: this.redirect || '/' })
            this.loading = false
          }).catch(() => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    account() {
      // if (document.cookie.length <= 0) {
      console.log(this.getCookie('username'))
      this.loginForm.username = this.getCookie('username')
      this.loginForm.password = this.getCookie('password')
      // }
    },
    setCookie(c_name, c_pwd, expiredays) {
      var exdate = new Date()
      exdate.setTime(exdate.getDate() + expiredays)
      document.cookie = c_name + '=' + decodeURIComponent(c_pwd) +
        ((expiredays == null) ? '' : ';expires=' + exdate.toGMTString())
    },
    getCookie: function(key) {
      if (document.cookie.length > 0) {
        var start = document.cookie.indexOf(key + '=')
        if (start !== -1) {
          start = start + key.length + 1
          var end = document.cookie.indexOf(';', start)
          if (end === -1) end = document.cookie.length
          return unescape(document.cookie.substring(start, end))
        }
      }
      return ''
    },
    clearCookie() {
      this.setCookie('', '', -1)
    },
    opening() {
      // eslint-disable-next-line no-unused-vars
      const convas = document.querySelector('#canvas') //
      const video = document.querySelector('#video')
      // const audio = document.querySelector('audio')
      const img = document.querySelector('#img')
      const btn = document.querySelector('button')
      const context = canvas.getContext('2d')
      const width = 320 // 视频和canvas的宽度
      const height = 0 //
      const streaming = false // 是否开始捕获媒体
      // 老的浏览器可能根本没有实现 mediaDevices，所以我们可以先设置一个空的对象
      if (navigator.mediaDevices == undefined) {
        navigator.mediaDevices = {}
      }
      // 获取用户媒体,包含视频和音频
      navigator.mediaDevices
        .getUserMedia({ video: true })
        .then((stream) => {
          video.srcObject = stream // 将捕获的视频流传递给video  放弃window.URL.createObjectURL(stream)的使用
          video.play() //  播放视频
          // audio.srcObject = stream
          // audio.play()
        })
    },
    tackcapture() {
      // 需要判断媒体流是否就绪
      const convas = document.querySelector('#canvas') //
      const video = document.querySelector('#video')
      // const audio = document.querySelector('audio')
      const img = document.querySelector('#img')
      const btn = document.querySelector('button')
      const context = canvas.getContext('2d')
      const width = 320 // 视频和canvas的宽度
      let height = 0 //
      let streaming = true // 是否开始捕获媒体
      if (streaming) {
        context.drawImage(video, 0, 0, 350, 200) // 将视频画面捕捉后绘制到canvas里面
        // eslint-disable-next-line no-undef
        img.src = canvas.toDataURL('image/png') // 将canvas的数据传送到img里 base64格式
        // eslint-disable-next-line no-undef
        const temp = canvas.toDataURL('image/png').slice(22)
        // eslint-disable-next-line no-const-assign,no-undef
        this.nowface.base64 = temp
        // console.log(this.nowface.base64)

        // faceLogin(this.nowface).then(response => {
        //   console.log('finish')
        //   const faceLogin = JSON.parse(JSON.stringify(response.data.items))
        //   console.log(faceLogin)
        //   setTimeout(() => {
        //     this.listLoading = false
        //   }, 5000)
        // })
        console.log('sb')
        this.$store.dispatch('user/faceLogin', this.nowface).then(() => {
          this.$router.push({ path: this.redirect || '/' })
          this.loading = false
        }).catch(() => {
          this.loading = false
        })
      }

      // 监听视频流就位事件,即视频可以播放了
      video.addEventListener(
        'canplay',
        function(ev) {
          if (!streaming) {
            height = video.videoHeight / (video.videoWidth / width)

            video.setAttribute('width', width)
            video.setAttribute('height', height)
            canvas.setAttribute('width', width)
            canvas.setAttribute('height', height)
            streaming = true
          }
        },
        false
      )
    },
    handleClose(done) {
      close()
      done()
    },
    close() {
      const video = document.querySelector('#video')
      video.srcObject.getTracks().forEach(track => track.stop())
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#283443;
$light_gray:#fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;

.box{
  width: 100%;
  height: 100%;
  background-color: #1f2d3d;
  .login-container {
    background-color: $bg;
    position: absolute;
    top:50%;
    left:50%;
    transform: translate(-50%,-50%);
    box-sizing:border-box;
    box-shadow: 0px 15px 25px rgba(0,0,0,.5);
    border-radius: 15px;

    .login-form {
      position: relative;
      width: 520px;
      max-width: 100%;
      padding: 50px 35px 0;
      margin: 0 auto;
      overflow: hidden;
    }

    .tips {
      font-size: 14px;
      color: #fff;
      margin-bottom: 10px;

      span {
        &:first-of-type {
          margin-right: 16px;
        }
      }
    }
    &-facelog{
      position: relative;
      left: 30%;
      width: 40%;
    }
    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
    }

    .title-container {
      position: relative;

      .title {
        font-size: 26px;
        color: $light_gray;
        margin: 0px auto 40px auto;
        text-align: center;
        font-weight: bold;
      }
    }

    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select: none;
    }
  }
}

</style>
