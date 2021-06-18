<template>
  <div class="func1-container">
    <switch-roles @change="handleRolesChange" />
    <el-table
      :data="tableData.filter(data => !search || data.name.includes(search))"
      style="width: 100%"
    >
      <el-table-column
        type="selection"
        width="55"
      />
      <el-table-column
        label="姓名"
        prop="name"
      />
      <el-table-column
        label="性别"
        prop="sex"
      />
      <el-table-column
        label="手机号"
        prop="phone"
      />
      <el-table-column
        label="邮箱"
        prop="email"
      />
      <el-table-column
        label="职位"
        prop="job"
      />
      <el-table-column
        label="学历"
        prop="education"
      />
      <el-table-column
        label="身份证号码"
        prop="card"
      />
      <el-table-column
        label="部门"
        prop="dept"
      />
      <el-table-column
        label="联系地址"
        prop="address"
      />
      <el-table-column
        label="建档日期"
        prop="createdate"
      />
      <el-table-column
        label="操作"
        prop="operation"
      >
        <template>
          <el-button v-if="checkPermission(['admin'])" size="mini" type="danger" @click="operation()">编辑</el-button>
        </template>
      </el-table-column>
      <el-table-column align="right">
        <template slot="header">
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入姓名搜索"
          />
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
// import { mapGetters } from 'vuex'
import checkPermission from '@/utils/permission'

export default {
  name: 'Func1',
  // computed: {
  //   ...mapGetters([
  //     'name'
  //   ])
  // },
  data() {
    return {
      tableData: [
        {
          name: '甄珍器',
          sex: '男',
          phone: '12345678900',
          email: '1004517408@qq.com',
          job: '蒙古海军司令',
          education: '大专人',
          card: '123456789456123046',
          dept: '宣传部',
          address: '翻斗花园',
          createdate: '111122222'
        }
      ],
      search: ''
    }
  },
  methods: {
    checkPermission,
    handleRolesChange() {
      this.key++
    },
    handleDownload(index, row) {
      console.log(index, row)
    },
    handleEdit(index, row) {
      console.log(index, row)
    },
    handleDelete(index, row) {
      console.log(index, row)
    },
    operation() {
      this.$prompt('请输入邮箱', '编辑', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
        inputErrorMessage: '邮箱格式不正确'
      }).then(({ value }) => {
        this.$message({
          type: 'success',
          message: '你的邮箱是: ' + value
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        })
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.func1 {
  &-container {
    margin: 30px;
  }
  //&-text {
  //  font-size: 30px;
  //  line-height: 46px;
  //}
}
</style>
