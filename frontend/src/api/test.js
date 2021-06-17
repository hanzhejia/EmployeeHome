import request from '@/utils/request'

export function post_something(data) {
  return request({
    url: '/vue-admin-template/test/post_something',
    method: 'post',
    data
  })
}

export function get_something(token) {
  return request({
    url: '/vue-admin-template/test/get_something',
    method: 'get',
    params: { token }
  })
}

export function do_something() {
  return request({
    url: '/vue-admin-template/test/do_something',
    method: 'post'
  })
}
