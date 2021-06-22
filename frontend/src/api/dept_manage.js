import request from '@/utils/request'

export function fetchList(data) {
  return request({
    url: '/vue-admin-template/department/list',
    method: 'post',
    data
  })
}

export function fetchListItem(data) {
  return request({
    url: '/vue-admin-template/department/listItem',
    method: 'post',
    data
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

export function deleteListItem(data) {
  return request({
    url: `/vue-admin-template/department/delete`,
    method: 'post',
    data
  })
}
