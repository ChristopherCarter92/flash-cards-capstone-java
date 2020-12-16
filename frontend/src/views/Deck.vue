<template>


    <b-container>
      <b-row>
    <b-col class="left-buttons" sm="2" >
      
    <deck-list ></deck-list>
     
    <img class="goat-image" src="@/assets/goat.png" alt="zenGoat"/>
    </b-col>
   

    <b-col class="cards-and-deck-titles">

       <h1 id="deck-title" >{{findCurrentDeckTitle}}</h1>
     

     <b-button id="study-session-button" v-on:click.prevent="
        $router.push({
          name: 'study-session',
          params: {deckId: $route.params.deckId},
        })
      ">Start A Study Session</b-button>
      <b-button id="edit-deck-button"
      v-on:click="
        $router.push({
          name: 'modifyDeck',
          params: { deckId: $route.params.deckId },
        })
      "
      >Edit Deck</b-button>
    <b-button id="delete-deck-button" v-on:click="deleteThisDeck">Delete Deck</b-button>

      <b-col class="card-list">
    <card-list
      v-bind:currentDeckId="$route.params.deckId"
      v-bind:key="$route.params.deckId"
    ></card-list>
      </b-col>
    </b-col>
     </b-row>
    
    </b-container>

  
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
    }, 

    

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

<style scoped>

.goat-image {
    max-width: 100%;

  height: auto;
}

#deck-title {
  color: #324B50;
  display: flex;
  flex-direction: row;
  justify-content: center;
  

}


.deck-list {
  
  margin: 1em 0 1em 0;
  /* background-color: #569FAD; */
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
  justify-content: center;
  

}

b-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  
}

/* b-col.buttons {
  col: 0;
} */

.card-list {
  display: flex;
  flex-direction: row;
  justify-content: center;
  background-color: #95b0b6;
  border-radius: 3px;
  padding-left: 3px;
  padding-right: 3px;
  
}


button {
  
  margin-bottom: 20px;
  width: 80%;
  background-color: #569FAD;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
}

.cards-and-deck-titles {
  margin-top: 20px;
  color: #324B50;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #95b0b6;
  border-radius: 3px;

}


@media screen and (max-width: 576px) {
.goat-image {
    max-width: 100%;
  height: auto;
}

  b-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
}


button {
  width: 70%;
}

#deck-list {
   width: 70%; 
  margin: 1em 0 1em 0;
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

/* .cards-and-deck-titles {} */
}

</style>