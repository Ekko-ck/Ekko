import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import axios from './plugins/axios'
import popupMixin from './mixins/popup'

Vue.config.productionTip = false
Vue.mixin(popupMixin)

new Vue({
  router,
  store,
  vuetify,
  axios,
  render: h => h(App)
}).$mount('#app')
