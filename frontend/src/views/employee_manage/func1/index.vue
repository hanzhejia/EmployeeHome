<template>
  <div class="func1-container">
    <switch-roles @change="handleRolesChange" />
    <el-form ref="searcheForm" :model="searcheForm" label-width="100px" class="demo-ruleForm">
      <el-row>
        <el-col :span="4">
          <el-form-item label="职位" prop="jobid">
            <el-select v-model="searcheForm.jobid" placeholder="选择职位搜索">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6" :offset="2">
          <el-form-item label="姓名" prop="name">
            <el-input
              v-model="searcheForm.name"
              size="mini"
              placeholder="输入姓名搜索"
            />
          </el-form-item>
        </el-col>
        <el-col :span="6" :offset="2">
          <el-form-item label="身份证号码" prop="card">
            <el-input
              v-model="searcheForm.card"
              size="mini"
              placeholder="输入身份证号搜索"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="4">
          <el-form-item label="性别" prop="sex">
            <el-select v-model="searcheForm.sex" placeholder="选择性别搜索">
              <el-option label="男" value="1" />
              <el-option label="女" value="2" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6" :offset="2">
          <el-form-item label="手机号" prop="phone">
            <el-input
              v-model="searcheForm.phone"
              size="mini"
              placeholder="输入手机号搜索"
            />
          </el-form-item>
        </el-col>
        <el-col :span="6" :offset="2">
          <el-form-item label="所属部门" prop="deptid">
            <el-select v-model="searcheForm.deptid" placeholder="选择部门搜索">
              <el-option
                v-for="item in optionsdept"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item>
            <el-button type="primary" @click="search()">搜索</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <el-table
      :data="list"
      style="width: 100%"
    >
      <el-table-column
        type="selection"
        width="55"
      />
      <el-table-column
        label="姓名"
        prop="name"
      />
      <el-table-column
        label="性别"
        prop="sex"
      />
      <el-table-column
        label="手机号"
        prop="phone"
      />
      <el-table-column
        label="邮箱"
        prop="email"
      />
      <el-table-column
        label="职位"
        prop="jobid"
      />
      <el-table-column
        label="学历"
        prop="education"
      />
      <el-table-column
        label="身份证号码"
        prop="card"
      />
      <el-table-column
        label="部门"
        prop="deptid"
      />
      <el-table-column
        label="联系地址"
        prop="address"
      />
      <el-table-column
        label="建档日期"
        prop="createdate"
      />
      <el-table-column
        label="操作"
        prop="operation"
      >
        <template slot-scope="scope">
          <el-button v-if="checkPermission(['admin'])" size="mini" type="danger" @click="operation(scope.$index, scope.row)">编辑</el-button>
          <el-button v-if="checkPermission(['admin'])" size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>

    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="search" />

    <el-dialog title="编辑" :visible.sync="dialogFormVisible">
      <el-form ref="temp" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-input v-model="temp.sex" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="temp.phone" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="temp.email" />
        </el-form-item>
        <el-form-item label="职位" prop="jobid">
          <el-select v-model="temp.jobid" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="学历" prop="education">
          <el-input v-model="temp.education" />
        </el-form-item>
        <el-form-item label="身份证号码" prop="card">
          <el-input v-model="temp.card" />
        </el-form-item>
        <el-form-item label="部门" prop="deptid">
          <el-select v-model="temp.deptid" placeholder="请选择">
            <el-option
              v-for="item in optionsdept"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="联系地址" prop="address">
          <el-input v-model="temp.address" />
        </el-form-item>
        <el-form-item label="建档日期" prop="createdate">
          <el-input v-model="temp.createdate" :disabled="true" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="updateData()">提交</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>

import checkPermission from '@/utils/permission'
import { fetchList, searchdateListItem, updateListItem, deleteListItem } from '@/api/employee_manage'
import Pagination from '@/components/Pagination'
import permission from '@/directive/permission'

