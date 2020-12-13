<template>
  <div>
    <h1>Decks</h1>
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

export default {
  components: {
    CardList,
    DeckList,
  },

  data() {
    return {};
  },

  methods: {
    deleteThisDeck() {
      let deckId = this.$route.params.deckId;
      if (confirm("Are you sure you want to delete this deck? You will not be able to undo this action.")) {
        DeckServices.deleteThisDeck(deckId)
          .then((response) => {
            if (response.status === 410) {
              this.$store.commit("DELETE_DECK", deckId);
              DeckServices.getAllDecks().then((response) => {
                this.$store.commit("SET_DECKS", response.data);
                if (this.$store.state.decks.length < 1) {
                  this.$router.push({ name: "home" });
                }
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