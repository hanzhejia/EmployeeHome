<template>
  <div class="func3-container">
  <el-form ref="form" :model="form" label-width="80px">
    <el-form-item label="姓 名：" >
      <el-col :span="8">
        <el-input width="200px" placeholder="请输入用户姓名"></el-input>
      </el-col>
    </el-form-item>
    <el-form-item label="登录名：">
      <el-col :span="8">
        <el-input placeholder="请输入登录名"></el-input>
      </el-col>
    </el-form-item>
    <el-form-item label="状 态：">
      <el-select v-model="form.region" placeholder="请选择状态">
        <el-option label="管理员" value="1"></el-option>
        <el-option label="普通用户" value="2"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="revise('r')">修改</el-button>
      <el-button type="primary" @click="cancel('c')">取消</el-button>
    </el-form-item>
  </el-form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      form: {
        region: '管理员'
      }
    }
  },
  methods: {
    revise(r) {
      console.log('revise!')
    },
    cancel(c) {
      console.log('cancel!')
    }
  }
}
</script>
<style lang="scss" scoped>
.func3 {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
