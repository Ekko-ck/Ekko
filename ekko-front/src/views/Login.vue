<template>
  <div class="login-form">
    <v-layout align-center justify-center>
      <v-flex xs12 sm8 md4>
        <v-card class="elevation-12">
          <v-toolbar dark color="primary">
            <v-toolbar-title>Ekko Login</v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            <v-form>
              <v-text-field
                prepend-icon="mdi-account"
                name="login"
                label="Username"
                type="text"
                v-model="userId"
              ></v-text-field>
              <v-text-field
                id="password"
                prepend-icon="mdi-lock"
                name="password"
                label="Password"
                type="password"
                v-on:keyup.enter="handleSigninUser"
                v-model="password"
              ></v-text-field>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-btn color="primary" @click="handleSignupUser">Join</v-btn>
            <v-spacer></v-spacer>
            <v-btn color="primary" @click="handleSigninUser">Login</v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'Login',
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
    handleSignupUser () {
      this.$router.push({ name: 'Join' })
    }
    // 아이디비밀번호 찾기 화면이동
  }
}
</script>
<style lang="scss" scoped>
.login-form {
  height: calc(100vh - 90px);
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
