import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store'
import NavbarType from '../store/modules/navigation/NavbarType'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    alias: ['/login'],
    name: 'Login',
    component: () => import('../views/Login.vue'),
    meta: {
      navbarType: NavbarType.HIDE
    }
  }, {
    path: '/question',
    name: 'Question',
    component: () => import('../views/question/Question.vue'),
    meta: {
      navbarType: NavbarType.MAIN
    }
  }, {
    path: '/question/detail',
    name: 'QuestionDetail',
    component: () => import('../views/question/QuestionDetail.vue'),
    props: true,
    meta: {
      navbarType: NavbarType.PAGE
    }
  }, {
    path: '/question/register',
    name: 'QuestionRegister',
    component: () => import('../views/question/QuestionRegister.vue'),
    props: true,
    meta: {
      navbarType: NavbarType.PAGE
    }
  }, {
    path: '/join',
    name: 'Join',
    component: () => import('../views/Join.vue'),
    meta: {
      navbarType: NavbarType.HIDE
    }
  }
]

if (process.env.VUE_APP_MODE === 'local') {
  routes.push({
    path: '/sample',
    name: 'Sample',
    component: () => import('../views/sample/Sample01.vue')
  })
}

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  const navbarType = (to.meta && to.meta.navbarType) ? to.meta.navbarType : NavbarType.HIDE
  setTimeout(() => {
    store.commit('navigation/setNavbarType', navbarType)
  }, 50)
  next()
})

export default router
