<template>
  <div
    v-for="myteam in this.myteams"
    :key="myteam.id"
  >
    <p>{{ teamcolor }}</p>
    <input type="color" v-model="color">
    <button @click="changecolor()">123</button>
  </div>
</template>

<script>
export default {
  name: "MyPage",
  data: function() {
    return {
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
  }
}
</script>

<style>

</style>