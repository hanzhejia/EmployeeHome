<template>
  <div class="func2-container">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="文档标题">
        <el-input
          v-model="form.name"
          maxlength="10"
          show-word-limit
        />
      </el-form-item>
      <el-form-item label="文档描述">
        <el-input
          v-model="form.detail"
          maxlength="30"
          show-word-limit
          type="textarea"
          :autosize="{ minRows: 6, maxRows: 9}"
        />
      </el-form-item>
      <el-form-item label="文档选择">
        <el-upload
          ref="upload"
          drag
          :limit="1"
          :auto-upload="false"
          :action="uploadUrl"
          :data="form"
          :before-upload="beforeUpload"
          :on-success="handleSuccess"
          :on-error="handleError"
          :file-list="fileList"
        >
          <i class="el-icon-upload" />
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div slot="tip" class="el-upload__tip">可上传任意格式文件，且不超过1M</div>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :loading="loading" @click="onSubmit">提交</el-button>
        <el-button @click="onReset">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { httphost_baseURL, httphost_upload } from '@/utils/global'

export default {
  name: 'Func2',
  data() {
    return {
      uploadUrl: httphost_upload,
      baseURL: httphost_baseURL,
      loading: false,
      fileList: [],
      form: {
        storageId: undefined,
        realName: '',
        name: '',
        createBy: '',
        createTime: '',
        detail: ''
      }
    }
  },
  methods: {
    beforeUpload(file) {
      let isLt2M = true
      isLt2M = file.size / 1024 / 1024 < 1
      if (!isLt2M) {
        this.$message.error('上传文件大小不能超过 1MB!')
      }
      return isLt2M
    },
    handleSuccess(response, file, fileList) {
      this.onReset()
      this.$notify({
        title: 'Success',
        message: 'Created Successfully',
        type: 'success',
        duration: 2000
      })
    },
    handleError(e, file, fileList) {
      this.fileList = []
      this.$notify({
        title: 'Failed',
        message: 'Created Failed',
        type: 'error',
        duration: 2000
      })
    },
    onSubmit() {
      this.form.storageId = parseInt(Math.random() * 100) + 1024
      this.form.createBy = this.$store.getters.name
      this.form.createTime = new Date()
      this.form.createTime = +new Date(this.form.createTime)
      console.log(this.form)
      this.$refs.upload.submit()
    },
    onReset() {
      this.fileList = []
      this.form.realName = ''
      this.form.name = ''
      this.form.createBy = ''
      this.form.createTime = ''
      this.form.detail = ''
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
