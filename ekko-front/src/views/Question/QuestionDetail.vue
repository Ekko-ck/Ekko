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

    <QuestionDetailAvatar
      :name="question.userName"
      avatar-color="teal"
      :text-date="question.registeredAt"
    >
    </QuestionDetailAvatar>

    <v-divider class="mt-2 mb-2"></v-divider>

    <QuestionDetailComments :question-id="question.id" :comments="question.comments"></QuestionDetailComments>

    <QuestionDetailAnswers :question-id="question.id" :answers="question.answers"></QuestionDetailAnswers>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import QuestionDetailVote from '@/components/question/QuestionDetailVote.vue'
import QuestionDetailAnswers from '@/components/question/QuestionDetailAnswers.vue'
import QuestionDetailAvatar from '@/components/question/QuestionDetailAvatar.vue'
import QuestionDetailComments from '@/components/question/QuestionDetailComments.vue'

export default {
  name: 'QuestionDetail',
  components: {
    QuestionDetailVote,
    QuestionDetailAnswers,
    QuestionDetailAvatar,
    QuestionDetailComments
  },
  props: {
    question: Object
  },
  data () {
    return {
    }
  },
  created () {
    console.log(this.question)
  },
  computed: {
    ...mapGetters('auth', ['user'])
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
