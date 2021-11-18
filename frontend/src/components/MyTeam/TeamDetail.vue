<template>
  <div class="container">
    <div>
      <div class="card-body">
        <div class="team-name">팀이름: {{ team.name }}</div>
        <p></p>
        <div class="team-content">팀 설명: {{ team.content }}</div>
        <p></p>
        <div>
          팀원 리스트
        </div>
        <ol class="list-group list-group-numbered">
          <li class="list-group-item" v-for="member in team.groupMemberDtos" :key="member">
           ID: {{member.userId}}&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;NAME: {{member.userName}}
          </li>
        </ol>
      </div>
      <button type="button" v-if="info.position == 'Leader'" class="btn btn-btn" data-bs-toggle="modal" data-bs-target="#joinlist">
        가입요청 확인
      </button>
      <button v-if="info.position == 'None'" class="btn btn-btn" @click="join()">가입신청</button>
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
        })
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
          console.log(err)
        })
  },
  methods: {
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
  font-size: 2rem;
}

.btn-btn {
  width: 100%;
  background-color: rgba(255,255,255,0.7);
  border-top-right-radius: 0;
  border-top-left-radius: 0;
}

</style>