<template>
  <div>
    <v-main>
      <v-card outlined>
        <v-card-title>
          Top Details
        </v-card-title>
      </v-card>
    </v-main>
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

</style>
