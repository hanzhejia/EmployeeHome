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
          v-model="form.desc"
          :autosize="{ minRows: 4, maxRows: 8}"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">修改</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        name: '',
        desc: ''
      }
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!')
    }
  }
}
</script>
