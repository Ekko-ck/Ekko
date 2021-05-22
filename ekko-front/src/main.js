import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import axios from './plugins/axios'

Vue.config.productionTip = false
/*
router.beforeEach(async (to, from, next) => {
  console.log('entered router.beforeEach')
  if (to.matched.some(record => record.meta.requiresAuth)) {
    console.log('is requiresAuth')
    if (!localStorage.getItem('token')) {
      next({
        path: '/login',
        query: { redirect: to.fullPath }
      })
    } else {
      next()
    }
  } else {
    console.log('is requiresAuth2')
    next()
  }
})
*/
new Vue({
  router,
  store,
  vuetify,
  axios,
  render: h => h(App)
}).$mount('#app')
