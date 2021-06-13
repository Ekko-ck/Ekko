<template>
  <div>
    <v-list-item v-for="question in questionList" :key="question.id" @click="handleClickItem"> <!-- 클릭 이벤트 -->
      <v-list-item-content>
        <v-chip-group> <!-- 컴포넌트 화 -->
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
    questionList: {
      type: Array,
      required: true
    }
  },

  methods: {
    handleClickItem () {
      this.$router.push('/question/details') // 라우터 보낼 때 파라미터 ( 현재 선택된 하나의 객체를 보내면 됨 )
    }
  }
}
</script>

<style scoped>

</style>
