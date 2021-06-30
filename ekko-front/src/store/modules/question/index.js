import API from '../../../api/question.js'

const state = {
  questionList: [],
  questionListResponse: [],
  page: 0
}
const getters = {
  questionList (state) {
    return state.questionList
  },
  questionListResponse (state) {
    return state.questionListResponse
  }
}
const mutations = {
  setQuestionList (state, questionList) {
    state.questionList.push(...questionList)
  },
  setPage (state, page) {
    state.page = page
  },
  setQuestionListResponse (state, questionList) {
    state.questionListResponse = questionList
  }
}
const actions = {
  async search ({ commit, state }, requestData = {}) {
    console.log(state)
    const questionList = await API.search(requestData)
    commit('setQuestionList', questionList)
    commit('setPage', requestData.page)
    commit('setQuestionListResponse', questionList)
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
