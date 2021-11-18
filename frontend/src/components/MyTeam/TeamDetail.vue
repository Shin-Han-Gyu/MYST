<template>
  <div class="bg-secondary container">
    <div style="text-align: center;" class="text-white team-name">{{ team.name }}</div>
    <p></p>
    <div style="text-align: center;" class="text-white team-content">{{ team.content }}</div>
    <p></p>
    <div class="text-white team-content">
      팀원 리스트
    </div>
    <ul class="list-group border border-3">
      <li class="border-warning bg-secondary d-flex justify-content-around list-group-flush" v-for="member in team.groupMemberDtos" :key="member">
        <div class="text-white p-2 flex-fill">ID: {{member.userId}}</div>  
        <div class="text-white p-2 flex-fill">NAME: {{member.userName}}</div>
      </li>
    </ul>
    <br>
    <button type="button" v-if="info.position == 'Leader'" class="btn btn-btn" data-bs-toggle="modal" data-bs-target="#joinlist">
      가입요청 확인
    </button>
    <br>
    <button v-if="info.position == 'None'" class="btn btn-btn" @click="join()">가입신청</button>
    <br>
    <div class="team-content text-white">Todo List</div>
    <div v-for="todo in todoList" :key="todo">
      <div class="card" :style="{'background-color': todo.colorCode}">
        <div class="card-body bg-warning">
          <div class="d-flex justify-content-between">
            <div>
              <p class="todotitle" :style="{ 'color': fontColor }">{{ todo.taskName }}</p>
            </div>
          </div>
      </div>
    </div>
    <br>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="joinlist" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">가입 요청 리스트</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <ul class="list-group">
              <li class="d-flex justify-content-between list-group-item" v-for="join in joinList" :key="join">
                ID: {{join.userId}}
                <div>
                  <button class="me-1 btn btn-primary" @click="accept(join.groupJoinId)">승인</button>
                  <button class="me-1 btn btn-danger" @click="deny(join.groupJoinId)">거절</button>
                </div>
              </li>
            </ul>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/api.js'

export default {
  name: "TeamDetail",
  data: function () {
    return {
      team: {
        id: this.$route.params.id,
        name: null,
        content: null,
        groupMemberDtos: null,
      },
      info: {
        position: null,
        teamId: null,
        userId: null,
      },
      joinList: {
        userId: null,
        groupJoinId: null,
      },
      todoList: Object,
      fontColor: "black",
    }
  },
  created() {
    axios({
        method: 'get',
        url: `${SERVER.URL}/group/detail/${this.team.id}`,
        headers: {
          jwt: this.$store.state.login.userinfo.userToken
        },
      })
        .then((res) => {
          console.log(res)
          this.team = res.data
        })
        .catch((err) => {
          console.log(err)
        }),
    axios({
        method: 'get',
        url: `${SERVER.URL}/group/position/${this.team.id}`,
        headers: {
          jwt: this.$store.state.login.userinfo.userToken
        },
      })
        .then((res) => {
          console.log(res)
          this.info = res.data
        })
        .catch((err) => {
          console.log(err)
        }),
    axios({
        method: 'get',
        url: `${SERVER.URL}/group/join/${this.team.id}`,
        headers: {
          jwt: this.$store.state.login.userinfo.userToken
        },
      })
        .then((res) => {
          console.log(res)
          this.joinList = res.data
        })
        .catch((err) => {
          this.joinList = null
          console.log(err)
        }),
    axios({
        method: 'get',
        url: `${SERVER.URL}/task/${this.team.id}`,
        headers: {
          jwt: this.$store.state.login.userinfo.userToken
        },
      })
        .then((res) => {
          console.log(res)
          this.todoList = res.data
        })
        .catch((err) => {
          this.todoList = null
          console.log(err)
        })
  },
  methods: {
    checkTodo: function (taskId) {
      this.$store.dispatch("todo/checkTodo", { "taskId":taskId, "token": this.$store.state.login.userinfo.userToken })
      location.reload()
    },
    join: function () {
      axios({
        method: 'post',
        url: `${SERVER.URL}/group/join/${this.$route.params.id}`,
        headers: {
          jwt: this.$store.state.login.userinfo.userToken
        },
      })
        .then((res) => {
          console.log(res)
          alert(`가입신청이 완료되었습니다.`)
          this.$router.push({ name: 'MainPage' })
        })
        .catch((err) => {
          alert(`신청 중 오류가 발생했습니다.`)
          console.log(err)
        })
      }, 
    accept: function (groupJoinId) {
      axios({
        method: 'patch',
        url: `${SERVER.URL}/group/join/${groupJoinId}`,
        headers: {
          jwt: this.$store.state.login.userinfo.userToken
        },
      })
        .then((res) => {
          console.log(res)
          alert(`가입승인이 완료되었습니다.`)
          this.$router.push({ name: 'MainPage' })
        })
        .catch((err) => {
          alert(`승인 중 오류가 발생했습니다.`)
          console.log(err)
        })
      }, 
      deny: function (groupJoinId) {
    axios({
        method: 'delete',
        url: `${SERVER.URL}/group/join/${groupJoinId}`,
        headers: {
          jwt: this.$store.state.login.userinfo.userToken
        },
      })
        .then((res) => {
          console.log(res)
          alert(`가입 거절이 완료되었습니다.`)
          this.$router.push({ name: 'MainPage' })
        })
        .catch((err) => {
          alert(`거절 중 오류가 발생했습니다.`)
          console.log(err)
        })
      }, 
    },
  }
</script>

<style>
.container {
  margin-top: 1rem;
}

.team-name {
  font-size: 3rem;
}

.team-content {
  font-size: 1.5rem;
}

.btn-btn {
  width: 100%;
  background-color: rgba(255,255,255,0.7);
  border-top-right-radius: 0;
  border-top-left-radius: 0;
}

</style>