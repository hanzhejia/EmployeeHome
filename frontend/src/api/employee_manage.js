import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/vue-admin-template/employee/list',
    method: 'post',
    data: query
  })
}

export function fetchDept() {
  return request({
    url: '/vue-admin-template/department/deptlist',
    method: 'post'
  })
}

export function fetchJob() {
  return request({
    url: '/vue-admin-template/job/joblist',
    method: 'post'
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

export function searchdateListItem(data, data2) {
  return request({
    url: '/vue-admin-template/employee/search',
    method: 'get',
    params: { ...data, ...data2 }
  })
}

export function deleteListItem(data) {
  return request({
    url: '/vue-admin-template/employee/delete',
    method: 'post',
    data
  })
}
