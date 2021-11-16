<template>
  <div class="container">
    <div class="card">
      <div class="card-body">
        <p>{{ todo.todotitle }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TodoListItem",
  props: {
    todo: Object
  },
}
</script>

<style scoped>
.container {
  max-width: 800px;
  margin-top: 1rem;
}
</style>