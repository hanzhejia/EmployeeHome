<template>
  <div class="func1-container">
    <article>
      <template>
        <div>
          face demo0.2 无后端链接api 前端拍照返回base64并弹窗显示
          *改为按钮+弹窗式验证+关闭
        </div>
      </template>
      <!--      <section>-->
      <!--        <video id="video" />-->
      <!--      </section>-->
      <!--            <section>-->
      <!--              <audio id="audio" />-->
      <!--            </section>-->
<!--      <button id="btn" @click="tackcapture">拍照</button>-->
<!--      <button @click="opening">开启</button>-->
<!--      <section>-->
<!--        <video id="video" />-->
<!--      </section>-->
<!--      <section>-->
<!--        <canvas id="canvas" />-->
<!--      </section>-->
<!--      <section><img id="img" src="" alt=""></section>-->
<!--      <button @click="close">关闭</button>-->
      <!--      <section>-->
      <!--        <canvas id="canvas" />-->
      <!--      </section>-->
      <!--      <section><img id="img" src="" alt=""></section>-->
    </article>
    <template>
      <el-button type="text" @click="dialogVisible = true;">刷脸</el-button>
      <el-dialog
        title="请正对屏幕"
        :visible.sync="dialogVisible"
        width="40%"
        :before-close="handleClose"
        @opened="opening"
        @closed="close"
        @close="tackcapture"
      >
                <section>
                  <video id="video" />
                </section>
                <section>
                  <canvas id="canvas" v-show="false"/>
                </section>
                <section><img id="img" src="" alt="" v-show="false"></section>
        <!--        <span slot="footer" class="dialog-footer">-->
        <!--        <el-button @click="dialogVisible = false">取 消</el-button>-->
        <el-button type="primary" class="func1-facelog" @click="dialogVisible = false;">登录</el-button>
        <!--        </span>-->
      </el-dialog>
    </template>
  </div>
</template>

<script>
// 拍照上传组件
// 父组件通过函数 getImg 获取照片路径,如 @getImg="getImg"
// eslint-disable-next-line no-unused-vars
const Address = ''
export default {
  name: 'TakePhotos',
  data() {
    return {
      dialogVisible: false
    }
  },
  // mounted() {
  //   this.opening()
  // },
  methods: {
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
        // context.drawImage(video, 0, 0, 350, 200) // 将视频画面捕捉后绘制到canvas里面
        // eslint-disable-next-line no-undef
        img.src = canvas.toDataURL('image/png') // 将canvas的数据传送到img里 base64格式
        console.log(img.src)
        alert(img.src) // 这边的值可以传入后端
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

<style lang="scss" scoped>
.func1 {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
  &-facelog{
    position: relative;
    left: 30%;
    width: 40%;
  }
}
</style>
