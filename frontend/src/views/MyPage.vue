<template>
  <div class = "container d-flex flex-column align-items-center">
    <div class="w-100">
      <h2 style="color: white; text-align: center;">
        {{this.username}}님의 마이페이지
      </h2>
      <button class="btn btn-secondary" style="float: right;" >회원정보 수정</button>

    </div>
    

    <h4 style="color: grey;">가입한 팀 리스트</h4>
    <MyPageTeams
      v-for="myteam in $store.state.myteam.myteamlist"
      :key="myteam.id"
      :myteam="myteam"
      class=""
    />

  </div>
  
  
</template>

<script>
import MyPageTeams from "@/components/MyPage/MyPageTeams.vue";

export default {
  name: "MyPage",
  components: {
    MyPageTeams,
  },
  data: function() {
    return {
      username: this.$store.state.login.userinfo.username,
      token: null,
      color : null,
      myteams: [{id: 1, teamname: "a", teamcolor: null}]
    }
  },
  methods: {
    changecolor: function () {
      this.myteams.teamcolor = this.color
      console.log(this.myteams.teamcolor)
      console.log(this.color)
    }
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
}
</script>

<style>

</style>