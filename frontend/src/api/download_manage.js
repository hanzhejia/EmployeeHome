import request from '@/utils/request'

export function fetchList(data) {
  return request({
    url: '/vue-admin-template/download/list',
    method: 'post',
    data
  })
}

export function fetchListItem(data) {
  return request({
    url: '/vue-admin-template/download/listItem',
    method: 'post',
    data
  })
}

export function createListItem(data) {
  return request({
    url: '/vue-admin-template/download/create',
    method: 'post',
    data
  })
}

export function updateListItem(data) {
  return request({
    url: '/vue-admin-template/download/update',
    method: 'post',
    data
  })
}

export function deleteListItem(data) {
  return request({
    url: `/vue-admin-template/download/delete`,
    method: 'post',
    data
  })
}

export function downloadFile(data) {
  return request({
    url: `/vue-admin-template/download/download`,
    method: 'post',
    data
  })
}

export function uploadFile(data) {
  return request({
    url: `/vue-admin-template/download/upload`,
    method: 'post',
    data
  })
}
