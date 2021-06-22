import request from '@/utils/request'

export function createLoginItem(data) {
  console.log('createLoginItem')
  return request({
    url: '/vue-admin-template/login',
    method: 'post',
    data
  })
}
