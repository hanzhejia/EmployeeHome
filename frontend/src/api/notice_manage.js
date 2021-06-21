import request from '@/utils/request'

export function fetchList(query) {
  console.log('sb')
  return request({
    url: '/vue-admin-template/noti/list',
    method: 'get',
    params: query
  })
}

export function fetchListItem(id) {
  return request({
    url: '/vue-admin-template/noti/detail',
    method: 'get',
    params: { id }
  })
}

export function createListItem(data) {
  console.log('sbssssssssssssssssssss')
  return request({
    url: '/vue-admin-template/noti/create',
    method: 'post',
    data
  })
}
export function updateListItem(data) {
  return request({
    url: '/vue-admin-template/test/update',
    method: 'post',
    data
  })
}
