<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--    用户资料    -->
      <el-col :xs="24" :sm="24" :md="8" :lg="6" :xl="5" style="margin-bottom: 10px">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>个人信息</span>
          </div>
          <div>
            <div style="text-align: center">
              <img :src="avatar" class="avatar" alt="">
            </div>
            <ul class="user-info">
              <li> 用户昵称 <div class="user-right">{{ name }}</div></li>
              <li> 用户设备 <div class="user-right">{{ device }}</div></li>
              <li> 用户介绍 <div class="user-right"> {{ introduction }}</div></li>
              <li> 用户权限 <div class="user-right">{{ roles }}</div></li>
            </ul>
          </div>
        </el-card>
      </el-col>
      <!--    公告    -->
      <el-col :xs="24" :sm="24" :md="16" :lg="18" :xl="19">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>公告</span>
          </div>
          <div>
            <el-table v-loading="listLoading" :data="list" style="width: 100%;">
              <el-table-column prop="tiitle" label="标题" />
              <el-table-column prop="content" label="内容" />
              <el-table-column label="创建时间">
                <template slot-scope="{row}">
                  <el-tag>{{ row.careTime | formatDate }}</el-tag>
                </template>
              </el-table-column>
              <el-table-column label="Actions" align="center" width="230" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                  <el-button :type="scope.row.status" icon="el-icon-finished" circle @click="scope.row.status==='success'?scope.row.status='danger':scope.row.status='success'" />
                </template>
              </el-table-column>
            </el-table>
            <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import permission from '@/directive/permission/index.js' // 权限判断指令
import checkPermission from '@/utils/permission' // 权限判断函数
import { fetchList } from '@/api/notice_manage'
import Pagination from '@/components/Pagination'

export default {
  name: 'Dashboard',
  components: { Pagination },
  directives: { permission },
  filters: {
    formatDate: function(value) {
      const date = new Date(value)
      const y = date.getFullYear()
      let MM = date.getMonth() + 1
      MM = MM < 10 ? '0' + MM : MM
      let d = date.getDate()
      d = d < 10 ? '0' + d : d
      let h = date.getHours()
      h = h < 10 ? '0' + h : h
      let m = date.getMinutes()
      m = m < 10 ? '0' + m : m
      let s = date.getSeconds()
      s = s < 10 ? '0' + s : s
      // eslint-disable-next-line no-unused-vars
      const time1 = y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s
      const time2 = y + '-' + MM + '-' + d
      return time2
    }
  },
  data() {
    return {
      listLoading: true,
      list: null,
      total: 0,
      listQuery: {
        page: 1,
        limit: 10
      }
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'device',
      'avatar',
      'introduction',
      'roles'
    ])
  },
  created() {
    this.getList()
  },
  methods: {
    checkPermission,
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.listLoading = false
        response.data.items.forEach(item => {
          item.status = 'success'
        })
        this.list = response.data.items
        this.total = response.data.total
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.avatar {
  width: 120px;
  height: 120px;
  border-radius: 50%;
}
.user-info {
  padding-left: 0;
  list-style: none;
  li{
    border-bottom: 1px solid #F0F3F4;
    padding: 11px 0;
    font-size: 13px;
  }
  .user-right {
    float: right;
    a{
      color: #317EF3;
    }
  }
}
</style>
