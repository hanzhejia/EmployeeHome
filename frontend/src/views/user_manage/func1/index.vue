<template>
  <div>
    <div>
      <!--批量删除    -->
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <template v-if="checkPermission(['admin'])">
            <el-button type="danger" size="mini" @click="handleMultipleDelete">批量删除</el-button>
          </template>
        </el-form-item>
      </el-form>

      <el-table
        v-loading="listLoading"
        :data="list.filter(data => !search || (data.username.toLowerCase().includes(search.toLowerCase())))"
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column label="ID" prop="id" />
        <el-table-column label="登录名" prop="loginname" />
        <el-table-column v-if="checkPermission(['admin'])" label="密码" prop="password" />
        <el-table-column label="用户名" prop="username" />
        <el-table-column label="状态" prop="status" />
        <el-table-column label="创建时间">
          <template slot-scope="{row}">
            <span>{{ row.createdate | formatDate }}</span>
          </template>
        </el-table-column>
        <el-table-column align="right">
          <template slot="header" slot-scope="data">
            <el-input v-model="search" size="mini" placeholder="输入用户名搜索">
              <el-button slot="append" icon="el-icon-search" @click="handleSearch" />
            </el-input>
          </template>
          <template v-if="checkPermission(['admin'])" slot-scope="scope">
            <el-button type="mini" @click="handleDelete(scope.$index,scope.row)">删除</el-button>
            <!--跳转至用户修改页面-->
            <el-button size="mini" @click="handleEdit(scope.$index,scope.row)">编辑</el-button>

            <el-dialog :visible.sync="dialogFormVisible" top="20px" width="300px">
              <el-form ref="temp" :model="temp" :rules="rules">
                <el-form-item :label-width="formLabeWidth" label="用户名:" prop="username">
                  <el-input v-model="temp.username" auto-complete="off" />
                </el-form-item>
                <el-form-item :label-width="formLabeWidth" label="登录名:" prop="loginname">
                  <el-input v-model="temp.loginname" auto-complete="off" />
                </el-form-item>
                <el-form-item :label-width="formLabeWidth" label="密码:" prop="password">
                  <el-input v-model="temp.password" auto-complete="off" />
                </el-form-item>
                <el-form-item label="状    态：" prop="status">
                  <el-select v-model="temp.status" placeholder="请选择状态">
                    <el-option label="管理员" value="1" />
                    <el-option label="普通用户" value="2" />
                  </el-select>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible=false">取消</el-button>
                <el-button type="primary" @click="handleUpdate(scope.$index,scope.row)">修改</el-button>
              </div>
            </el-dialog>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div>
      <!--分页     -->
      <pagination v-show="total>0" :limit.sync="listQuery.limit" :page.sync="listQuery.page" :total="total" @pagination="getList" />
    </div>
  </div>
</template>

<script>
import {
  deleteListItem,
  fetchList,
  updateListItem,
  fetchListItem
} from '@/api/user_manage'
import Pagination from '@/components/Pagination'// secondary package based on el-pagination
import permission from '@/directive/permission/index.js' // 权限判断指令
import checkPermission from '@/utils/permission'

export default {
  name: 'Func1',
  components: { Pagination },
  directives: { permission },
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
      search: '',
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10
      },
      dialogFormVisible: false, // 弹窗默认关闭
      dialogStatus: '',
      downloadLoading: false,
      formLabelWidth: '50px',
      multipleSelection: [],
      /* 搜索栏*/
      /*  searchform: {
        username: ' ',
        status: '全部'
      },*/
      /* 数据*/
      temp:
        {
          id: '',
          loginname: '',
          password: '',
          username: '',
          status: '',
          createdate: ''
        },
      rules: {
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 6, message: '密码长度为6位', trigger: 'blur' }
        ]
      }

    }
  },

  // 初始化页面
  created() {
    this.getList()
  },

  methods: {

    /* 显示用户*/
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.items
        this.total = response.data.total
        console.log(this.listQuery)
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 100)
      })
    },
    // v-if权限
    checkPermission,
    /* 编辑用户*/
    handleEdit(index, row) {
      this.temp = Object.assign({}, row)
      this.temp.time = new Date(this.temp.time)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['temp'].clearValidate()
      })
    },
    /* 更新用户信息*/
    handleUpdate() {
      this.$refs['temp'].validate((valid) => {
        if (valid) {
          // this.form.id = parseInt(Math.random() * 100) + 1024
          console.log(this.temp)
          updateListItem(this.temp).then(() => {
            const index = this.list.findIndex(v => v.id === this.temp.id)
            this.list.splice(index, 1, this.temp)
            console.log(this.list)
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
    },
    handleSearch(index, row) {
      this.listLoading = true
      this.searchData = {
        search: this.search,
        listQuery: this.listQuery
      }
      fetchListItem(this.searchData).then(response => {
        this.list = response.data.items
        this.total = response.data.total
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 100)
      })
    },
    /* 删除用户*/
    handleDelete(index, row) {
      this.$confirm('此操作将永久删除该用户信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const rowList = [row]
        deleteListItem(rowList).then(() => {
          this.$notify({
            title: 'Success',
            message: 'Delete Successfully',
            type: 'success',
            duration: 2000
          })
          this.list.splice(index, 1)
        })
      })
    },
    /* 选择删除*/
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    handleMultipleDelete() {
      this.$confirm('是否确定删除所选用户?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteListItem(this.multipleSelection).then(() => {
          this.$notify({
            title: 'Success',
            message: 'Delete Successfully',
            type: 'success',
            duration: 2000
          })
          let arr1 = this.list
          const arr2 = this.multipleSelection
          const idList = arr2.map(item => item.id)
          arr1 = arr1.filter(item => {
            return !idList.includes(item.id)
          })
          this.list = arr1
        })
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
  &-text {
    font-size: 30px;
    line-height: 46px;
  }

}
</style>

