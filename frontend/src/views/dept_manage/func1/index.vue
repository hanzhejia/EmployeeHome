<template>
  <div class="func1-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <template>
          <el-input v-model="search" placeholder="请输入内容" autosize class="input-with-select">
            <el-button slot="append" icon="el-icon-search" @click="handleSearch"/>
          </el-input>
        </template>
      </el-form-item>
      <el-form-item style="float: right">
        <template v-if="checkPermission(['admin'])">
          <el-button type="danger" @click="handleMultipleDelete">批量删除</el-button>
        </template>
      </el-form-item>
    </el-form>

    <el-table
      v-loading="listLoading"
      :data="list"
      style="width: 100%"
      highlight-current-row
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"/>

      <el-table-column label="部门名称" prop="name"/>
      <el-table-column label="详细信息" prop="remark"/>

      <el-table-column align="right" label="操作">
<!--        <template slot="header">-->
<!--          <el-input v-model="search" size="mini" placeholder="输入关键字搜索">-->
<!--            <el-button slot="append" icon="el-icon-search" @click="handleSearch" />-->
<!--          </el-input>-->
<!--        </template>-->
        <template slot-scope="scope">

          <template v-if="checkPermission(['admin'])">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          </template>

          <template v-if="checkPermission(['admin'])">
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" align="center"/>

    <el-dialog title="编辑" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="部门名称" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item label="详细信息" prop="remark">
          <el-input v-model="temp.remark" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateData()">提交</el-button>
        <el-button @click="dialogFormVisible = false">取消</el-button>
<!--        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">提交</el-button>-->
      </div>
    </el-dialog>
  </div>
</template>

<script>
import permission from '@/directive/permission/index.js' // 权限判断指令
import checkPermission from '@/utils/permission' // 权限判断函数

import { fetchList, deleteList, fetchListItem, updateListItem } from '@/api/dept_manage'
import Pagination from '@/components/Pagination'

export default {
  name: 'Func1',
  components: { Pagination },
  directives: { permission },
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

      temp: {
        id: undefined,
        name: '',
        remark: ''
      },
      dialogFormVisible: false,
      // dialogPvVisible: false,
      dialogStatus: '',
      downloadLoading: false,
      multipleSelection: []
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
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

    resetTemp() {
      this.temp = {
        id: '',
        name: '',
        remark: ''
      }
    },

    checkPermission,

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

    handleEdit(index, row) {
      this.temp = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },

    handleDelete(index, row) {
      this.$confirm('是否确定删除该部门?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const rowList = [row]
        deleteList(rowList).then(() => {
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

    handleSelectionChange(val) {
      this.multipleSelection = val
    },

    handleMultipleDelete() {
      if (this.multipleSelection.length === 0) {
        this.$notify({
          title: 'Failed',
          message: 'No Select',
          type: 'error',
          duration: 2000
        })
      } else {
        this.$confirm('是否确定删除所选部门?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteList(this.multipleSelection).then(() => {
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
    },

    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
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
}
</style>
