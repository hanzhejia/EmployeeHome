<template>
  <div class="func2-container">
    <el-form ref="ruleForm" status-icon :rules="rules" :model="ruleForm" label-width="100px" class="demo-ruleForm">

      <el-row>
        <el-col :span="10">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="ruleForm.name" placeholder="姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="身份证号" prop="card" required>
            <el-input v-model="ruleForm.card" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="10">
          <el-form-item label="性别" prop="sex">
            <el-select v-model="ruleForm.sex" placeholder="♂/♀">
              <el-option label="男" value=1 />
              <el-option label="女" value=2 />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="职位" prop="jobid">
            <el-select v-model="ruleForm.jobid" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="10">
          <el-form-item label="学历" prop="education">
            <el-input v-model="ruleForm.education" placeholder="文盲/小学/初中/高中/大专/本科/..." />
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="邮箱" prop="email" required>
            <el-input v-model="ruleForm.email" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="10">
          <el-form-item label="电话" prop="tel1" required>
            <el-col :span="6">
              <el-form-item prop="tel1">
                <el-input v-model="ruleForm.tel1" placeholder="区号" />
              </el-form-item>
            </el-col>
            <el-col class="line" :span="1" :offset="1">-</el-col>
            <el-col :span="10">
              <el-form-item prop="tel2">
                <el-input v-model="ruleForm.tel2" placeholder="固话号" />
              </el-form-item>
            </el-col>
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="手机" prop="phone" required>
            <el-input v-model="ruleForm.phone" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="10">
          <el-form-item label="政治面貌" prop="party">
            <el-input v-model="ruleForm.party" placeholder="群众/共青团员/预备党员/中共党员/或其他民主党派成员" />
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="QQ号码" prop="qqnum" required>
            <el-input v-model="ruleForm.qqnum" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="10">
          <el-form-item label="联系地址" prop="address">
            <el-input v-model="ruleForm.address" placeholder="省/市/区/详细地址" />
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="邮政编码" prop="post" required>
            <el-input v-model="ruleForm.post" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="10">
          <el-form-item label="出生日期" required>
            <el-col :span="11">
              <el-form-item prop="date">
                <el-date-picker
                  v-model="ruleForm.birthday"
                  type="date"
                  placeholder="选择日期"
                  style="width: 100%;"
                />
              </el-form-item>
            </el-col>
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="民族" prop="race">
            <el-input v-model="ruleForm.race" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="10">
          <el-form-item label="所学专业" prop="speciality">
            <el-input v-model="ruleForm.speciality" />
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="爱好" prop="hobby">
            <el-input v-model="ruleForm.hobby" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="10">
          <el-form-item label="备注" prop="remark">
            <el-input v-model="ruleForm.remark" />
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="所属部门" prop="deptid">
            <el-select v-model="ruleForm.deptid" placeholder="请选择">
              <el-option
                v-for="item in optionsdept"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item>
        <el-button type="primary" @click="createData('ruleForm')">添加</el-button>
        <el-button @click="resetForm('ruleForm')">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
// import { mapGetters } from 'vuex'

import { createListItem, fetchDept, fetchJob } from '@/api/employee_manage'

export default {
  name: 'Func2',
  // computed: {
  //   ...mapGetters([
  //     'name'
  //   ])
  // }
  data() {
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
    var validateCard = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入身份证号码'))
      } else if (value.length !== 18) {
        callback(new Error('身份证长度为18个字符'))
      } else if (value !== '') {
        var reg = /(^\d{17}(\d|X)$)/
        if (!reg.test(value)) {
          callback(new Error('请输入有效身份证号码'))
        }
      }
      callback()
    }
    var validatePhone = (rule, value, callback) => {
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
    var validateQnum = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入QQ号码'))
      } else if (value.length < 5 || value.length > 11) {
        callback(new Error('QQ号码长度在 5 到 11 个字符'))
      } else if (value !== '') {
        var reg = /(^\d{5,11}$)/
        if (!reg.test(value)) {
          callback(new Error('请输入有效QQ号码'))
        }
      }
      callback()
    }
    var validateTel1 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入区号'))
      } else if (value.length < 3 || value.length > 4) {
        callback(new Error('区号长度在 3 到 4 个字符'))
      } else if (value !== '') {
        var reg = /(^\d{3,4}$)/
        if (!reg.test(value)) {
          callback(new Error('请输入有效区号'))
        }
      }
      callback()
    }
    var validateTel2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入固话号'))
      } else if (value.length < 7 || value.length > 8) {
        callback(new Error('固话号长度在 7 到 8 个字符'))
      } else if (value !== '') {
        var reg = /(^\d{7,8}$)/
        if (!reg.test(value)) {
          callback(new Error('请输入有效固话号'))
        }
      }
      callback()
    }
    // var validatePhone = (rule, value, callback) => {
    //   if (value === '') {
    //     callback(new Error('请输入手机号码'))
    //   } else if (value.length !== 11) {
    //     callback(new Error('手机号码长度为11个字符'))
    //   } else if (value !== '') {
    //     var reg = /(^\d{11}$)/
    //     if (!reg.test(value)) {
    //       callback(new Error('请输入有效手机号码'))
    //     }
    //   }
    //   callback()
    // }
    var validatePost = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入邮编号码'))
      } else if (value.length !== 6) {
        callback(new Error('邮编号码长度为6个字符'))
      } else if (value !== '') {
        var reg = /(^\d{6}$)/
        if (!reg.test(value)) {
          callback(new Error('请输入有效邮编号码'))
        }
      }
      callback()
    }
    return {
      list: null,
      ruleForm: {
        id: '',
        name: '',
        card: '',
        sex: '',
        jobid: '',
        education: '',
        email: '',
        tel1: '',
        tel2: '',
        tel: '',
        phone: '',
        party: '',
        qqnum: '',
        address: '',
        post: '',
        birthday: '',
        race: '',
        speciality: '',
        hobby: '',
        remark: '',
        deptid: ''
      },
      options: [],
      optionsdept: [],
      rules: {
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
        tel1: [{ validator: validateTel1, trigger: 'blur' }],
        tel2: [{ validator: validateTel2, trigger: 'blur' }],
        phone: [{ validator: validatePhone, trigger: 'blur' }],
        party: [
          { required: true, message: '请输入政治面貌', trigger: 'blur' },
          { min: 2, max: 8, message: '长度在 2 到 8 个字符', trigger: 'blur' }
        ],
        qqnum: [{ validator: validateQnum, trigger: 'blur' }],
        address: [
          { required: true, message: '请输入联系地址', trigger: 'blur' },
          { min: 1, max: 30, message: '长度在 1 到 30 个字符', trigger: 'blur' }
        ],
        post: [{ validator: validatePost, trigger: 'blur' }],
        race: [
          { required: true, message: '请输入民族', trigger: 'blur' },
          { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
        ],
        speciality: [
          { required: true, message: '请输入专业', trigger: 'blur' },
          { min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur' }
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
  },
  methods: {
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
        console.log(response.data.items)
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 100)
      })
    },
    createData() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.ruleForm)
          tempData.id = parseInt(Math.random() * 100) + 1024 // mock a id
          tempData.tel = tempData.tel1 + tempData.tel2
          tempData.sex = parseInt(tempData.sex)
          console.log(tempData)
          createListItem(tempData).then(() => {
            // this.list.unshift(this.ruleForm)
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
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style lang="scss" scoped>
.func2 {
  &-container {
    margin: 30px;
  }

  //&-text {
  //  font-size: 30px;
  //  line-height: 46px;
  //}
}
</style>
