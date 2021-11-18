<template>
  <TodoList />
  <button type="button" class="btn btn-create" data-bs-toggle="modal" data-bs-target="#exampleModal">
    Todo 생성
  </button>
  <TodoCreate />
</template>

<script>
import TodoList from "@/components/TodoList/TodoList.vue"
import TodoCreate from "@/components/TodoList/TodoCreate.vue"

export default {
  name: "EntrancePage",
  components: {
    TodoList,
    TodoCreate
  },
  created() {
    if (this.$store.state.login.userinfo.username == null) {
      console.log(this.$store.state.login.userinfo.username)
      console.log("main to entrance")
      this.$router.push({ name: "EntrancePage" })
    }
    console.log(this.$store.state.login.userinfo.userToken)
    this.$store.dispatch("todo/getMyTodo", this.$store.state.login.userinfo.userToken)

  }
}
</script>

<style scoped>
.btn-create {
  position: fixed;
  background-color: rgb(31,78,121);
  color: white;
  bottom: 20px;
  right: 20px;
}
</style>