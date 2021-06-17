
<template>
  <div style="width: 1000px">
    <el-row >
      <p style="top: 10px ;font-size: 18px ;color:#303133;position: absolute">公告标题</p>
      <el-input v-model="input1" size="small"
                :autosize="{ minRows: 1, maxRows: 1}"
                placeholder="请输入内容" style="left: 100px; top: 20px;position: absolute;
            width: 200px;
"></el-input>
      <p style="top: 10px ;font-size: 18px ;color:#303133;position: absolute;left: 320px">公告内容</p>
      <el-input v-model="input2" size="small"
                :autosize="{ minRows: 1, maxRows: 1}"
                placeholder="请输入内容" style="left: 400px; top: 20px;position: relative;width: 400px;
"></el-input>
    </el-row>
    <el-row style="position: relative;left: 850px;top: -13px">
      <el-button size="small" type="primary" @click="search('sb')">搜索</el-button>
      <el-button size="small" type="success" >删除</el-button>
    </el-row>
    <div>
      <el-table
        :data="tableData"
        border
        style="top: 0px">
        <el-table-column
          fixed
          prop="date"
          label="日期"
          width="150">
        </el-table-column>
        <el-table-column
          prop="name"
          label="公告名称"
          align="center"
          width="200">
        </el-table-column>
        <el-table-column
          prop="province"
          label="公告内容"
          align="center"
          width="200">
        </el-table-column>
        <el-table-column
          prop="city"
          label="创建时间"
          align="center"
          width="200">
        </el-table-column>
        <el-table-column
          prop="address"
          label="公告人"
          align="center"
          width="150">
        </el-table-column>
        <el-table-column
          align="center"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
            <el-button type="text" size="small">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        background
        layout="prev, pager, next"
        :total="100">
      </el-pagination>
    </div>
    <Nav>
    </Nav>
  </div>
</template>

<script>

import axios from "axios";
import Nav from './sb.vue'

export default {
  components: {
    Nav
  },
  methods: {
    handleClick(row) {
      console.log(row.date)
      this.$alert(row.province, row.name, {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'info',
            message: `action: ${ action }`
          });
        }
      });
    },
    search(sb){
      console.log(sb);
      axios.get('',{
        params:{
        }
      })
        .then(function (a){
          console.log("d");
        })
        .catch(function (a){
          console.log("dsd");
        })
    }
  },
  data() {
    return {
      input1: '',
      input2: '',
      tableData: [{
        date: '2016-05-02',
        name: '吃饭',
        province: '内容1',
        city: '2016-05-02',
        address: '小花',
        zip: 200333
      }, {
        date: '2016-05-04',
        name: '会议2',
        province: '内容2',
        city: '2016-05-02',
        address: '姐',
        zip: 200333
      }, {
        date: '2016-05-01',
        name: '会议3',
        province: '内容3',
        city: '2016-05-02',
        address: '龙',
        zip: 200333
      }, {
        date: '2016-05-03',
        name: '会议4',
        province: '内容4',
        city: '2016-05-02',
        address: '兔子',
        zip: 200333
      }]
    }
  }
}
</script>

