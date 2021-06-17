<template>
  <div>
    <v-app-bar
          dark
          color="#1E88E5"
        >
      <v-app-bar-nav-icon></v-app-bar-nav-icon>
      <v-toolbar-title>Stack Overflow</v-toolbar-title>
      <v-spacer></v-spacer>

      <v-autocomplete
        hide-no-data
        hide-details
        placeholder="Search.."
        v-model="query"
      ></v-autocomplete>
      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
    </v-app-bar>

    <v-main>
      <v-card outlined>
        <v-card-title>
          Top Questions
        </v-card-title>
      </v-card>

      <v-btn @click="handleClickRegister">
        버튼
      </v-btn>

      <div class="list-area" ref="list">
        <v-list two-line>
          <v-list-item-group v-for="question in questionList" :key="question.id">
            <QuestionListItem :question="question" />
          </v-list-item-group>
        </v-list>
      </div>
    </v-main>
  </div>
</template>

<script>
import QuestionListItem from '../../components/question/QuestionListItem.vue'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'Question',
  components: {
    QuestionListItem
  },
  data () {
    return {
      query: '',
      page: 0
    }
  },
  created () {
    this.search({ query: this.query, page: this.page })
  },
  mounted () {
    // this.handleDebouncedScroll = this._.debounce(this.handleScroll, 100)
    this.$refs.list.addEventListener('scroll', this.handleScroll)
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
