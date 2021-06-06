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
      <v-container fluid>
        <div class="list-area" ref="list">
          <v-list two-line>
            <v-list-item-group>
              <v-list-item v-for="question in questionList" :key="question.id"> <!-- 클릭 이벤트 -->
                <v-list-item-content>

                  <v-chip-group>
                    <v-chip outlined small label>{{ question.votes }} vote</v-chip>
                    <v-chip outlined small label>answer</v-chip>
                    <v-chip outlined small label>{{ question.views }} views</v-chip>
                  </v-chip-group>

                  <v-list-item-title>{{ question.title }}</v-list-item-title>

                  <v-chip-group>
                    <v-chip v-for="(tag, index) in question.tags" :key="tag+index" small label color="#E3F2FD">{{ tag }}</v-chip>
                  </v-chip-group>

                  <v-list-item-subtitle class="text-right">{{ question.registeredAt }}</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </div>
      </v-container>
    </v-main>
  </div>

</template>

<script>

import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'Question',
  components: {
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
    ...mapGetters('question', ['questionList'])
  },
  methods: {
    ...mapActions('question', ['search']),
    handleSearch () {
      this.search({ query: this.query, page: this.page })
    },
    handleScroll (event) {
      const el = this.$refs.list
      console.log((el.scrollHeight - el.offsetHeight), el.scrollTop)
      if ((el.scrollHeight - el.offsetHeight) <= (el.scrollTop + 20)) {
        console.log('last')
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
