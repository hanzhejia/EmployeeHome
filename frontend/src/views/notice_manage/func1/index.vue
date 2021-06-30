
<template >
  <div style="width: 100%">
    <el-row style="width: 100%">
      <p style="top: 10px ;font-size: 18px ;color:#303133;position: absolute">公告标题</p>
      <el-input
        v-model="input1"
        @input="valuechange"
        size="small"
        :autosize="{ minRows: 1, maxRows: 1}"
        placeholder="请输入内容"
        style="left: 100px; top: 20px;position: absolute;
            width: 200px;
"
      />
<!--      <p style="top: 10px ;font-size: 18px ;color:#303133;position: absolute;left: 320px ">公告内容</p>-->
<!--      <el-input-->
<!--        v-model="input2"-->
<!--        size="small"-->
<!--        :autosize="{ minRows: 1, maxRows: 1}"-->
<!--        placeholder="请输入内容"-->
<!--        style="left: 400px; top: 20px;position: relative;width: 400px;-->
<!--"-->
<!--      />-->
    </el-row>
    <el-row style="position: relative;left: 450px;top: 20px">
      <el-button size="small" type="primary" @click="searchs('sb')" >搜索</el-button>

    </el-row>
    <div>
      <el-table
        :data="curData.filter(
          data=>!input1 || data.tiitle.toLowerCase().includes(input1.toLowerCase()))"
        border
        style="top: 40px"
        @selection-change="selsChange"
      >
        <el-table-column
          type="selection"
          width="55"
          v-model="picked"
        />
        <el-table-column
          prop="tiitle"
          label="公告名称"
          align="center"
          width="200"
        />
        <el-table-column
          prop="content"
          label="公告内容"
          align="center"
          width="200"
        />
        <el-table-column
          prop="careTime"
          label="创建时间"
          align="center"
          width="200"
        />
        <el-table-column
          prop="userid"
          label="公告人"
          align="center"
          width="150"
        />
        <el-table-column
          align="center"
          label="操作"
          width="100"
        >

          <template slot-scope="scope">
            <el-button type="text" size="small" @click="sb(scope.row)">查看</el-button>
            <el-dialog
              @closeDialog="close"
              :modal-append-to-body="false"
              :title="sbs"
              :visible.sync="centerDialogVisible"
              width="40%"
              center
            >
              <span>{{ info }}</span>
              <span slot="footer" class="dialog-footer">
                <el-button @click="centerDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
              </span>
            </el-dialog>
            <el-button v-if=" buttonid=='admin'" type="text" size="small" @click="edit(scope.row)">编辑</el-button>
            <el-dialog
              :title="neww"
              :modal-append-to-body="false"
              :visible.sync="dialogVisible"
              width="50%"
              center
              :before-close="handleClose"
            >
              <Nav :text="''+infos" :tittle="''+info" :tempid="''+tempid" :re="''+random_numebr" />
            </el-dialog>
          </template>
        </el-table-column>
      </el-table>
      <div class="block" >
<!--        <el-pagination-->
<!--          v-show="total>0"-->
<!--          :total="total"-->
<!--          :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" @current-change="changePage">-->
<!--        </el-pagination>-->

<!--        <div class="block">-->
          <el-pagination
            style="position: relative;top: 50px"
            @pagination="getList"
            @size-change="handleSizeChange"
            @current-change="changePage"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="10"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
          </el-pagination>

        </div>

      </div>

    <div style="margin-top: 60px">

      <el-button v-if=" buttonid=='admin'" @click="deletall()">删除选中的所有数据</el-button>
    </div>
    </div>

</template>
<script>
import axios from 'axios'
import Nav from './sb.vue'
import { fetchList , deleteallListItem } from '@/api/notice_manage'


export default {
  inject: ['reload'],
  components: {
    Nav
  },
  data() {
    return {
      buttonid:'admin',
      list: null,
      listLoading: true,
      tempid:'',
      info: '',
      infos: '',
      random_numebr: 2,
      time:0,
      dialogVisible: false,
      centerDialogVisible: false,
      input1: '',
      input2: '',
      total: 0,
      size:10,
      listQuery: {
        page: 5,
        limit: 10
      },
      tableDatasize:0,
      midData:[],
      curData:[],
      tableData: [{
        careTime: '2021-06-19',
        content: '吃饭',
        id: '0',
        tiitle: 'opo',
        userid: '2' }]
    }

  },
  created() {
    this.getList()
  },
  methods: {
    selsChange(val) {
      this.sels = val;
      console.log(this.sels)
    },
    deletall() {
      console.log(this.sels)
      deleteallListItem(this.sels).then(() => {
        console.log('sdsdd')
        this.$notify({
          title: '成功',
          message: '删除成功',
          type: 'success',
          duration: 2000
        })
        this.getList()
      })
      // this.handleDelete(0,this.sels[i])
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.size = val
      this.curData = this.midData.slice(0,this.size)
    },
    getList() {

    this.buttonid = this.$store.getters.roles
      // this.buttonid=2
      console.log('sbsssssssssssssddd')
      this.listLoading = true
      console.log(this.listQuery)
      fetchList(this.listQuery).then(response => {
        console.log(response)
        this.list = response.data.items
        this.total = response.data.total
        this.tableDatasize = this.total
        this.tableData = this.list
        for(var i=0;i<this.tableData.length;i++){
          this.tableData[i].careTime=this.datetimeFormat(this.tableData[i].careTime)
          // this.tableData[i].userid='admin'
        }
        this.midData = this.tableData
        console.log(this.tableData)
        this.curData = this.midData.slice(0,this.size)
        console.log(this.list)
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 100)
      })
    },
    handleClick(row) {
      console.log(row.date)
    },
    datetimeFormat(longTypeDate){
      var dateTypeDate = "";
      var date = new Date();
      date.setTime(longTypeDate);
      console.log(date)
      dateTypeDate += date.getFullYear(); //年
      var q=date.getMonth()+1
      dateTypeDate += "-" +q; //月
      let d = date.getDate()
      d = d < 10 ? ('0' + d) : d
      dateTypeDate += "-" + d;
      return dateTypeDate;
    },
    searchs(sb) {
      // console.log(this.input1)
      for(var i=0;i<this.tableData.length;i++) {
        console.log(this.tableData[i].content)
        if(this.tableData[i].tiitle.indexOf(this.input1)!=-1){
          if(this.time==0){
           this.midData=[]}
         this.time=1
          this.midData.push(this.tableData[i])
        }
      }
      this.total = this.midData.length
      console.log(this.total)
      this.curData = this.midData.slice(0,this.size)
      this.time=0
    },
    valuechange(){

      if(this.input1==''){
        this.midData = this.tableData
        this.curData = this.midData.slice(0,this.size)
        this.total = this.tableDatasize
}
    },
    sb(po) {
      this.centerDialogVisible = true
      this.sbs = `${po.tiitle}`
      console.log(po.tiitle)
      this.info = po.content

    },
    edit(po) {
      this.info = po.content
      this.infos = po.tiitle
      this.tempid = po.id
      console.log( this.tempid)
      this.dialogVisible = true
      this.random_numebr = Math.round(Math.random() * 10000)
    },
    handleClose(done) {
          done()
      this.getList()
      // this.reload()
    },
    changePage(page){
      this.curData = this.midData.slice((page-1)*this.size,(page-1)*this.size+this.size)
    }
  }
}
</script>

