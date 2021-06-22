import request from '@/utils/request'

export function faceList() {
  return request({
    url: '/vue-admin-template/face/list',
    method: 'get'
  })
}

export function faceResult(data) {
  console.log('testfind')
  return request({
    url: '/vue-admin-template/face/find',
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
