<template>
  <div class="welcome1 d-flex flex-column justify-content-center">
    <h1 class="p1"> {{title}} </h1>
    <h2 class="p2"> {{subject}}</h2>
  </div>
</template>

<script>
export default {
  // name: "WelcomeOne",
  props:['title','subject']
}
</script>

<style>
  .welcome1{
    height: 1000px;
    width: 100%;
    padding-bottom: 200px;
    background-color:floralwhite;
    text-align: center;
  }
  .p1{
    font-size: 60px;
    font-weight: bold;
    color:teal;
  }

  .p2{
    font-weight: bold;
    color: darkgray;
  }
</style>