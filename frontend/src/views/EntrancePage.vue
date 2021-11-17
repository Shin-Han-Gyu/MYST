<template>
  <h1>no login -> entrancepage</h1>
</template>

<script>
export default {
  name: "MainPage",
  components: {
  },
  created() {
    if (this.$store.state.login.userinfo.username != null) {
      this.$router.push({ name: "MainPage" })
    }
  }
}
</script>

<style>

</style>