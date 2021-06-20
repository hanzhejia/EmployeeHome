import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/vue-admin-template/employee/list',
    method: 'get',
    params: query
  })
}

export function fetchListItem(id) {
  return request({
    url: '/vue-admin-template/employee/detail',
    method: 'get',
    params: { id }
  })
}

export function fetchPv(pv) {

  return request({
    url: '/vue-admin-template/employee/pv',
    method: 'get',
    params: { pv }
  })
}

export function createListItem(data) {
  return request({
    url: '/vue-admin-template/employee/create',
    method: 'post',
    data
  })
}

export function updateListItem(data) {
  return request({
    url: '/vue-admin-template/employee/update',
    method: 'post',
    data
  })
}

export function searchdateListItem(data) {
  return request({
    url: '/vue-admin-template/employee/searchdate',
    method: 'post',
    params: data
  })
}
