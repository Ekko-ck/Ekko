const state = {
  popup: {
    title: '',
    body: '',
    type: 'alert',
    show: false,
    handleOk: null
  }
}
const getters = {
  getPopup: (state) => {
    return state.popup
  }
}
const mutations = {
  setPopup: (state, popup) => {
    state.popup = {
      title: popup.title,
      body: popup.body,
      type: popup.type ? 'alert' : popup.type,
      handleOk: popup.handleOk
    }
  }
}
const actions = {
  openPopup: ({ commit }, popup) => {
    commit('setPopup', popup)
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
