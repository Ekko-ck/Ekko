<template>
  <v-app>
    <Popup />
    <Navbar />
    <v-main>
      <v-container fluid>
        <router-view />
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import Popup from './components/common/Popup.vue'
import Navbar from './components/common/Navbar.vue'

export default {
  name: 'App',
  components: { Popup, Navbar },
  data: () => ({
  }),
  methods: {
  }
}
</script>

<style lang="scss">
.text-center {
  text-align: center;
}
.align-items-center {
  align-items: center;
}
</style>
