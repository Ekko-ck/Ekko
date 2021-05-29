<template>
  <div>
    <v-app-bar
      dark
      color="pink"
    >
      <v-toolbar-title>Sample</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-app-bar>
    <v-main>
      <v-container fluid>
        <!-- icon -->
        <v-card
          elevation="2"
          color="blue-grey lighten-5"
          class="mb-3"
        >
          <v-card-title>icon</v-card-title>
          <v-card-text>
            <v-row>
              <v-col cols="3">
                <v-icon>mdi-source-branch</v-icon>
              </v-col>
              <v-col cols="3">
                <v-icon>mdi-leaf</v-icon>
              </v-col>
              <v-col cols="3">
                <v-icon>mdi-google-controller</v-icon>
              </v-col>
              <v-col cols="3">
                <v-icon>mdi-account-circle</v-icon>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12">
                <a href="https://vuetifyjs.com/en/components/icons" target="_blank">https://vuetifyjs.com/en/components/icons/</a><br/>
                <a href="https://materialdesignicons.com/" target="_blank">https://materialdesignicons.com/</a>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
        <!-- API 호출 -->
        <v-card
          elevation="2"
          color="blue-grey lighten-5"
          class="mb-3"
        >
          <v-card-title>API 호출</v-card-title>
          <v-card-text>
            <v-row>
              <v-col cols="6">
                <v-btn color="green" dark @click="handleApiCall1">Login</v-btn>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12">
                {{ jwt }}
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="6">
                <v-btn color="green" dark @click="handleApiCall2">Request API</v-btn>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12">
                <div v-for="(question, index) in questionList" :key="question.id">
                  {{ index + 1 }}. {{ question.title }}
                </div>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
        <!-- popup -->
        <v-card
          elevation="2"
          color="blue-grey lighten-5"
          class="mb-3"
        >
          <v-card-title>Popup</v-card-title>
          <v-card-text>
            <v-row>
              <v-col cols="6">
                <v-btn color="purple" block dark @click="handlePopup1">Alert Popup</v-btn>
              </v-col>
              <v-col cols="6">
                <v-btn color="purple" block dark @click="handlePopup2">Confirm Popup</v-btn>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
        <!-- button - loading -->
        <v-card
          elevation="2"
          color="blue-grey lighten-5"
          class="mb-3"
        >
          <v-card-title>Button - loading</v-card-title>
          <v-card-text>
            <v-row>
              <v-col cols="6">
                <v-btn
                  color="indigo"
                  block
                  dark
                  :loading="button.loading"
                  @click="handleClickLoadingButton"
                >
                  Loading Button
                </v-btn>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-container>
    </v-main>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'Login',
  data: () => {
    return {
      button: {
        loading: false
      }
    }
  },
  computed: {
    ...mapGetters('question', ['questionList']),
    ...mapGetters('auth', ['jwt'])
  },
  methods: {
    ...mapActions('question', ['search']),
    ...mapActions('auth', ['login']),
    handleApiCall1 () {
      this.login({ userId: 'test', password: '131313' })
    },
    handleApiCall2 () {
      this.search({ query: 'server' })
    },
    async handlePopup1 () {
      if (await this.$popup.alert({ title: 'title', body: 'alert 팝업' })) {
        console.log('ok! do something...')
      }
      // await this.$popup.alert({ title: '팝업타이틀', body: 'alert 팝업' })
      // await this.$popup.alert({ body: 'alert 팝업' })
    },
    async handlePopup2 () {
      if (await this.$popup.confirm({ title: '알림', body: 'confirm 팝업' })) {
        console.log('ok! do something...')
      }
    },
    handleClickLoadingButton () {
      this.button.loading = true
      setTimeout(() => {
        this.button.loading = false
      }, 2000)
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
