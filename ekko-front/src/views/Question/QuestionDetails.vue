<template>
  <div>
    <div>
      <v-row class="align-items-center">
        <v-col cols="2" class="text-center">
          <v-btn
            icon
            color="blue-grey darken-1"
            @click="handleClickVoteUp"
          >
            <v-icon>mdi-chevron-up-circle</v-icon>
          </v-btn>
          <br/>
          {{ question.votes }}
          <br/>
          <v-btn
            icon
            color="blue-grey darken-1"
            @click="handleClickVoteDown"
          >
            <v-icon>mdi-chevron-down-circle</v-icon>
          </v-btn>
        </v-col>
        <v-col cols="10">
          <div class="text-h5">
            {{ question.title }}
          </div>
          <div class="pt-3 pb-3">
            <v-chip
              v-for="(tag, index) in question.tags"
              :key="tag+index"
              small
              color="#E3F2FD"
              class="mr-2"
            >
              {{ tag }}
            </v-chip>
          </div>
        </v-col>
      </v-row>
    </div>

    <v-divider class="mt-2 mb-2"></v-divider>

    <div class="contents">
      {{ question.contents }}
    </div>

    <v-divider class="mt-2 mb-2"></v-divider>

    <div>
      <v-row class="align-items-center">
        <v-col cols="1">
          <v-avatar
            color="teal"
            dark
            size="30"
          >
            <span class="white--text">{{ userNameForAvatar }}</span>
          </v-avatar>
        </v-col>
        <v-col cols="3" class="pl-4">
          {{ this.user.userNm }}
        </v-col>
        <v-col cols="8" class="text-right">
          <span class="text--secondary">{{ question.registeredAt }}</span>
        </v-col>
      </v-row>
    </div>

    <v-alert
      dark
      color="blue-grey darken-1"
      class="pt-2 pb-2 mt-5 mb-2"
    >
      <span class="text-h6">{{ answerCount }} Answer</span>
    </v-alert>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'QuestionDetails',
  props: {
    question: Object
  },
  components: {
  },
  data () {
    return {
    }
  },
  created () {
    console.log(this.question)
  },
  computed: {
    ...mapGetters('auth', ['user']),
    userNameForAvatar () {
      return this.user.userNm.substring(0, 2)
    },
    answerCount () {
      return this.question.answers ? this.question.answers.length : '0'
    }
  },
  methods: {
    handleClickVoteUp () {
      console.log('up')
    },
    handleClickVoteDown () {
      console.log('down')
    }
  }
}
</script>

<style scoped>
.contents {
  min-height: 150px;
}
.text-center {
  text-align: center;
}
.align-items-center {
  align-items: center;
}
</style>
