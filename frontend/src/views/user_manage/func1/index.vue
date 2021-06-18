<template>
  <div class="func1-container">
    <div>
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="用户名：">
          <el-input v-model="formInline.user" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="用户状态：">
          <el-select v-model="formInline.region" placeholder="全部">
            <el-option label="全部" value="全部" />
            <el-option label="管理员" value="1" />
            <el-option label="普通用户" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="finds('s')">搜索</el-button>
          <el-button type="primary" @click="back('d')">删除</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column
          type="selection"
          width="55"
        />
        <el-table-column
          label="登录名"
          prop="Loginname"
        />
        <el-table-column
          label="密码"
          prop="Password"
        />
        <el-table-column
          label="用户名"
          prop="Username"
        />
        <el-table-column
          label="状态"
          prop="Status"
        />
        <el-table-column
          label="创建时间"
          prop="Createdate"
        />
        <el-table-column label="操作">
          <template slot-scope="scope">
            <!--跳转至用户修改页面-->
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div>
      <!--分页-->
      <el-pagination
        background
        layout="prev, pager, next"
        :total="100"
      />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formInline: {
        user: '用户名',
        region: '全部'
      },
      /* 数据举例*/
      tableData: [
        {
          Loginname: 'testname1',
          Password: 'testpassword1',
          Username: 'testusername1',
          Status: 'testStatus1',
          Createdate: 'testdate1'
        },
        {
          Loginname: 'testname2',
          Password: 'testpassword2',
          Username: 'testusername2',
          Status: 'testStatus2',
          Createdate: 'testdate2'
        },
        {
          Loginname: 'testname3',
          Password: 'testpassword3',
          Username: 'testusername3',
          Status: 'testStatus3',
          Createdate: 'testdate3'
        },
        {
          Loginname: 'testname4',
          Password: 'testpassword4',
          Username: 'testusername4',
          Status: 'testStatus4',
          Createdate: 'testdate4'
        }
      ],
      multipleSelection: []
    }
  },
  methods: {
    finds(s) {
      console.log('search!')
    },
    back(d) {
      console.log('delete!')
    },
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row)
        })
      } else {
        this.$refs.multipleTable.clearSelection()
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
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

