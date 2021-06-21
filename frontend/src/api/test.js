import request from '@/utils/request'

export function fetchList(data) {
  return request({
    url: '/vue-admin-template/test/list',
    method: 'post',
    data
  })
}

export function fetchListItem(data) {
  return request({
    url: '/vue-admin-template/test/listItem',
    method: 'post',
    data
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
