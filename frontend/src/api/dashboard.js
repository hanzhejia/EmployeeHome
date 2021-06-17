import request from '@/utils/request'

export function post_something(data) {
  return request({
    url: '/vue-admin-template/dashboard/post_something',
    method: 'post',
    data
  })
}

export function get_something(token) {
  return request({
    url: '/vue-admin-template/dashboard/get_something',
    method: 'get',
    params: { token }
  })
}

export function do_something() {
  return request({
    url: '/vue-admin-template/dashboard/do_something',
    method: 'post'
  })
}
