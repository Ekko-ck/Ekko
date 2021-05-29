import API from '../../../api/question.js'

const state = {
  questionList: []
}
const getters = {
  questionList (state) {
    return state.questionList
  }
}
const mutations = {
  setQuestionList (state, questionList) {
    state.questionList = questionList
  }
}
const actions = {
  async search ({ commit }, requestData) {
    const questionList = await API.search(requestData)
    commit('setQuestionList', questionList)
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
