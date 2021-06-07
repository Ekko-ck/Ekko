<template>
  <div>질문에 대한 답변</div>
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

</style>
