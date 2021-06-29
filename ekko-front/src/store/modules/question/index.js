import API from '../../../api/question.js'

const state = { // 여러 컴포넌트에 공유되는 데이터 ( 상태 )
  questionList: [],
  questionListResponse: [],
  page: 0
}
const getters = { // 연산된 state 값을 접근하는 속성 ( computed )
  questionList (state) {
    return state.questionList
  },
  questionListResponse (state) {
    return state.questionListResponse
  }
}
const mutations = { // 이벤트 로직 & 메서드 - state의 값을 변경할 수 있는 방법이자 메서드
  setQuestionList (state, questionList) { // 2. 배열 뒤로 추가할 수 있게 만들어주기.
    state.questionList.push(...questionList)
  },
  setPage (state, page) {
    state.page = page
  },
  setQuestionListResponse (state, questionList) {
    state.questionListResponse = questionList
  }
}
const actions = { // 비동기 로직
  async search ({ commit, state }, requestData = {}) {
    console.log(state)
    const questionList = await API.search(requestData)
    commit('setQuestionList', questionList) // api 호출
    commit('setPage', requestData.page)
    commit('setQuestionListResponse', questionList)
    // 1. 현재 페이지와 다음 페이지가 다를 때 ++, 페이지가 첫페이지일 때 setPage 호출
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
