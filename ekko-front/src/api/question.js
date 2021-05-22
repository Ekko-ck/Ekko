import http from './http.js'

export default {
  async search (query) {
    return await http.get(`/api/question/search?query=${query}`)
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
