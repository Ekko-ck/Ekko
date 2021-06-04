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
  popup: (state) => {
    return state.popup
  }
}
const mutations = {
  setPopup: (state, popup) => {
    state.popup = {
      title: popup.title,
      body: popup.body,
      show: popup.show,
      type: !popup.type ? 'alert' : popup.type,
      resolve: popup.resolve
    }
  }
}
const actions = {
  alert: ({ commit }, popup) => {
    popup.type = 'alert'
    popup = setDefaultPopup(popup)
    commit('setPopup', popup)
  },
  confirm: ({ commit }, popup) => {
    popup.type = 'confirm'
    popup = setDefaultPopup(popup)
    commit('setPopup', popup)
  },
  close: ({ commit, getters }) => {
    getters.popup.show = false
    commit('setPopup', getters.popup)
  }
}

const setDefaultPopup = (popup) => {
  popup.show = true
  if (!popup.title) {
    popup.title = '알림'
  }
  return popup
}

export default {
  state,
  getters,
  actions,
  mutations
}
