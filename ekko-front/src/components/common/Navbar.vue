<template>
  <div v-if="showNavbar">
    <v-app-bar
      color="green"
      dark
      flat
      app
    >
      <v-btn
        v-if="showMenuButton"
        icon
        @click="drawer = !drawer"
      >
        <v-icon>mdi-menu</v-icon>
      </v-btn>
      <v-btn
        v-else
        icon
        @click="handleClickBack"
      >
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <v-toolbar-title>Ekko</v-toolbar-title>
    </v-app-bar>
    <v-navigation-drawer
      v-model="drawer"
      absolute
      temporary
    >
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="text-h6">
            Menu
          </v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list
        nav
        dense
      >
        <v-list-item-group
          v-model="group"
          active-class="grey lighten-4"
        >
          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-account-circle</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              회원정보변경
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-logout</v-icon>
            </v-list-item-icon>
            <v-list-item-content @click="handleClickLogout">
              Logout
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'Navigation',
  data: () => ({
    drawer: false,
    group: null
  }),
  computed: {
    ...mapGetters('navigation', ['showNavbar', 'showMenuButton'])
  },
  methods: {
    ...mapActions('auth', ['logoutUser']),
    handleClickBack () {
      this.$router.back()
    },
    handleClickLogout () {
      this.logoutUser()
      this.drawer = false
      this.$router.push('/login')
    }
  }
}
</script>
<style lang="scss" scoped>
$btn-hover-opacity: 0;
</style>
