import request from '@/utils/request'

export function fetchList(query) {
  console.log('sbssssssssssssss')
  return request({
    url: '/vue-admin-template/test/list',
    method: 'get',
    params: query
  })
}

export function fetchListItem(id) {
  return request({
    url: '/vue-admin-template/test/detail',
    method: 'get',
    params: { id }
  })
}

export function createListItem(data) {
  return request({
    url: '/vue-admin-template/test/create',
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

export function deleteListItem(data) {
  return request({
    url: `/vue-admin-template/test/delete`,
    method: 'post',
    data
  })
}
