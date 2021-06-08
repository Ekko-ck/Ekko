<template>
  <div>
  <v-app id="inspire">
    <v-container>
      <v-layout wrap>
        <v-flex sm12 md6 offset-md3>
          <v-card elevation="4" light tag="section">
            <v-card-title>
              <v-layout align-center justify-space-between>
                <v-flex>
                  <v-img class="ml-3" contain height="48px" position="center right" src="https://www.mobygames.com/images/i/12/25/1435075.png"></v-img>
                </v-flex>
              </v-layout>
            </v-card-title>
            <v-divider></v-divider>
            <v-card-text>
              <p>Sign in with your username and password:</p>
              <v-form>
                <v-text-field
                              outline
                              label="Username"
                              type="text"
                              v-model="userId"></v-text-field>
                <v-text-field
                              outline
                              hide-details
                              label="Password"
                              type="password"
                              v-model="password"></v-text-field>
              </v-form>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions>
              <v-btn @click="handleJoin" color="info">
                <v-icon left>mdi-account</v-icon>
                Join
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn @click="handleSigninUser" color="info">
                <v-icon left>mdi-lock</v-icon>
                Login
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
    </v-app>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'Login',
  components: {
  },
  data () {
    return {
      userId: '',
      password: ''
    }
  },
  methods: {
    ...mapActions('auth', ['signinUser']),
    // 로그인 처리
    async handleSigninUser () {
      const resSigninUser = await this.signinUser({ userId: this.userId, password: this.password })
      if (resSigninUser != null) {
        this.$router.push({ name: 'Question' })
      }
    },
    // 회원가입 화면이동
    handleJoin () {
      this.$router.push({ name: 'Join' })
      // this.$router.push('/join')
    }
    // 아이디비밀번호 찾기 화면이동
  }
}
</script>
