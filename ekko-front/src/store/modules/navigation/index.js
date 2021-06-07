const NAVBAR_TYPE = {
  HIDE: 'hide',
  MAIN: 'main',
  PAGE: 'page'
}

const state = {
  navbarType: NAVBAR_TYPE.LOGIN,
  show: false
}
const getters = {
  navbarType (state) {
    return state.navbarType
  },
  showNavbar (state) {
    return state.show
  },
  showMenuButton (state) {
    return state.navbarType === NAVBAR_TYPE.MAIN
  }
}
const mutations = {
  setNavbarType (state, navbarType) {
    state.navbarType = navbarType
    state.show = navbarType !== NAVBAR_TYPE.HIDE
  }
}
const actions = {
}

export default {
  state,
  getters,
  actions,
  mutations
}
