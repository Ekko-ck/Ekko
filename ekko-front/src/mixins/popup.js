import { mapActions } from 'vuex'

export default {
  created () {
    this.$_popupMixin_wrap()
  },
  methods: {
    ...mapActions('popup', {
      $_popupMixin_alert: 'alert',
      $_popupMixin_confirm: 'confirm'
    }),
    $_popupMixin_wrap () {
      this.$popup = {
        alert: (popup) => {
          return new Promise((resolve) => {
            popup.resolve = resolve
            this.$_popupMixin_alert(popup)
          })
        },
        confirm: (popup) => {
          return new Promise((resolve) => {
            popup.resolve = resolve
            this.$_popupMixin_confirm(popup)
          })
        }
      }
    }
  }
}
