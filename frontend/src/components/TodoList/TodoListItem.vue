<template>
  <div class="container">
    <div class="card" :style="{'background-color': todo.colorCode}">
      <div class="card-body">
        <div class="d-flex justify-content-between">
          <div>
            <p class="todotitle" :style="{ 'color': fontColor }">{{ todo.taskName }}</p>
            <p class="todoteam" :style="{ 'color': fontColor }" v-if="todo.teamId === -1">개인 todo</p>
            <p class="todoteam" :style="{ 'color': fontColor }" v-else>{{ todo.teamName }}</p>
          </div>
          <i v-if="taskDone === false" :style="{ 'color': fontColor }" class="far fa-square checkbox" @click="checkTodo()"></i>
          <i v-if="taskDone === true" :style="{ 'color': fontColor }" class="far fa-check-square checkbox" @click="checkTodo()"></i>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TodoListItem",
  props: {
    todo: Object
  },
  data: function () {
    return {
      fontColor: "black",
      taskDone: false
    }
  },
  created () {
    const c = this.todo.colorCode.substring(1)      // 색상 앞의 # 제거
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
    if (this.todo.teamId == -1 && this.todo.taskDone =="N") {
      this.taskDone = false
    } else if (this.todo.teamId == -1 && this.todo.taskDone == "Y") {
      this.taskDone = true
    } else if (this.$store.state.login.userinfo.username in this.todo.done) {
      this.taskDone = true
    } else {
      this.taskDone = false
    }
  },
  methods: {
    checkTodo () {
      this.taskDone = !this.taskDone
      this.$store.dispatch("todo/checkTodo", { "taskId":this.todo.taskId, "token": this.$store.state.login.userinfo.userToken })
    }
  }

}
</script>

<style scoped>
.container {
  max-width: 800px;
  margin-top: 1rem;
}

.todotitle {
  font-size: 1.5rem;
  font-family: "NanumBarunGothic-Bold";
  margin: 0;
}

.todoteam {
  font-size: 1rem;
  font-family: "NanumBarunGothic-Regular";
  margin: 0;
}

.checkbox {
  font-size: 2rem;
  margin-top: auto;
  margin-bottom: auto;
}
</style>