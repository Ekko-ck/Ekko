<template>
  <div>
    <v-alert
      dark
      color="blue-grey darken-1"
      class="py-1 mt-5 mb-2"
    >
      <span class="text-h6">{{ answerCount }} Answers</span>
    </v-alert>

    <v-card
      v-for="(answer, index) in answers"
      :key="answer.id"
      outlined
      class="pa-2 mb-2">
        <v-row>
          <v-col cols="2" class="text-center px-0">
            <QuestionDetailVote
              :votes="answer.votes"
              :handle-click-up="handleClickAnswerVoteUp"
              :handle-click-down="handleClickAnswerVoteDown"
            >
            </QuestionDetailVote>
          </v-col>
          <v-col cols="1" class="px-0">
            <v-divider vertical></v-divider>
          </v-col>
          <v-col cols="9" class="ml-n6">
            {{ answer.contents }}
          </v-col>
        </v-row>

        <v-divider class="mt-2 mb-2"></v-divider>

        <v-row class="align-items-center">
          <v-col cols="1">
            <v-avatar
              :color="avatarColor(index)"
              dark
              size="25"
            >
              <span class="white--text">{{ userNameForAvatar(answer.userName) }}</span>
            </v-avatar>
          </v-col>
          <v-col cols="3" class="pl-4">
            {{ answer.userName }}
          </v-col>
          <v-col cols="8" class="text-right">
            <ui-text-date :text="answer.registeredAt"></ui-text-date>
          </v-col>
        </v-row>
    </v-card>
  </div>
</template>

<script>
import QuestionDetailVote from './QuestionDetailVote.vue'

export default {
  name: 'QuestionDetailAnswers',
  props: {
    answers: {
      type: Array,
      requried: true
    }
  },
  data () {
    return {
      avatarColorMap: {
        0: 'red darken-3',
        1: 'orange darken-3',
        2: 'yellow darken-2',
        3: 'green darken-3',
        4: 'blue darken-3',
        5: 'indigo darken-3',
        6: 'purple darken-3'
      }
    }
  },
  components: {
    QuestionDetailVote
  },
  created () {
    console.log(this.answers)
  },
  computed: {
    answerCount () {
      return this.answers ? this.answers.length : '0'
    }
  },
  methods: {
    userNameForAvatar (userName) {
      return userName.substring(0, 2)
    },
    avatarColor (index) {
      const value = index % 7
      return this.avatarColorMap[value]
    },
    handleClickAnswerVoteUp () {
      console.log('up')
    },
    handleClickAnswerVoteDown () {
      console.log('down')
    }
  }
}
</script>
