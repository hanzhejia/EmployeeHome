<template>
  <div class="func1-container">
    <switch-roles @change="handleRolesChange" />
    <el-form ref="searcheForm"  status-icon :rules="rules" :model="searcheForm" label-width="100px" class="demo-ruleForm">
      <el-row>
        <el-col :span="4">
          <el-form-item label="职位" prop="jobid">
            <el-select v-model="searcheForm.jobid"  clearable  size="mini" placeholder="选择职位搜索">
              <el-option
                v-for="item in options"
                :key="item.id"
                :label="item.name"
                :value="item.id"
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
            <el-select v-model="searcheForm.sex"  size="mini" clearable  placeholder="选择性别搜索">
              <el-option label="男" value=1 />
              <el-option label="女" value=2 />
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
            <el-select v-model="searcheForm.deptid"  size="mini" clearable  placeholder="选择部门搜索">
              <el-option
                v-for="item in optionsdept"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item>
            <el-button type="primary" @click="search()">搜索</el-button>
            <el-button v-if="checkPermission(['admin'])"  type="danger" @click="deleteFileOrDirectory(sels)" :disabled="this.sels.length === 0"> 删除</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <el-divider class="divider_cp" content-position="left" ><i class="el-icon-s-order"></i><span class="midText">表单信息</span></el-divider>

    <el-table
      :data="list"
      :header-cell-style="{background:'#eef1f6',color:'#606266','text-align':'center'}"
      :cell-style="rowStyle"
      style="width: 100%"
      max-height="600"
      @selection-change="selsChange"
    >
      <el-table-column
        v-if="checkPermission(['admin'])"
        type="selection"
        width="55"
      />
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="姓名">
              <span>{{ props.row.name }}</span>
            </el-form-item>
            <el-form-item label="员工号">
              <span>{{ props.row.id }}</span>
            </el-form-item>
            <el-form-item label="性别">
              <span>{{ props.row.sex }}</span>
            </el-form-item>
            <el-form-item label="手机号">
              <span>{{ props.row.phone }}</span>
            </el-form-item>
            <el-form-item label="邮箱">
              <span>{{ props.row.email }}</span>
            </el-form-item>
            <el-form-item label="职位">
              <span>{{ props.row.jobid }}</span>
            </el-form-item>
            <el-form-item label="学历">
              <span>{{ props.row.education }}</span>
            </el-form-item>
            <el-form-item label="身份证号码">
              <span>{{ props.row.card }}</span>
            </el-form-item>
            <el-form-item label="部门">
              <span>{{ props.row.deptid }}</span>
            </el-form-item>
            <el-form-item label="地址">
              <span>{{ props.row.address }}</span>
            </el-form-item>
            <el-form-item label="建档日期">
              <span>{{ props.row.createdate }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <el-table-column
        label="姓名"
        prop="name"
      />
<!--      <el-table-column-->
<!--        label="员工号"-->
<!--        prop="id"-->
<!--      />-->
      <el-table-column
        label="性别"
        prop="sex"
      />
      <el-table-column
        label="手机号"
        prop="phone"
      />
<!--      <el-table-column-->
<!--        label="邮箱"-->
<!--        prop="email"-->
<!--      />-->
      <el-table-column
        label="职位"
        prop="jobid"
      />
<!--      <el-table-column-->
<!--        label="学历"-->
<!--        prop="education"-->
<!--      />-->
      <el-table-column
        label="身份证号码"
        prop="card"
      />
      <el-table-column
        label="部门"
        prop="deptid"
      />
<!--      <el-table-column-->
<!--        label="联系地址"-->
<!--        prop="address"-->
<!--      />-->
<!--      <el-table-column-->
<!--        label="建档日期"-->
<!--        prop="createdate"-->
<!--      />-->
      <el-table-column
        v-if="checkPermission(['admin'])"
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
      <el-form ref="temp"  status-icon :rules="rules2" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="temp.sex" >
            <el-option label="男" value=1 />
            <el-option label="女" value=2 />
          </el-select>
        </el-form-item>
        <el-form-item label="手机号" prop="phone" required>
          <el-input v-model="temp.phone" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email" required>
          <el-input v-model="temp.email" />
        </el-form-item>
        <el-form-item label="职位" prop="jobid">
          <el-select v-model="temp.jobid" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="学历" prop="education">
          <el-input v-model="temp.education" />
        </el-form-item>
        <el-form-item label="身份证号码" prop="card" required>
          <el-input v-model="temp.card" />
        </el-form-item>
        <el-form-item label="部门" prop="deptid">
          <el-select v-model="temp.deptid" placeholder="请选择">
            <el-option
              v-for="item in optionsdept"
              :key="item.id"
              :label="item.name"
              :value="item.id"
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

<style>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
.divider_cp {
  position: relative;
  margin: 2% auto;
  width: 1550px;
  height: 1px;
  background-color: #d4d4d4;
  text-align: center;
  font-size: 16px;
  color: rgba(101, 101, 101, 1);
}
.midText {
  font-family: "Arial","Microsoft YaHei","黑体","宋体",sans-serif;
  /*font-weight:bold;*/
  font-size:16px;
  left: 20%;
  background-color: #ffffff;
  padding: 0 15px;

}
</style>

<script>

import checkPermission from '@/utils/permission'
import {
  fetchList,
  searchdateListItem,
  updateListItem,
  deleteListItem,
  fetchDept,
  fetchJob
} from '@/api/employee_manage'
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
  inject: ['reload'],
  data() {
    var validateCard = (rule, value, callback) => {
      if (value !== '') {
        if ((value.length !== 18)) {
          callback(new Error('身份证长度为18个字符'))
        } else if (value !== '') {
          var reg = /(^\d{17}(\d|X)$)/
          if (!reg.test(value)) {
            callback(new Error('请输入有效身份证号码'))
          }
        }
      }
      callback()
    }
    var validateEmail = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入邮箱'))
      } else {
        if (value !== '') {
          var reg = /^[A-Za-z0-9_\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
          if (!reg.test(value)) {
            callback(new Error('请输入有效的邮箱'))
          }
        }
        callback()
      }
    }
    var validatePhone2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入手机号码'))
      } else if (value.length !== 11) {
        callback(new Error('手机号码长度为11个字符'))
      } else if (value !== '') {
        var reg = /(^\d{11}$)/
        if (!reg.test(value)) {
          callback(new Error('请输入有效手机号码'))
        }
      }
      callback()
    }
    return {
      sels: [],
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
      options: [],
      optionsdept: [],
      rules: {
        // name: [
        //   { min: 2, max: 24, message: '长度在 2 到 24 个字符', trigger: 'blur' }
        // ],
        // card: [{ validator: validateCard, trigger: 'blur' }],
        // sex: [
        //   { message: '请选择性别', trigger: 'change' }
        // ],
        // phone: [{ validator: validatePhone, trigger: 'blur' }]
      },
      rules2: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 24, message: '长度在 2 到 24 个字符', trigger: 'blur' }
        ],
        card: [{ validator: validateCard, trigger: 'blur' }],
        sex: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        jobid: [
          { required: true, message: '请选择职位', trigger: 'change' }
        ],
        education: [
          { required: true, message: '请输入学历', trigger: 'blur' },
          { min: 2, max: 8, message: '长度在 2 到 8 个字符', trigger: 'blur' }
        ],
        email: [{ validator: validateEmail, trigger: 'blur' }],
        phone: [{ validator: validatePhone2, trigger: 'blur' }],
        address: [
          { required: true, message: '请输入联系地址', trigger: 'blur' },
          { min: 1, max: 30, message: '长度在 1 到 30 个字符', trigger: 'blur' }
        ],
        deptid: [
          { required: true, message: '请选择职位', trigger: 'change' }
        ]
      }
    }
  },
  created() {
    this.getdept()
    this.getjob()
    this.getList()
  },
  methods: {
    rowStyle() {
      return 'text-align:center'
    },
    checkPermission,
    handleRolesChange() {
      this.key++
    },
    selsChange(sels) {
      this.sels = sels
    },
    getdept() {
      this.listLoading = true
      fetchDept().then(response => {
        this.optionsdept = response.data.items
        response.data.total
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 100)
      })
    },
    getjob() {
      this.listLoading = true
      fetchJob().then(response => {
        this.options = response.data.items
        response.data.total
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 100)
      })
    },
    handleDelete(index, row) {
      this.$confirm('此操作将永久删除该员工信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let flag = 0
        const tempData = Object.assign({}, row)
        this.options.forEach((vul) => {
          if (tempData.jobid === vul.name) {
            tempData.jobid = vul.id
            flag = 1
          }
        })
        if (flag === 0 || tempData.jobid === 'NULL') {
          tempData.jobid = 0
        }
        flag = 0
        this.optionsdept.forEach((vul) => {
          if (tempData.deptid === vul.name) {
            tempData.deptid = vul.id
            flag = 1
          }
        })
        if (flag === 0 || tempData.deptid === 'NULL') {
          tempData.deptid = 0
        }
        if (tempData.sex === '男') {
          tempData.sex = 1
        } else if (tempData.sex === '女') {
          tempData.sex = 2
        }
        deleteListItem(tempData).then(() => {
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
    deleteFileOrDirectory() {
      this.$confirm('此操作将永久删除该员工信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.sels.forEach((vau) => {
          let flag = 0
          this.options.forEach((vul) => {
            if (vau.jobid === vul.name) {
              vau.jobid = vul.id
              flag = 1
            }
          })
          if (flag === 0 || vau.jobid === 'NULL') {
            vau.jobid = 0
          }
          flag = 0
          this.optionsdept.forEach((vul) => {
            if (vau.deptid === vul.name) {
              vau.deptid = vul.id
              flag = 1
            }
          })
          if (flag === 0 || vau.deptid === 'NULL') {
            vau.deptid = 0
          }
          if (vau.sex === '男') {
            vau.sex = 1
          } else if (vau.sex === '女') {
            vau.sex = 2
          }
          deleteListItem(vau).then(() => {

            // this.list.splice(index, 1)
          })
        })
        this.$notify({
          title: 'Success',
          message: 'Delete Successfully',
          type: 'success',
          duration: 2000
        })
        this.reload()
        console.log('删除成功')
      })
    },
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.items
        this.list.forEach((val) => {
          this.options.forEach((vul) => {
            if (val.jobid === vul.id) {
              val.jobid = vul.name
            }
          })
          if (val.jobid === 0) {
            val.jobid = 'NULL'
          }
          this.optionsdept.forEach((vul) => {
            if (val.deptid === vul.id) {
              val.deptid = vul.name
            }
          })
          if (val.deptid === 0) {
            val.deptid = 'NULL'
          }
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
      this.$confirm('此操作将永久修改该员工信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$refs['temp'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.temp)
            let flag = 0
            this.options.forEach((vul) => {
              if (tempData.jobid === vul.name) {
                tempData.jobid = vul.id
                flag = 1
              }
            })
            if (flag === 0 || tempData.jobid === 'NULL') {
              tempData.jobid = 0
            }
            flag = 0
            this.optionsdept.forEach((vul) => {
              if (tempData.deptid === vul.name) {
                tempData.deptid = vul.id
                flag = 1
              }
            })
            if (flag === 0 || tempData.deptid === 'NULL') {
              tempData.deptid = 0
            }
            if (tempData.sex === '男') {
              tempData.sex = 1
            } else if (tempData.sex === '女') {
              tempData.sex = 2
            }
            tempData.sex = parseInt(tempData.sex)
            console.log(tempData)
            updateListItem(tempData).then(() => {
              const index = this.list.findIndex(v => v.card === this.temp.card)
              this.options.forEach((vul) => {
                if (this.temp.jobid === vul.id) {
                  this.temp.jobid = vul.name
                }
              })
              if (this.temp.jobid === 0) {
                this.temp.jobid = 'NULL'
              }
              this.optionsdept.forEach((vul) => {
                if (this.temp.deptid === vul.id) {
                  this.temp.deptid = vul.name
                }
              })
              if (this.temp.deptid === 0) {
                this.temp.deptid = 'NULL'
              }
              if (this.temp.sex === 1) {
                this.temp.sex = '男'
              } else if (this.temp.sex === 2) {
                this.temp.sex = '女'
              }
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
      })
    },

    search() {
      this.$refs['searcheForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.searcheForm)
          if (tempData.sex === '') { tempData.sex = 0 }
          if (tempData.deptid === '') { tempData.deptid = 0 }
          if (tempData.jobid === '') { tempData.jobid = 0 }
          if (tempData.sex !== 0) {
            tempData.sex = parseInt(tempData.sex)
          }
          searchdateListItem(tempData, this.listQuery).then(response => {
            this.list = response.data.items
            this.list.forEach((val) => {
              this.options.forEach((vul) => {
                if (val.jobid === vul.id) {
                  val.jobid = vul.name
                }
              })
              if (val.jobid === 0) {
                val.jobid = 'NULL'
              }
              this.optionsdept.forEach((vul) => {
                if (val.deptid === vul.id) {
                  val.deptid = vul.name
                }
              })
              if (val.deptid === 0) {
                val.deptid = 'NULL'
              }
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
