import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },

  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'el-icon-s-grid' }
    }]
  },

  {
    path: '/login',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'func2',
        name: 'Func2',
        component: () => import('@/views/login/func2/index'),
        meta: { title: 'func2', icon: 'table' },
        hidden: true
      }
    ]
  }
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [
  {
    path: '/face_manage',
    component: Layout,
    redirect: '/face_manage/func1',
    alwaysShow: true, // will always show the root menu
    name: 'Face_manage',
    meta: {
      title: '人脸管理',
      icon: 'el-icon-s-tools',
      roles: ['admin', 'editor'] // you can set roles in root nav
    },
    children: [
      {
        path: 'func1',
        name: 'Func1',
        component: () => import('@/views/face_mamage/func1/index'),
        meta: { title: '人脸增改', icon: 'el-icon-camera' }
      },
      {
        path: 'func2',
        name: 'Func2',
        component: () => import('@/views/face_mamage/func2/index'),
        meta: { title: '信息管理', icon: 'el-icon-tickets' }
      }
    ]
  },

  {
    path: '/user_manage',
    component: Layout,
    redirect: '/user_manage/func1',
    alwaysShow: true, // will always show the root menu
    name: 'User_manage',
    meta: {
      title: '用户管理',
      icon: 'el-icon-s-custom',
      roles: ['admin', 'editor'] // you can set roles in root nav
    },
    children: [
      {
        path: 'func1',
        name: 'Func1',
        component: () => import('@/views/user_manage/func1/index'),
        meta: { title: '用户查询', icon: 'el-icon-tickets' }
      },
      {
        path: 'func2',
        name: 'Func2',
        component: () => import('@/views/user_manage/func2/index'),
        meta: { title: '添加用户', icon: 'el-icon-plus' }
      }
    ]
  },

  {
    path: '/dept_manage',
    component: Layout,
    redirect: '/dept_manage/func1',
    alwaysShow: true, // will always show the root menu
    name: 'Dept_manage',
    meta: {
      title: '部门管理',
      icon: 'el-icon-s-help',
      roles: ['admin', 'editor'] // you can set roles in root nav
    },
    children: [
      {
        path: 'func1',
        name: 'Func1',
        component: () => import('@/views/dept_manage/func1/index'),
        meta: { title: '查询部门', icon: 'el-icon-tickets' }
      },
      {
        path: 'func2',
        name: 'Func2',
        component: () => import('@/views/dept_manage/func2/index'),
        meta: { title: '添加部门', icon: 'el-icon-plus' }
      }
    ]
  },

  {
    path: '/job_manage',
    component: Layout,
    redirect: '/job_manage/func1',
    alwaysShow: true, // will always show the root menu
    name: 'Job_manage',
    meta: {
      title: '职位管理',
      icon: 'el-icon-s-management',
      roles: ['admin', 'editor'] // you can set roles in root nav
    },
    children: [
      {
        path: 'func1',
        name: 'Func1',
        component: () => import('@/views/job_manage/func1/index'),
        meta: { title: '职位查询', icon: 'el-icon-tickets' }
      },
      {
        path: 'func2',
        name: 'Func2',
        component: () => import('@/views/job_manage/func2/index'),
        meta: { title: '添加职位', icon: 'el-icon-plus' }
      }
    ]
  },

  {
    path: '/employee_manage',
    component: Layout,
    redirect: '/employee_manage/func1',
    alwaysShow: true, // will always show the root menu
    name: 'Employee_manage',
    meta: {
      title: '员工管理',
      icon: 'el-icon-s-cooperation',
      roles: ['admin', 'editor'] // you can set roles in root nav
    },
    children: [
      {
        path: 'func1',
        name: 'Func1',
        component: () => import('@/views/employee_manage/func1/index'),
        meta: { title: '员工查询', icon: 'el-icon-tickets' }
      },
      {
        path: 'func2',
        name: 'Func2',
        component: () => import('@/views/employee_manage/func2/index'),
        meta: { title: '添加员工', icon: 'el-icon-plus', roles: ['admin'] }
      }
    ]
  },

  {
    path: '/notice_manage',
    component: Layout,
    redirect: '/notice_manage/func1',
    alwaysShow: true, // will always show the root menu
    name: 'Notice_manage',
    meta: {
      title: '公告管理',
      icon: 'el-icon-s-order',
      roles: ['admin', 'editor'] // you can set roles in root nav
    },
    children: [
      {
        path: 'func1',
        name: 'Func1',
        component: () => import('@/views/notice_manage/func1/index'),
        meta: { title: '公告查询', icon: 'el-icon-tickets' }
      },
      {
        path: 'func2',
        name: 'Func2',
        component: () => import('@/views/notice_manage/func2/index'),
        meta: { title: '添加公告', icon: 'el-icon-plus' }
      }
    ]
  },

  {
    path: '/download_manage',
    component: Layout,
    redirect: '/download_manage/func1',
    alwaysShow: true, // will always show the root menu
    name: 'Download_manage',
    meta: {
      title: '下载中心',
      icon: 'el-icon-s-promotion',
      roles: ['admin', 'editor'] // you can set roles in root nav
    },
    children: [
      {
        path: 'func1',
        name: 'Func1',
        component: () => import('@/views/download_manage/func1/index'),
        meta: {
          title: '文档查询',
          icon: 'el-icon-tickets'
        }
      },
      {
        path: 'func2',
        name: 'Func2',
        component: () => import('@/views/download_manage/func2/index'),
        meta: {
          title: '上传文档',
          icon: 'el-icon-plus',
          roles: ['admin']
        }
      }
    ]
  },

  {
    path: '/test',
    component: Layout,
    redirect: '/test/func1',
    name: 'Test',
    meta: { title: 'Test', icon: '' },
    children: [
      {
        path: 'func1',
        name: 'Func1',
        component: () => import('@/views/test/func1/index'),
        meta: { title: 'func1', icon: '' }
      },
      {
        path: 'func2',
        name: 'Func2',
        component: () => import('@/views/test/func2/index'),
        meta: { title: 'func2', icon: '' }
      },
      {
        path: 'func3',
        name: 'Func3',
        component: () => import('@/views/test/func3/index'),
        meta: { title: 'func3', icon: '' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
