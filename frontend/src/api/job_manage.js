import request from '@/utils/request'

export function fetchList(data) {
  console.log('hah')
  console.log(data)
  return request({
    url: '/vue-admin-template/job/list',
    method: 'get',
    params: {data}
  })
}

export function fetchListItem(id) {
  return request({
    url: '/vue-admin-template/job/detail',
    method: 'get',
    params: { id }
  })
}

export function fetchPv(pv) {
  return request({
    url: '/vue-admin-template/job/pv',
    method: 'get',
    params: { pv }
  })
}

export function createListItem(data) {
  console.log(data)
  return request({
    url: '/vue-admin-template/job/create',
    method: 'get',
    params: data
  })
}

export function updateListItem(data) {
  console.log(data)
  return request({
    url: '/vue-admin-template/job/update',
    method: 'post',
    data
  })
}
export function deleteListItem(data) {
  return request({
    url: `/vue-admin-template/job/delete`,
    method: 'post',
    data
  })
}
