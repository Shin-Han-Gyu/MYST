<template>
  <div class="container">
    <div class="card" :style="{'background-color': myColor}">
      <router-link class="card-router" :to=" { name: 'TeamDetail', params: {id: myteam.id } }">
        <div class="card-body">
          <p :style="{ 'color': fontColor }" class="teamname">{{ myteam.name }}</p>
          <p :style="{ 'color': fontColor }" class="teamcontent">{{ myteam.content }}</p>
        </div>
      </router-link>
      <div class="d-flex justify-content-end w-100">
        <input type="color" class="form-control form-control-color" id="exampleColorInput" v-model="color.colorCode">
        <button class="btn btn-light" @click="colorChoose()">color 변경</button>
      </div>
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
      fontColor: "black"

    }
  },
  created() {
    const c = this.myteam.colorCode.substring(1)      // 색상 앞의 # 제거
    const rgb = parseInt(c, 16)   // rrggbb를 10진수로 변환
    const r = (rgb >> 16) & 0xff  // red 추출
    const g = (rgb >>  8) & 0xff  // green 추출
    const b = (rgb >>  0) & 0xff  // blue 추출
    const luma = 0.2126 * r + 0.7152 * g + 0.0722 * b // per ITU-R BT.709
    // 색상 선택
    if (luma < 127.5) {
      this.fontColor = "white"
    } else {
      this.fontColor = "black"
    }
  },
  methods: {
    colorChoose: function () {
      this.$store.dispatch("myteam/colorChoose", this.color, this.$store.state.login.userinfo.userToken)
      location.reload()
    }
  }
}
</script>

<style scoped>
.container {
  margin-top: 1rem;
}
.card-router {
  text-decoration-line: none;
}

.teamname {
  color: black;
  font-size: 1.5rem;
  font-family: "NanumBarunGothic-Regular";
}

.teamcontent {
  text-decoration-line: none;
  color: black;
  font-size: 1.0rem;
}

#exampleColorInput{
  margin-right: 10px;
}
</style>