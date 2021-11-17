<template>
  <TodoListItem
    v-for="todo in $store.state.todo.todolist"
    :key="todo.id"
    :todo="todo"
  />
</template>

<script>
import TodoListItem from "@/components/TodoList/TodoListItem.vue"
export default {
  name: "TodoList",
  components: {
    TodoListItem
  },
}
</script>

<style>

</style>