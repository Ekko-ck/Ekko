<template>
  <v-row class="align-items-center">
    <v-col cols="1">
      <v-avatar
        :color="avatarColor"
        dark
        size="25"
      >
        <span class="white--text">{{ iconName() }}</span>
      </v-avatar>
    </v-col>
    <v-col cols="3" class="pl-4">
      {{ name }}
    </v-col>
    <v-col cols="8" class="text-right">
      <UiTextDate v-if="textDate" :text="textDate"></UiTextDate>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: 'QuestionDetailAvatar',
  props: {
    name: {
      type: String,
      requried: true
    },
    avatarColor: {
      type: String,
      requried: true
    },
    textDate: {
      type: String,
      required: false
    }
  },
  methods: {
    iconName () {
      return this.name.substring(0, 2)
    }
  }
}
</script>
