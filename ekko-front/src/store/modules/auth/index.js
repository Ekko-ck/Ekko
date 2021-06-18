import API from '../../../api/login'
import User from '../../../models/User'

/**
 * Auth Module
 */
const state = {
  user: localStorage.getItem('user'),
  token: localStorage.getItem('token'),
  refreshToken: localStorage.getItem('refreshToken'),
  isValidateJwtToken: false
}

// getters
const getters = {
  user: state => {
    return state.user
  },
  token: state => {
    return state.token
  },
  refreshToken: state => {
    return state.refreshToken
  },
  isValidateJwtToken: state => {
    return state.isValidateJwtToken
  },
  jwt: state => {
    return state.token
  }
}

// actions
const actions = {
  async signinUser ({ commit }, requestData) {
    const response = await API.signin(requestData.userId, requestData.password)
    if (response != null) {
      commit('signinUserSuccess', response)
    }
    return response
  },
  async signupUser ({ commit }, requestData) {
    return await API.signup(requestData.userId, requestData.password, requestData.userEmailAddr)
  },
  logoutUser ({ commit }) {
    commit('logoutUser')
  },
  async refreshToken ({ commit }, requestData) {
    const response = await API.refreshToken()
    if (response != null) {
      commit('refreshTokenSuccess', response)
    }
    return response
  }
}
// mutations
const mutations = {
  signinUser () {
  },
  signinUserSuccess (state, response) {
    const data = response
    const loginUser = new User(data.id, data.email, '', data.userId, data.userNm)

    state.user = JSON.stringify(loginUser)
    localStorage.setItem('user', JSON.stringify(loginUser))

    state.token = data.token
    localStorage.setItem('token', data.token)

    state.refreshToken = data.refreshToken
    localStorage.setItem('refreshToken', data.refreshToken)

    state.type = data.type
    localStorage.setItem('type', data.type)
  },
  logoutUser () {
    state.user = null
    localStorage.removeItem('user')

    state.token = null
    localStorage.removeItem('token')

    state.refreshToken = null
    localStorage.removeItem('refreshToken')
  },
  signupUser () {
  },
  signupUserSuccess (state, response) {
  },
  signUpUserFailure (state, error) {
  },
  refreshTokenSuccess (state, response) {
    const data = response

    state.token = data.token
    localStorage.setItem('token', data.token)

    state.refreshToken = data.refreshToken
    localStorage.setItem('refreshToken', data.refreshToken)
  },
  validateJwtToken (state, response) {
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
