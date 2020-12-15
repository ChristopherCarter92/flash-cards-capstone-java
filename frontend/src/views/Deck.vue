<template>

  <div id="deck-div">

    <div>
    <h1 id="deck-title" >{{findCurrentDeckTitle}}</h1>
    <b-button id="edit-deck-button"
      v-on:click="
        $router.push({
          name: 'modifyDeck',
          params: { deckId: $route.params.deckId },
        })
      "
      >Edit Deck</b-button
    >
    <b-button id="delete-deck-button" v-on:click="deleteThisDeck">Delete Deck</b-button>
    <deck-list ></deck-list>
    <b-button id="study-session-button" v-on:click.prevent="
        $router.push({
          name: 'study-session',
          params: {deckId: $route.params.deckId},
        })
      ">Start A Study Session</b-button>
      

      <div id="card-list">
    <card-list
      v-bind:currentDeckId="$route.params.deckId"
      v-bind:key="$route.params.deckId"
    ></card-list>
    </div>
    </div>

  </div>
</template>

<script>
import CardList from "@/components/CardList.vue";
import DeckList from "@/components/DeckList.vue";
import DeckServices from "@/services/DeckServices.js";
import CardServices from '@/services/CardServices.js';

export default {
  components: {
    CardList,
    DeckList,
  },

  data() {
    return {
      
    };
  },

  computed: {
    findCurrentDeckTitle() {
      for (let item of this.decksArrayFromStore) {
        if(item.deckId === this.$route.params.deckId ) {
          return item.title;
        }  
    }
    return null;
    },

    decksArrayFromStore() {
      return this.$store.state.decks;
    }

  },

  methods: {
    deleteThisDeck() {
      let deckId = this.$route.params.deckId;
      if (
        confirm(
          "Are you sure you want to delete this deck? You will not be able to undo this action."
        )
      ) {
        DeckServices.deleteThisDeck(deckId)
          .then((response) => {
            if (response.status === 204) {
              DeckServices.getAllDecks().then(() => {
                CardServices.getAllCards().then(() =>
                  this.$router.push({ name: "home" })
                );
              });
            }
          })
          .catch(() => {
            console.log("Error deleting.");
          });
      }
    },
  },
};
</script>

<style>
#deck-title {
  color: #324B50;
  flex-direction: column;
  justify-content: center;

}

#edit-deck-button {
  width: 50%;
  margin: 1em 0 1em 0;
  background-color: #569FAD;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
  

}

#delete-deck-button {
  width: 50%;
  margin: 1em 0 1em 0;
  background-color: #569FAD;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
  
}

#study-session-button {
   width: 50%;
  margin: 1em 0 1em 0;
  background-color: #569FAD;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
  
}

#deck-list {
   width: 50%; 
  margin: 1em 0 1em 0;
  /* background-color: #569FAD; */
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
  justify-content: center;

}

#deck-div {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
#card-list {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}


@media screen and (max-width: 680px) {
#deck-title {
  color: #324B50;
  flex-direction: column;
  justify-content: center;

}

#edit-deck-button {
  width: 70%;
  margin: 1em 0 1em 0;
  background-color: #569FAD;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
  

}

#delete-deck-button {
  width: 70%;
  margin: 1em 0 1em 0;
  background-color: #569FAD;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
  
}

#study-session-button {
   width: 70%;
  margin: 1em 0 1em 0;
  background-color: #569FAD;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
  
}

#deck-list {
   width: 70%; 
  margin: 1em 0 1em 0;
  /* background-color: #569FAD; */
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;

}

#deck-div {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
#card-list {
  display: flex;
  flex-direction: row;
  justify-content: center;
}
}

</style>