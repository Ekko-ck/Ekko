import Vue from 'vue'
import Vuex from 'vuex'

// modules
import popup from './modules/popup'
import auth from './modules/auth'
import question from './modules/question'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    popup: {
      namespaced: true,
      ...popup
    },
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
