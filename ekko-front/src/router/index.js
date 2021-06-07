import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    alias: ['/login'],
    name: 'Login',
    component: () => import('../views/Login.vue'),
    meta: {
      navbarType: 'hide'
    }
  }, {
    path: '/question',
    name: 'Question',
    component: () => import('../views/question/Question.vue'),
    meta: {
      navbarType: 'main'
    }
  }, {
    path: '/question/details',
    name: 'QuestionDetails',
    component: () => import('../views/question/QuestionDetails.vue'),
    props: true,
    meta: {
      navbarType: 'page'
    }
  }, {
    path: '/join',
    name: 'Join',
    component: () => import('../views/Join.vue'),
    meta: {
      navbarType: 'hide'
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
  const navbarType = (to.meta && to.meta.navbarType) ? to.meta.navbarType : 'login'
  setTimeout(() => {
    store.commit('navigation/setNavbarType', navbarType)
  }, 50)
  next()
})

export default router
