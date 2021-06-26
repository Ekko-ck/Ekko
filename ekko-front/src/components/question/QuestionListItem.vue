<template>
  <div>
    <v-list-item @click="handleClickItem(question)">
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
  </div>
</template>

<script>

export default {
  name: 'questionList',
  components: {
  },

  data () {
    return {
    }
  },

  props: { // Prop은 가능한 상세하게 정의되어야 합니다.
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
      }) // 라우터 보낼 때 파라미터 ( 현재 선택된 하나의 객체를 보내면 됨 )
    }
  }
}
</script>

<style scoped>

</style>
