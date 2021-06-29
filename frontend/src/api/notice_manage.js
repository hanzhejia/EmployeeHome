import request from '@/utils/request'

export function fetchList(data) {
  console.log('sds')
  console.log('sds')
  console.log('sds')
  console.log('sds')
  console.log('sds')
  console.log('sds')
  console.log(data)
  return request({
    url: '/vue-admin-template/noti/list',
    method: 'get',
    params:{data}
  })
}

export function createListItem(data) {
  return request({
    url: '/vue-admin-template/noti/create',
    method: 'post',
    data
  })
}

export function updateListItem(data) {
  console.log(data)
  return request({
    url: '/vue-admin-template/noti/update',
    method: 'post',
    data
  })
}

export function deleteListItem(data) {
  console.log('sbssssssssssssssssssss')
  return request({
    url: `/vue-admin-template/noti/delete`,
    method: 'post',
    data
  })
}
export function fetchListItem(date) {
  return request({
    url: '/vue-admin-template/noti/detail',
    method: 'get',
    params: { id }
  })
}
export function deleteallListItem(data) {
  console.log('popop')
  return request({
    url: `/vue-admin-template/noti/deleteall`,
    method: 'post',
    data
  })
}
