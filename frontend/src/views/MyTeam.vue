<template>
  <MyTeamItem
    v-for="myteam in $store.state.myteam.myteamlist"
    :key="myteam.id"
    :myteam="myteam"
    class=""
  />

  <div class="container">
    <!-- <button class="btn team-btn" data-bs-toggle="modal" data-bs-target="#exampleModal" @click="team()">팀 생성</button> -->
    <button
      type="button"
      class="btn team-btn"
      data-bs-toggle="modal"
      data-bs-target="#teamCreateModal"
    >
      팀 생성
    </button>
  </div>
  <TeamCreate />
</template>

<script>
import MyTeamItem from "@/components/MyTeam/MyTeamItem.vue";
import TeamCreate from "@/components/MyTeam/TeamCreate.vue";
export default {
  name: "MyTeam",
  components: {
    MyTeamItem,
    TeamCreate,
  },
  data: function() {
    return {
      token: null,
    };
  },
  created() {
    this.$store.dispatch(
      "myteam/getTeamList",
      this.$store.state.login.userinfo.userToken
    );
    this.$store.dispatch(
      "myteam/getTeamColor",
      this.$store.state.login.userinfo.userToken
    );
  },
};
</script>

<style>
.container {
  margin-top: 1rem;
  max-width: 600px;
}
.team-btn {
  width: 100%;
  background-color: rgba(255, 255, 255, 0.7);
}

.myteam {
  display: flex;
  flex-direction: column;
}
</style>
