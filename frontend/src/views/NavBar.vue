<template>
  <nav class="navbar navbar-expand-sm navbar-light sticky-top">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Navbar</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav me-auto mb-2 mb-sm-0">
          <li class="nav-item" v-if="this.$store.state.login.username">
            <a class="nav-link" @click="myteam()">My Team</a>
          </li>
          <li v-else></li>
        </ul>
        <ul class="navbar-nav d-flex" v-if="this.$store.state.login.username">
          <li class="nav-item">
            <a class="nav-link" href="#">Logout</a>
          </li>
          <li class="nav-item nav-right">
            <a class="nav-link">My Page</a>
          </li>
        </ul>
        <ul class="navbar-nav d-flex" v-else>
          <li class="nav-item">
            <a class="nav-link" @click="login()">Login</a>
          </li>
          <li class="nav-item nav-right">
            <a class="nav-link" @click="signup()">Signup</a>
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
  methods: {
    login() {
      this.$router.push({ name: "Login" })
    },
    signup() {
      this.$router.push({ name: "Signup" })
    },
    myteam() {
      this.$router.push({ name: "MyTeam" })
    }
  }
}
</script>

<style>
.navbar {
  background-color: transparent;
  top: 0;
  width: 100%;
}
</style>