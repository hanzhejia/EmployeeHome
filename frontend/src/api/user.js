import request from '@/utils/request'
import da from 'element-ui/src/locale/lang/da'

export function login(data) {
  console.log('call /user/login', data)
  return request({
    url: '/vue-admin-template/user/login',
    method: 'post',
    data
  })
}
export function updatePwd(data) {
  console.log('call /user/updatePwd', data)
  return request({
    url: '/vue-admin-template/user/updatePwd',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  console.log('call /user/info', token, typeof token)
  return request({
    url: '/vue-admin-template/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  console.log('call /user/logout')
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}
