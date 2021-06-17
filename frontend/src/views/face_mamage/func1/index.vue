<template>
  <el-table
    :data="tableData"
    stripe
    style="width: 100%">
    <el-table-column
      prop="date"
      label="日期"
      width="180">
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="100">
      <template >
        <el-button type="text" size="small">编辑</el-button>
      </template>
    </el-table-column>
    <el-table-column
      prop="name"
      label="姓名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="address"
      label="face_massage">
    </el-table-column>
  </el-table>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'Func1',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  data() {
    return {
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '001'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '001'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '11'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '11'
      }]
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
