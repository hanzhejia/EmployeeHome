<template>
  <div class="func1-container">
    <div class="func1-text">x-1name: {{ name }}</div>
    <div class="func1-list">
      <li v-for="(item, index) in list" :key="index" class="list-item">
        <el-card class="box-card" shadow="hover">
          <div class="style1">{{ item.info1 }}</div>
          <div class="style2">{{ item.info2 }}</div>
          <el-button style="float: right; padding: 3px 0" type="text" @click="func_post_something(item)">
            func_post_something
          </el-button>
          <el-button style="float: right; padding: 3px 0" type="text" @click="func_get_something(item)">
            func_get_something
          </el-button>
          <el-button style="float: right; padding: 3px 0" type="text" @click="func_do_something()">
            func_do_something
          </el-button>
        </el-card>
      </li>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { do_something, get_something, post_something } from '@/api/test'

export default {
  name: 'Func1',
  data() {
    return {
      count: 0,
      list: [
        { info1: '123', info2: '456' },
        { info1: '789', info2: '111' }
      ]
    }
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  methods: {
    func_post_something(data) {
      this.list = [
        { info1: '1223', info2: '456' },
        { info1: '789', info2: '111' }
      ]
      // setTimeout(() => {
      //   post_something({
      //     count: this.count
      //   }).then(response => {
      //     console.log('func_post_sth')
      //   })
      // }, 10)
    },
    func_get_something(token) {
      setTimeout(() => {
        get_something({
          count: this.count
        }).then(response => {
          console.log('func_get_sth')
        })
      }, 10)
    },
    func_do_something() {
      setTimeout(() => {
        do_something({
          count: this.count
        }).then(response => {
          console.log('func_do_sth')
        })
      }, 10)
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

.func1-list {
  margin: 0 auto;
  padding-right: 5px;
  font-size: 14px;
}
.list-item {
  list-style: none;
  display:flex;
  justify-content: center;
  margin-bottom: 18px;
}
.box-card {
  width: 100%;
  border-radius: 20px
}
</style>
