<template>
  <h1>팀 생성</h1>
  <div>
      <div class="container">
        <p class="teamcreate-title">팀 생성</p>
        <input type="text" id="name" class="form-control name-form" placeholder="팀 이름" v-model="team.name">
        <input type="text" id="content" class="form-control name-form" placeholder="팀 설명" v-model="team.content">
        <button class="btn teamcreate-btn" @click="teamcreate()">팀 생성</button>
      </div>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/api.js'

export default {
  name: "TeamCreate",
  data: function () {
    return {
      team: {
        name: null,
        content: null,
      },
    }
  },
  methods: {
    teamcreate: function () {
      console.log(this.team)
      axios({
        method: 'post',
        url: `${SERVER.URL}/group`,
        data: this.team,
      })
        .then((res) => {
          console.log(res)
          this.$router.push({ name: 'MyTeam' })
        })
        .catch((err) => {
          console.log(err)
        })
      }, 
    },
  }
</script>

<style>
.teamcreate-title {
  font-size: 2rem;
}

.form-control {
  border: 0px;
}

.container {
  max-width: 600px;
}

.name-form {
  width: 100%;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
  border-top-right-radius: 0;
  border-top-left-radius: 0;
}

.teamcreate-btn {
  width: 100%;
  background-color: rgba(255,255,255,0.7);
  border-top-right-radius: 0;
  border-top-left-radius: 0;
}
</style>