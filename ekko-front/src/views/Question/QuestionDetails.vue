<template>
  <div>
    <div class="text-h5">
      {{ question.title }}
    </div>
    <div class="pt-3 pb-3">
      <v-chip
        v-for="(tag, index) in question.tags"
        :key="tag+index"
        small
        color="#E3F2FD"
        class="mr-2"
      >
        {{ tag }}
      </v-chip>
    </div>
    <hr class="division-line" />
  </div>
</template>

<script>

export default {
  name: 'QuestionDetails',
  props: {
    question: Object
  },
  components: {
  },
  data () {
    return {
    }
  },
  created () {
    console.log(this.question)
  },
  computed: {
  },
  methods: {
  }
}
</script>

<style scoped>
.division-line {
  border-top: 1px solid #cecece;
}
</style>
