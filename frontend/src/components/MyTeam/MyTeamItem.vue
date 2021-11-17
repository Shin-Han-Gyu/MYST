<template>
  <div class="container">
    <div class="card">
      <router-link :to=" { name: 'TeamDetail', params: {id: myteam.id } }">
        <div class="card-body">
          <p>{{ myteam.teamname }}</p>
        </div>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "MyTeamItem",
  props: {
    myteam: Object
  },
  methods: {
    team: function () {
      this.$router.push({ name: 'TeamCreate' })
    },
  }
}
</script>

<style scoped>
.container {
  margin-top: 1rem;
}
</style>