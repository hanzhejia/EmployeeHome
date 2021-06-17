<template>
  <div class="func1-container">
    <el-table
      :data="tableData.filter(data => !search || data.title.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%"
    >
      <el-table-column
        type="selection"
        width="55"
      />
      <el-table-column
        label="标题"
        prop="title"
      />
      <el-table-column
        label="创建时间"
        prop="time"
      />
      <el-table-column
        label="创建人"
        prop="master"
      />
      <el-table-column
        label="描述"
        prop="desc"
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
          <el-button size="mini" @click="handleDownload(scope.$index, scope.row)">下载</el-button>
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
          title: '标题1',
          time: '2021-06-17',
          master: '创建人1',
          desc: '描述1'
        },
        {
          title: '标题2',
          time: '2021-06-18',
          master: '创建人2',
          desc: '描述2'
        },
        {
          title: '标题3',
          time: '2021-06-19',
          master: '创建人3',
          desc: '描述3'
        },
        {
          title: '标题4',
          time: '2021-06-20',
          master: '创建人4',
          desc: '描述4'
        },
        {
          title: '标题5',
          time: '2021-06-21',
          master: '创建人5',
          desc: '描述5'
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
