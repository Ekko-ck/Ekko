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
    commit('signinUserSuccess', response)
    return response
  },
  async signupUser ({ commit }, requestData) {
    return await API.signup(requestData.userId, requestData.password, requestData.userEmailAddr)
  }
  /*
  signinUser (context, payload) {
    console.log('action signinUser')
    const user = payload
    context.commit('signinUser')
    API.signin(user.userId, user.password)
      .then(response => {
        setTimeout(() => {
          context.commit('signinUserSuccess', response)
        }, 500)
      })
      .catch(error => {
        console.log('error', error)
      })
  },
  logoutUser (context) {
    context.commit('logoutUser')
  },
  refreshToken (context, payload) {
  },
  signupUser (context, playload) {
    const user = playload
    context.commit('signupUser')
    API.signup(user.userId, user.password, user.userEmailAddr).then((response) => {
      setTimeout(() => {
        context.commit('signupUserSuccess', response)
      }, 500)
    }).catch(error => {
      console.log('error', error)
    })
  },
  validateJwtToken (context, payload) {
  }
  */
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
  refreshToken (state, response) {
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
