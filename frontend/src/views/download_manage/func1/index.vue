<template>
  <div class="func1-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <template>
          <el-input v-model="search" placeholder="请输入内容" class="input-with-select">
            <el-select slot="prepend" v-model="select" placeholder="请选择">
              <el-option label="文件名" value="1" />
              <el-option label="创建人" value="2" />
              <el-option label="描述" value="3" />
            </el-select>
            <el-button slot="append" icon="el-icon-search" @click="handleSearch" />
          </el-input>
        </template>
      </el-form-item>
      <el-form-item style="float: right">
        <template v-if="checkPermission(['admin'])">
          <el-button type="danger" @click="handleMultipleDelete">批量删除</el-button>
        </template>
      </el-form-item>
      <el-form-item style="float: right">
        <template v-if="checkPermission(['admin'])">
          <el-button type="danger" @click="dialogFormVisible2 = true">上传文档</el-button>
        </template>
      </el-form-item>
    </el-form>

    <el-table
      v-loading="listLoading"
      :data="list.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%"
      highlight-current-row
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" />

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

      <el-table-column align="right" label="操作">
        <template slot-scope="scope">
          <el-button size="mini" :href="'http://localhost:8088' + '/file/' + scope.row.type + '/' + scope.row.realName">下载</el-button>
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
    <el-dialog title="添加" :visible.sync="dialogFormVisible2">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="文档标题">
          <el-input
            v-model="form.name"
            maxlength="10"
            show-word-limit
          />
        </el-form-item>
        <el-form-item label="文档描述">
          <el-input
            v-model="form.detail"
            maxlength="30"
            show-word-limit
            type="textarea"
            :autosize="{ minRows: 6, maxRows: 9}"
          />
        </el-form-item>
        <el-form-item label="文档选择">
          <el-upload
            ref="upload"
            drag
            :limit="1"
            :auto-upload="false"
            :action="uploadUrl"
            :data="form"
            :before-upload="beforeUpload"
            :on-success="handleSuccess"
            :on-error="handleError"
            :file-list="fileList"
          >
            <i class="el-icon-upload" />
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div slot="tip" class="el-upload__tip">可上传任意格式文件，且不超过1M</div>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :loading="loading" @click="onSubmit">提交</el-button>
          <el-button @click="onReset">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import permission from '@/directive/permission/index.js' // 权限判断指令
import checkPermission from '@/utils/permission' // 权限判断函数

import { fetchList, fetchListItem, createListItem, updateListItem, deleteList } from '@/api/download_manage'
import Pagination from '@/components/Pagination'
import { httphost } from '@/utils/global'

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
      select: '',
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
      dialogFormVisible2: false,
      dialogStatus: '',
      downloadLoading: false,

      multipleSelection: [],

      uploadUrl: httphost,
      loading: false,
      fileList: [],
      form: {
        storageId: undefined,
        realName: '',
        name: '',
        createBy: '',
        createTime: '',
        detail: ''
      }
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

    handleSelectionChange(val) {
      this.multipleSelection = val
    },

    handleSearch() {
      this.listLoading = true
      this.searchData = {
        select: this.select,
        search: this.search,
        listQuery: this.listQuery
      }
      fetchListItem(this.searchData).then(response => {
        this.listLoading = false
        this.list = response.data.items
        this.total = response.data.total
      })
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
    },

    handleMultipleDelete() {
      deleteList(this.multipleSelection).then(() => {
        this.$notify({
          title: 'Success',
          message: 'Delete Successfully',
          type: 'success',
          duration: 2000
        })
        let arr1 = this.list
        const arr2 = this.multipleSelection
        const idList = arr2.map(item => item.storageId)
        arr1 = arr1.filter(item => {
          return !idList.includes(item.storageId)
        })
        this.list = arr1
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
    },

    beforeUpload(file) {
      let isLt2M = true
      isLt2M = file.size / 1024 / 1024 < 1
      if (!isLt2M) {
        this.$message.error('上传文件大小不能超过 1MB!')
      }
      return isLt2M
    },
    handleSuccess(response, file, fileList) {
      this.list.unshift(response.data)
      this.onReset()
      this.dialogFormVisible2 = false
      this.$notify({
        title: 'Success',
        message: 'Created Successfully',
        type: 'success',
        duration: 2000
      })
    },
    handleError(e, file, fileList) {
      this.fileList = []
      this.$notify({
        title: 'Failed',
        message: 'Created Failed',
        type: 'error',
        duration: 2000
      })
    },
    onSubmit() {
      this.form.storageId = parseInt(Math.random() * 100) + 1024
      this.form.createBy = this.$store.getters.name
      this.form.createTime = new Date()
      this.form.createTime = +new Date(this.form.createTime)
      console.log(this.form)
      this.$refs.upload.submit()
    },
    onReset() {
      this.fileList = []
      this.form.realName = ''
      this.form.name = ''
      this.form.createBy = ''
      this.form.createTime = ''
      this.form.detail = ''
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
.el-select{
  width: 130px;
}
//.el-input {
//  width: 130px;
//}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
</style>
