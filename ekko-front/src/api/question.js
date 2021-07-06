import http from './http.js'

export default {
  async search (requestData) {
    return await http.get('/api/question/search', requestData)
  },
  async registerQuestion (requestData) {
    return await http.post('/api/question', requestData)
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
  async regiserCommentToQuestion (requestData) {
    return await http.post(`/api/question/${requestData.questionId}/comment`, requestData)
  },
  async removeCommentFromQuestion (requestData) {
    return await http.delete(`/api/question/${requestData.questionId}/comment/${requestData.commentId}`)
  },
  async regiserCommentToAnswer (requestData) {
    return await http.post(`/api/question/${requestData.questionId}/answer/${requestData.answerId}/comment`, requestData)
  },
  async removeCommentFromAnswer (requestData) {
    return await http.delete(`/api/question/${requestData.questionId}/answer/${requestData.answerId}/comment/${requestData.commentId}`)
  }
}
