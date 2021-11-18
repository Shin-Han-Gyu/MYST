<template>
  <div
    class="modal fade"
    id="teamCreateModal"
    tabindex="-1"
    aria-labelledby="teamCreateModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="teamCreateModalLabel">
            Team Todo Create
          </h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <form class="form-floating">
            <input
              type="text"
              class="form-control"
              id="teamFloatingInputValue"
              placeholder="팀 이름을 입력해주세요"
              v-model="team.name"
            />
            <label for="teamFloatingInputValue">팀 이름</label>
          </form>
          <form class="form-floating">
            <input
              type="text"
              class="form-control"
              id="teamContentFloatingInputValue"
              placeholder="팀 설명을 작성해주세요"
              v-model="team.content"
            />
            <label for="teamContentFloatingInputValue">팀 설명</label>
          </form>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Close
          </button>
          <button
            type="button"
            class="btn btn-primary"
            data-bs-dismiss="modal"
            @click="teamcreate()"
          >
            팀 생성
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- 
  <div>
      <div class="container">
        <p class="teamcreate-title">팀 생성</p>
        <input type="text" id="name" class="form-control name-form" placeholder="팀 이름" v-model="team.name">
        <input type="text" id="content" class="form-control name-form" placeholder="팀 설명" v-model="team.content">
        <button class="btn teamcreate-btn" @click="teamcreate()">팀 생성</button>
      </div>
  </div> -->
</template>

<script>
import axios from "axios";
import SERVER from "@/api/api.js";

export default {
  name: "TeamCreate",
  data: function() {
    return {
      team: {
        name: null,
        content: null,
      },
    };
  },
  methods: {
    teamcreate: function() {
      console.log(this.team);
      axios({
        method: "post",
        url: `${SERVER.URL}/group/`,
        headers: {
          jwt: this.$store.state.login.userinfo.userToken,
        },
        data: this.team,
      })
        .then((res) => {
          console.log(res);
          this.$router.push({ name: "MyTeam" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style>
.teamcreate-title {
  font-size: 2rem;
}

.form-control {
  border: 0px;
}

.container {
  max-width: 600px;
}

.name-form {
  width: 100%;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
  border-top-right-radius: 0;
  border-top-left-radius: 0;
}

.teamcreate-btn {
  width: 100%;
  background-color: rgba(255, 255, 255, 0.7);
  border-top-right-radius: 0;
  border-top-left-radius: 0;
}
</style>
