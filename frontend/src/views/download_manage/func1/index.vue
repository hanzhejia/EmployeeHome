<template>
  <div class="func1-container">
    <el-table
      v-loading="listLoading"
      :data="list.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%"
      highlight-current-row
    >
      <el-table-column type="selection" width="55" />

<!--      <el-table-column label="文件名" prop="name" />-->
      <el-table-column label="文件名" prop="name">
        <template slot-scope="scope">
          <el-popover
            :content="'file/' + scope.row.type + '/' + scope.row.realName"
            placement="top-start"
            title="路径"
            width="200"
            trigger="hover"
          >
            <a
              slot="reference"
              :href="'http://localhost:8088' + '/file/' + scope.row.type + '/' + scope.row.realName"
              class="el-link--primary"
              style="word-break:keep-all;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;color: #1890ff;font-size: 13px;"
              target="_blank"
            >
              {{ scope.row.name }}
            </a>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="创建时间">
        <template slot-scope="{row}">
          <span>{{ row.createTime | formatDate }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建人" prop="createBy" />
      <el-table-column label="描述" prop="detail" />

      <el-table-column align="right">
        <template slot="header">
          <el-input v-model="search" size="mini" placeholder="输入关键字搜索" >
            <el-button slot="append" icon="el-icon-search" @click="handleSearch" />
          </el-input>
        </template>
        <template slot-scope="scope">
          <el-button size="mini" @click="handleDownload(scope.$index, scope.row)">下载</el-button>

          <template v-if="checkPermission(['admin'])">
            <el-button size="mini" type="danger" @click="handleCreate(scope.$index, scope.row)">添加</el-button>
          </template>

          <template v-if="checkPermission(['admin'])">
            <el-button size="mini" type="danger" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          </template>

          <template v-if="checkPermission(['admin'])">
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <el-dialog title="编辑" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="文件名" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createTime">
          <el-date-picker v-model="temp.createTime" type="datetime" placeholder="Please pick a date" />
        </el-form-item>
        <el-form-item label="创建人" prop="createBy">
          <el-input v-model="temp.createBy" />
        </el-form-item>
        <el-form-item label="描述" prop="detail">
          <el-input v-model="temp.detail" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">提交</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import permission from '@/directive/permission/index.js' // 权限判断指令
import checkPermission from '@/utils/permission' // 权限判断函数

import { downloadFile, fetchList, fetchListItem, createListItem, updateListItem, deleteListItem } from '@/api/download_manage'
import Pagination from '@/components/Pagination'

import { mapGetters } from 'vuex'

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

      temp: {
        storageId: undefined,
        realName: '',
        name: '',
        createTime: '',
        createBy: '',
        detail: ''
      },
      dialogFormVisible: false,
      dialogStatus: '',
      downloadLoading: false
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.listLoading = false
        this.list = response.data.items
        this.total = response.data.total
      })
    },

    resetTemp() {
      this.temp = {
        storageId: undefined,
        name: '',
        createTime: +new Date(),
        createBy: '',
        detail: ''
      }
    },

    checkPermission,

    handleSearch() {
      this.listLoading = true
      this.searchData = {
        search: this.search,
        listQuery: this.listQuery
      }
      fetchListItem(this.searchData).then(response => {
        this.listLoading = false
        this.list = response.data.items
        this.total = response.data.total
      })
    },

    handleDownload(index, row) {
      console.log(index, row)
      this.listLoading = true
      this.listLoading = false
    },

    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },

    handleEdit(index, row) {
      this.temp = Object.assign({}, row)
      this.temp.createTime = +new Date(this.temp.createTime)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },

    handleDelete(index, row) {
      deleteListItem(row).then(() => {
        // TODO download file
        this.$notify({
          title: 'Success',
          message: 'Delete Successfully',
          type: 'success',
          duration: 2000
        })
        this.list.splice(index, 1)
      })
    },

    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.temp.storageId = parseInt(Math.random() * 100) + 1024 // mock a id
          this.temp.createTime = +new Date(this.temp.createTime)
          createListItem(this.temp).then(() => {
            this.list.unshift(this.temp)
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Created Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },

    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          tempData.createTime = +new Date(tempData.createTime)
          updateListItem(tempData).then(() => {
            const index = this.list.findIndex(v => v.storageId === this.temp.storageId)
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
  //&-text {
  //  font-size: 30px;
  //  line-height: 46px;
  //}
}
</style>
