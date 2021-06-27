<template>
  <div>
    <div
      v-for="(comment) in comments"
      :key="comment.id">
      <v-row>
        <v-col cols="1" class="my-0 pt-2">
          <v-icon small class="ml-2 mr-0">mdi-subdirectory-arrow-right</v-icon>
        </v-col>
        <v-col cols="11" class="my-0">
          <span class="text--primary comment-contents font-size-09-rem">{{ comment.contents }}</span>
          <v-icon small class="mx-1">mdi-minus</v-icon>
          <span class="font-weight-medium mr-2 font-size-09-rem">{{ comment.userName }}</span>
          <UiTextDate :text="comment.registeredAt"></UiTextDate>
          <v-divider class="mt-2"></v-divider>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
export default {
  name: 'QuestionDetailComments',
  props: {
    comments: {
      type: Array,
      requried: true
    }
  },
  methods: {
  }
}
</script>
