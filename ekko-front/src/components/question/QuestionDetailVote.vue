<template>
  <div>
    <v-btn
      icon
      color="grey darken-1"
      @click="handleClickUp"
    >
      <v-icon>mdi-chevron-up</v-icon>
    </v-btn>
    <br/>
    {{ votes }}
    <br/>
    <v-btn
      icon
      color="grey darken-1"
      @click="handleClickDown"
    >
      <v-icon>mdi-chevron-down</v-icon>
    </v-btn>
  </div>
</template>

<script>
export default {
  name: 'QuestionDetailVote',
  props: {
    votes: {
      type: Number,
      requried: true
    },
    handleClickUp: Function,
    handleClickDown: Function
  }
}
</script>
