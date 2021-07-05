import http from './http.js'

export default {
  async search (requestData) {
    return await http.get('/api/question/search', requestData)
  },
  async registerQuestion () {
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
  }
}
