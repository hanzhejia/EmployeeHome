<template v-if="checkPermission(['admin'])">
  <div>
    <div class="func1-container">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="用户名：">
          <el-input v-model="formInline.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="用户状态：">
          <el-select v-model="formInline.status" placeholder="全部">
            <el-option label="全部" value="全部" />
            <el-option label="管理员" value="1" />
            <el-option label="普通用户" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="finds()">搜索</el-button>
          <el-button type="primary" @click="handleDelete(scope.$index,scope.row)">删除</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table
        ref="multipleTable"
        :data="list"
        style="width: 100%"
        tooltip-effect="dark"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"/>
        <el-table-column label="登录名" prop="loginname"/>
        <el-table-column label="密码" prop="password"/>
        <el-table-column label="用户名" prop="username"/>
        <el-table-column label="状态" prop="status"/>
        <el-table-column label="创建时间">
          <template slot-scope="{row}">
            <span>{{ row.createdate | formatDate }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">

            <!--跳转至用户修改页面-->
            <el-button size="mini" @click="handleEdit(scope.$index,scope.row)">编辑</el-button>

            <el-dialog :visible.sync="dialogFormVisible" title="修改用户" top="20px" width="300px">
              <el-form :model="temp">
                <el-form-item :label-width="formLabeWidth" label="用户名:">
                  <el-input v-model="temp.username" auto-complete="off"/>
                </el-form-item>
                <el-form-item :label-width="formLabeWidth" label="登录名:">
                  <el-input v-model="temp.loginname" auto-complete="off"/>
                </el-form-item>
                <el-form-item :label-width="formLabeWidth" label="密码:">
                  <el-input v-model="temp.password" auto-complete="off"/>
                </el-form-item>
                <el-form-item label="状    态：">
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
import {deleteListItem,fetchList,updateListItem, searchdateListItem} from '@/api/user_manage'
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
        limit: 10,
        multipleSelection: []
      },
      dialogFormVisible: false, // 弹窗默认关闭
      dialogStatus: '',
      downloadLoading: false,
      // 弹出层的表单
      form: {
        username: '',
        loginname: '',
        password: '',
        status: '管理员'
      },
      formLabelWidth: '50px',
      /*搜索栏*/
      formInline: {
        username: ' ',
        status: '全部'
      },
      /* 数据举例*/
      temp:
        {
          id:undefined,
          loginname: '',
          password: '',
          username: '',
          status: '',
          createdate: ''
        },

    }
  },
      //初始化页面
  created()
    {
      this.getList()
    },

    methods: {

      /*显示用户*/
      getList()
      {
        this.listLoading = true
        fetchList(this.listQuery).then(response => {
          this.list = response.data.items
          this.total = response.data.total
          console.log(this.list)
          // Just to simulate the time of the request
          setTimeout(() => {
            this.listLoading = false
          }, 100)
        })
      },
      /*编辑用户*/
      handleEdit(index, row) {
        this.temp = Object.assign({}, row)
        this.temp.time = new Date(this.temp.time)
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      /*更新用户信息*/
      handleUpdate() {
        const tempData = Object.assign({}, this.temp)
        updateListItem(tempData).then(() => {
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
      },
      /*搜索用户*/
      finds()
      {
        const tempData = Object.assign({}, this.formInline)
        searchdateListItem(tempData).then(response => {
          this.list = response.data.items
          console.log(this.list)
          this.total = response.data.total
          setTimeout(() => {
            this.listLoading = false
          }, 100)
        })
        console.log('search!')
      },
      /*删除用户*/
      handleDelete()
      {
        deleteListItem(row).then(() => {
          this.$notify({
            title: 'Success',
            message: 'Delete Successfully',
            type: 'success',
            duration: 2000
          })
          this.list.splice(index, 1)
        })
      },
      /*选择用户*/
      toggleSelection(rows)
      {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row)
          })
        } else {
          this.$refs.multipleTable.clearSelection()
        }
      },
      handleSelectionChange(val)
      {
        this.multipleSelection = val
      }
    }
  }

</script>

<style lang="scss" scoped>
.func1 {
  &-container {
    margin: 30px;
  }
/*  &-text {
    font-size: 30px;
    line-height: 46px;
  }*/

}
</style>

