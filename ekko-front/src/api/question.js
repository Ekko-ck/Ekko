import http from './http.js'

export default {
  async search (data) {
    return await http.post('/api/user/auth/signin', data)
  },
  async regiserQuestion () {
  },
  async modifyQuestion () {
  },
  async removeQuestion () {
  },
  async regiserAnswer () {
  },
  async modifyAnswer () {
  },
  async removeAnswer () {
  },
  async regiserCommentToQuestion () {
  },
  async removeCommentFromQuestion () {
  },
  async regiserCommentToAnswer () {
  },
  async removeCommentFromAnswer () {
  },
}
