<template>
  <nav class="navbar navbar-expand-sm navbar-light sticky-top">
    <div class="container-fluid">
      <img src="@/assets/logo.png" style="width:40px; height:40px;" @click="home()" alt="">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav me-auto mb-2 mb-sm-0">
          <li class="nav-item" v-if="this.$store.state.login.userinfo.username">
            <a class="nav-link" style="cursor: pointer; color: white;" @click="myteam()">My Team</a>
          </li>
          <li v-else></li>
        </ul>
        <ul class="navbar-nav d-flex" v-if="this.$store.state.login.userinfo.username">
          <li class="nav-item">
            <a class="nav-link" style="cursor: pointer; color: white;" @click="logout()">Logout</a>
          </li>
          <li class="nav-item nav-right">
            <a class="nav-link" style="cursor: pointer; color: white;">My Page</a>
          </li>
        </ul>
        <ul class="navbar-nav d-flex" v-else>
          <li class="nav-item">
            <a class="nav-link" style="cursor: pointer; color: white;" @click="login()">Login</a>
          </li>
          <li class="nav-item nav-right">
            <a class="nav-link" style="cursor: pointer; color: white;" @click="signup()">Signup</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <div class="nav-bar">

  </div>
</template>

<script>
export default {
  name: "NavBar",
  methods: {
    home() {
      this.$router.push({ name: "MainPage"})
    },
    login() {
      this.$router.push({ name: "Login" })
    },
    signup() {
      this.$router.push({ name: "Signup" })
    },
    myteam() {
      this.$router.push({ name: "MyTeam" })
    },
    logout() {
      this.$store.dispatch("login/deleteJWT")
      this.$router.push({ name: "EntrancePage" })
    }
  }
}
</script>

<style scoped>
.navbar {
  background-color: rgb(31,78,121);
  top: 0;
  width: 100%;
  font-family: "nanumsquare";
}


</style>