import API from '../../../api/login.js'

const state = {
  jwt: ''
}
const getters = {
  jwt (state) {
    return state.jwt
  }
}
const mutations = {
  setJwt (state, jwt) {
    state.jwt = jwt
  }
}
const actions = {
  async login ({ commit }, requestData) {
    const responseData = await API.login(requestData)
    commit('setJwt', responseData.token)
    return true
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
