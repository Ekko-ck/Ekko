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

    <v-row v-if="!showCommentInput">
      <v-col>
        <v-btn
          outlined
          block
          small
          color="light-blue darken-4"
          @click="handleClickCommentInputShow"
        >
          댓글 작성하기
        </v-btn>
      </v-col>
    </v-row>
    <div v-else>
      <v-row class="align-end">
        <v-col cols="10" class="mr-n5">
          <v-textarea
            class="comment-text"
            v-model="commentContents"
            label="댓글"
            outlined
            color="light-blue darken-4"
            rows="2"
            row-height="20"
            :error="commentContentsError"
            :hide-details="true"
          ></v-textarea>
        </v-col>
        <v-col clos="2" class="text-right">
          <v-btn
            color="light-blue darken-2"
            dark
            small
            @click="handleRegisterComment"
            :loading="commentRegisterLoading"
          >
            등록
          </v-btn>
        </v-col>
      </v-row>
    </div>

  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'QuestionDetailComments',
  props: {
    questionId: {
      type: String,
      required: true
    },
    answerId: {
      type: String,
      required: false
    },
    comments: {
      type: Array,
      requried: true
    }
  },
  data () {
    return {
      isQuestion: true,
      showCommentInput: false,
      commentContents: '',
      commentContentsError: false,
      commentRegisterLoading: false
    }
  },
  created () {
    if (this.answerId) {
      this.isQuestion = false
    }
  },
  methods: {
    ...mapActions('question', [
      'regiserCommentToQuestion',
      'removeCommentFromQuestion',
      'regiserCommentToAnswer',
      'removeCommentFromAnswer'
    ]),
    handleClickCommentInputShow () {
      this.showCommentInput = !this.showCommentInput
    },
    handleRegisterComment () {
      const contents = this.commentContents.trim()
      if (contents === '') {
        this.commentContentsError = true
        return false
      }
      this.commentRegisterLoading = true

      const requestData = {
        questionId: this.questionId,
        answerId: this.answerId,
        contents: this.commentContents
      }

      if (this.isQuestion) {
        this.regiserCommentToQuestion(requestData)
      } else {
        this.regiserCommentToAnswer(requestData)
      }

      this.commentContents = ''
      this.showCommentInput = false
      this.commentRegisterLoading = false
    },
    handleRemoveComment (commentId) {
    }
  },
  watch: {
    commentContents (text) {
      text = text.trim()
      if (text !== '') {
        this.commentContentsError = false
      }
    }
  }
}
</script>
<style lang="scss" scoped>
.comment-text {
  font-size: 14px;
}
</style>
