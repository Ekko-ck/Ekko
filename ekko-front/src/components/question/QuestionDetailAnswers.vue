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
      class="pa-2 mb-2"
    >
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

      <QuestionDetailAvatar
        :name="answer.userName"
        :avatar-color="avatarColor(index)"
        :text-date="answer.registeredAt"
      >
      </QuestionDetailAvatar>

      <v-divider class="mt-2 mb-2"></v-divider>

      <QuestionDetailComments :comments="answer.comments"></QuestionDetailComments>
    </v-card>
  </div>
</template>

<script>
import QuestionDetailVote from '@/components/question/QuestionDetailVote.vue'
import QuestionDetailAvatar from '@/components/question/QuestionDetailAvatar.vue'
import QuestionDetailComments from '@/components/question/QuestionDetailComments.vue'

export default {
  name: 'QuestionDetailAnswers',
  components: {
    QuestionDetailVote,
    QuestionDetailAvatar,
    QuestionDetailComments
  },
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
  created () {
    console.log(this.answers)
  },
  computed: {
    answerCount () {
      return this.answers ? this.answers.length : '0'
    }
  },
  methods: {
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
