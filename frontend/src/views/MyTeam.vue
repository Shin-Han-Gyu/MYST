<template>
  <MyTeamItem
    v-for="myteam in $store.state.myteam.myteamlist"
    :key="myteam.id"
    :myteam="myteam"
  />
  <div class="container">
    <button class="btn team-btn" @click="team()">팀 생성</button>
  </div>
</template>

<script>
import MyTeamItem from "@/components/MyTeam/MyTeamItem.vue"
export default {
  name: "MyTeam",
  components: {
    MyTeamItem,

  },
  data: function () {
    return {
      token: null
    }
  },
  created() {
    this.$store.dispatch("myteam/getTeamList", this.$store.state.login.userinfo.userToken)
  },
  methods: {
    team: function () {
      this.$router.push({ name: 'TeamCreate' })
    },
  }
}
</script>

<style>
.container {
  margin-top: 1rem;
  max-width: 600px;
}
.team-btn {
  width: 100%;
  background-color: rgba(255,255,255,0.7);
  border-top-right-radius: 0;
  border-top-left-radius: 0;
}

.myteam {
  display: flex;
  flex-direction: column;
}
</style>