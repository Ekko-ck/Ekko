<template>
  <v-dialog
    v-model="popup.show"
    persistent
    width="500"
  >
    <v-card>
      <v-card-title id="popupTitle">
        {{ popup.title }}
      </v-card-title>
      <v-card-text id="popupBody" v-html="popup.body">
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          color="red accent-3"
          text
          @click="handleCancel"
          v-show="popup.type === 'confirm'"
        >
          취소
        </v-btn>
        <v-btn
          color="primary"
          text
          @click="handleOk"
        >
          확인
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'Popup',
  computed: {
    ...mapGetters('popup', ['popup'])
  },
  methods: {
    ...mapActions('popup', ['close']),
    handleOk () {
      this.popup.resolve(true)
      this.close()
    },
    handleCancel () {
      this.popup.resolve(false)
      this.close()
    }
  }
}
</script>
<style lang="scss" scoped>
#popupTitle {
  font-size: 1.1rem;
  font-weight: 1000;
}
#popupBody {
  color: black;
}
</style>
