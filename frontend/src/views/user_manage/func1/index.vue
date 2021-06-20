<template>
  <div>
    <div class="func1-container">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="用户名：">
          <el-input v-model="formInline.Username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="用户状态：">
          <el-select v-model="formInline.Status" placeholder="全部">
            <el-option label="全部" value="全部" />
            <el-option label="管理员" value="1" />
            <el-option label="普通用户" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="finds('s')">搜索</el-button>
          <el-button type="primary" @click="back('d')">删除</el-button>
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
        <el-table-column
          type="selection"
          width="55"
        />
        <el-table-column
          label="登录名"
          prop="Loginname"
        />
        <el-table-column
          label="密码"
          prop="Password"
        />
        <el-table-column
          label="用户名"
          prop="Username"
        />
        <el-table-column
          label="状态"
          prop="Status"
        />
        <el-table-column
          label="创建时间"
          prop="Createdate"
        />
        <el-table-column label="操作">
          <template slot-scope="scope">
            <!--跳转至用户修改页面-->
            <el-button size="mini" @click="handleEdit(scope.$index,scope.row)">编辑</el-button>
            <el-dialog
              :visible.sync="dialogFormVisible"
              title="修改用户"
              top="20px"
              width="300px"
            >
              <el-form :model="temp1">
                <el-form-item
                  :label-width="formLabeWidth"
                  label="用户名:"
                >
                  <el-input
                    v-model="temp1.Username"
                    auto-complete="off"
                  />
                </el-form-item>
                <el-form-item
                  :label-width="formLabeWidth"
                  label="登录名:"
                >
                  <el-input
                    v-model="temp1.Loginname"
                    auto-complete="off"
                  />
                </el-form-item>
                <el-form-item label="状    态：">
                  <el-select v-model="temp1.Status" placeholder="请选择状态">
                    <el-option label="管理员" value="1" />
                    <el-option label="普通用户" value="2" />
                  </el-select>
                </el-form-item>
              </el-form>
              <div
                slot="footer"
                class="dialog-footer"
              >
                <el-button @click="dialogFormVisible=false">取消</el-button>
                <el-button type="primary" @click="handleUpdate(scope.$index,scope.row)">修改</el-button>
              </div>
            </el-dialog>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div>
      //分页
      <pagination v-show="total>0" :limit.sync="listQuery.limit" :page.sync="listQuery.page" :total="total" @pagination="getList" />

    </div>
  </div>
</template>

<script>
import { fetchList } from '@/api/user_manage'
import Pagination from '@/components/Pagination'
import permission from '@/directive/permission'
import { updateListItem } from '@/api/download_manage'

export default {
  components: { Pagination },
  directives: { permission },
  data() {
    return {
      dialogFormVisible: false, // 弹窗默认关闭
      // 弹出层的表单
      temp1: {
        Username: '',
        Loginname: '',
        Status: '管理员'
      },
      formLabelWidth: '50px',
      formInline: {
        Username: ' ',
        Status: '全部'
      },
      /* 数据举例*/
      temp:
        {
          Loginname: '',
          Password: '',
          Username: '',
          Status: '',
          Createdate: ''
        },
      search: '',
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10,
        multipleSelection: []
      }
    }
  },
      //初始化页面
  created()
    {
      this.getList()
    },

    methods: {
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
      getList()
      {
        this.listLoading = true
        fetchList(this.listQuery).then(response => {
          this.list = response.data.items
          this.total = response.data.total

          // Just to simulate the time of the request
          setTimeout(() => {
            this.listLoading = false
          }, 100)
        })
      },
      handleEdit(index, row) {
        console.log(index, row)
        this.temp = Object.assign({}, row)
        this.temp.time = new Date(this.temp.time)
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      handleSet(index, row)
      {
        this.dialogFormVisible = false
        // eslint-disable-next-line no-unused-vars
        var params = {
          name: this.form.name,
          loginname: this.form.loginname
        }
      },
      finds(s)
      {
        console.log('search!')
      },
      back(d)
      {
        console.log('delete!')
      },
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
  &-text {
    font-size: 30px;
    line-height: 46px;
  }

}
</style>

