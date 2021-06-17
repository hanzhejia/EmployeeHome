<template>
  <div class="func2-container">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="文档标题">
        <el-input
          v-model="form.title"
          maxlength="10"
          show-word-limit
        ></el-input>
      </el-form-item>
      <el-form-item label="文档描述">
        <el-input
          v-model="form.desc"
          maxlength="30"
          show-word-limit
          type="textarea"
          :autosize="{ minRows: 6, maxRows: 9}"
        ></el-input>
      </el-form-item>
      <el-form-item label="文档选择">
        <el-upload
          class="upload-demo"
          drag
          action="https://jsonplaceholder.typicode.com/posts/"
          multiple>
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">只能上传xxx文件，且不超过500kb</div>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button @click="onReset">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

export default {
  name: 'Func2',
  data() {
    return {
      form: {
        title: '',
        desc: ''
      }
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!')
    },
    onReset() {
      console.log('reset!')
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
