<template>
  <div class="dashboard-container">
    <div class="dashboard-text">name: {{ name }}</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import permission from '@/directive/permission/index.js' // 权限判断指令
import checkPermission from '@/utils/permission' // 权限判断函数

export default {
  name: 'Dashboard',
  directives: { permission },
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  methods: {
    checkPermission,
    handleRolesChange() {
      this.key++
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
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
