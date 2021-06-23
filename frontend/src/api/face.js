import request from '@/utils/request'

export function faceList() {
  return request({
    url: '/vue-admin-template/face/list',
    method: 'get'
  })
}

export function faceLogin(data) {
  console.log('faceLogin')
  return request({
    url: '/vue-admin-template/face/faceLogin',
    method: 'post',
    data: data
  })
}

export function addFace(data) {
  console.log('testadd')
  return request({
    url: '/vue-admin-template/face/add',
    method: 'post',
    data: data
  })
}
export function faceDel(data) {
  console.log('testadd')
  return request({
    url: '/vue-admin-template/face/del',
    method: 'post',
    data: data
  })
}
