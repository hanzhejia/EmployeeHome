import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/vue-admin-template/user/login',
    method: 'post',
    data
  })
}
export function updatePwd(data) {
  console.log('updata', data)
  return request({
    url: '/vue-admin-template/user/updatePwd',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  console.log('api/user/getInfo', token)
  return request({
    url: '/vue-admin-template/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}
