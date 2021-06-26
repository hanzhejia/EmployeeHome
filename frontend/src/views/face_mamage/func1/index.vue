<template>
  <div class="func1-container">
    <div>
      <el-table
        v-if="this.$store.getters.roles[0] === 'admin'"
        ref="multipleTable"
        :data="curData"
        style="width: 100%"
        tooltip-effect="dark"
      >
        <el-table-column label="UID" prop="id" />
        <el-table-column label="登录名" prop="loginname" />
        <el-table-column label="用户名" prop="username" />
        <el-table-column label="人脸状态">
          <template slot-scope="face">
            <span v-if="ifFace(face.row.id)">已注册</span>
            <span v-else>未注册</span>
          </template>
        </el-table-column>
        <el-table-column label="人脸操作">
          <template slot-scope="data">
            <el-button @click="dialogVisible = true; clickid=data.row.id">扫脸</el-button>
          </template>
        </el-table-column>
        <el-table-column label="注销人脸">
          <template slot-scope="del">
            <el-button v-if="ifFace(del.row.id)" @click="delFace.id = del.row.id;dialogVisibleDel = true;">注销</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-table
        v-else
        ref="multipleTable"
        :data="userData"
        style="width: 100%"
        tooltip-effect="dark"
      >
        <el-table-column label="UID" prop="id" />
        <el-table-column label="登录名" prop="loginname" />
        <el-table-column label="用户名" prop="username" />
        <el-table-column label="人脸状态">
          <template slot-scope="face2">
            <span v-if="ifFace(face2.row.id)">已注册</span>
            <span v-else>未注册</span>
          </template>
        </el-table-column>
        <el-table-column label="人脸操作">
          <template slot-scope="data2">
            <el-button @click="dialogVisible = true; clickid=data2.row.id">扫脸</el-button>
          </template>
        </el-table-column>
        <el-table-column label="注销人脸">
          <template slot-scope="del2">
            <el-button v-if="ifFace(del2.row.id)" @click="delFace.id = del2.row.id;dialogVisibleDel = true;">注销</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog
        title="确定要注销吗"
        :visible.sync="dialogVisibleDel"
        width="30%"
      >
        <el-button @click="dialogVisibleDel = false;">取消</el-button>
        <el-button type="primary" @click="dialogVisibleDel = false;delNowFace();overFlod();">确定</el-button>
      </el-dialog>
      <el-dialog
        title="请正对屏幕"
        :visible.sync="dialogVisible"
        width="50%"
        @opened="opening"
        @closed="close"
      >
        <section>
          <video id="video" style="position:relative;left: 50%;top: 50%;transform: translateX(-50%) ;" />
        </section>
        <section>
          <canvas v-show="false" id="canvas" />
        </section>
        <section><img v-show="false" id="img" src="" alt=""></section>
        <el-button type="primary" class="func1-facelog" @click="dialogVisible = false;tackcapture()">提交</el-button>
      </el-dialog>
    </div>
    <div>
      <el-pagination
        v-if="this.$store.getters.roles[0] === 'admin'"
        v-show="total>0"
        :total="total"
        :page.sync="listQuery.page"
        :limit.sync="listQuery.limit"
        @pagination="getList"
        @current-change="changePage"
      />
    </div>
  </div>
