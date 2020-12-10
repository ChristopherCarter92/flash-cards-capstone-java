<template>
  <div>
    <form v-on:submit.prevent="createDeck">
      <label for="deckTitle">Title: </label>
      <input type="text" v-model="newDeck.title" id="deckTitle" />
      <b-button type="submit">Add Deck</b-button>
    </form>
    <p v-if="errorMsg !== ''">{{ errorMsg }}</p>
  </div>
</template>

<script>
import DeckServices from "@/services/DeckServices.js";

export default {
  data() {
    return {
      newDeck: {
        title: "",
        cards: [],
      },
    };
  },
  methods: {
    getDecks() {
      DeckServices.getAllDecks().then(() => {
        this.$router.push({ name: "home" });
      });
    },
  },

  createDeck() {
    DeckServices.addDeck(this.newDeck)
      .then((response) => {
        if (response.status === 201) {
          this.$store.commit("ADD_DECK", response.data);
          let parameter = this.$store.state.decks.length;
          this.$router.push(`/decks/${parameter}`);
        }
      })
      .catch((error) => {
        this.handleErrorResponse(error, "adding");
      });
  },

  handleErrorResponse(error, verb) {
    if (error.response) {
      console.log(error.response);
      this.errorMsg =
        "Error " +
        verb +
        ' deck. Response received was "' +
        error.response.data.errors[0].defaultMessage +
        '".';
    } else if (error.request) {
      this.errorMsg = "Error " + verb + " deck. Server could not be reached.";
    } else {
      this.errorMsg = "Error " + verb + " deck. Request could not be created.";
    }
  },
};
</script>

<style>
</style>