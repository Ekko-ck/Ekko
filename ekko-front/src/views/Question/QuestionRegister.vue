<template>
  <div>
    <v-container class="py-6">
      <h2 class="mb-5 pl-1">
        질문
        <v-icon>mdi-chat-question</v-icon>
      </h2>
      <v-card outlined class="mb-5">
        <div class="d-flex justify-space-between align-center">
          <v-card-subtitle>
            제목
          </v-card-subtitle>
          <v-text-field class="pr-4"></v-text-field>

        </div>

        <div class="px-4">
          <v-textarea
            counter
            label="질문을 입력하세요."
            :rules="rules"
            :value="value"
          ></v-textarea>
        </div>

        <v-card-subtitle class="pb-0">
          Tags
        </v-card-subtitle>
        <v-text-field class="px-4" label="e.g. (swift jquery wordpress)"></v-text-field>
      </v-card>

      <v-btn small color="success">Review your question</v-btn>
    </v-container>
  </div>
</template>

<script>

export default {
  name: 'QuestionRegister',
  components: {

  },

  data () {
    return {
    }
  }

}

</script>

<style lang="scss" scoped>

  .v-card__subtitle {
    font-weight: bold;
    color: #202020 !important;
  }

</style>
