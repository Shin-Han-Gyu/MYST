<template>
  <div class="container">
    <div class="card" :style="{'background-color': myColor}">
      <router-link :to=" { name: 'TeamDetail', params: {id: myteam.id } }">
        <div class="card-body" >
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
        colorCode: this.myteam.colorCode,
        teamId: this.myteam.id
      },
      myColor: this.myteam.colorCode,
    }
  },
  created() {
  },
  methods: {
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