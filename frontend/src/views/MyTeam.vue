<template>
  <div class="myteam">
    <MyTeamItem
      v-for="myteam in $store.state.myteam.myteamlist"
      :key="myteam.id"
      :myteam="myteam"
    />

  </div>
  
</template>

<script>
import MyTeamItem from "@/components/MyTeam/MyTeamItem.vue"
export default {
  name: "MyTeam",
  components: {
    MyTeamItem,
  },
  created: function () {
    this.$store.dispatch("myteam/getMyTeamList", this.$store.state.login.userinfo.userToken)
  }

}
</script>

<style scoped>
.myteam {
  display: flex;
  flex-direction: column;
}
</style>