export default {
  name: 'Func1',
  // computed: {
  //   ...mapGetters([
  //     'name'
  //   ])
  // },
  components: { Pagination },
  directives: { permission },
  data() {
    return {

      temp: {
        name: '',
        sex: '',
        phone: '',
        email: '',
        jobid: '',
        education: '',
        card: '',
        deptid: '',
        address: '',
        createdate: ''
      },
      scope: '',
      dialogFormVisible: false,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10
      },
      searcheForm: {
        jobid: '',
        name: '',
        card: '',
        sex: '',
        phone: '',
        deptid: ''
      },
      options: [{
        value: 1,
        label: '黄金糕'
      }, {
        value: 2,
        label: '双皮奶'
      }, {
        value: 3,
        label: '蚵仔煎'
      }, {
        value: 4,
        label: '龙须面'
      }, {
        value: 5,
        label: '北京烤鸭'
      }],
      optionsdept: [{
        value: 1,
        label: '黄金糕'
      }, {
        value: 2,
        label: '双皮奶'
      }, {
        value: 3,
        label: '蚵仔煎'
      }, {
        value: 4,
        label: '龙须面'
      }, {
        value: 5,
        label: '北京烤鸭'
      }]
    }
  },
  created() {
    this.getList()
  },
  methods: {
    checkPermission,
    handleRolesChange() {
      this.key++
    },
    handleDelete(index, row) {
      // const tempData = Object.assign({}, row)
      this.options.forEach((vul) => {
        if (row.jobid === vul.label) {
          row.jobid = vul.value
        }
      })
      this.optionsdept.forEach((vul) => {
        if (row.deptid === vul.label) {
          row.deptid = vul.value
        }
      })
      if (row.sex === '男') {
        row.sex = 1
      } else if (row.sex === '女') {
        row.sex = 2
      }
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
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.items
        this.list.forEach((val) => {
          this.options.forEach((vul) => {
            if (val.jobid === vul.value) {
              val.jobid = vul.label
            }
          })
          this.optionsdept.forEach((vul) => {
            if (val.deptid === vul.value) {
              val.deptid = vul.label
            }
          })
          if (val.sex === 1) {
            val.sex = '男'
          } else if (val.sex === 2) {
            val.sex = '女'
          }
        })
        this.total = response.data.total
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 100)
      })
    },
    operation(index, row) {
      this.temp = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['temp'].clearValidate()
      })
    },
    updateData() {
      this.$refs['temp'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          this.options.forEach((vul) => {
            if (tempData.jobid === vul.label) {
              tempData.jobid = vul.value
            }
          })
          this.optionsdept.forEach((vul) => {
            if (tempData.deptid === vul.label) {
              tempData.deptid = vul.value
            }
          })
          if (tempData.sex === '男') {
            tempData.sex = 1
          } else if (tempData.sex === '女') {
            tempData.sex = 2
          }
          updateListItem(tempData).then(() => {
            const index = this.list.findIndex(v => v.card === this.temp.card)
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

    search() {
      this.$refs['searcheForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.searcheForm)
          if (tempData.sex === '') { tempData.sex = 0 }
          if (tempData.deptid === '') { tempData.deptid = 0 }
          if (tempData.jobid === '') { tempData.jobid = 0 }
          searchdateListItem(tempData, this.listQuery).then(response => {
            this.list = response.data.items
            this.list.forEach((val) => {
              this.options.forEach((vul) => {
                if (val.jobid === vul.value) {
                  val.jobid = vul.label
                }
              })
              this.optionsdept.forEach((vul) => {
                if (val.deptid === vul.value) {
                  val.deptid = vul.label
                }
              })
              if (val.sex === 1) {
                val.sex = '男'
              } else if (val.sex === 2) {
                val.sex = '女'
              }
            })
            this.total = response.data.total
            setTimeout(() => {
              this.listLoading = false
            }, 100)
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
