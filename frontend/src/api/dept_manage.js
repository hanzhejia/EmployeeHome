import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/vue-admin-template/department/list',
    method: 'get',
    params: query
  })
}

export function fetchListItem(id) {
  return request({
    url: '/vue-admin-template/department/detail',
    method: 'get',
    params: { id }
  })
}

export function createListItem(data) {
  return request({
    url: '/vue-admin-template/department/create',
    method: 'post',
    data
  })
}

export function updateListItem(data) {
  return request({
    url: '/vue-admin-template/department/update',
    method: 'post',
    data
  })
}
