import http from './http.js'

export default {
  async search (params) {
    return await http.get(`/api/question/search?query=${params.query}`)
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
  }
}
