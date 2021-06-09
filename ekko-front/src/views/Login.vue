<template>
   <v-app id="inspire">
      <v-main>
         <v-container fluid fill-height>
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
         </v-container>
      </v-main>
   </v-app>
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
      console.log('resSigninUser ' + resSigninUser)
      if (resSigninUser != null) {
        this.$router.push({ name: 'Question' })
      }
    },
    // 회원가입 화면이동
    handleSignupUser () {
      this.$router.push({ name: 'Join' })
      // this.$router.push('/join')
    }
    // 아이디비밀번호 찾기 화면이동
  }
}
</script>
