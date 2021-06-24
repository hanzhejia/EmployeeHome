<template>
  <div class="dashboard-container">
    <div class="dashboard-text">name: {{ name }}</div>
    <div class="dashboard-text">device: {{ device }}</div>
    <div class="dashboard-text">token: {{ token }}</div>
    <div class="dashboard-text">avatar: {{ avatar }}</div>
    <div class="dashboard-text">introduction: {{ introduction }}</div>
    <div class="dashboard-text">roles: {{ roles }}</div>
    <el-button type="danger" @click="apiTestNormal">接口测试-用户</el-button>
    <el-button type="danger" @click="apiTestAdmin">接口测试-管理员</el-button>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { apiTestNormal, apiTestAdmin } from '@/api/test'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name',
      'device',
      'token',
      'avatar',
      'introduction',
      'roles'
    ])
  },
  methods: {
    apiTestNormal() {
      apiTestNormal().then(response => {
        this.$notify({
          title: 'Success',
          message: 'Request Successfully',
          type: 'success',
          duration: 2000
        })
      }).catch(reason => {
        this.$notify({
          title: 'Failed',
          message: 'Request Failed',
          type: 'error',
          duration: 2000
        })
      })
    },
    apiTestAdmin() {
      apiTestAdmin().then(response => {
        this.$notify({
          title: 'Success',
          message: 'Request Successfully',
          type: 'success',
          duration: 2000
        })
      }).catch(reason => {
        this.$notify({
          title: 'Failed',
          message: 'Request Failed',
          type: 'error',
          duration: 2000
        })
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    //font-size: 30px;
    line-height: 46px;
  }
}
</style>
