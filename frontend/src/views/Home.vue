<template>

  <div class="home">
    <h1 id="home-title">Home</h1>
    <div>
      <h1 id="allDecks">My Decks</h1>
      <b-button id="add-deck-button" v-on:click="$router.push({name: 'createDeck'})">Add Deck</b-button>
          <deck-list></deck-list>
    </div>
  </div>
</template>

<script>

import DeckList from '@/components/DeckList.vue';

export default {
  components: {
    DeckList
  },
  data() {
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

/* .home {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas: 
  "Title"
  
} */

.home {
  display: flex;
  flex-direction: column;
}

.allCards {
  display: flex;
  flex-direction: column;
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
