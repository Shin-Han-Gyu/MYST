<template>
  <MyTeamItem
    v-for="myteam in $store.state.myteam.myteamlist"
    :key="myteam.id"
    :myteam="myteam"
  />
  
</template>

<script>
import MyTeamItem from "@/components/MyTeam/MyTeamItem.vue"
export default {
  name: "MyTeam",
  components: {
    MyTeamItem,

  }

}
</script>

<style>

</style>