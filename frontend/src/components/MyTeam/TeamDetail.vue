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
        })
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


</style>