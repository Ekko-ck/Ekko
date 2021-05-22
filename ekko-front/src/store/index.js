import Vue from 'vue'
import Vuex from 'vuex'

// modules
import common from './modules/common'
import auth from './modules/auth'
import question from './modules/question'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    common,
    auth: {
      namespaced: true,
      ...auth
    },
    question: {
      namespaced: true,
      ...question
    }
  }
})
