<template>
   <v-app id="inspire">
      <v-main>
         <v-container fluid fill-height>
            <v-layout align-center justify-center>
               <v-flex xs12 sm8 md4>
                  <v-card class="elevation-12">
                     <v-toolbar dark color="primary">
                        <v-toolbar-title>Ekko Join</v-toolbar-title>
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
                              v-model="password"
                           ></v-text-field>
                           <v-text-field
                              prepend-icon="mdi-email"
                              name="userEmailAddr"
                              label="Email"
                              type="text"
                              v-model="userEmailAddr"
                           ></v-text-field>
                        </v-form>
                     </v-card-text>
                     <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="primary" @click="handleSignupUser">SingUp</v-btn>
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
  name: 'Join',
  components: {
  },
  data () {
    return {
      userId: '',
      password: '',
      userEmailAddr: ''
    }
  },
  methods: {
    ...mapActions('auth', ['signupUser']),
    // 회원가입
    async handleSignupUser () {
      const resSignupUser = await this.signupUser({ userId: this.userId, password: this.password, userEmailAddr: this.userEmailAddr })
      if (resSignupUser != null) {
        if (await this.$popup.alert({ body: '회원가입 되었습니다' })) {
          this.$router.push({ name: 'Login' })
        }
      }
    }
  }
}
</script>
