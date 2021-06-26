<template>
  <div>
    <div>
      <!-- Title -->
      <v-row class="align-items-center">
        <v-col cols="2" class="text-center">
          <QuestionDetailVote
            :votes="question.votes"
            :handle-click-up="handleClickVoteUp"
            :handle-click-down="handleClickVoteDown"
          >
          </QuestionDetailVote>
        </v-col>
        <v-col cols="10">
          <div class="text-h5">
            {{ question.title }}
          </div>
          <div class="pt-3 pb-3">
            <v-chip
              v-for="(tag, index) in question.tags"
              :key="tag + index"
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

    <!-- Contents -->
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
            size="25"
          >
            <span class="white--text">{{ userNameForAvatar }}</span>
          </v-avatar>
        </v-col>
        <v-col cols="3" class="pl-4">
          {{ this.user.userNm }}
        </v-col>
        <v-col cols="8" class="text-right">
          <ui-text-date :text="question.registeredAt"></ui-text-date>
        </v-col>
      </v-row>
    </div>

    <!-- Answers -->
    <QuestionDetailAnswers :answers="question.answers"></QuestionDetailAnswers>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import QuestionDetailVote from '../../components/question/QuestionDetailVote.vue'
import QuestionDetailAnswers from '../../components/question/QuestionDetailAnswers.vue'

export default {
  name: 'QuestionDetail',
  props: {
    question: Object
  },
  components: {
    QuestionDetailVote,
    QuestionDetailAnswers
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
</style>
