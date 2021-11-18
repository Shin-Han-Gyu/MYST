<template>
  <div class="container">
    <div class="card" :style="{'background-color': todo.colorCode}">
      <div class="card-body">
        <div class="d-flex justify-content-between">
          <div>
            <p :style="{ 'color': fontColor }">{{ todo.taskName }}</p>
            <p :style="{ 'color': fontColor }" v-if="todo.teamId === -1">개인 todo</p>
            <p :style="{ 'color': fontColor }" v-else>{{ todo.teamName }}</p>
          </div>
          <i :style="{ 'color': fontColor }" class="far fa-square checkbox"></i>
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
      fontColor: "black"
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
  }

}
</script>

<style scoped>
.container {
  max-width: 800px;
  margin-top: 1rem;
}

.checkbox {
  font-size: 2rem;
  margin-top: auto;
  margin-bottom: auto;
}
</style>