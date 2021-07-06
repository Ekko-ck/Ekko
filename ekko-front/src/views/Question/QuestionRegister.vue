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
          <v-text-field class="pr-4" v-model="form.title"></v-text-field>
        </div>

        <div class="px-4">
          <v-textarea
            counter
            label="질문을 입력하세요."
            v-model="form.contents"
          ></v-textarea>
        </div>

        <v-card-subtitle class="pb-0">
          Tags
        </v-card-subtitle>
        <v-text-field
          class="px-4"
          label="e.g. (swift jquery wordpress)"
          v-model="form.tagsText"
        >
        </v-text-field>
      </v-card>

      <v-btn small color="success" @click="handleRegister">Review your question</v-btn>
    </v-container>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'QuestionRegister',
  components: {
  },
  data () {
    return {
      form: {
        title: '',
        contents: '',
        tagsText: ''
      }
    }
  },
  methods: {
    ...mapActions('question', ['registerQuestion']),
    async handleRegister () {
      const tags = this.form.tagsText.split(',')
      const requestData = {
        title: this.form.title,
        contents: this.form.contents,
        tags: tags.map(tag => tag.trim())
      }
      await this.registerQuestion(requestData)
      this.$router.push({ name: 'Question' })
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
