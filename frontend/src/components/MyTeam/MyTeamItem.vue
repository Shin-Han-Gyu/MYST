<template>
  <div class="container">
    <div class="card">
      <router-link :to=" { name: 'TeamDetail', params: {id: myteam.id } }">
        <div class="card-body">
          <p>{{ myteam.name }}</p>
          <p>{{ myteam.content }}</p>
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
}
</script>

<style scoped>
.container {
  margin-top: 1rem;
}
</style>