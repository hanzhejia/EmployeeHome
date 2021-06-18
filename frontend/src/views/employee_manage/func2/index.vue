<template>
  <div>
    <el-form :ref="ruleForm" :model="ruleForm" label-width="100px" class="demo-ruleForm">

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
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="10" :offset="2">
          <el-form-item label="职位" prop="job">
            <el-select v-model="ruleForm.job" >
              <el-option label="蒙古海军司令" value="蒙古海军司令"></el-option>
              <el-option label="梵蒂冈空军最高指挥官" value="梵蒂冈空军最高指挥官"></el-option>
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
          <el-form-item label="所属部门" prop="dept">
            <el-select v-model="ruleForm.dept" >
              <el-option label="人事部" value="人事部"></el-option>
              <el-option label="采购部" value="采购部"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">添加</el-button>
        <el-button @click="resetForm('ruleForm')">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
// import { mapGetters } from 'vuex'

export default {
  name: 'Func2',
  // computed: {
  //   ...mapGetters([
  //     'name'
  //   ])
  // }
  data() {
    return {
      ruleForm: {
        name: '',
        card: '',
        sex: '',
        job: '',
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
        dept: ''
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
        } else {
          console.log('error submit!!')
          return false
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
