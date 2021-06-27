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
.font-size-07-rem {
  font-size: 0.7rem;
}
.font-size-08-rem {
  font-size: 0.8rem;
}
.font-size-09-rem {
  font-size: 0.9rem;
}
</style>
