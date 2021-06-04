import API from '../../../api/login'
import router from '../../../router'
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
  getUser: state => {
    return state.user
  },
  getToken: state => {
    return state.token
  },
  getRefreshToken: state => {
    return state.refreshToken
  },
  isValidateJwtToken: state => {
    return state.isValidateJwtToken
  },
  getJwt: state => {
    return state.token
  }
}

// actions
const actions = {
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

    router.push('/question')
  },
  logoutUser () {
    state.user = null
    localStorage.removeItem('user')

    state.token = null
    localStorage.removeItem('token')

    state.refreshToken = null
    localStorage.removeItem('refreshToken')

    router.push('/login')
  },
  signupUser () {
  },
  signupUserSuccess (state, response) {
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

    router.push('/question')
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
