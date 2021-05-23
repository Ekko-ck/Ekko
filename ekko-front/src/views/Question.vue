<template>
  <v-app><!-- 첫 화면의 시작 -->
    <v-card
    height="50px">
    <v-toolbar color="#1E88E5" dark>
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

    </v-toolbar>
  </v-card>

  <v-card outlined>
    <v-card-title>
      Top Questions
    </v-card-title>

    <v-card-text>
      <v-chip-group
        active-class="deep-purple--text text--accent-4"
        mandatory
      >
        <v-chip outlined label>Interesting</v-chip>
        <v-chip outlined label>Bountied</v-chip>
        <v-chip outlined label>Hot</v-chip>
        <v-chip outlined label>Week</v-chip>
        <v-chip outlined label>Month</v-chip>
      </v-chip-group>
    </v-card-text>
  </v-card>

  <v-list two-line>
      <v-list-item-group>
       <template v-for="questionList in questionLists">
            <v-list-item :key="questionList.id">
              <v-list-item-content>
                <v-chip-group>
                  <v-chip outlined small label>{{ questionList.votes }} vote</v-chip>
                  <v-chip outlined small label>answer</v-chip>
                  <v-chip outlined small label>{{ questionList.views }} views</v-chip>
                </v-chip-group>

                <v-list-item-title>{{ questionList.title }}</v-list-item-title>

                <v-chip-group>
                  <v-chip small label color="#E3F2FD">{{ questionList.tags[0] }}</v-chip>
                  <v-chip small label color="#E3F2FD">{{ questionList.tags[1] }}</v-chip>
                </v-chip-group>

                <v-list-item-subtitle class="text-right">{{ questionList.registeredAt }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </template>
      </v-list-item-group>
  </v-list>

  </v-app>
</template>

<script>
import API from '../api/question'

export default {
  name: 'Question',
  components: {
  },

  data () {
    return {
      questionLists: [],
      tags: []
    }
  },

  async created () {
    const questionList = await API.search('server')

    this.questionLists = questionList.data

    this.tags = this.questionLists.filter(item => {
      console.log(item.tags)
      return item.tags
    })

    console.log(this.tags)
  },

  methods: {
  }
}
</script>
