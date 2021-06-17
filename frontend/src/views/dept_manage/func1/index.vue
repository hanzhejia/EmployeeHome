<template>
  <div class="table">
    <!--  <el-table-->
    <!--    :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"-->
    <!--    style="width: 100%">-->
    <el-table :data="tableData" style="width: 100%">
      <el-table-column
        label="部门名称"
        prop="dept_name"/>
      <el-table-column
        label="详细信息"
        prop="detail"/>
      <el-table-column align="right">
        <template slot="header">
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"/>
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">修改
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [{
        dept_name: '技术部',
        detail: '技术部'
      }, {
        dept_name: '财务部',
        detail: '财务部'
      }, {
        dept_name: '运营部',
        detail: '运营部'
      }, {
        dept_name: '人事部',
        detail: '人事部'
      }],
      search: ''
    }
  },
  methods: {
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

  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
