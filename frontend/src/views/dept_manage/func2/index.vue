<template>
  <div style="padding:30px">
    <div class="func2-container">
      <el-form :rules="rules" ref="form" :model="form" label-width="80px" label-position="top">
        <el-form-item label="部门名称" prop="name">
          <div style="width: 300px">
            <el-input v-model="form.name"></el-input>
          </div>
        </el-form-item>
        <el-form-item label="详细描述" prop="remark">
          <div style="width: 500px">
            <el-input
              type="textarea"
              v-model="form.remark"
              :autosize="{ minRows: 6, maxRows: 10}"
              maxlength="50"
              show-word-limit>
            </el-input>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">添加</el-button>
          <el-button @click="reset">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { checkSameName, createListItem } from '@/api/dept_manage'

export default {
  data() {
    return {
      form: {
        name: '',
        remark: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入部门名称', trigger: 'blur' }
        ],
        remark: [
          { required: true, message: '请输入详细描述', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    onSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          checkSameName(this.form).then(response => {
            if (response.data === 'success') {
              createListItem(this.form).then(() => {
                this.$notify({
                  title: 'Success',
                  message: 'Created Successfully',
                  type: 'success',
                  duration: 2000
                })
              })
            } else {
              this.$notify({
                title: 'failed',
                message: 'Created failed',
                type: 'failed',
                duration: 2000
              })
            }
          })
        }
      })
    },
    reset() {
      this.form = {
        name: '',
        remark: ''
      }
    }
  }
}
</script>
