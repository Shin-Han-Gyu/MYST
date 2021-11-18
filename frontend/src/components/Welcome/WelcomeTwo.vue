<template>
<div class="welcome2 d-flex flex-column justify-content-center">
    <h1 class="p3"> {{title2}} </h1>
    <h2 class="p4"> {{subject2}}</h2>
  </div>
</template>

<script>
export default {
  // name: "WelcomeOne",
  props:['title2','subject2']
}
</script>

<style>
  .welcome2{
    height: 1000px;
    width: 100%;
    padding-bottom: 200px;
    background-color:teal;
    text-align: center;
  }
  .p3{
    font-size: 60px;
    font-weight: bold;
    color:white;
  }

  .p4{
    font-weight: bold;
    color: darkgray;
  }
</style>