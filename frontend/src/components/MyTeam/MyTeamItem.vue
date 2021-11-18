<template>
  <div class="container">
    <div class="card" :style="{'background-color': myColor}">
      <router-link :to=" { name: 'TeamDetail', params: {id: myteam.id } }">
        <div class="card-body">
          <p class="teamname">{{ myteam.name }}</p>
          <p>{{ myteam.content }}</p>
        </div>
      </router-link>
      <input type="color" class="form-control form-control-color" id="exampleColorInput" v-model="color.colorCode">
      <button class="btn btn-light" @click="colorChoose()">color 결정</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "MyTeamItem",
  props: {
    myteam: Object
  },
  data: function () {
    return {
      color: {
        colorCode: null,
        teamId: this.myteam.id
      },
      myColor: null,
    }
  },
  created() {
    console.log(this.$store.state.myteam.myTeamColor)
    for (var d in this.$store.state.myteam.myTeamColor) {
      console.log(this.$store.state.myteam.myTeamColor[d])
      if (this.$store.state.myteam.myTeamColor[d].teamId === this.myteam.id) {
        this.myColor = this.$store.state.myteam.myTeamColor[d].colorCode
        this.color.colorCode = this.$store.state.myteam.myTeamColor[d].colorCode
        console.log(this.myColor)
      }
    }
  },
  methods: {
    colorChoose: function () {
      this.$store.dispatch("myteam/colorChoose", this.color, this.$store.state.login.userinfo.userToken)
    }
  }
}
</script>

<style scoped>
.container {
  margin-top: 1rem;
}

.teamname {
  text-decoration-line: none;
  color: black;
  font-size: 1.5rem;
}
</style>