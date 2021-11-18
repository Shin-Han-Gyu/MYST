<template>
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Todo Create</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form class="form-floating">
            <input type="text" class="form-control" id="floatingInputValue" placeholder="title을 입력해주세요" v-model="createEle.newtodo.taskName">
            <label for="floatingInputValue">Todo Title</label>
          </form>
          <div class="form-floating">
            <select class="form-select" id="floatingSelect" aria-label="Floating label select example" v-model="createEle.createTeam">
              <option selected>팀을 선택 해주세요</option>
              <option value="">개인 todo</option>
              <option
                v-for="myteam in $store.state.myteam.myteamlist"
                :key="myteam.id"
                :myteam="myteam"
                :value="myteam.id"
              >
              {{myteam.name}}
              </option>
            </select>
            <label for="floatingSelect">팀 선택</label>
          </div>
          <div class="form-floating">
            <select class="form-select" id="floatingSelect" aria-label="Floating label select example" v-model="createEle.newtodo.completeOption">
              <option selected>완료 기준을 선택해주세요</option>
              <option value="ALL">팀원 모두 완료</option>
              <option value="ONE">1인 완료</option>
            </select>
            <label for="floatingSelect">완료기준 선택</label>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" data-bs-dismiss="modal" @click="createTodo()">Create</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TodoCreate",
  data: function () {
    return {
      createEle: {
        createTeam: null,
        newtodo: {
          taskName: null,
          completeOption: null,
        },
        userToken: this.$store.state.login.userinfo.userToken
      }
    }
  },
  methods: {
    createTodo: function () {
      console.log(this.createTeam)
      this.$store.dispatch("todo/createTodo", this.createEle)
      this.$store.dispatch("todo/getMyTodo", this.$store.state.login.userinfo.userToken)
    }
  },
  created() {
    this.$store.dispatch("myteam/getTeamList", this.$store.state.login.userinfo.userToken)
  }
}
</script>

<style>

</style>