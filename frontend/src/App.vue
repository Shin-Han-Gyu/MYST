<template>
  <div>
    <NavBar class="nav"/>
    <router-view></router-view>
  </div>
</template>

<script>
import NavBar from '@/views/NavBar.vue'
export default {
  name: 'App',
  components: {
    'NavBar': NavBar,
  },
}
</script>

<style>
</style>