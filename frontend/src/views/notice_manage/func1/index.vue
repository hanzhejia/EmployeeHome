
<template>
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
      <p style="top: 10px ;font-size: 18px ;color:#303133;position: absolute;left: 320px">公告内容</p>
      <el-input
        v-model="input2"
        size="small"
        :autosize="{ minRows: 1, maxRows: 1}"
        placeholder="请输入内容"
        style="left: 400px; top: 20px;position: relative;width: 400px;
"
      />
    </el-row>
    <el-row style="position: relative;left: 850px;top: -13px">
      <el-button size="small" type="primary" @click="searchs('sb')" >搜索</el-button>
      <el-button size="small" type="success">删除</el-button>
    </el-row>
    <div>
      <el-table
        :data="curData.filter(
          data=>!input1 || data.content.toLowerCase().includes(input1.toLowerCase()))"
        border
        style="top: 0px"
      >
        <el-table-column
          fixed
          prop="careTime"
          label="日期"
          width="150"
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
            <el-button type="text" size="small" @click="edit(scope.row)">编辑</el-button>
            <el-dialog
              :title="neww"
              :modal-append-to-body="false"
              :visible.sync="dialogVisible"
              width="50%"
              center
            >
              <Nav :text="''+infos" :tittle="''+info" :tempid="''+tempid" />
            </el-dialog>
          </template>
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
          v-show="total>0"
          :total="total"
          :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" @current-change="changePage">
        </el-pagination>
<!--        <div class="block">-->
<!--          <span class="demonstration">完整功能</span>-->
<!--          <el-pagination-->
<!--            @pagination="getList"-->
<!--            @size-change="handleSizeChange"-->
<!--            @current-change="changePage"-->
<!--            :current-page="currentPage4"-->
<!--            :page-sizes="[100, 200, 300, 400]"-->
<!--            :page-size="100"-->
<!--            layout="total, sizes, prev, pager, next, jumper"-->
<!--            :total="400">-->
<!--          </el-pagination>-->
        </div>
<!--        <el-pagination-->
<!--          @size-change="handleSizeChange"-->
<!--          @current-change="handleCurrentChange"-->
<!--          :current-page="currentPage4"-->
<!--          :page-sizes="[100, 200, 300, 400]"-->
<!--          :page-size="100"-->
<!--          layout="total, sizes, prev, pager, next, jumper"-->
<!--          :total="400">-->
<!--        </el-pagination>-->
      </div>
    </div>
</template>
<script>
import axios from 'axios'
import Nav from './sb.vue'
import { fetchList } from '@/api/notice_manage'

export default {
  components: {
    Nav
  },
  data() {
    return {
      list: null,
      listLoading: true,
      tempid:'',
      info: '',
      infos: '',
      time:0,
      dialogVisible: false,
      centerDialogVisible: false,
      input1: '',
      input2: '',
      total: 0,
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
    getList() {
      console.log('sbsssssssssssssddd')
      this.listLoading = true
      console.log(this.listQuery)
      fetchList(this.listQuery).then(response => {
        this.list = response.data.items
        this.total = response.data.total
        console.log('sssssssssssssssssssssssssssssssssssssssssda1111111111111111111111')
        console.log(this.total)
        this.tableDatasize = this.total
        this.tableData = this.list
        this.midData = this.tableData
        console.log(this.tableData)
        this.curData = this.midData.slice(0,10)
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
    searchs(sb) {
      // console.log(this.input1)
      for(var i=0;i<this.tableData.length;i++) {
        if(this.tableData[i].content.indexOf(this.input1)!=-1){
          if(this.time==0){
           this.midData=[]}
         this.time=1
          this.midData.push(this.tableData[i])
        }
      }
      this.total = this.midData.length
      console.log(this.total)
      this.curData = this.midData.slice(0,10)
      this.time=0
    },
    valuechange(){

      if(this.input1==''){
        this.midData = this.tableData
        this.curData = this.midData.slice(0,10)
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
      console.log(po)
      console.log('sp')
      this.info = po.content
      this.infos = po.tiitle
      this.tempid = po.id
      console.log( this.tempid)
      this.dialogVisible = true
    },
    changePage(page){
      this.curData = this.midData.slice((page-1)*10,(page-1)*10+9)
    }
  }
}
</script>

