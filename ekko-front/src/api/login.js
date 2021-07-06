import http from './http.js'

export default {
  async signin (userId, password) {
    return http.post('/api/user/auth/signin', { userId, password }, true)
  },
  async signup (userId, password, userEmailAddr) {
    return http.post('/api/user/auth/signup', { userId, password, userEmailAddr }, false)
  },
  async refreshToken () {
    return http.get('/api/user/auth/refreshtoken', {}, true)
  }
}
