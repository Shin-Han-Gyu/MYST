<template>
  <div class="signup-con">
    <div class="container">
      <p class="signup-title">회원 가입</p>
      <div class="id-input">
        <input type="text" id="userid" class="form-control id-form" placeholder="아이디 입력" v-model="credentials.userId">
        <button class="btn id-dup-btn" type="button" data-bs-toggle="modal" data-bs-target="#exampleModal" @click="idDupCheck()">중복검사</button>
      </div>
      <input type="text" id="name" class="form-control name-form" placeholder="이름 입력" v-model="credentials.name">
      <input type="password" id="password" class="form-control pw-form" placeholder="비밀번호 입력" v-model="credentials.password">
      <input type="password" id="passwordConfirmation" class="form-control pw-form" placeholder="비밀번호 확인" v-model="passwordConfirmation">
      <button class="btn signup-btn" @click="signup()">회원가입</button>
      
      <!-- 아이디 중복 체크 모달 -->
      <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">아이디 중복 확인</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <p
                v-if="idDup === '401'"
                class="warning-text"  
              >
                "이미 존재하는 아이디입니다."
              </p>
              <p
                v-else-if="idDup === '200'"
                class="warning-text"
              >
                "해당 아이디를 사용할 수 있습니다."
              </p>
              <p
                v-else-if="idDup === '100'"
                class="warning-text"
              >
                "아이디 입력창이 비어있습니다. 아이디를 입력해주세요."
              </p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
          </div>
        </div>
      </div>
      <!-- 비밀번호 일치여부 멘트 -->
      <p
        v-if="
          credentials.password === passwordConfirmation &&
          credentials.password != null
        "
        class="warning-text"
      >
        "비밀번호가 일치합니다."
      </p>
      <p
        v-else-if="credentials.password != null"
        class="warning-text"
      >
        "비밀번호가 일치하지 않습니다."
      </p>
    </div>

  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/api.js'

export default {
  name: "Signup",
  data: function () {
    return {
      credentials: {
        name: null,
        password: null,
        userId: null
      },
      idDup: null,
      idDupid: null,
      passwordConfirmation: null,
      signupstate: null,
      warningtext: null,
    }
  },
  methods: {
    idDupCheck: function () {
      axios({
        method: 'post',
        url: `${SERVER.URL}/user/id-duplication`,
        data: this.credentials,
      })
        .then((res) => {
          if (this.credentials.userId == null || this.credentials.userId == "") {
            this.idDup = '100'
          } else {
            if (res.status == 200) {
              this.idDup = '200'
              this.idDupid = this.credentials.userId
            } else if (res.status != 200) {
              this.idDup = '401'
            }
          }
        })
        .catch((err) => {
          console.log(err)
          this.idDup = '401'
        })
    },
    signup: function () {
      console.log(this.credentials)
      if (
        this.idDupid === this.credentials.userId &&
        this.credentials.password === this.passwordConfirmation
      ) {
        this.signupstate = 'success'
        axios({
          method: 'post',
          url: `${SERVER.URL}/user/sign-up`,
          data: this.credentials,
        })
          .then((res) => {
            console.log(res)
            this.$router.push({ name: 'Login' })
          })
          .catch((err) => {
            console.log(err)
          })
      } else {
        this.signupstate = 'fail'
        if (!(this.idDupid === this.credentials.userid)) {
          this.warningtext = 
            '중복체크한 아이디와 입력된 아이디가 다릅니다. 다시 확인해주세요.'
        } else if (!(this.credentials.password === this.passwordConfirmation)) {
          this.warningtext =
            '비밀번호가 다릅니다.'
        }
        console.log(this.warningtext)
      }
    },
  },

}
</script>

<style scoped>
.signup-con {
  position: absolute;
  width: 100%;
  height: 100%;
}

.container {
  max-width: 600px;
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
  border: 0px;
}

.id-form {
  width: 100%;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.id-dup-btn {
  width: 100%;
  background-color: rgba(255,255,255,0.7);
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
  border-top-right-radius: 0;
  border-top-left-radius: 0;
}
.name-form {
  width: 100%;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
  border-top-right-radius: 0;
  border-top-left-radius: 0;
}
.pw-form {
  width: 100%;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
  border-top-right-radius: 0;
  border-top-left-radius: 0;
}

.signup-btn {
  width: 100%;
  background-color: rgba(255,255,255,0.7);
  border-top-right-radius: 0;
  border-top-left-radius: 0;
}


@media (min-width: 576px) {
  .id-input {
    display: flex;
    flex-flow: row wrap;
    justify-content: space-between;
  }

  .id-form {
    width: 70%;
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
  }

  .id-dup-btn {
    margin-top: 0px;
    border-top-left-radius: 0;
    border-bottom-left-radius: 0;
    border-bottom-right-radius: 0;
    border-top-right-radius: 0.25rem;
    width: 30%;
  }

  
}

</style>