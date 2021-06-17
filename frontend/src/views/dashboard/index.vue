<template>
  <div class="dashboard-container">
    <div class="dashboard-text">name: {{ name }}</div>
    <switch-roles @change="handleRolesChange" />
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import permission from '@/directive/permission/index.js' // 权限判断指令
import checkPermission from '@/utils/permission' // 权限判断函数
import SwitchRoles from './components/SwitchRoles'

export default {
  name: 'Dashboard',
  components: { SwitchRoles },
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
