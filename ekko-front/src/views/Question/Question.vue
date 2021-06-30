<template>
  <div>
    <v-card outlined class="px-4 d-flex justify-space-between align-center">

      <div class="d-flex justify-space-between align-center">
        <v-text-field class="mr-1"></v-text-field>
        <v-icon>mdi-magnify</v-icon>
      </div>

      <v-btn @click="handleClickRegister">질문하기</v-btn>
    </v-card>

    <div class="list-area" ref="list">
      <v-list two-line>
        <v-list-item-group v-for="question in questionList" :key="question.id">
          <QuestionListItem :question="question" />
        </v-list-item-group>
      </v-list>
      <infinite-loading spinner="circles" @infinite="handleInfiniteScroll"></infinite-loading>
    </div>
  </div>
</template>

<script>
import QuestionListItem from '../../components/question/QuestionListItem.vue'
import { mapGetters, mapActions } from 'vuex'
import InfiniteLoading from 'vue-infinite-loading'
import store from '../../store'

export default {
  name: 'Question',
  components: {
    QuestionListItem,
    InfiniteLoading
  },
  data () {
    return {
      query: '',
      page: 0
    }
  },
  created () {
    // this.search({ query: this.query, page: this.page })
  },
  mounted () {
    // this.handleDebouncedScroll = this._.debounce(this.handleScroll, 100)
    // this.$refs.list.addEventListener('scroll', this.handleScroll)
  },
  computed: {
    ...mapGetters('question', ['questionList']) // 여기서 네임스페이스 사용
  },
  methods: {
    ...mapActions('question', ['search']),
    handleSearch () {
      this.search({ query: this.query, page: this.page })
    },
    handleScroll (event) {
      const el = this.$refs.list

      console.log(this.page)

      if ((Math.round(el.scrollTop + el.offsetHeight)) === el.scrollHeight) {
        this.page++
        this.handleSearch()
      }
    },
    handleClickItem (question) {
      this.$router.push({ name: 'QuestionDetails', params: { question: question } })
    },

    handleClickRegister () {
      this.$router.push({ name: 'QuestionRegister' })
    },

    async handleInfiniteScroll ($state) {
      await this.search({ query: this.query, page: this.page })
      this.page++
      const questionListResponse = store.getters['question/questionListResponse']
      if (questionListResponse.length) {
        $state.loaded()
      } else {
        $state.complete()
      }
    }

  }
}
</script>

<style lang="scss" scoped>
.list-area {
  overflow: auto;
  height: calc(100vh - 150px);
}
</style>
