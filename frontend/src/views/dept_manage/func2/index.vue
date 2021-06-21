<template>
  <div class="func2-container">
    <div style="margin: 50px;"></div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="部门名称">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="详细描述">
        <el-input
          type="textarea"
          v-model="form.remark"
          :autosize="{ minRows: 4, maxRows: 8}"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">添加</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { createListItem } from '@/api/dept_manage'

export default {
  data() {
    return {
      form: {
        name: '',
        remark: ''
      }
    }
  },
  methods: {
    onSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          // this.form.id = parseInt(Math.random() * 100) + 1024
          console.log(this.form)
          createListItem(this.form).then(() => {
            this.$notify({
              title: 'Success',
              message: 'Created Successfully',
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
