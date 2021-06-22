<template>
  <div class="func2-container">
    <el-form ref="ruleForm" :model="ruleForm" label-width="100px" class="demo-ruleForm">

      <el-row>
        <el-col :span="10">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="ruleForm.name" placeholder="姓名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="身份证号" prop="card">
            <el-input v-model="ruleForm.card"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="10">
          <el-form-item label="性别" prop="sex">
            <el-select v-model="ruleForm.sex" placeholder="♂/♀">
              <el-option label="男" value=1></el-option>
              <el-option label="女" value=2></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="职位" prop="jobid">
            <el-select v-model="ruleForm.jobid" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="10">
          <el-form-item label="学历" prop="education">
            <el-input v-model="ruleForm.education"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="ruleForm.email"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="10">
          <el-form-item label="电话" prop="tel">
            <el-input v-model="ruleForm.tel"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="手机" prop="phone">
            <el-input v-model="ruleForm.phone"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="10">
          <el-form-item label="政治面貌" prop="party">
            <el-input v-model="ruleForm.party"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="QQ号码" prop="qqnum">
            <el-input v-model="ruleForm.qqnum"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="10">
          <el-form-item label="联系地址" prop="address">
            <el-input v-model="ruleForm.address" />
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="邮政编码" prop="post">
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
                ></el-date-picker>
              </el-form-item>
            </el-col>
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="民族" prop="race">
            <el-input v-model="ruleForm.race"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="10">
          <el-form-item label="所学专业" prop="speciality">
            <el-input v-model="ruleForm.speciality"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="爱好" prop="hobby">
            <el-input v-model="ruleForm.hobby"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="10">
          <el-form-item label="备注" prop="remark">
            <el-input v-model="ruleForm.remark"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="所属部门" prop="deptid">
            <el-select v-model="ruleForm.deptid" placeholder="请选择">
              <el-option
                v-for="item in optionsdept"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
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

import { createListItem } from '@/api/employee_manage'

export default {
  name: 'Func2',
  // computed: {
  //   ...mapGetters([
  //     'name'
  //   ])
  // }
  data() {
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
  methods: {
    createData() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          this.ruleForm.id = parseInt(Math.random() * 100) + 1024 // mock a id
          createListItem(this.ruleForm).then(() => {
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
