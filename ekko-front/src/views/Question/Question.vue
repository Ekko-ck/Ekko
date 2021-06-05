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

    }
  },

  computed: {
    ...mapGetters('question', ['questionList'])
  },

  methods: {
    ...mapActions('question', ['search'])

    // this.search({ query: 'server' })

  }
}
</script>

<style scoped>

</style>
