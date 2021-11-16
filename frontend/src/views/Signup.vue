<template>
  <div class="container">
    <p class="signup-title">회원 가입</p>
    <br>
    <p class="signup-eletitle">아이디</p>
    <div class="id-input">
      <input type="text" id="userid" class="form-control id-form" placeholder="아이디 입력">
      <button class="btn id-dup-btn">중복검사</button>
    </div>
    <p>중복 여부 멘트 나오는 자리</p>
    <p class="signup-eletitle">비밀번호</p>
    <input type="password" id="password" class="form-control pw-form" placeholder="비밀번호 입력">
    <p class="signup-eletitle">비밀번호 확인</p>
    <input type="password" id="passwordConfirmation" class="form-control pw-form" placeholder="비밀번호 확인">
    <p>비밀번호 일치 여부 멘트 나오는 자리</p>
    <p class="signup-eletitle">이름</p>
    <input type="text" id="name" class="form-control" placeholder="이름 입력">

    <button class="btn signup-btn">회원가입</button>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.container {
  max-width: 600px;
  margin: auto;
}

.signup-title {
  font-size: 2rem;
}

.signup-eletitle {
  font-size: 1.3rem;
  margin-top: 1rem;
  margin-bottom: 0.2rem;
}

.form-control {
  background-color: rgba(255,255,255, 0.3);
  border: 0px;
}

.id-form {
  width: 100%;
}

.id-dup-btn {
  width: 100%;
  margin-top: 10px;
  background-color: rgba(255,255,255,0.7);
}

.pw-form {
  width: 100%;
}


@media (min-width: 576px) {
  .id-input {
    display: flex;
    flex-flow: row wrap;
    justify-content: space-between;
  }
  .id-form {
    width: 78%;
  }

  .id-dup-btn {
    margin-top: 0px;
    width: 19%;
  }
}

.signup-btn {
  width: 100%;
  margin-top: 10px;
  background-color: rgba(255,255,255,0.7);
}
</style>