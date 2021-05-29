<template>
  <v-app>
    <Popup />
    <router-view />
  </v-app>
</template>

<script>
import Popup from './components/common/Popup.vue'

export default {
  name: 'App',
  components: { Popup },
  data: () => ({
  }),
  methods: {
  }
}
</script>
