import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: () => import('../views/Login.vue')
  }, {
    path: '/question',
    name: 'Question',
    component: () => import('../views/Question.vue')
  }, {
    path: '/join',
    name: 'Join',
    component: () => import('../views/Join.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
