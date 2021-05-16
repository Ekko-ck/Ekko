import http from './http.js'

export default {
  async login (data) {
    return await http.post('/api/user/auth/signin', data)
  },
  async join () {
  }
}
