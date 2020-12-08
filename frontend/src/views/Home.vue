<template>
  <div class="home">
    <h1 id="home-title">Home</h1>
    <b-button id="show-cards-button" v-on:click="$router.push({name: 'cards', params: {cardId: 1}})">See My Cards</b-button>
    
  </div>
</template>

<script>

export default {
  data () {
      return {
       
      }
    },
    created() {
      

    },
    computed: {
      
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

#show-cards-button {
  width: 50%; 
  margin: 1em 0 1em 0;
  background-color: #569FAD;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
  justify-self: center;
}



</style>