</template>
<script>
// eslint-disable-next-line no-unused-vars
import { faceList, addFace, faceDel } from '@/api/face'
import { fetchList } from '@/api/user_manage'
export default {
  name: 'TakePhotos',
  filters: {
    formatDate: function(value) {
      const date = new Date(value)
      const y = date.getFullYear()
      let MM = date.getMonth() + 1
      MM = MM < 10 ? '0' + MM : MM
      let d = date.getDate()
      d = d < 10 ? '0' + d : d
      let h = date.getHours()
      h = h < 10 ? '0' + h : h
      let m = date.getMinutes()
      m = m < 10 ? '0' + m : m
      let s = date.getSeconds()
      s = s < 10 ? '0' + s : s
      const time1 = y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s
      const time2 = y + '-' + MM + '-' + d
      return time2
    }
  },
  data() {
    return {
      nowrole: null,
      list: [],
      findface: [],
      total: 0,
      listQuery: {
        page: 1,
        limit: 10
      },
      input1: '',
      ID: 1,
      clickid: 7,
      facedatalist: null,
      base64: null,
      dialogVisible: false,
      dialogVisibleDel: false,
      nowface: {
        id: 0,
        base64: ''
      },
      temp:
      {
        id: undefined,
        loginname: '',
        password: '',
        username: '',
        status: '',
        createdate: ''
      },
      curData: [],
      userData: [],
      tableData: [{
        careTime: '2021-06-19',
        content: 'face',
        id: '0',
        tiitle: 'opo',
        userid: '2' }],
      delFace: {
        id: null,
        base64: null
      }
    }
  },
  created() {
    this.getList()
    this.getFace()
  },
  // mounted() {
  //   this.getList()
  // },
  // created() {
  // this.getList()
  // this.getFace()
  // },
  methods: {
    changePage(page) {
      this.curData = this.tableData.slice((page - 1) * 10, (page - 1) * 10 + 10)
    },
    getList() {
      this.listLoading = true
      console.log(this.listQuery)
      fetchList(this.listQuery).then(response => {
        this.list = response.data.items
        this.total = response.data.total
        this.tableData = this.list
        this.curData = this.tableData.slice(0, 10)
        // eslint-disable-next-line eqeqeq
        if (this.$store.getters.roles[0] === 'normal') {
          for (var i in this.tableData) {
            if (this.tableData[i].username === this.$store.getters.name) {
              this.userData.push(this.tableData[i])
              break
            }
          }
        }
        console.log(this.userData)
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 100)
      })
    },
    getFace() {
      faceList().then(response => {
        this.facedatalist = response.data.items
        const basedata = JSON.parse(JSON.stringify(this.facedatalist))
        console.log('allface')
        for (var i in basedata) {
          this.findface.push(basedata[i].id)
        }
        console.log(this.findface)
        setTimeout(() => {
          this.listLoading = false
        }, 1000)
      })
    },
    delNowFace() {
      faceDel(this.delFace)
    },
    overFlod() {
      location.reload()
    },
    ifFace(data) {
      for (var i in this.findface) {
        if (this.findface[i] === data) {
          return true
        }
      }
      return false
    },
    opening() {
      // eslint-disable-next-line no-unused-vars
      const convas = document.querySelector('#canvas') //
      const video = document.querySelector('#video')
      // const audio = document.querySelector('audio')
      const img = document.querySelector('#img')
      const btn = document.querySelector('button')
      const context = canvas.getContext('2d')
      const width = 320
      const height = 0 //
      const streaming = false
      if (navigator.mediaDevices === undefined) {
        navigator.mediaDevices = {}
      }
      // 获取用户媒体,包含视频和音频
      navigator.mediaDevices
        .getUserMedia({ video: true })
        .then((stream) => {
          video.srcObject = stream
          video.play()
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
      const width = 320
      let height = 0 //
      let streaming = true
      if (streaming) {
        context.drawImage(video, 0, 0, 350, 200)
        // eslint-disable-next-line no-undef
        img.src = canvas.toDataURL('image/png')
        // eslint-disable-next-line no-undef
        const temp = canvas.toDataURL('image/png').slice(22)
        // eslint-disable-next-line no-const-assign,no-undef
        this.nowface.base64 = temp
        this.nowface.id = this.clickid
        // console.log(this.nowface.base64)
        addFace(this.nowface).then(response => {
          console.log('finish')
          const faceresult = JSON.parse(JSON.stringify(response))
          console.log(faceresult)
          setTimeout(() => {
            this.listLoading = false
          }, 5000)
        })
      }
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
      location.reload()
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
