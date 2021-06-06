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
              <p>Sign up with your username and password:</p>
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
                <v-text-field
                              outline
                              hide-details
                              label="email"
                              type="text"
                              v-model="userEmailAddr"></v-text-field>
              </v-form>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn @click="handleSignupUser" color="info">
                <v-icon left>mdi-account</v-icon>
                Join
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
    // 회원가입 화면이동
    handleSignupUser () {
      this.signupUser({ userId: this.userId, password: this.password, userEmailAddr: this.userEmailAddr })
    }
  }
}
</script>
