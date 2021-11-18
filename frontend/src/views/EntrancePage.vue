<template>
  <div style="form1">
    <welcome-one title="개인알림장과 공유알림장을 동시에" subject="팀원과 공유를 하면서 나만의 알림장을 써봐요"></welcome-one>
    <welcome-two title2="까먹지 않도록 메모하세요" subject2="초등학생보다 못한 내 기억력을위한 나만의 알림장"></welcome-two>
    <welcome-one title="Work - Life - Balance" subject="회사일과 개인일을 구분하여, 워라벨을 체크하세요"></welcome-one>
    <welcome-two title2="구분되는 회사일과 나의일" subject2="개인 할 일과 회사 일을 구분하여 헷갈리지 않게, 빠뜨리지 않게 하세요"></welcome-two>
  </div>
</template>

<script>
import WelcomeOne from "@/components/Welcome/WelcomeOne.vue"
import WelcomeTwo from "@/components/Welcome/WelcomeTwo.vue"
export default {
  name: "MainPage",
  components: {
    "welcome-one" : WelcomeOne,
    "welcome-two" : WelcomeTwo,
  },
  created() {
    if (this.$store.state.login.userinfo.username != null) {
      this.$router.push({ name: "MainPage" })
    }
  }
}
</script>

<style>
  .form1{
    width: 100%;
    height: 300px;
  }
</style>