import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/vue-admin-template/userm/list',
    method: 'get',
    params: query
  })
}

export function fetchListItem(id) {
  return request({
    url: '/vue-admin-template/userm/detail',
    method: 'get',
    params: { id }
  })
}

export function fetchPv(pv) {
  return request({
    url: '/vue-admin-template/userm/pv',
    method: 'get',
    params: { pv }
  })
}

export function createListItem(data) {
  return request({
    url: '/vue-admin-template/userm/create',
    method: 'post',
    data
  })
}

export function updateListItem(data) {
  return request({
    url: '/vue-admin-template/userm/update',
    method: 'post',
    data
  })
}
