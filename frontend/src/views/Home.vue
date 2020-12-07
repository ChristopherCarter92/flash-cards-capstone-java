<template>
  <div class="home">
    <h1 id="home-title">Home</h1>
    <p id="placeholder">Placeholder for Cards List</p>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  
};
</script>
<style scoped>
* {
  box-sizing: border-box;
}

.home {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas: 
  "Title"
  "Card-List";
}

#home-title {
  grid-area: Title;
  justify-self: center;
}

#placeholder {
  grid-area: Card-List;
  justify-self: center;
}

</style>
