<template>
  <div class="func1-container">
    <switch-roles @change="handleRolesChange" />
    <el-form ref="ruleForm" :model="searcheForm" label-width="100px" class="demo-ruleForm">
      <el-row>
        <el-col :span="4">
          <el-form-item label="职位" prop="job">
            <el-select v-model="searcheForm.job" >
              <el-option label="蒙古海军司令" value="蒙古海军司令"></el-option>
              <el-option label="梵蒂冈空军最高指挥官" value="梵蒂冈空军最高指挥官"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6" :offset="2">
            <el-form-item label="姓名" prop="name">
              <el-input
                v-model="searcheForm.name"
                size="mini"
                placeholder="输入姓名搜索"
              />
            </el-form-item>
        </el-col>
        <el-col :span="6" :offset="2">
          <el-form-item label="身份证号码" prop="job">
              <el-input
                v-model="searcheForm.card"
                size="mini"
                placeholder="输入身份证号搜索"
              />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="4">
          <el-form-item label="性别" prop="sex">
            <el-select v-model="searcheForm.sex" >
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6" :offset="2">
          <el-form-item label="手机号" prop="phone">
            <el-input
              v-model="searcheForm.phone"
              size="mini"
              placeholder="输入手机号搜索"
            />
          </el-form-item>
        </el-col>
        <el-col :span="6" :offset="2">
          <el-form-item label="所属部门" prop="dept">
            <el-select v-model="searcheForm.dept" >
              <el-option label="人事部" value="人事部"></el-option>
              <el-option label="采购部" value="采购部"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-form-item>
          <el-button type="primary" @click="search()">搜索</el-button>
          <el-button @click="resetForm('searcheForm')">取消</el-button>
        </el-form-item>
      </el-row>
    </el-form>

    <el-table
      :data="list"
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
        <template slot-scope="scope">
          <el-button v-if="checkPermission(['admin'])" size="mini" type="danger" @click="operation(scope.$index, scope.row)">编辑</el-button>
        </template>
      </el-table-column>

    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <el-dialog title="编辑" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-input v-model="temp.sex" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="temp.phone" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="temp.email" />
        </el-form-item>
        <el-form-item label="职位" prop="job">
          <el-input v-model="temp.job" />
        </el-form-item>
        <el-form-item label="学历" prop="education">
          <el-input v-model="temp.education" />
        </el-form-item>
        <el-form-item label="身份证号码" prop="card">
          <el-input v-model="temp.card" />
        </el-form-item>
        <el-form-item label="部门" prop="dept">
          <el-input v-model="temp.dept" />
        </el-form-item>
        <el-form-item label="联系地址" prop="address">
          <el-input v-model="temp.address" />
        </el-form-item>
        <el-form-item label="建档日期" prop="createdate">
          <el-input v-model="temp.createdate" :disabled="true" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="handleUpdate()">提交</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
// import { mapGetters } from 'vuex'
import checkPermission from '@/utils/permission'
import { fetchList, searchdateListItem, updateListItem } from '@/api/employee_manage'
import Pagination from '@/components/Pagination'
import permission from '@/directive/permission' // secondary package based on el-pagination

export default {
  name: 'Func1',
  // computed: {
  //   ...mapGetters([
  //     'name'
  //   ])
  // },
  components: { Pagination },
  directives: { permission },
  data() {
    return {

      temp: {
        name: '',
        sex: '',
        phone: '',
        email: '',
        job: '',
        education: '',
        card: '',
        dept: '',
        address: '',
        createdate: ''
      },

      dialogFormVisible: false,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10
      },
      searcheForm: {
        job: '',
        name: '',
        card: '',
        sex: '',
        phone: '',
        dept: ''
      }
    }
  },
  created() {
    this.getList()
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
    getList() {
      this.listLoading = true
      console.log(this.listQuery.page)
      fetchList(this.listQuery).then(response => {
        this.list = response.data.items
        this.total = response.data.total
        console.log(response.data.total)
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 100)
      })
    },
    operation(index, row) {
      console.log(index, row)
      this.temp = Object.assign({}, row)
      this.temp.time = new Date(this.temp.time)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleUpdate() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          updateListItem(tempData).then(() => {
            const index = this.list.findIndex(v => v.id === this.temp.id)
            this.list.splice(index, 1, this.temp)
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Update Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },

    search() {
      const tempData = Object.assign({}, this.searcheForm)
      searchdateListItem(tempData).then(response => {
        this.list = response.data.items
        console.log(this.list)
        this.total = response.data.total
        setTimeout(() => {
          this.listLoading = false
        }, 100)
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
