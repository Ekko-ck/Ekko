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
    const res = await API.search({})
    console.log(res)
  },
  components: {
  },
  methods: {
  }
}
</script>
