import API from '../../../api/question.js'

const state = { // 여러 컴포넌트에 공유되는 데이터 ( 상태 )
  questionList: []
}
const getters = { // 연산된 state 값을 접근하는 속성 ( computed )
  questionList (state) {
    return state.questionList
  }
}
const mutations = { // 이벤트 로직 & 메서드 - state의 값을 변경할 수 있는 방법이자 메서드
  setQuestionList (state, questionList) {
    state.questionList = questionList
  }
}
const actions = { // 비동기 로직
  async search ({ commit }, requestData = {}) {
    const questionList = await API.search(requestData)
    commit('setQuestionList', questionList) // api 호출
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
