import http from './http.js'

export default {
  async search (query) {
    const res = await http.get(`/api/question/search?query=${query}`)
    return res.data.data
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
