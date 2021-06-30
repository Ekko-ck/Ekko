import API from '../../../api/question.js'

const state = {
  questionList: [],
  page: 0
}
const getters = {
  questionList (state) {
    return state.questionList
  }
}
const mutations = {
  setQuestionList (state, questionList) {
    state.questionList = questionList
  },
  setPage (state, page) {
    state.page = page
  }
}
const actions = {
  async search ({ commit, state }, requestData = {}) {
    console.log(state)
    const questionList = await API.search(requestData)
    commit('setQuestionList', questionList)
    commit('setPage', requestData.page)
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
