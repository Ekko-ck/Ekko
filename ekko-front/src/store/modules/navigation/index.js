import NavbarType from './NavbarType'

const state = {
  navbarType: NavbarType.HIDE,
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
    return state.navbarType === NavbarType.MAIN
  }
}
const mutations = {
  setNavbarType (state, navbarType) {
    state.navbarType = navbarType
    state.show = navbarType !== NavbarType.HIDE
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
