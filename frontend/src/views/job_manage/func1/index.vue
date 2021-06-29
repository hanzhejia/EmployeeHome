<template>
  <div class="func1-container">
    <el-table
      ref="multipleTable"
      :data="tableData.filter(
          data=>!search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%"
      @selection-change="selsChange"
    >
      <el-table-column
        type="selection"
        width="55"
        v-model="picked"
      />
      <el-table-column
        label="职位名称"
        prop="name"
      />
      <el-table-column
        label="详细信息"
        prop="remake"
      />
      <el-table-column
        label="操作"
        prop="job"
      />
      <el-table-column align="right">
        <template slot="header">
          <el-input
            style="position: relative;width: 200px"
            v-model="search"
            size="mini"
            @input="valuechange"
            placeholder="输入关键字搜索"
          />
          <el-button size="small" type="primary" @click="searchs('sb')" >搜索</el-button>
        </template>

        <template slot-scope="scope">
          <el-button v-if="buttonid=='admin'" size="mini" type="danger"  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button v-if="buttonid=='admin'" size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
<!--    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" @current-change="changePage" />-->
<!--    <el-pagination-->
<!--      v-show="total>0"-->
<!--      :total="total"-->
<!--      :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" @current-change="changePage">-->
<!--    </el-pagination>-->
    <el-pagination
      style="position: relative;top: 10px"
      @pagination="getList"
      @size-change="handleSizeChange"
      @current-change="changePage"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="10"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">-
    </el-pagination>
    <el-dialog title="编辑" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="职位名称" prop="job">
          <el-input v-model="textarea1" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="textarea2" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="handleUpdate()">提交</el-button>
      </div>
    </el-dialog>
    <div style="margin-top: 20px">
      <el-button @click="deletall()">删除选中的所有数据</el-button>

    </div>
  </div>
</template>

<script>
import permission from '@/directive/permission/index.js' // 权限判断指令
import checkPermission from '@/utils/permission'
import Pagination from '@/components/Pagination'
import { fetchList, updateListItem,deleteListItem,deleteallListItem } from '@/api/job_manage' // 权限判断函数

export default {
  name: 'Func1',
  components: { Pagination },
  directives: { permission },
  data() {
    return {
      picked:['css'],
      sels:'',
      buttonid:'admin',
      textarea1: '',
      textarea2: '',
      superDatasize:0,
      ts:'',
      temp: {
        job: '',
        remark: ''
      },
      tableData:[],
      curData:[],
      superData:{
        id:'',
        name:'',
        remake:''
      },
      size:10,
      midData:{
        id:'',
        name:'',
        remake:''
      },
      time:0,
      faData:{
        id:'',
        name:'',
        remake:''
      },
      search: '',
      dialogFormVisible: false,
      list: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    checkPermission,
    selsChange(val) {
      this.sels = val;
      console.log(this.sels)
    }
    ,
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
      this.tableData = this.midData.slice(0, this.size)
    },

    valuechange() {
      if (this.search == '') {
        this.midData = this.superData
        this.tableData = this.midData.slice(0, 10)
        this.total = this.superDatasize
      }
    },
    handleDelete(index, row) {
      this.faData.id = row.id
      this.faData.name = row.name
      this.faData.remake = row.remake
      console.log('sk')
      deleteListItem(this.faData).then(() => {
        console.log('sdsdd')
        this.$notify({
          title: '成功',
          message: '删除成功',
          type: 'success',
          duration: 2000
        })
        this.getList()
      })
    },
    getList() {
      this.buttonid = this.$store.getters.roles
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.temp = response.data.items
        this.total = response.data.total
        this.superData = this.temp

        this.superDatasize = this.total
        this.midData = this.superData
        this.tableData = this.midData.slice(0, this.size)
        console.log(this.tableData[0].id)
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 100)
      })
    },
    handleEdit(index, row) {
      console.log(index, row)
      this.temp = Object.assign({}, row)
      this.textarea1 = row.name
      this.textarea2 = row.remake
      this.ts = row.id
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    changePage(page) {
      console.log('sdsd')
      this.tableData = this.midData.slice((page - 1) * this.size, (page - 1) * this.size + this.size)
    },
    searchs(sb) {
      console.log(this.search)
      for (var i = 0; i < this.superData.length; i++) {

        if (this.superData[i].name.indexOf(this.search) != -1) {
          if (this.time == 0) {
            this.midData = []
          }
          this.time = 1
          this.midData.push(this.superData[i])
          console.log(this.search)
        }
      }
      this.total = this.midData.length
      console.log(this.total)
      this.tableData = this.midData.slice(0, this.size)
      console.log(this.tableData)
      this.time = 0
    },
    handleUpdate() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.faData.id = this.ts
          this.faData.name = this.textarea1
          this.faData.remake = this.textarea2
          updateListItem(this.faData).then(() => {
            const index = this.list.findIndex(v => v.id === this.temp.id)
            this.list.splice(index, 1, this.temp)
            this.getList()
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Update Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.func1 {
  &-container {
    margin: 30px;
  }
  //&-text {
  //  font-size: 30px;
  //  line-height: 46px;
  //}
}
</style>
