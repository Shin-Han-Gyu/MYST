<template>
  <div class="container">
    <p class="login-title">로그인</p>
    <input type="text" id="userid" class="form-control id-form" placeholder="아이디 입력">
    <input type="password" id="password" class="form-control pw-form" placeholder="비밀번호 입력">
    <button class="btn login-btn">로그인</button>

  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.container {
  max-width: 350px;
  margin: auto;
}

.login-title {
  font-size: 2rem;
}

.login-btn {
  width: 100%;
  margin-top: 10px;
  background-color: rgba(255,255,255,0.7);
}

.id-form {
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
}

.pw-form {
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>