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
    component: () => import('../views/question/Question.vue'),
    children: []
  }, {
    path: '/question/details',
    name: 'QuestionDetails',
    component: () => import('../views/question/QuestionDetails.vue'),
    props: true
  }, {
    path: '/join',
    name: 'Join',
    component: () => import('../views/Join.vue')
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

export default router
