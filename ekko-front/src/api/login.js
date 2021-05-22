import http from './http.js'

export default {
  async signin (userId, password) {
    return http.post('/api/user/auth/signin', { userId, password })
  },
  async join () {
  }
}
