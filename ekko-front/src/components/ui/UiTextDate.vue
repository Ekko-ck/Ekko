<template>
  <span class="text--secondary date-text">{{ text }}</span>
</template>

<script>
export default {
  name: 'UiTextDate',
  props: {
    text: {
      type: String,
      requried: true
    }
  }
}
</script>
<style lang="scss" scoped>
.date-text {
  font-size: 0.8rem;
}
</style>
