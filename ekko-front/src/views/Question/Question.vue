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
      <div class="list-area" ref="list">
        <v-list two-line>
          <v-list-item-group>
            <QuestionList :questionList="questionList" />
            <QuestionList :questionList="questionList" />
            <QuestionList :questionList="questionList" />
          </v-list-item-group>
        </v-list>
      </div>
    </v-main>
  </div>
</template>

<script>
import QuestionList from '../../components/question/QuestionList.vue'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'Question',
  components: {
    QuestionList
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
    }

    // https://medium.com/@jbbpatel94/difference-between-offsetheight-clientheight-and-scrollheight-cfea5c196937
  }
}
</script>

<style lang="scss" scoped>
.list-area {
  overflow: auto;
  height: calc(100vh - 150px);
}
</style>
