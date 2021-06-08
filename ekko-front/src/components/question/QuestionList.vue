<template>
  <div>
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
  }
}
</script>

<style scoped>

</style>
