<template>
  <footer style="display: flex">
    <div class="footerCaption"></div>
    <div class="footerImage">
      <img src="/img/footerlogo.png" alt="img"/>
    </div>
  </footer>
</template>

<script>
export default {
  name: "v-footer"
}
</script>

<style>
footer {
/*  flex-shrink: 0;
  display: flex;
  height: 100px;
  justify-content: space-between;*/
  background-color: #333;
}
.content {
  flex: 1;
}
</style>