import request from '@/utils/request'

export function fetchList(data) {
  return request({
    url: '/vue-admin-template/user/list',
    method: 'post',
    data
  })
}

export function createListItem(data) {
  console.log(data)
  return request({
    url: '/vue-admin-template/user/create',
    method: 'post',
    data
  })
}

export function updateListItem(data) {
  return request({
    url: '/vue-admin-template/user/update',
    method: 'post',
    data
  })
}
export function fetchListItem(data) {
  return request({
    url: '/vue-admin-template/user/searchdate',
    method: 'post',
    params: data
  })
}
export function deleteListItem(data) {
  return request({
    url: `/vue-admin-template/user/delete`,
    method: 'post',
    data
  })
}
/*export function deleteList(data) {
  return request({
    url: '/vue-admin-template/user/delete',
    method: 'post',
    data
  })
}*/
export function checkSameName(data) {
  return request({
    url: '/vue-admin-template/user/check',
    method: 'post',
    data
  })
}
