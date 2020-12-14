<template>
  <div>
    <h1>{{findCurrentDeckTitle}}</h1>
    <b-button
      v-on:click="
        $router.push({
          name: 'modifyDeck',
          params: { deckId: $route.params.deckId },
        })
      "
      >Edit Deck</b-button
    >
    <b-button v-on:click="deleteThisDeck">Delete Deck</b-button>
    <deck-list></deck-list>
    <card-list
      v-bind:currentDeckId="$route.params.deckId"
      v-bind:key="$route.params.deckId"
    ></card-list>
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
</style>