<template>
  <div>
    <v-list-item @click="handleClickItem(question)" class="list-item">
      <v-list-item-content>
        <v-chip-group>
          <v-chip outlined small label>{{ question.votes }} vote</v-chip>
          <v-chip outlined small label>{{ question.answers ? question.answers.length : 0 }} answer</v-chip>
          <v-chip outlined small label>{{ question.views }} views</v-chip>
        </v-chip-group>

        <v-list-item-title class="ml-1">{{ question.title }}</v-list-item-title>

        <v-chip-group>
          <v-chip v-for="(tag, index) in question.tags" :key="tag+index" small label color="#E3F2FD">{{ tag }}</v-chip>
        </v-chip-group>

        <v-list-item-subtitle class="text-right">{{ question.registeredAt }}</v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>
  </div>
</template>

<script>

export default {
  name: 'QuestionListItem',
  components: {
  },

  data () {
    return {
    }
  },

  props: {
    question: {
      type: Object,
      required: true
    }
  },

  methods: {
    handleClickItem (question) {
      this.$router.push({
        name: 'QuestionDetail',
        params: {
          question
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  .list-item {
    border-bottom: 1px solid #ededed;
  }
</style>
