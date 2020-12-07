<template>
  <div class="home">
    <h1 id="home-title">Home</h1>
    <b-button>See Cards</b-button>
    
  </div>
</template>

<script>

export default {

  components: {
    
  }
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
  
}

#home-title {
  grid-area: Title;
  justify-self: center;
}



</style>
