<template>

  <div class="home">
    
    <h1 id="allDecks">My Decks</h1>
    <b-button class="add-deck-button" v-on:click="$router.push({name: 'createDeck'})">Add Deck</b-button>
    <div id="deck-list-container" >
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

.home {
  display: flex;
  flex-direction: column;
}

.allCards {
  display: flex;
  flex-direction: column;
}



button {
  margin-bottom: 20px;
  margin-left: auto;
  margin-right: auto;
   width: 30%;
  grid-area: "addDeckButton";
  background-color: #B68C71;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
  font-weight: bolder;

}

#deck-list-container {
  width: 30%;
  margin-left: auto;
  margin-right: auto;
}

@media screen and (max-width: 576px) {

.home {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas: 
  "title",
  "myDecks",
  "addDeckButton",
  "deck";
}

#home-title { 
  display: flex;
  justify-content: center;
  grid-area: "title";
}

#allDecks {
  
  justify-content: center;
  grid-area: "myDecks";
}

button {
  
   width: 70%;
 
}

deck-list {
  
  justify-content: right;
  grid-area: "deck";
}


  
}


</style>
