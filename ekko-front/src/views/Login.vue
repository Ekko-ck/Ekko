<template>
  <div>
    Login 화면
    <br/>
    <v-btn @click="handleLogin">로그인</v-btn>
    <br/>
    <v-btn @click="handleJoin">회원가입</v-btn>
  </div>
</template>

<script>
export default {
  name: 'Login',
  components: {
  },
  methods: {
    handleLogin () {
      this.$router.push('/question')
    },
    handleJoin () {
      this.$router.push('/join')
    }
  }
}
</script>
