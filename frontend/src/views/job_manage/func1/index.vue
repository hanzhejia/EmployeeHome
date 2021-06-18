<template>
  <div class="func1-container">
    <el-table
      :data="tableData.filter(data => !search || data.jobName.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%"
    >
      <el-table-column
        type="selection"
        width="55"
      />
      <el-table-column
        label="职位名称"
        prop="jobName"
      />
      <el-table-column
        label="详细信息"
        prop="detail"
      />
      <el-table-column align="right">
        <template slot="header">
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"
          />
        </template>
        <template slot-scope="scope">
          <el-button v-if="checkPermission(['admin'])" size="mini" type="danger" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button v-if="checkPermission(['admin'])" size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import permission from '@/directive/permission/index.js' // 权限判断指令
import checkPermission from '@/utils/permission' // 权限判断函数

export default {
  name: 'Func1',
  directives: { permission },
  data() {
    return {
      tableData: [
        {
          jobName: '职员1',
          detail: '职员1'
        },
        {
          jobName: '职员2',
          detail: '职员2'
        },
        {
          jobName: '职员3',
          detail: '职员3'
        },
        {
          jobName: '职员4',
          detail: '职员4'
        },
        {
          jobName: '职员5',
          detail: '职员5'
        }
      ],
      search: ''
    }
  },
  methods: {
    checkPermission,
    handleDownload(index, row) {
      console.log(index, row)
    },
    handleEdit(index, row) {
      console.log(index, row)
    },
    handleDelete(index, row) {
      console.log(index, row)
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
