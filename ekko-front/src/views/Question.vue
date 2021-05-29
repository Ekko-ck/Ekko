<template>
  <div>
    <h1>This is an Question page</h1>
  </div>
</template>

<script>
import API from '../api/question'
export default {
  name: 'Question',
  async created () {
    const questionList = await API.search('server')
    console.log(questionList)
  },
  components: {
  },
  methods: {
  }
}
</script>
